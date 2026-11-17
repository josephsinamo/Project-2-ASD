package dsa;

public class sHashMap <K, V>{
    public  class Pair{
        K key;
        V value;

        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    private sLinkedList [] buckets = new sLinkedList[15];

    public sHashMap(){
        for(int i = 0; i < 15; i++){
            buckets[i] = new sLinkedList<Pair>();
        }
    }

    private int hashFunction(K k){
        String s = String.valueOf(k);
        int nums = 0;
        for(int i = 0; i<s.length(); i++){
            nums += s.charAt(i);
        }
        return nums%15;
    }

    public void put(K key, V value){
        int index = hashFunction(key);
        buckets[index].add(new Pair(key, value));
    }

    public V get(K key){
        int index = hashFunction(key);
        sLinkedList.Node node = buckets[index].head;
        while (node != null){
            if(((Pair)(node.data)).key.equals(key)){
                return ((Pair)(node.data)).value;
            }
            node = node.next;
        }
        return null;
    }
}
