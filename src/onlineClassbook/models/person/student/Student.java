package onlineClassbook.models.person.student;

import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.person.Person;
import onlineClassbook.models.subject.Subject;

import java.util.*;

public class Student extends Person {

    int year;
    int semester;
    Group group;
    Set<Subject> subjects;

    public Student() {
        super();
        year = 0;
        semester = 0;
        subjects = new HashSet<Subject>();
    }

    public Student(String first_name, String last_name, String sex, String birth_date, String phone_number,
                   String email, String join_date, int year, int semester) {
        super(first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.subjects = new HashSet<Subject>();
    }

    public Student(int personID, String first_name, String last_name, String sex, String birth_date, String phone_number,
                   String email, String join_date, int year, int semester) {
        super(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.subjects = new HashSet<Subject>();
    }

    public Student(int personID, String first_name, String last_name, String sex, String birth_date, String phone_number,
                   String email, String join_date, int year, int semester, Group group) {
        super(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.group = group;
        this.subjects = new HashSet<Subject>();
    }

    public Student(int personID, String first_name, String last_name, String sex, String birth_date, String phone_number,
                   String email, String join_date, int year, int semester, Set<Subject> subjects) {
        super(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.year = year;
        this.semester = semester;
        this.subjects = subjects;
    }

    public void setStudent() {
        super.setPerson();
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter year: ");
        this.year = myInput.nextInt();

        System.out.println("Enter semester: ");
        this.semester = myInput.nextInt();

    }

    public Subject getSubjectByID(int subjectID) {
        for(Subject subject : subjects) {
            if(subject.getSubjectID() == subjectID) {
                return subject;
            }
        }
        return null;
    }

    public int getNumOfSubjects() { return subjects.size(); }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubjects(Set<Subject> subjects) {
        this.subjects.addAll(subjects);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject){ this.subjects.remove(subject); }

    @Override
    public String toString() {
        if(this.group == null)
        {
            return super.toString() + " : Student" +
                    "\nYear: " + this.year +
                    " || Semester: " + this.semester + '\n';
        }
        return super.toString() + " : Student" +
                "\nYear: " + this.year +
                " || Semester: " + this.semester +
                " || "+ this.group.toString() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return year == student.year && semester == student.semester;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), year, semester);
    }

}
