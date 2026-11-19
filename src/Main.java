import java.util.*;

import dsa.sHashMap;
import dsa.sLinkedList;
import dsa.sTree;

import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception {
        sHashMap <String, String> map= new sHashMap<>();
        map.put("abc","1");
        map.put("acb","2");
        map.put("cba","3");

        System.out.println(map.get("abc"));
        System.out.println(map.get("cba"));

        sLinkedList tes = map.makeValueList();
        tes.print();
    }

    public void inisialisasi(sLinkedList list) throws Exception{
        Matakuliah matakuliah [] = new Matakuliah[37];
        sHashMap <String, sTree> map = new sHashMap<>();
        
        makeDatabase(matakuliah,"data/matakuliah.csv");

        for(Matakuliah m : matakuliah){
            map.put(m.getKode(), new sTree(m));
        }   


    }

    public void clean(sHashMap map){
        
    }

    public static void makeDatabase(Object data [], String path) throws IOException{
        Scanner scan = new Scanner(new File(path));
        
        int i = -1;
        while(scan.hasNext()){
            try{
                if (data.getClass().getComponentType() == Matakuliah.class){
                    data[i++] = new Matakuliah(scan.nextLine().split(";"));
                }else{
                    data[i++] = scan.nextLine();
                }
            }catch (Exception e){}
        }
        scan.close();
    }
}
