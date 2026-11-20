package DataStructures;
import Entity.Course;

public class Graph {
  private LinkedList<Course> courses;
  private HashMap<Course> courseMap;

  public Graph () {
    courses = new LinkedList<>();
    courseMap = new HashMap<>();
  }

  public void addCourse (Course course) {
    courses.addLast(course);
    courseMap.put(course.getCode(), course);
  }

  public void addPrereq (String prereqCode, String targetCode) {
    Course prereq = courseMap.get(prereqCode);
    Course target = courseMap.get(targetCode);

    if ((prereq != null) && (target != null)) {
      target.prereq.addLast(prereq);
    }

  }

  public LinkedList<Course> getAllCourse () {
    return courses;
  }

  public LinkedList<Course> getMissingPrerequisites (Course target, HashMap<Boolean> passed) {
    LinkedList<Course> missing = new LinkedList<Course>();
    HashMap<Course> visited = new HashMap<Course>();
    dfs(target, passed, visited, missing);
    
    return missing;
  }

  private void dfs (Course curr, HashMap<Boolean> passed, HashMap<Course> visited, LinkedList<Course> missing) {
    for (Node<Course> node = curr.prereq.head; node != null; node = node.next) {
      Course prereq = node.data;

      if (visited.contains(prereq.getCode())) {
        continue;
      }
      visited.put(prereq.getCode(), prereq);

      if (!passed.contains(prereq.getCode())) {
        dfs(prereq, passed, visited, missing);
        missing.addLast(prereq);
      }
    }
  }
}
