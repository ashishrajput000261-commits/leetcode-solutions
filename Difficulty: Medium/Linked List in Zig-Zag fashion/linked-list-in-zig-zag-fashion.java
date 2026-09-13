/* Structure of link list Node
class Node {
  public int data;
  public Node next;

  public Node(int x) {
      data = x;
      next = null;
  }
}*/
class Solution {
    public Node zigZag(Node head) {
        // code here
        if (head == null || head.next == null) {
                   return head;
               }

               Node current = head;
               boolean less = true;

               while (current != null && current.next != null) {

                   if (less) {
                       // current < current.next hona chahiye
                       if (current.data > current.next.data) {
                           int temp = current.data;
                           current.data = current.next.data;
                           current.next.data = temp;
                       }
                   } else {
                       // current > current.next hona chahiye
                       if (current.data < current.next.data) {
                           int temp = current.data;
                           current.data = current.next.data;
                           current.next.data = temp;
                       }
                   }

                   less = !less;
                   current = current.next;
               }

               return head;
    }
}