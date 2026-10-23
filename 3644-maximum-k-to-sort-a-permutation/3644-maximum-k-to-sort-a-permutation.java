class Solution {
    public int sortPermutation(int[] nums) {
        // Initialize with all bits set (-1 in two's complement has all 1s)
        int maxK = -1;
        
        for (int i = 0; i < nums.length; i++) {
            // Check if the element is not in its sorted position
            if (nums[i] != i) {
                maxK &= nums[i];
            }
        }
        
        // If maxK is still -1, no elements were out of place (already sorted)
        return maxK == -1 ? 0 : maxK;
    }
}