import DataStructures.*;
import Entity.*;

import java.io.*;

public class Main {
  public static void main (String args[]) throws IOException {
    Graph graph = new Graph();
    
    loadCourse("data/matakuliah.csv", graph);
    loadPrereq("data/prasyarat.csv", graph);

    // LinkedList<Course> courses = graph.getAllCourse();
    // courses.display();
    // System.out.println("size = " + courses.getSize());

    String nextSemester = "genap";
    HashMap<Boolean> lulus = new HashMap<>();
    lulus.put("RPL", true);
    lulus.put("PDAS", true);
    lulus.put("PKK", true);
    lulus.put("AOK", true);
    lulus.put("KAL", true);
    lulus.put("STP", true);
    lulus.put("AGM", true);

    LinkedList<Course> canBeTaken = new LinkedList<>();
    LinkedList<BlockedCourse> cantBeTaken = new LinkedList<>();

    for (Node<Course> node =  graph.getAllCourse().head; node != null; node = node.next) {
      Course c = node.data;

      if (lulus.contains(c.getCode())) {
        continue;
      }

      if (! c.periode.equalsIgnoreCase(nextSemester)) {
        continue;
      }

      LinkedList<Course> missing = graph.getMissingPrerequisites(c, lulus);
      if (missing.isEmpty()) {
        canBeTaken.addLast(c);
      } else {
        cantBeTaken.addLast(new BlockedCourse(c, missing));
      }
    }


    System.out.println("Yang bisa diambil:");
    for (Node<Course> node = canBeTaken.head; node != null; node = node.next) {
      System.out.println(node.data);
    }

    System.out.println("\n");
    
    System.out.println("Yang tidak bisa diambil:");
    for (Node<BlockedCourse> node = cantBeTaken.head; node != null; node = node.next) {
      BlockedCourse bc = node.data;

      System.out.print(bc.course + " Karena: ");

      for (Node<Course> nodeCourse = bc.missingPrereq.head; nodeCourse != null; nodeCourse = nodeCourse.next) {
        System.out.print(nodeCourse.data + " ");
      }
      System.out.println();
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

        String[] parts = line.split(";");
        String code = parts[0].trim();
        String name = parts[1].trim();
        int sks = Integer.parseInt(parts[2].trim());
        int semester = Integer.parseInt(parts[3].trim());
        String periode = parts[4].trim();

        Course course = new Course(code, name, sks, semester, periode);
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
}
