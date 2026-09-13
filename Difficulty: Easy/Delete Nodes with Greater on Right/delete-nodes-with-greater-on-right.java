/* Structure of linked list node
class Node {

    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}
*/
class Solution {
    Node compute(Node head) {
        // code here
        if (head == null || head.next == null) {
                    return head;
                }

                // Step 1: Reverse the linked list
                Node prev = null;
                Node curr = head;

                while (curr != null) {
                    Node next = curr.next;

                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                // prev is now the new head
                head = prev;

                // Step 2: Delete nodes smaller than maximum seen so far
                curr = head;
                int max = curr.data;

                while (curr != null && curr.next != null) {

                    if (curr.next.data < max) {
                        // Delete curr.next
                        curr.next = curr.next.next;
                    } else {
                        // Keep curr.next
                        curr = curr.next;
                        max = curr.data;
                    }
                }

                // Step 3: Reverse back
                prev = null;
                curr = head;

                while (curr != null) {
                    Node next = curr.next;

                    curr.next = prev;
                    prev = curr;
                    curr = next;
                }

                return prev;
    }
}