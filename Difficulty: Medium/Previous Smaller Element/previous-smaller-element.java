class Solution {
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();

              // Initially sabka answer -1
              for (int i = 0; i < arr.length; i++) {
                  ans.add(-1);
              }

              Stack<Integer> st = new Stack<>();

              for (int i = 0; i < arr.length; i++) {

                  // Greater ya equal elements ko hata do
                  while (!st.isEmpty() && st.peek() >= arr[i]) {
                      st.pop();
                  }

                  // Stack ka top = Previous Smaller Element
                  if (!st.isEmpty()) {
                      ans.set(i, st.peek());
                  }

                  // Current element ko stack mein push karo
                  st.push(arr[i]);
              }

              return ans;
    }
}