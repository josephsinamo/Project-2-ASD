package Entity;

import DataStructures.*;

public class BlockedCourse {
  public Course course;
  public LinkedList<Course> missingPrereq;
  public String specialReason;

  public BlockedCourse (Course course, LinkedList<Course> missingPrereq) {
    this.course = course;
    this.missingPrereq = missingPrereq;
  }

  public BlockedCourse (Course course, String specialReason) {
    this.course = course;
    this.specialReason = specialReason;
  }
}
