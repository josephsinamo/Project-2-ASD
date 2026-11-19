package dsa;

public class sLinkedList  {

    public class Node{
        Object data;
        Node next;

        public Node(Object data){
            this.data = data;
        }
    }

    public Node head,tail;

    public void add(Object value){
        Node node = new Node(value);

        if(head == null){
            head = tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
    }

    public void print(){
        if (head == null){
            System.out.println("KOSONG");
            return;
        }
        Node curr =  head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void remove(Object value){
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr.data.equals(value)){
                if(curr == head){
                    head = curr.next;
                }else if(curr == tail){
                    tail = prev;
                }else{
                    prev.next = curr.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void remove(Node value){
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr == value){
                if(curr == head){
                    head = curr.next;
                }else if(curr == tail){
                    tail = prev;
                }else{
                    prev.next = curr.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }
    }
}
