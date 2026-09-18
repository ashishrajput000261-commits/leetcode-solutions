/* Linked List Node Structure
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
*/

class Solution {
    public boolean detectLoop(Node head) {
        // code here
        
        Node slow = head;
        Node fast = head;

        while (slow != null && fast != null && fast.next != null) {

            slow = slow.next;       // 1 step
            fast = fast.next.next;  // 2 steps

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}