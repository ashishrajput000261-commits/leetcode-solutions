class Solution {
    public ArrayList<Integer> preGreaterEle(int[] arr) {
        // code here
        
        ArrayList<Integer> ans = new ArrayList<>();

        // Initially sabka answer -1
        for (int i = 0; i < arr.length; i++) {
            ans.add(-1);
        }

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            // Chhote ya equal elements useful nahi hain
            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            // Jo top bacha, wahi Previous Greater hai
            if (!st.isEmpty()) {
                ans.set(i, st.peek());
            }

            // Current element ko stack mein daalo
            st.push(arr[i]);
        }

        return ans;
    
    }
}