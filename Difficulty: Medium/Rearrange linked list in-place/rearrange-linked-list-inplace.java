/* Structure of linked list Node
class Node {
int data;
Node next;
    Node(int key)
    {
        data = key;
        next = null;
    }
}*/
class Solution {
    public Node inPlace(Node root) {
        // Code Here//Position this line where user code will be pasted.
       if (root == null || root.next == null) {
                 return root;
             }

             // Step 1: Middle find karo
             Node slow = root;
             Node fast = root;

             while (fast.next != null && fast.next.next != null) {
                 slow = slow.next;
                 fast = fast.next.next;
             }

             // Step 2: Second half alag karo
             Node second = slow.next;
             slow.next = null;

             // Step 3: Second half reverse karo
             Node prev = null;
             Node current = second;

             while (current != null) {
                 Node next = current.next;

                 current.next = prev;
                 prev = current;
                 current = next;
             }

             second = prev;

             // Step 4: Alternate merge
             Node first = root;

             while (second != null) {

                 Node firstNext = first.next;
                 Node secondNext = second.next;

                 first.next = second;
                 second.next = firstNext;

                 first = firstNext;
                 second = secondNext;
             }

             return root;
    }
}