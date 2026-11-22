package Entity;
import java.util.InputMismatchException;

import DataStructures.LinkedList;

public class Course {
  private String code;
  private String nama;
  private int sks;
  private int semester;
  public String periode;
  public LinkedList<Course> prereq;

  public Course (String kode, String nama, int sks, int semester, String isGanjil) {
    this.code = kode;
    this.nama = nama;
    this.sks = sks;
    this.semester = semester;
    this.periode = isGanjil;
    this.prereq = new LinkedList<>();
  }

  public Course(String [] input){
    if (input.length != 5){
      throw new InputMismatchException("Input tidak sesuai");
    }
    this(input[0].trim(),input[1],Integer.valueOf(input[2]),Integer.valueOf(input[3]),input[4].trim());
  }

  public String getCode () {
    return code;
  }

  public void cetak(){
    System.out.println("nama saya:"+nama+"   kode:"+code);
  }

  @Override 
  public String toString(){
    return String.format("%s", code);
  }

  public String getDetail(){
    return String.format("%-5s %-50s %d sks", code,nama,sks);
  }
}
