import DataStructures.*;
import Entity.*;

import java.io.*;

public class Main {
    public static void main (String args[]) throws IOException {
        Graph graph = new Graph();
    
        loadCourse("data/matakuliah.csv", graph);
        loadPrereq("data/prasyarat.csv", graph);

        File[] dirInput = new File("input").listFiles();
        if (dirInput != null) {
          for (File input : dirInput) {
            if (!input.isFile() || !input.getName().endsWith(".txt")) {
              continue;
            }

            pBorder(60);
            System.out.printf("Nama mhs : %s\n", input.getName());

            HashMap<Boolean> lulus = new HashMap<>();
            String nextSemester = loadInput(input.getPath(), lulus);

            printMhs(graph, lulus, nextSemester);
            pBorder(60);
          }
        }
    }

    private static void pBorder(int pjg) {
        if (pjg <= 0) return;
        System.out.println(String.format("%" + pjg + "s", "").replace(' ', '='));  
    }

  public static void printMhs(Graph graph, HashMap<Boolean> lulus, String nextSemester){
    LinkedList<Course> canBeTaken = new LinkedList<>();
    LinkedList<BlockedCourse> cantBeTaken = new LinkedList<>();

    for (Node<Course> node =  graph.getAllCourse().getHead(); node != null; node = node.getNext()) {
      Course c = node.getData();

      if (lulus.contains(c.getCode())) {
        continue;
      }

      if (!c.periode.equalsIgnoreCase(nextSemester) && !c.periode.equalsIgnoreCase("Keduanya")) {
        continue;
      }

      if (c.getCode().equalsIgnoreCase("SKR")) {
        if (lulus.getSize() == graph.getTotalCourse()-1) {
          canBeTaken.addLast(c);
        } else {
          cantBeTaken.addLast(new BlockedCourse(c, "Belum lulus semua MK wajib"));
        }
        continue;
      }

      LinkedList<Course> missing = graph.getMissingPrerequisites(c, lulus);
      if (missing.isEmpty()) {
        canBeTaken.addLast(c);
      } else {
        cantBeTaken.addLast(new BlockedCourse(c, missing));
      }
    }

        int i = 1;
    System.out.println("Daftar MK yang bisa diambil:");
    for (Node<Course> node = canBeTaken.getHead(); node != null; node = node.getNext()) {
      System.out.printf("%-4s%s\n", (i++) + ".", node.getData().getDetail());
    }

    System.out.println();

    System.out.println("Daftar MK yang belum bisa diambil semester depan:");
    for (Node<BlockedCourse> node = cantBeTaken.getHead(); node != null; node = node.getNext()) {
      BlockedCourse bc = node.getData();

      if (bc.missingPrereqIsNull()) {
        System.out.printf("%-6s %s\n", bc.getCourse().getCode() + ":", bc.getSpecialReason());
      } else {
        System.out.printf("%-6s belum lulus ", bc.getCourse().getCode() + ":");
        for (Node<Course> mNode = bc.getMissingPrereq().getHead(); mNode != null; mNode = mNode.getNext()) {
          System.out.print(mNode.getData().getCode());
          if (mNode.getNext() != null) {
            System.out.print(", ");
          }
        }
        System.out.println();
      }
    }
  }

  private static void loadCourse (String path, Graph graph) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      br.readLine();

      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }
        Course course = new Course(line.split(";"));
        graph.addCourse(course);
      }
    }
  }

  private static void loadPrereq (String path, Graph graph) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      br.readLine();

      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] parts = line.split(";");
        String prereqCode = parts[0].trim();
        String targetCode = parts[1].trim();

        graph.addPrereq(prereqCode, targetCode);
      }
    }
  }

  public static String loadInput(String path, HashMap<Boolean> lulus) throws IOException {
    String semester = "";
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String firstLine = br.readLine();
      if (firstLine != null && !firstLine.trim().isEmpty()) {
        String[] semParts = firstLine.split("=", 2);
        if (semParts.length > 1) {
          semester = semParts[1].trim();
        }
      }

      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) {
          continue;
        }

        String[] kv = line.split("=", 2);
        if (kv.length < 2 || kv[1].trim().isEmpty()) {
          continue;
        }

        String[] parts = kv[1].split(",");
        for (String s : parts) {
          String trimmed = s.trim();
          if (!trimmed.isEmpty()) {
            lulus.put(trimmed, true);
          }
        }
      }
    }

    return semester;
  }
}
