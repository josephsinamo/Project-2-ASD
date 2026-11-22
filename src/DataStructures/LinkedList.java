package DataStructures;

public class LinkedList<T> {
  private Node<T> head, tail;
  private int size;

  public LinkedList () {
    this.head = this.tail = null;
    this.size = 0;
  }

  public Node<T> getHead () {
    return head;
  }

  public boolean isEmpty () {
    return size == 0;
  }

  public int getSize () {
    return size;
  }

  public void addLast (T data) {
    Node<T> node = new Node<T>(data);

    if (isEmpty()) {
      head = tail = node;
    } else {
      tail.setNext(node);
      tail = node;
    }

    size++;
  }
}

