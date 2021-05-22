package onlineClassbook.models.subject;

import onlineClassbook.models.person.professor.Professor;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.*;

public class Subject {

    public static class StudyClass { // can be a course, seminar, laboratory
        private Professor professor; // for every study class there is a single professor
        private float weight; // percentage of the final grade
        private float grade; // the grade of the student
        private float passingGrade; // the minimum grade for the class

        public StudyClass() {
            weight = 0;
            passingGrade = -1;
        }

        public StudyClass(Professor professor, float weight, float passingGrade) {
            this.professor = professor;
            this.weight = weight;
            this.passingGrade = passingGrade;
        }

        public StudyClass(Professor professor, float weight, float grade, float passingGrade) {
            this.professor = professor;
            this.weight = weight;
            this.grade = grade;
            this.passingGrade = passingGrade;
        }

        public void setStudyClass() // read data for creating a new object
        {
            Scanner myInput = new Scanner(System.in);

            System.out.print("Enter weight: ");
            this.weight = myInput.nextFloat();

            System.out.print("Enter passingGrade: ");
            this.passingGrade = myInput.nextFloat();
        }
        // if the study class has a valid configuration and the grade is greater than the passing grade then return true
        public Boolean hasPassed() {
            return (!this.isValid()) || (grade >= passingGrade);
        }
        // if the passing grade is -1, then the configuration for the study class is invalid and should be ignored
        public Boolean isValid() {
            return (this.passingGrade != -1);
        }

        public void setStudyClass(float weight, float passingGrade) {
            this.weight = weight;
            this.passingGrade = passingGrade;
        }

        public float getPassingGrade() {
            return passingGrade;
        }

        public void setPassingGrade(float passingGrade) {
            this.passingGrade = passingGrade;
        }

        public Professor getProfessor() {
            return professor;
        }

        public void setProfessor(Professor professor) {
            this.professor = professor;
        }

        public float getWeight() {
            return weight;
        }

        protected void setWeight(float weight) {
            this.weight = weight;
        }

        public float getGrade() {
            return grade;
        }

        public float getWeightedGrade() {
            return weight * grade;
        }

        public void setGrade(float grade) {
            this.grade = grade;
        }

        @Override
        public String toString() {
            if(professor == null){
                return  " weight = " + weight +
                        ", grade = " + grade +
                        ", passingGrade = " + passingGrade;
            }
            return  " professor = " + professor +
                    ", weight = " + weight +
                    ", grade = " + grade +
                    ", passingGrade = " + passingGrade;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudyClass that = (StudyClass) o;
            return Float.compare(that.weight, weight) == 0 && Float.compare(that.grade, grade) == 0 && Float.compare(that.passingGrade, passingGrade) == 0 && professor.equals(that.professor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(professor, weight, grade, passingGrade);
        }
    }

    private static int counter = 0;
    private final int subjectID;
    private String name;

    private StudyClass course;
    private StudyClass seminar;
    private StudyClass laboratory;

    private int grade;
    private float passingGrade;
    private int credits;

    public Subject() {
        counter += 1;
        subjectID = counter;
        this.name = "";
        this.course = new StudyClass();
        this.seminar = new StudyClass();
        this.laboratory = new StudyClass();
        this.grade = 1;
        this.passingGrade = 5;
        this.credits = 1;
    }

    public Subject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade, float passingGrade, int credits) {
        counter += 1;
        subjectID = counter;
        this.name = name;
        this.course = course;
        this.seminar = seminar;
        this.laboratory = laboratory;
        this.grade = grade;
        this.passingGrade = passingGrade;
        this.credits = credits;
    }

    public Subject(String name, float passingGrade, int credits) {
        counter += 1;
        subjectID = counter;
        this.name = name;
        this.course = new StudyClass();
        this.seminar = new StudyClass();
        this.laboratory = new StudyClass();
        this.grade = 1;
        this.passingGrade = passingGrade;
        this.credits = credits;
    }

    public Subject(int subjectID, String name, float passingGrade, int credits) {
        counter = max(subjectID, counter);
        this.subjectID = subjectID;
        this.name = name;
        this.course = new StudyClass();
        this.seminar = new StudyClass();
        this.laboratory = new StudyClass();
        this.grade = 1;
        this.passingGrade = passingGrade;
        this.credits = credits;
    }

    public Boolean hasPassed() { // check the passing condition for the subclass objects and itself
        return course.hasPassed() && seminar.hasPassed() && laboratory.hasPassed() && (grade >= passingGrade);
    }

    protected void normalizeWeight() {
        course.weight = abs(course.weight);
        seminar.weight = abs(seminar.weight);
        laboratory.weight = abs(laboratory.weight);
        float sum = course.weight + seminar.weight + laboratory.weight;
        course.weight /= sum;
        seminar.weight /= sum;
        laboratory.weight /= sum;
    }

    public void setSubject() {
        Scanner myInput = new Scanner(System.in);

        System.out.print("Enter name: ");
        this.name = myInput.nextLine();

        System.out.print("Enter passing grade: ");
        this.passingGrade = myInput.nextFloat();

        System.out.print("Enter credits: ");
        this.credits = myInput.nextInt();

        System.out.println("Course: ");
        this.course.setStudyClass();

        myInput.nextLine(); // consume the stream

        System.out.print("Does it have a seminar? (Y/N): ");
        String aux;
        aux = myInput.nextLine();
        if(aux.equals("Y") || aux.equals("Yes")) {
            System.out.println("Seminar:");
            this.seminar.setStudyClass();

            System.out.print("Does it have a laboratory? (Y/N): ");
            aux = myInput.nextLine();
            if (aux.equals("Y") || aux.equals("Yes")) {
                System.out.println("Laboratory:");
                this.laboratory.setStudyClass();
            }
        } else {
            System.out.println("Laboratory:");
            this.laboratory.setStudyClass();
        }
        normalizeWeight();
    }

    public int getSubjectID() {
        return subjectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudyClass getCourse() {
        return course;
    }

    public void setCourse(StudyClass course) {
        this.course = course;
    }

    public StudyClass getSeminar() {
        return seminar;
    }

    public void setSeminar(StudyClass seminar) {
        this.seminar = seminar;
    }

    public StudyClass getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(StudyClass laboratory) {
        this.laboratory = laboratory;
    }

    public int getGrade() {
        return grade;
    }

    public void readGrades(){ // read grades for every study class that is valid
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter course grade (0-10): ");
        this.course.grade = min(max(myInput.nextFloat(), 0), 10); // check if the grade is in the correct range

        if(seminar.isValid()) {
            System.out.print("Enter seminar grade (0-10): ");
            this.seminar.grade = min(max(myInput.nextFloat(), 0), 10);
        }

        if(laboratory.isValid()) {
            System.out.print("Enter laboratory grade (0-10): ");
            this.laboratory.grade = min(max(myInput.nextFloat(), 0), 10);
        }
    }
    public void setGrades(float courseGrade, float seminarGrade, float laboratoryGrade) {
        this.course.setGrade(courseGrade);
        if(this.seminar.isValid()){
            this.seminar.setGrade(seminarGrade);
        }
        if(this.laboratory.isValid()){
            this.laboratory.setGrade(laboratoryGrade);
        }
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setCourse() { this.course = new StudyClass(); }
    public void setSeminar() { this.seminar = new StudyClass(); }
    public void setLaboratory() { this.laboratory = new StudyClass(); }

    public void setCourseGrade(float grade) { this.course.setGrade(grade); }
    public void setSeminarGrade(float grade) { this.seminar.setGrade(grade); }
    public void setLaboratoryGrade(float grade) { this.laboratory.setGrade(grade); }

    public void setCoursePassingGrade(float passingGrade) { this.course.setPassingGrade(passingGrade); }
    public void setSeminarPassingGrade(float passingGrade) { this.seminar.setPassingGrade(passingGrade); }
    public void setLaboratoryPassingGrade(float passingGrade) { this.laboratory.setPassingGrade(passingGrade); }

    public void setWeights(float courseWeight, float seminarWeight, float laboratoryWeight){
        this.course.setWeight(courseWeight);
        if(this.seminar.isValid()){
            this.seminar.setWeight(seminarWeight);
        }
        if(this.course.isValid()){
            this.laboratory.setWeight(laboratoryWeight);
        }
        this.normalizeWeight();
    }

    public void setCourseWeight(float weight) { this.course.setWeight(weight); }
    public void setSeminarWeight(float weight) { this.seminar.setWeight(weight); }
    public void setLaboratoryWeight(float weight) { this.laboratory.setWeight(weight); }

    public float getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(int passingGrade) {
        this.passingGrade = passingGrade;
    }

    public int calculateGrade()
    {
        if(seminar.isValid() && laboratory.isValid()) {
            return (int) (course.getWeightedGrade()
                    + seminar.getWeightedGrade()
                    + laboratory.getWeightedGrade() + 0.5);
        }
        else if(seminar.isValid()) {
            return (int) (course.getWeightedGrade()
                    + seminar.getWeightedGrade() + 0.5);
        }
        return (int) (course.getWeightedGrade()
                + laboratory.getWeightedGrade() + 0.5);
    }

    public void setGrade(float course_grade, float seminar_grade, float laboratory_grade) {
        course.setGrade(course_grade);
        seminar.setGrade(seminar_grade);
        laboratory.setGrade(laboratory_grade);
        this.grade = calculateGrade();
    }

    public int getCredits() { return credits; }

    public int getWeightedCredits() {
        return grade * credits; // num of credits times the grade
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        if(!(laboratory.isValid())) {
            return name + " ::: " +
                    " grade=" + grade +
                    ", passingGrade=" + passingGrade +
                    ", credits=" + credits +
                    "\ncourse: " + course +
                    "\nseminar: " + seminar;
        }

        if(!(seminar.isValid())) {
            return name + " ::: " +
                    " grade=" + grade +
                    ", passingGrade=" + passingGrade +
                    ", credits=" + credits +
                    "\ncourse: " + course +
                    "\nlaboratory: " + laboratory;
        }

        return name + " ::: " +
                " grade=" + grade +
                ", passingGrade=" + passingGrade +
                ", credits=" + credits +
                "\ncourse: " + course +
                "\nseminar: " + seminar +
                "\nlaboratory: " + laboratory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return name.equals(subject.name) && course.equals(subject.course) && seminar.equals(subject.seminar) && laboratory.equals(subject.laboratory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, course, seminar, laboratory);
    }
}
