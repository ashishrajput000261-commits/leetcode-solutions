/* Structure of linked list Node
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
};*/
class Solution {

    public static Node quickSort(Node head) {
        Node tail = getTail(head);
        quickSortUtil(head, tail);
        return head;
    }

    static void quickSortUtil(Node head, Node tail) {

        if (head == null || head == tail || head == tail.next) {
            return;
        }

        Node[] result = partition(head, tail);

        Node pivot = result[0];
        Node pivotPrev = result[1];

        // Left part
        if (pivotPrev != null && pivotPrev != head) {
            quickSortUtil(head, pivotPrev);
        }

        // Right part
        if (pivot.next != null) {
            quickSortUtil(pivot.next, tail);
        }
    }

    static Node getTail(Node head) {
        while (head != null && head.next != null) {
            head = head.next;
        }
        return head;
    }

    static Node[] partition(Node head, Node tail) {

        int pivot = tail.data;

        Node i = head;
        Node j = head;
        Node prev = null;
        Node pivotPrev = null;

        while (j != tail) {

            if (j.data < pivot) {

                int temp = i.data;
                i.data = j.data;
                j.data = temp;

                prev = i;
                i = i.next;
            }

            j = j.next;
        }

        // Pivot ko correct position par lao
        int temp = i.data;
        i.data = tail.data;
        tail.data = temp;

        pivotPrev = prev;

        Node pivotNode = i;

        return new Node[]{pivotNode, pivotPrev};
    }
}