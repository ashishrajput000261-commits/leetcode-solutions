/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
         PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );

        // Sabhi lists ke heads heap mein daalo
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {

            // Sabse chhota node nikalo
            ListNode current = pq.poll();

            // Result mein attach karo
            tail.next = current;
            tail = current;

            // Usi list ka next node heap mein daalo
            if (current.next != null) {
                pq.add(current.next);
            }
        }

        return dummy.next;
        
    }
}