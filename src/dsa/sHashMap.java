package dsa;

import java.util.Iterator;

public class sHashMap <K, V>{
    public  class Pair {
        K key;
        V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }

        public V getValue(){
            return value;
        }

        public K getKey(){
            return key;
        }

        @Override 
        public String toString(){
            return String.format("(key : %s   -- value : %s)",key,value);
        }
    }
    private int size, length;
    private sLinkedList [] buckets;
    private sLinkedList <K> keySet;

    public sHashMap(){
        size = 15;
        length = 0;
        keySet = new sLinkedList<>();
        buckets = new sLinkedList[size];
        for(int i = 0; i < size; i++){
            buckets[i] = new sLinkedList();
        }
    }

    public int getInt(){
        return size;
    }

    public int getLength(){
        return length;
    }

    public sLinkedList [] getList(){
        return buckets;
    }

    private int hashFunction(K k){
        String s = String.valueOf(k);
        int nums = 0;
        for(int i = 0; i<s.length(); i++){
            nums += s.charAt(i);
        }
        return nums%size;
    }

    public void put(K key, V value){
        int index = hashFunction(key);
        buckets[index].add(new Pair(key, value));
        keySet.add(key);
        length++;
    }

    public void put(K key){
        int index = hashFunction(key);
        buckets[index].add(new Pair(key, (V)key));
        keySet.add(key);
        length++;
    }

    public V get(K key){
        int index = hashFunction(key);
        sLinkedList.Node node = buckets[index].head;
        while (node != null){
            if(((Pair)(node.data)).key.equals(key)){
                return ((Pair)(node.data)).getValue();
            }
            node = node.next;
        }
        return null;
    }

    public sLinkedList<K> keySet(){
        return keySet;
    }

    public void remove(K key){
        int index = hashFunction(key);
        sLinkedList.Node node = buckets[index].head;
        while (node != null){
            if(((Pair)(node.data)).key.equals(key)){
                buckets[index].remove(node);
                keySet.remove(key);;
                return;
            }
            node = node.next;
        }
    }

    public boolean contain(K key){
        int index = hashFunction(key);
        sLinkedList.Node node = buckets[index].head;
        while (node != null){
            if(((Pair)(node.data)).key.equals(key)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public sLinkedList makeValueList(){
        sLinkedList listValue = new sLinkedList();
        for(sLinkedList list : buckets){
            if(!list.isEmpty()){
                // list.print();
                sLinkedList.Node node = list.head;
                while (node != null){
                    // System.out.println(((Pair)node.data).value+" "+(((Pair)(node.data)).value).getClass());
                    // ((sLinkedList)((Pair)(node.data)).value).print();
                    listValue.add(((Pair)node.data).value);
                    node = node.next;
                }
            }
        }
        return listValue;
    }
}
