class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 1; // Any single element [x] has prod = x, gcd = x, lcm = x (if x == 1) or length >= 2 works
        int maxPossible = 25200; // Max possible lcm(1..10) * max(1..10) = 2520 * 10

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int g = 0;
            int l = 1;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                prod *= val;

                // Early exit prevents overflow since prod only increases
                if (prod > maxPossible) {
                    break;
                }

                g = gcd(g, val);
                l = lcm(l, val);

                if (prod == (long) l * g) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}