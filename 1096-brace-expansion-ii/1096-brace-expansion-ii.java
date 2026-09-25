class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> set = parse(expression);

        List<String> ans = new ArrayList<>(set);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();

        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            // UNION
            if (ch == ',') {

                result.addAll(current);

                current = new HashSet<>();
                current.add("");

                index++;

            } 
            else {

                Set<String> next = new HashSet<>();

                // BRACE
                if (ch == '{') {

                    index++; // skip '{'

                    next = parse(s);

                    index++; // skip '}'

                } 
                // SINGLE CHARACTER
                else {

                    next.add(String.valueOf(ch));

                    index++;
                }

                // CONCATENATION
                Set<String> temp = new HashSet<>();

                for (String a : current) {
                    for (String b : next) {
                        temp.add(a + b);
                    }
                }

                current = temp;
            }
        }

        // Add last/current expression
        result.addAll(current);

        return result;
    }
}