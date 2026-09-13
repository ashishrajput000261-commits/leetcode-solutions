/* Structure of a Linked List Node
class Node {
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}*/

class Solution {
    Node divide(Node head) {
        // code here
        Node evenDummy = new Node(-1);
               Node oddDummy = new Node(-1);

               Node evenTail = evenDummy;
               Node oddTail = oddDummy;

               Node current = head;

               while (current != null) {

                   if (current.data % 2 == 0) {
                       evenTail.next = current;
                       evenTail = current;
                   } else {
                       oddTail.next = current;
                       oddTail = current;
                   }

                   current = current.next;
               }

               // Odd list ko terminate karo
               oddTail.next = null;

               // Even list ke end par odd list attach karo
               evenTail.next = oddDummy.next;

               // Agar even nodes nahi hain
               if (evenDummy.next == null) {
                   return oddDummy.next;
               }

               return evenDummy.next;
    }
}