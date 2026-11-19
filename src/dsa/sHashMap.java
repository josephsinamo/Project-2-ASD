package dsa;

public class sHashMap <K, V>{
    public  class Pair <J , L>{
        J key;
        L value;

        public Pair(J key, L value){
            this.key = key;
            this.value = value;
        }
    }
    private int size = 15;
    
    private sLinkedList [] buckets = new sLinkedList[size];

    public sHashMap(){
        for(int i = 0; i < size; i++){
            buckets[i] = new sLinkedList();
        }
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
    }

    public void put(K key){
        int index = hashFunction(key);
        buckets[index].add(new Pair(key, key));
    }

    public Object get(K key){
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

    public void remove(K key){
        int index = hashFunction(key);
        sLinkedList.Node node = buckets[index].head;
        while (node != null){
            if(((Pair)(node.data)).key.equals(key)){
                buckets[index].remove(node);
            }
            node = node.next;
        }
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
