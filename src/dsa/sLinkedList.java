package dsa;

public class sLinkedList <T> {

    public class Node{
        T data;
        Node next;

        public Node(T data){
            this.data = data;
        }
    }

    public Node head,tail;

    public void add(T value){
        Node node = new Node(value);

        if(head == null){
            head = tail = node;
        }else{
            tail.next = node;
            tail = node;
        }
    }

    public void print(){
        Node curr =  head;
        while (curr != null) {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
}
