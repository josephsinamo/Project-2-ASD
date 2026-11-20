package Entity;

import DataStructures.*;

public class BlockedCourse {
  public Course course;
  public LinkedList<Course> missingPrereq;

  public BlockedCourse (Course course, LinkedList<Course> missingPrereq) {
    this.course = course;
    this.missingPrereq = missingPrereq;
  }
}
