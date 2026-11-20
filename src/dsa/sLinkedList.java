package dsa;

public class sLinkedList <T> {

    public Node head,tail;

    public class Node{
        public T data;
        public Node next;

        public Node(T data){
            this.data = data;
        }

        public T getData(){
            return data;
        }

        @Override 
        public String toString(){
            return String.format("[LS data: %s]", data);
        }
    }

    public T getData(Node node){
        return node.getData();
    }

    public Node getHead(){
        return head;
    }

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

    public void remove(T value){
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
