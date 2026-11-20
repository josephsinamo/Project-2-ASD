package dsa;

public class sTree {
    Object data;
    boolean isRoot = true;
    sLinkedList childs;

    public sTree(Object data){
        this.data = data;
        childs = new sLinkedList();
    }

    public Object getData(){
        return data;
    }

    public void add(sTree node){
        node.isRoot = false;
        childs.add(node);
    }

    public boolean getIsRoot(){
        return isRoot;
    }

    @Override 
    public String toString(){
        return String.format("{data sTree: %s}",(data));
    }
}
