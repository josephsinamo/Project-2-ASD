import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Matakuliah matakuliah [] = new Matakuliah[38];
        
        makeDatabase(matakuliah,"data/matakuliah.csv");

        for (Matakuliah m:matakuliah){
            if (m != null){
                m.cetak();
            }
        }
    }

    public static void makeDatabase(Matakuliah matakuliah [], String path) throws IOException{
        Scanner scan = new Scanner(new File(path));
        
        int i = 0;
        while(scan.hasNext()){
            String buf [] = scan.nextLine().split(";");
            try{
                matakuliah[i++] = new Matakuliah(buf);
            }catch (Exception e){}
        }
        scan.close();
    }
}
