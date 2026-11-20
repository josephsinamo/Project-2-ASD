package DataStructures;

public class HashMap<T> {
  private class Entry<T> {
    String key;
    T value;
    Entry<T> next;

    public Entry (String key, T value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }

  }
  private Entry<T>[] buckets;
  private int capacity;
  private int size;

  public HashMap () {
    this(79);
  }

  public HashMap (int capacity) {
    this.capacity = capacity;
    this.buckets = (Entry<T>[]) new Entry[capacity];
  }

  private int hash (String key) {
    int h = 0;
    for (int i = 0; i < key.length(); i++) {
      h = (h*31 + key.charAt(i)) % capacity;
    }
    return h;
  }

  public void put (String key, T value) {
    if (key == null) {
      return;
    }

    int idx = hash(key);
    Entry<T> curr = buckets[idx];

    while (curr != null) {
      if (curr.key.equals(key)) {
        curr.value = value;
        return;
      }
      curr = curr.next;
    }

    Entry<T> newEntry = new Entry<T>(key, value);
    newEntry.next = buckets[idx];
    buckets[idx] = newEntry;
    size++;
  }

  public T get (String key) {
    if (key == null) {
      return null;
    }

    int idx = hash(key);
    Entry<T> curr = buckets[idx];

    while (curr != null) {
      if (curr.key.equals(key)) {
        return curr.value;
      }
      curr = curr.next;
    }
    return null;
  }

  public boolean contains (String key) {
    return get(key) != null;
  }

  public int getSize () {
    return size;
  }
}
