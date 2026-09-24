class Solution {
    static ArrayList<Integer> nextSmallerEle(int[] arr) {
        // code here
     int n = arr.length;

           ArrayList<Integer> ans = new ArrayList<>();

           for (int i = 0; i < n; i++) {
               ans.add(-1);
           }

           Stack<Integer> st = new Stack<>();

           for (int i = 0; i < n; i++) {

               while (!st.isEmpty() && arr[st.peek()] > arr[i]) {

                   int index = st.pop();

                   ans.set(index, arr[i]);
               }

               st.push(i);
           }

           return ans;
    }
}