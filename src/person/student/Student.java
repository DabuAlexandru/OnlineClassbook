package person.student;

import person.Person;
import subject.OptionalSubject;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends Person {

    int year = 0;
    int semester = 0;

    List<Subject> subjects = new ArrayList<>();

    public Student() {
        setStudent();
    }

    public Student(String first_name, String last_name, String sex, String birth_date, String phone_number, String email, String join_date, int year, int semester, List<Subject> subjects) {
        super(first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.subjects = subjects;
    }

    public Student(String first_name, String last_name, String birth_date, String join_date, String email,
                   int year, int semester, List<Subject> subjects) {
        this.year = year;
        this.semester = semester;
        this.subjects = subjects;
    }

    public void setStudent() {
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter year: ");
        this.year = myInput.nextInt();

        System.out.println("Enter semester: ");
        this.semester = myInput.nextInt();

    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubjects(List<Subject> subjects) {
        this.subjects.addAll(subjects);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    @Override
    public String toString() {
        return super.toString() + "\nStudent{" +
                "year=" + year +
                ", semester=" + semester +
                ", subjects=" + subjects +
                '}';
    }
}
