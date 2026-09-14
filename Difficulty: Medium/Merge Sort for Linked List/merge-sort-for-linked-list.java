/* Structure of a Linked List node
class Node {
    int data;
    Node next;
    Node(int key) {
        data = key;
        next = null;
    }
} */

class Solution {
    public Node mergeSort(Node head) {
        // code here
        // Base case
               if (head == null || head.next == null) {
                   return head;
               }

               // Middle find karo
               Node slow = head;
               Node fast = head.next;

               while (fast != null && fast.next != null) {
                   slow = slow.next;
                   fast = fast.next.next;
               }

               // Second half ka head
               Node second = slow.next;

               // Dono lists ko split karo
               slow.next = null;

               // Dono halves ko recursively sort karo
               Node left = mergeSort(head);
               Node right = mergeSort(second);

               // Dono sorted lists ko merge karo
               return merge(left, right);
           }

           Node merge(Node first, Node second) {

               if (first == null) {
                   return second;
               }

               if (second == null) {
                   return first;
               }

               Node result;

               if (first.data <= second.data) {
                   result = first;
                   result.next = merge(first.next, second);
               } else {
                   result = second;
                   result.next = merge(first, second.next);
               }

               return result;
    }
}