package Entity;

import DataStructures.*;

public class BlockedCourse {
  private Course course;
  private LinkedList<Course> missingPrereq;
  private String specialReason;

  public BlockedCourse (Course course, LinkedList<Course> missingPrereq) {
    this.course = course;
    this.missingPrereq = missingPrereq;
  }

  public BlockedCourse (Course course, String specialReason) {
    this.course = course;
    this.specialReason = specialReason;
  }

  public Course getCourse () {
    return course;
  }

  public LinkedList<Course> getMissingPrereq () {
    return missingPrereq;
  }

  public String getSpecialReason () {
    return specialReason;
  }

  public boolean missingPrereqIsNull () {
    return missingPrereq == null;
  }
}
