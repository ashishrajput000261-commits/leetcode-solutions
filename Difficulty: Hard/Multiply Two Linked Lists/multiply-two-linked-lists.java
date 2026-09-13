/* Node Structure
class Node {
    int data;
    Node next;
    Node(int key) {
        data = key;
        next = null;
    }
} 
*/
class Solution {
    public int multiplyTwoLists(Node first, Node second) {

        long mod = 1000000007;

        long num1 = 0;
        long num2 = 0;

        Node temp = first;

        while (temp != null) {
            num1 = (num1 * 10 + temp.data) % mod;
            temp = temp.next;
        }

        temp = second;

        while (temp != null) {
            num2 = (num2 * 10 + temp.data) % mod;
            temp = temp.next;
        }

        return (int)((num1 * num2) % mod);
    }
}