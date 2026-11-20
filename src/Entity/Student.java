package Entity;

import DataStructures.*;

public class Student {
  String nextSemeter;
  HashMap<Boolean> passed = new HashMap<>();

  public Student (String nextSemester, HashMap<Boolean> passed) {
    this.nextSemeter = nextSemester;
    this.passed = passed;
  }
}
