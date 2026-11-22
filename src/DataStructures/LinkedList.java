package DataStructures;

public class LinkedList<T> {
  public Node<T> head, tail;
  private int size;

  public LinkedList () {
    this.head = this.tail = null;
    this.size = 0;
  }

  public boolean isEmpty () {
    return size == 0;
  }

  public int getSize () {
    return size;
  }

  public void addFirst (T data) {
    Node<T> node = new Node<T>(data);
    
    if (isEmpty()) {
      head = tail = node;
    } else {
      node.next = head;
      head = node;
    }

    size++;
  }

  public void addLast (T data) {
    Node<T> node = new Node<T>(data);

    if (isEmpty()) {
      head = tail = node;
    } else {
      tail.next = node;
      tail = node;
    }

    size++;
  }

  public void display () {
    Node<T> curr = head;

    for (int i = 0; i < size; i++) {
      // if (i != 0) {
      //   System.out.print(" ");
      // }
      System.out.println(curr.data);
      curr = curr.next;
    }
    System.out.println();
  }

  public static void main (String args[]) {
    LinkedList<Integer> list = new LinkedList<Integer>();

    list.addLast(1);
    list.addLast(2);
    list.addLast(3);

    list.display();
  }

  @Override 
  public String toString(){
    Node curr = head;
    String s = "";

    while(curr != null){
      s += String.format("%s%s", curr.data,(curr != tail) ? "," : "");

      curr = curr.next;
    }

    return s;
  }
}

