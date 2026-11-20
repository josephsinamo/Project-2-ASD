import java.util.*;

import dsa.sHashMap;
import dsa.sTree;

import java.io.*;

public class Matakuliah {
  private String kode;
  private String nama;
  private int sks;
  private int semester;
  private boolean isGanjil;

  public Matakuliah (String kode, String nama, int sks, int semester, boolean isGanjil) {
    this.kode = kode;
    this.nama = nama;
    this.sks = sks;
    this.semester = semester;
    this.isGanjil = isGanjil;
  }

  public Matakuliah (String data []) {
    this.kode = data[0];
    this.nama = data[1];
    this.sks = Integer.valueOf(data[2]);
    this.semester = Integer.valueOf(data[3]);
    this.isGanjil = data[4].equalsIgnoreCase("Ganjil");
  }

  public static sHashMap <String,sTree> makeMataKuliah(String path)throws IOException{
    Scanner scan = new Scanner(new File(path));
    sHashMap<String,sTree> map = new sHashMap<>();

    if(scan.hasNextLine()){
      scan.nextLine();
    }

    while(scan.hasNextLine()){
      Matakuliah matkul = new Matakuliah(scan.nextLine().split(";"));
      System.out.println("tes");
      sTree tree = new sTree(matkul);
      map.put(matkul.getKode(), tree);
    }

    return map;
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
