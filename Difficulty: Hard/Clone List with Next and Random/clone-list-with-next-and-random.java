/* Structure of Linked List Node
class Node {
    int data;
    Node next;
    Node random;

    Node(int x) {
        data = x;
        next = null;
        random = null;
    }
}*/

class Solution {
    public Node cloneLinkedList(Node head) {
        // code here
        
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();

        // Step 1: Har original node ka clone banao
        Node current = head;

        while (current != null) {
            map.put(current, new Node(current.data));
            current = current.next;
        }

        // Step 2: next aur random pointers connect karo
        current = head;

        while (current != null) {

            Node clone = map.get(current);

            clone.next = map.get(current.next);
            clone.random = map.get(current.random);

            current = current.next;
        }

        // Clone list ka head
        return map.get(head);
    }
}