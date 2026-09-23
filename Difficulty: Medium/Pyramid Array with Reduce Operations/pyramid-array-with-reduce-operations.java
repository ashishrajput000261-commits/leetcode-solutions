class Solution {
    public long formPyramid(int[] arr) {
        int n = arr.length;

        long total = 0;

        for (int x : arr) {
            total += x;
        }

        long[] left = new long[n];
        long[] right = new long[n];

        // Maximum possible height from left
        left[0] = Math.min(arr[0], 1);

        for (int i = 1; i < n; i++) {
            left[i] = Math.min(arr[i], left[i - 1] + 1);
        }

        // Maximum possible height from right
        right[n - 1] = Math.min(arr[n - 1], 1);

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(arr[i], right[i + 1] + 1);
        }

        long maxPyramidSum = 0;

        for (int i = 0; i < n; i++) {
            long peak = Math.min(left[i], right[i]);

            // Sum of pyramid with peak height x is x²
            long pyramidSum = peak * peak;

            maxPyramidSum = Math.max(maxPyramidSum, pyramidSum);
        }

        return total - maxPyramidSum;
    }
}