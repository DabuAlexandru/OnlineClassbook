package onlineClassbook.models.curriculum;

import onlineClassbook.models.person.student.Student;
import onlineClassbook.models.subject.OptionalSubject;
import onlineClassbook.models.subject.Subject;

import java.util.*;

import static java.lang.Math.max;

public class Curriculum {
    private static int counter = 0;
    private final int curriculumID;
    private String major;
    private int year;
    private int semester;
	private int reqCredit;

    private Set<Subject> obligatory;
    private Set<OptionalSubject> optional;

    public Curriculum() {
        counter += 1;
        this.curriculumID = counter;
        this.major = "";
        this.year = 1;
        this.semester = 1;
        this.reqCredit = 0;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
    }

    public Curriculum(String major, int year, int semester, int reqCredit) {
        counter += 1;
        this.curriculumID = counter;
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.reqCredit = reqCredit;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
    }

    public Curriculum(int curriculumID, String major, int year, int semester, int reqCredit) {
        counter = max(curriculumID, counter);
        this.curriculumID = curriculumID;
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.reqCredit = reqCredit;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
    }

    public Curriculum(int curriculumID, String major, int year, int semester, int reqCredit,
                      Set<Subject> obligatory, Set<OptionalSubject> optional) {
        counter = max(curriculumID, counter);
        this.curriculumID = curriculumID;
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.reqCredit = reqCredit;
        this.obligatory = new HashSet<>();
        this.optional = new HashSet<>();
        this.obligatory = obligatory;
        this.optional = optional;
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
        this.reqCredit = myInput.nextInt();
        System.out.println();
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public Subject getObligatoryByID(int subjectID) {
        for(Subject subject : obligatory) {
            if(subject.getSubjectID() == subjectID) {
                return subject;
            }
        }
        return null;
    }

    public OptionalSubject getOptionalByID(int subjectID) {
        for(OptionalSubject optionalSubject : optional) {
            if(optionalSubject.getSubjectID() == subjectID) {
                return optionalSubject;
            }
        }
        return null;
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

    public int getReqCredit() {
        return reqCredit;
    }

    public void setReqCredit(int reqCredit) {
        this.reqCredit = reqCredit;
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
            System.out.println("!!!Student year or semester doesn't correspond to the onlineClassbook.model.curriculum!!!");
        }
    }

    @Override
    public String toString() {
        return "Curriculum ::: " +
                "major = " + major +
                "\nyear = " + year +
                ", semester = " + semester +
                ", reqCredit=" + reqCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum that = (Curriculum) o;
        return year == that.year && semester == that.semester && reqCredit == that.reqCredit && major.equals(that.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, year, semester, reqCredit);
    }
}
