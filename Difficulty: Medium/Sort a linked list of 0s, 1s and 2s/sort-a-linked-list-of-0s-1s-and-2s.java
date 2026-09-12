class Solution {
    public Node segregate(Node head) {
        Node zeroDummy = new Node(-1);
        Node oneDummy = new Node(-1);
        Node twoDummy = new Node(-1);

        Node zeroTail = zeroDummy;
        Node oneTail = oneDummy;
        Node twoTail = twoDummy;

        Node current = head;

        while (current != null) {
            if (current.data == 0) {
                zeroTail.next = current;
                zeroTail = current;
            } else if (current.data == 1) {
                oneTail.next = current;
                oneTail = current;
            } else {
                twoTail.next = current;
                twoTail = current;
            }
            current = current.next;
        }

        // stitch: zero -> (one if present else two)
        zeroTail.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        // stitch: one -> two (safe even if one is empty, since it just re-sets the dummy's next)
        oneTail.next = twoDummy.next;
        // terminate
        twoTail.next = null;

        if (zeroDummy.next != null) return zeroDummy.next;
        if (oneDummy.next != null) return oneDummy.next;
        return twoDummy.next;
    }
}