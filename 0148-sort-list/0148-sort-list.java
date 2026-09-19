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
    public ListNode sortList(ListNode head) {
          // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Split into two lists
        ListNode second = slow.next;
        slow.next = null;

        // 3. Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(second);

        // 4. Merge sorted lists
        return merge(left, right);
    }

    private ListNode merge(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } 
            else {
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        tail.next = (list1 != null) ? list1 : list2;

        return dummy.next;
        
    }
}