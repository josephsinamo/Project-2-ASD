import dsa.sLinkedList;

import java.util.*;
import java.io.*;

public class Prasyarat {
    private String praSyarat, kodeMk;

    public Prasyarat(String p, String k){
        praSyarat = p;
        kodeMk = k;
    }

    public Prasyarat(String [] data){
        praSyarat = data[0];
        kodeMk = data[1];
    }

    public static sLinkedList makePrasyarat(String path) throws IOException{
        Scanner scan = new Scanner(new File(path));
        sLinkedList list = new sLinkedList<>();

        if(scan.hasNextLine()){
            scan.nextLine();
        }

        while (scan.hasNextLine()) {
            list.add(new Prasyarat(scan.nextLine().split(";")));
        }

        return list;
    }

    public String getPraSyarat(){
        return praSyarat;
    }

    public String getKodeMk(){
        return kodeMk;
    }
}
