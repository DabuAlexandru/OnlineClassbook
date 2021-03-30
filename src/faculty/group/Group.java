package faculty.group;

import faculty.series.Series;
import person.student.Student;

import java.util.*;

public class Group {
    String name;
    Series series;
    Set<Student> students;

    public Group() {
        name = "";
        students = new HashSet<>();
    }

    public Group(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public void setGroup() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Group:");
        System.out.print("name = ");
        this.name = myInput.nextLine();
        System.out.println();
    }

    public void addStudent(Student student){
        if(student.getGroup() == null){
            student.setGroup(this);
            this.students.add(student);
        }
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        if(series == null){ return "group " + name; }
        return "group " + name + " : " + series.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
