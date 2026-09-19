class myStack {
    // Define your stack
    Stack<Integer> stack = new Stack<>();
    
    

    public void push(int x) {
        // insert x into stack
        stack.push(x);
    }

    public void pop() {
        // remove top ele from stack
        stack.pop();
    }

    public int peek() {
        // return top of stack
         return stack.peek();
    }

    public int getSize() {
        // return current size of stack
           return stack.size();
    }

    public boolean isEmpty() {
        // check whether stack is empty
         return stack.isEmpty();
        
    }
}
