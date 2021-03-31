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

    public Curriculum() {}

    public Curriculum(String major, int year, int semester, int req_credit) {
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.req_credit = req_credit;
        obligatory = new HashSet<>();
        optional = new HashSet<>();
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

    public Set<Subject> getObligatory(){
        return this.obligatory;
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
                student.addSubject(new Subject(optSubj));
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
