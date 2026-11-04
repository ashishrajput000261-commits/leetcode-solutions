import java.util.*;

class Solution {

    static class DualHeap {
        private final int kLeft;
        private final int kRight;
        private int leftSize = 0;
        private int rightSize = 0;
        private long sumLeft = 0;
        private long sumRight = 0;

        private final PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Long> minHeap = new PriorityQueue<>();
        private final Map<Long, Integer> delayed = new HashMap<>();

        public DualHeap(int x) {
            this.kLeft = (x + 1) / 2;
            this.kRight = x / 2;
        }

        private void prune(PriorityQueue<Long> pq) {
            while (!pq.isEmpty()) {
                long top = pq.peek();
                int count = delayed.getOrDefault(top, 0);
                if (count > 0) {
                    if (count == 1) {
                        delayed.remove(top);
                    } else {
                        delayed.put(top, count - 1);
                    }
                    pq.poll();
                } else {
                    break;
                }
            }
        }

        public void add(long val) {
            if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
                maxHeap.offer(val);
                sumLeft += val;
                leftSize++;
            } else {
                minHeap.offer(val);
                sumRight += val;
                rightSize++;
            }
            balance();
        }

        public void remove(long val) {
            delayed.put(val, delayed.getOrDefault(val, 0) + 1);
            if (!maxHeap.isEmpty() && val <= maxHeap.peek()) {
                sumLeft -= val;
                leftSize--;
                if (val == maxHeap.peek()) {
                    prune(maxHeap);
                }
            } else {
                sumRight -= val;
                rightSize--;
                if (!minHeap.isEmpty() && val == minHeap.peek()) {
                    prune(minHeap);
                }
            }
            balance();
        }

        private void balance() {
            while (leftSize > kLeft) {
                prune(maxHeap);
                long val = maxHeap.poll();
                minHeap.offer(val);
                sumLeft -= val;
                sumRight += val;
                leftSize--;
                rightSize++;
            }
            while (leftSize < kLeft && rightSize > 0) {
                prune(minHeap);
                long val = minHeap.poll();
                maxHeap.offer(val);
                sumRight -= val;
                sumLeft += val;
                rightSize--;
                leftSize++;
            }
            prune(maxHeap);
            prune(minHeap);
        }

        public long getCost() {
            prune(maxHeap);
            long median = maxHeap.peek();
            return sumRight - sumLeft + (long) (kLeft - kRight) * median;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n + 1];
        DualHeap dh = new DualHeap(x);

        for (int i = 0; i < n; i++) {
            dh.add(nums[i]);
            if (i >= x) {
                dh.remove(nums[i - x]);
            }
            if (i >= x - 1) {
                cost[i + 1] = dh.getCost();
            }
        }

        final long INF = (long) 1e18;
        long[] prevDp = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] currDp = new long[n + 1];
            Arrays.fill(currDp, INF);

            for (int i = j * x; i <= n; i++) {
                currDp[i] = currDp[i - 1];
                if (prevDp[i - x] != INF) {
                    currDp[i] = Math.min(currDp[i], prevDp[i - x] + cost[i]);
                }
            }
            prevDp = currDp;
        }

        return prevDp[n];
    }
}