import java.util.*;

import dsa.sHashMap;
import dsa.sLinkedList;

import java.io.*;

public class Mahasiswa {
    private String nama;
    private sHashMap<String, String> mkLulus;
    private String tgrSem;
    private sLinkedList<Matakuliah> ambilMk;
    private sHashMap <Matakuliah,String> belumMk;

    public Mahasiswa(String pathInput) throws FileNotFoundException{
        this(new File(pathInput)); 
    }

    public Mahasiswa(File file) throws FileNotFoundException{
        mkLulus = new sHashMap<>();
        Scanner scan = new Scanner(file);

        nama = file.getName();
        tgrSem =  scan.nextLine().split("=")[1].toLowerCase();

        String [] dafLulus = scan.nextLine().split("=")[1].split(",");
        for (String s: dafLulus){
            mkLulus.put(s);
        }

        ambilMk = new sLinkedList<>();
        belumMk = new sHashMap<>();
        scan.close();
    }

    public void addMk(Matakuliah mk){
        ambilMk.add(mk);
    }

    public void addBlmMk(Matakuliah mk, String p){
        belumMk.put(mk,p);
    }

    public boolean isLulus(String kodeMK){
        return mkLulus.contain(kodeMK);
    }

    public String getTgrSem(){
        return tgrSem;
    }

    public String getNama(){
        return nama;
    }

    public boolean isTgrSemGanjil(){
        return tgrSem.equals("ganjil");
    }

    public static Mahasiswa [] makeMhs(String inputPath) throws Exception{
        File file = new File(inputPath);

        if (!file.isDirectory()){
            throw new Exception("Input Path tidak Direktory ");
        }

        File [] files = file.listFiles();
        Mahasiswa mhs [] = new Mahasiswa[files.length];
        for(int i=0; i< files.length; i++){
            mhs[i] = new Mahasiswa(files[i]);
        }
        return mhs;
    }

    public void printKRS(){
        System.out.printf("\n\n==========================\nNama : %s\n==========================\n",nama);
        System.out.println("Mk yang bisa di Ambil");
        int count = 1;
        for(Matakuliah mk : ambilMk){
            System.out.printf("%3d. %s\n",count++,mk.printDetail());
        }

        System.out.println("\nBelum Bisa di Ambil :");
        sLinkedList<Matakuliah> keySet = belumMk.keySet();
        for(Matakuliah mk : keySet){
            System.out.printf(" --> %s: belum lulus %s\n",mk.getKode(), belumMk.get(mk));
        }
    }
}
