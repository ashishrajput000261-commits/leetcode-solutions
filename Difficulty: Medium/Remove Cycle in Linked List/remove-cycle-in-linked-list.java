/* Structure of Linked List Node
class Node {
    int data;
    Node next;
    Node(int val) {
        data = val;
        next = null;
    }
} */
class Solution {
    public static void removeLoop(Node head) {
        // code here
        if (head == null || head.next == null) {
                   return;
               }

               Node slow = head;
               Node fast = head;

               // Loop detect
               while (fast != null && fast.next != null) {
                   slow = slow.next;
                   fast = fast.next.next;

                   if (slow == fast) {
                       break;
                   }
               }

               // No loop
               if (slow != fast) {
                   return;
               }

               // Loop ka starting point find karo
               slow = head;

               if (slow == fast) {
                   // Loop head se start ho raha hai
                   while (fast.next != slow) {
                       fast = fast.next;
                   }
               } else {
                   while (slow.next != fast.next) {
                       slow = slow.next;
                       fast = fast.next;
                   }
               }

               // Loop remove
               fast.next = null;
    }
}