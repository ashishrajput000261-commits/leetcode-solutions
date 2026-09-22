class Solution {

    public String findLongestWord(String s, List<String> d) {

        int n = s.length();

        int[][] next = new int[n + 1][26];

        // Last row
        for (int c = 0; c < 26; c++) {
            next[n][c] = -1;
        }

        // Build next occurrence table
        for (int i = n - 1; i >= 0; i--) {

            for (int c = 0; c < 26; c++) {
                next[i][c] = next[i + 1][c];
            }

            next[i][s.charAt(i) - 'a'] = i;
        }

        String ans = "";

        for (String word : d) {

            if (isSubsequence(word, next, n)) {

                if (word.length() > ans.length()) {
                    ans = word;
                }
                else if (word.length() == ans.length()
                        && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubsequence(String word, int[][] next, int n) {

        int pos = 0;

        for (int i = 0; i < word.length(); i++) {

            int index = next[pos][word.charAt(i) - 'a'];

            if (index == -1) {
                return false;
            }

            pos = index + 1;
        }

        return true;
    }
}