class Solution {
    public String digitSum(String s, int k) {
        // Continue performing rounds while the string length is greater than k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Iterate through the string in steps of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                // Sum the digits in the current group, making sure not to exceed s.length()
                for (int j = i; j < Math.min(i + k, s.length()); j++) {
                    sum += s.charAt(j) - '0';
                }
                nextRound.append(sum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}