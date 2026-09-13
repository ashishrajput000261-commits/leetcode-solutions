/* Structure of Doubly Linked List Node
class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
*/
class Solution {
    public Node reverse(Node head) {
        // code here
        if (head == null || head.next == null) {
                  return head;
              }

              Node current = head;
              Node prev = null;

              while (current != null) {

                  Node next = current.next;

                  current.next = current.prev;
                  current.prev = next;

                  prev = current;
                  current = next;
              }

              return prev;
    }
}