/* Structure of linked list Node
class Node
{
    int data;
    Node next;
    Node(int d) {
        data = d;
        next = null;
    }
}*/
class Solution {
    public Node removeDuplicates(Node head) {
      HashSet<Integer> set = new HashSet<>();
      
      Node current = head;
      Node prev = null;
      
      while(current != null){
            
            if(set.contains(current.data)){
               prev.next = current.next;    
            }  
            else{
                set.add(current.data);
                prev = current;
            }
            
            current = current.next;
          
          
      }
      return head;
        
    }
}