package faculty.group;

import person.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Group {
    String name;
    int numberOfStudents;
    List<Student> students;

    public Group() {
        name = "";
        numberOfStudents = 0;
        students = new ArrayList<>();
    }

    public Group(String name, int numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
        setStudents();
    }

    public void setGroup() {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Group:");
        System.out.print("name = ");
        this.name = myInput.nextLine();
        System.out.println();
    }

    public void setStudents() {
        if(numberOfStudents == 0) {
            Scanner myInput = new Scanner(System.in);
            System.out.print("numberOfStudents = ");
            this.numberOfStudents = myInput.nextInt();
        }
        for(int i = 0; i < numberOfStudents; i++) {
            Student student = new Student();
            student.setStudent();
            this.students.add(student);
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

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
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
