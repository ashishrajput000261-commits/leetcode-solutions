class Solution {
   static int floorDiv(int a, int b) {
         if (a * b < 0 && a % b != 0) {
             return (a / b) - 1;
         }
         return a / b;
     }

     public static int evaluatePostfix(String[] arr) {

         Stack<Integer> st = new Stack<>();

         for (String token : arr) {

             // Number
             if (Character.isDigit(token.charAt(0)) ||
                 (token.length() > 1 && token.charAt(0) == '-')) {

                 st.push(Integer.parseInt(token));
             }

             // Operator
             else {
                 int a = st.pop();
                 int b = st.pop();

                 if (token.equals("+")) {
                     st.push(b + a);
                 }
                 else if (token.equals("-")) {
                     st.push(b - a);
                 }
                 else if (token.equals("*")) {
                     st.push(b * a);
                 }
                 else if (token.equals("/")) {
                     st.push(floorDiv(b, a));
                 }
                 else if (token.equals("^")) {
                     st.push((int) Math.pow(b, a));
                 }
             }
         }

         return st.pop();
    }
}