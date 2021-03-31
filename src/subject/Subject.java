package subject;

import person.professor.Professor;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.*;

public class Subject {

    public static class StudyClass {
        Professor professor;
        float weight; // percentage of the final grade
        float grade; // the grade of the student
        float passingGrade; // the minimum grade for the class

        public StudyClass() {
            weight = 0;
            passingGrade = -1;
        }

        public StudyClass(Professor professor, float weight, float grade, float passingGrade) {
            this.professor = professor;
            this.weight = weight;
            this.grade = grade;
            this.passingGrade = passingGrade;
        }

        public void setStudyClass()
        {
            Scanner myInput = new Scanner(System.in);

            System.out.print("Enter weight: ");
            this.weight = myInput.nextFloat();

            System.out.print("Enter passingGrade: ");
            this.passingGrade = myInput.nextFloat();
        }

        public Boolean hasPassed() {
            return (!this.isValid()) || (grade > passingGrade);
        }

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

    String name;

    StudyClass course;
    StudyClass seminar;
    StudyClass laboratory;

    int grade;
    float passingGrade;
    int credits;

    protected int currentWeight = 0;

    public Subject() {
        this.name = "";
        this.course = new StudyClass();
        this.seminar = new StudyClass();
        this.laboratory = new StudyClass();
        this.grade = 1;
        this.passingGrade = 5;
        this.credits = 1;
    }

    public Subject(OptionalSubject optSubject) {
        this.name = optSubject.name;
        this.course = optSubject.course;
        this.seminar = optSubject.seminar;
        this.laboratory = optSubject.laboratory;

        this.grade = optSubject.grade;
        this.passingGrade = optSubject.passingGrade;
        this.credits = optSubject.getCredits();
    }

    public Subject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade, float passingGrade, int credits) {
        this.name = name;
        this.course = course;
        this.seminar = seminar;
        this.laboratory = laboratory;
        this.grade = grade;
        this.passingGrade = passingGrade;
        this.credits = credits;
    }

    public Boolean hasPassed() {
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

        System.out.println("Course: ");
        this.course.setStudyClass();

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

    public void readGrades(){
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter course grade: ");
        this.course.grade = min(max(myInput.nextFloat(), 0), 10);

        if(seminar.passingGrade >= 0) {
            System.out.print("Enter seminar grade: ");
            this.seminar.grade = min(max(myInput.nextFloat(), 0), 10);
        }

        if(laboratory.passingGrade >= 0) {
            System.out.print("Enter laboratory grade: ");
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
        this.currentWeight = 0;
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

    public int getCredits() {
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
