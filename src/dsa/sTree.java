package dsa;

public class sTree {
    Object data;
    sLinkedList childs;

    public sTree(Object data){
        this.data = data;
        childs = new sLinkedList();
    }

    public void add(sTree node){
        childs.add(node);
    }
}
