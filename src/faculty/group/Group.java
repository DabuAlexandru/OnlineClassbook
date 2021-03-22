package faculty.group;

import person.student.Student;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String name;
    int numberOfStudents;
    List<Student> students = new ArrayList<Student>();

    public Group() {
    }

    public Group(String name, int numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        for(int i = 0; i < numberOfStudents; i++) {
            this.students.add(new Student());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
