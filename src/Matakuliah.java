public class Matakuliah {
  private String kode;
  private String nama;
  private int sks;
  private String semester;
  private boolean isGanjil;

  public Matakuliah (String kode, String nama, int sks, String semester, boolean isGanjil) {
    this.kode = kode;
    this.nama = nama;
    this.sks = sks;
    this.semester = semester;
    this.isGanjil = isGanjil;
  }

  public String getKode () {
    return kode;
  }
}
