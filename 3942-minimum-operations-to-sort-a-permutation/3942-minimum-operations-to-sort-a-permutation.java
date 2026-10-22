class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        boolean isInc = true;
        boolean isDec = true;
        int pos0 = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos0 = i;
            }
            int next = nums[(i + 1) % n];
            
            // Check if circularly increasing
            if (next != (nums[i] + 1) % n) {
                isInc = false;
            }
            // Check if circularly decreasing
            if (next != (nums[i] - 1 + n) % n) {
                isDec = false;
            }
        }

        int minOps = Integer.MAX_VALUE;

        if (isInc) {
            // Option 1: Only Rotate Left
            int op1 = pos0;
            // Option 2: Reverse -> Rotate Left -> Reverse
            int op2 = 2 + (n - pos0) % n;
            minOps = Math.min(minOps, Math.min(op1, op2));
        }

        if (isDec) {
            // Option 1: Rotate Left -> Reverse
            int op1 = 1 + (pos0 + 1) % n;
            // Option 2: Reverse -> Rotate Left
            int op2 = 1 + (n - 1 - pos0);
            minOps = Math.min(minOps, Math.min(op1, op2));
        }

        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}