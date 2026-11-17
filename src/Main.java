import java.util.*;

import dsa.sHashMap;

import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception {
        // Matakuliah matakuliah [] = new Matakuliah[37];
        
        // makeDatabase(matakuliah,"data/matakuliah.csv");

        // for (Matakuliah m:matakuliah){
        //     m.cetak();
        // }


        sHashMap <String, String> tes = new sHashMap<>();

        tes.put("sa", "lol");
        tes.put("as", "lil");

        
        System.out.println(tes.get("sa"));
        System.out.println(tes.get("as"));
        
    }

    public static void makeDatabase(Matakuliah matakuliah [], String path) throws IOException{
        Scanner scan = new Scanner(new File(path));
        
        int i = -1;
        while(scan.hasNext()){
            String buf [] = scan.nextLine().split(";");
            try{
                matakuliah[i++] = new Matakuliah(buf);
            }catch (Exception e){}
        }
        scan.close();
    }
}
