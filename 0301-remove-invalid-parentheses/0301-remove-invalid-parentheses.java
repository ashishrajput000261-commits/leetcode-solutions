import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        // Start DFS from left-to-right, treating '(' as open and ')' as close
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private void remove(String s, List<String> result, int last_i, int last_j, char[] par) {
        int stack = 0;
        
        // Scan the string from our last checked position
        for (int i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            
            // As long as count is >= 0, we don't have an extra closing parenthesis
            if (stack >= 0) continue;
            
            // We found an extra closing parenthesis (par[1]).
            // We must remove one par[1] from the prefix s[last_j ... i].
            for (int j = last_j; j <= i; j++) {
                // Only remove the FIRST one in any consecutive sequence to avoid duplicate results.
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    // Create the new string without s[j] and recurse
                    String nextString = s.substring(0, j) + s.substring(j + 1);
                    // Pass 'i' and 'j' as the new starting points to avoid rescanning
                    remove(nextString, result, i, j, par);
                }
            }
            
            // Stop further processing for this branch; we already triggered removals
            return;
        }
        
        // If we make it through the loop, there are no extra closing parentheses.
        // However, there might be extra opening parentheses! 
        // We reverse the string and run the exact same logic, swapping the roles of '(' and ')'.
        String reversed = new StringBuilder(s).reverse().toString();
        
        if (par[0] == '(') {
            // First pass finished (left-to-right). Now check right-to-left.
            remove(reversed, result, 0, 0, new char[]{')', '('});
        } else {
            // Both passes finished. The string is completely valid.
            // Reverse it one last time to restore its original order.
            result.add(reversed);
        }
    }
}