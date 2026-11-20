import java.util.*;

import dsa.sHashMap;
import dsa.sTree;

import java.io.*;

public class Matakuliah {
  private String kode;
  private String nama;
  private int sks;
  private int semester;
  private String syaratSem;

  public Matakuliah (String kode, String nama, int sks, int semester, String syaratSem) {
    this.kode = kode;
    this.nama = nama;
    this.sks = sks;
    this.semester = semester;
    this.syaratSem = syaratSem;
  }

  public Matakuliah (String data []) {
    this.kode = data[0];
    this.nama = data[1];
    this.sks = Integer.valueOf(data[2]);
    this.semester = Integer.valueOf(data[3]);
    this.syaratSem = data[4].toLowerCase();
  }

  public static sHashMap <String,sTree> makeMataKuliah(String path)throws FileNotFoundException{
    Scanner scan = new Scanner(new File(path));
    sHashMap<String,sTree> map = new sHashMap<>();

    if(scan.hasNextLine()){
      scan.nextLine();
    }

    while(scan.hasNextLine()){
      Matakuliah matkul = new Matakuliah(scan.nextLine().split(";"));
      // System.out.println("tes");
      sTree tree = new sTree(matkul);
      map.put(matkul.getKode(), tree);
    }

    return map;
  }

  public boolean canTake(String s){
    return s.equals(syaratSem) || s.equals("keduanya");
  }

  public String getKode () {
    return kode;
  }

  public void cetak(){
    System.out.println("nama saya:"+nama+"   kode:"+kode);
  }

  @Override 
  public String toString(){
    return String.format("<Matkul.kode MK: %s>", kode);
  }
}
