import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        if (n < 4) {
            return ans;
        }

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Minimum possible sum
            long minSum = (long) nums[i]
                    + nums[i + 1]
                    + nums[i + 2]
                    + nums[i + 3];

            if (minSum > target) {
                break;
            }

            // Maximum possible sum
            long maxSum = (long) nums[i]
                    + nums[n - 1]
                    + nums[n - 2]
                    + nums[n - 3];

            if (maxSum < target) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second element
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // Minimum possible sum for this j
                long min = (long) nums[i]
                        + nums[j]
                        + nums[j + 1]
                        + nums[j + 2];

                if (min > target) {
                    break;
                }

                // Maximum possible sum for this j
                long max = (long) nums[i]
                        + nums[j]
                        + nums[n - 1]
                        + nums[n - 2];

                if (max < target) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i]
                            + nums[j]
                            + nums[left]
                            + nums[right];

                    if (sum == target) {

                        ans.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[left],
                                nums[right]
                        ));

                        left++;
                        right--;

                        // Skip duplicates
                        while (left < right &&
                                nums[left] == nums[left - 1]) {
                            left++;
                        }

                        while (left < right &&
                                nums[right] == nums[right + 1]) {
                            right--;
                        }

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return ans;
    }
}