package curriculum;

import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.util.*;

public class Curriculum {
    String major;
    int year;
    int semester;
	int req_credit;

    Set<Subject> obligatory;
    Set<OptionalSubject> optional;

    public Curriculum() {
        this.major = "";
        this.year = 1;
        this.semester = 1;
        this.req_credit = 0;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
    }

    public Curriculum(String major, int year, int semester, int req_credit) {
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.req_credit = req_credit;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
    }

    public void setCurriculum(){
        Scanner myInput = new Scanner(System.in);
        System.out.println("Curriculum:");
        System.out.print("major = ");
        this.major = myInput.next();
        System.out.print("year = ");
        this.year = myInput.nextInt();
        System.out.print("semester = ");
        this.semester = myInput.nextInt();
        System.out.print("required credits = ");
        this.req_credit = myInput.nextInt();
        System.out.println();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
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

    public int getReq_credit() {
        return req_credit;
    }

    public void setReq_credit(int req_credit) {
        this.req_credit = req_credit;
    }

    public Set<Subject> getObligatory(){
        return this.obligatory;
    }

    public Set<OptionalSubject> getOptional() { return this.optional; }

    public int getNumOfObligatory() { return this.obligatory.size(); }

    public int getNumOfOptional() { return this.optional.size(); }

    public void addObligatory(Subject subject) {
        obligatory.add(subject);
    }

    public void addOptional(OptionalSubject optionalSubject) {
        optional.add(optionalSubject);
    }

    public void removeObligatory(Subject subject){
        obligatory.remove(subject);
    }

    public void removeOptional(OptionalSubject optionalSubject) {
        optional.remove(optionalSubject);
    }

    public void appendCurriculum(Student student){
        if((student.getYear() == 0) || (student.getSemester() == 0))
        {
            student.setYear(this.year);
            student.setSemester(this.semester);
        }
        else if((student.getYear() == year) && (student.getSemester() == semester))
        {
            student.addSubjects(obligatory);
            for(OptionalSubject optSubj : optional) {
                student.addSubject(optSubj);
            }
        }
        else
        {
            System.out.println("!!!Student year or semester doesn't correspond to the curriculum!!!");
        }
    }

    @Override
    public String toString() {
        return "Curriculum ::: " +
                "major = " + major +
                "\nyear = " + year +
                ", semester = " + semester +
                ", req_credit=" + req_credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum that = (Curriculum) o;
        return year == that.year && semester == that.semester && req_credit == that.req_credit && major.equals(that.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, year, semester, req_credit);
    }
}
