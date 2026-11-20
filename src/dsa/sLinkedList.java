package dsa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class sLinkedList <T> implements Iterable<T>{

    public Node head,tail;
    private int size = 0;

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

        size++;
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

    @Override 
    public String toString(){
        if (head == null){
            return null;
        }

        Node curr =  head;
        String s = "";
        while (curr != null) {
            s += curr.data;
            if (curr != tail){
                s += ", ";
            } 
            curr = curr.next;
        }

        return s;
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

    public int getSize(){
        return size;
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
                    tail.next = null;
                }else{
                    prev.next = curr.next;
                }
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void removeTail(){
        this.remove(tail);
    }

    @Override 
    public Iterator<T> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
        private Node node = head;
        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }    

            T data = node.data;
            node = node.next;
            return data;
        }
        
    }

}
