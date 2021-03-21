package person.student;

import person.Person;
import subject.Subject;

public class Student extends Person {

    int year;
    int semester;

    Subject[] subjects;

    public Student() {
    }

    public Student(String first_name, String last_name, String sex, String birth_date, String phone_number, String email, String join_date, int year, int semester, Subject[] subjects) {
        super(first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.subjects = subjects;
    }

    public Student(String first_name, String last_name, String birth_date, String join_date, String email,
                   int year, int semester, Subject[] subjects) {

        this.year = year;
        this.semester = semester;
        this.subjects = subjects;
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

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }
}
