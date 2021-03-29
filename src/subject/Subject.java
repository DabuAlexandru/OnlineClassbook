package subject;

import person.professor.Professor;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.Math.max;

public class Subject {

    protected static class StudyClass {
        Professor professor = new Professor();
        float weight; // percentage of the final grade
        float grade; // the grade of the student
        float passing_grade; // the minimum grade for the class

        public StudyClass() {
            weight = 0;
            passing_grade = -1;
        }

        public StudyClass(Professor professor, float weight, float grade, float passing_grade) {
            this.professor = professor;
            this.weight = weight;
            this.grade = grade;
            this.passing_grade = passing_grade;
        }

        public void setStudyClass()
        {
            Scanner myInput = new Scanner(System.in);

            System.out.print("Enter weight: ");
            this.weight = myInput.nextFloat();

            System.out.print("Enter passing_grade: ");
            this.passing_grade = myInput.nextFloat();
        }

        public void setStudyClass(float weight, float passing_grade) {
            this.weight = weight;
            this.passing_grade = passing_grade;
        }

        public float getPassing_grade() {
            return passing_grade;
        }

        public void setPassing_grade(float passing_grade) {
            this.passing_grade = passing_grade;
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

        public void setWeight(float weight) {
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
            return "StudyClass{" +
                    "professor=" + professor +
                    ", weight=" + weight +
                    ", grade=" + grade +
                    ", passing_grade=" + passing_grade +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudyClass that = (StudyClass) o;
            return Float.compare(that.weight, weight) == 0 && Float.compare(that.grade, grade) == 0 && Float.compare(that.passing_grade, passing_grade) == 0 && professor.equals(that.professor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(professor, weight, grade, passing_grade);
        }
    }

    String name;

    StudyClass course;
    StudyClass seminar;
    StudyClass laboratory;

    int grade;
    float passing_grade;
    int credits;

    protected int current_weight = 0;

    public Subject() {
        this.name = "";
        this.course = new StudyClass();
        this.seminar = new StudyClass();
        this.laboratory = new StudyClass();
        this.grade = 1;
        this.passing_grade = 5;
        this.credits = 1;
    }

    public Subject(OptionalSubject optSubject) {
        this.name = optSubject.name;
        this.course = optSubject.course;
        this.seminar = optSubject.seminar;
        this.laboratory = optSubject.laboratory;

        this.grade = optSubject.grade;
        this.passing_grade = optSubject.passing_grade;
        this.credits = optSubject.getCredits();
    }

    public Subject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade, float passing_grade, int credits) {
        this.name = name;
        this.course = course;
        this.seminar = seminar;
        this.laboratory = laboratory;
        this.grade = grade;
        this.passing_grade = passing_grade;
        this.credits = credits;
    }

    protected void checkWeight(float weight) {
        if(weight + this.current_weight > 1)
        {
            this.course.setWeight(1 - weight);
            this.current_weight = 1;
        }
        else
        {
            this.current_weight += weight;
        }
    }

    protected void normalizeWeight() {
        float ratio = current_weight / (course.weight + seminar.weight + laboratory.weight);
        course.weight *= ratio;
        seminar.weight *= ratio;
        laboratory.weight *= ratio;
    }

    public void setSubject() {
        Scanner myInput = new Scanner(System.in);

        System.out.print("Enter name: ");
        this.name = myInput.nextLine();

        System.out.println("Course: ");
        this.course.setStudyClass();
        checkWeight(course.getWeight());

        System.out.print("Does it have a seminar? (Y/N): ");
        String aux;
        aux = myInput.nextLine();
        if(aux.equals("Y") || aux.equals("Yes"))
        {
            System.out.println("Seminar:");
            this.seminar.setStudyClass();
            checkWeight(seminar.getWeight());
            /* if the weight of the course + the weight of the seminar are greater than 1
            then the seminar will have to take the rest to 1, and the laboratory will have a weight of 0
             */

            System.out.print("Does it have a laboratory? (Y/N): ");
            aux = myInput.nextLine();
            if (aux.equals("Y") || aux.equals("Yes")) {
                System.out.println("Laboratory:");
                this.laboratory.setStudyClass();
                checkWeight(laboratory.getWeight());
            }
        }
        else
        {
            System.out.println("Laboratory:");
            this.laboratory.setStudyClass();
            checkWeight(laboratory.getWeight());
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

    public void setGrades(){
        Scanner myInput = new Scanner(System.in);
        System.out.print("Enter course grade: ");
        this.course.grade = max(myInput.nextFloat(), 10);

        if(seminar.passing_grade >= 0) {
            System.out.print("Enter seminar grade: ");
            this.seminar.grade = max(myInput.nextFloat(), 10);
        }

        if(laboratory.passing_grade >= 0) {
            System.out.print("Enter laboratory grade: ");
            this.laboratory.grade = max(myInput.nextFloat(), 10);
        }
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setCourse()
    {
        this.course = new StudyClass();
    }

    public void setSeminar()
    {
        this.seminar = new StudyClass();
    }

    public void setLaboratory()
    {
        this.laboratory = new StudyClass();
    }

    public void setCourseGrade(float grade) {
        this.course.setGrade(grade);
    }

    public void setSeminarGrade(float grade) {
        this.seminar.setGrade(grade);
    }

    public void setLaboratoryGrade(float grade) {
        this.laboratory.setGrade(grade);
    }

    public void setCourseWeight(float weight) {
        this.course.setWeight(weight);
    }

    public void setSeminarWeight(float weight) {
        this.seminar.setWeight(weight);
    }

    public void setLaboratoryWeight(float weight) {
        this.laboratory.setWeight(weight);
    }

    public float getPassing_grade() {
        return passing_grade;
    }

    public void setPassing_grade(int passing_grade) {
        this.passing_grade = passing_grade;
    }

    public int calculateGrade()
    {
        if(seminar != null && laboratory != null) {
            return (int) (course.getWeightedGrade()
                    + seminar.getWeightedGrade()
                    + laboratory.getWeightedGrade() + 0.5);
        }
        else if(seminar != null) {
            return (int) (course.getWeightedGrade()
                    + seminar.getWeightedGrade() + 0.5);
        }
        else {
            return (int) (course.getWeightedGrade()
                    + laboratory.getWeightedGrade() + 0.5);
        }
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
        if(laboratory == null) {
            return "Subject{" +
                    "name='" + name + '\'' +
                    ", course=" + course +
                    ", seminar=" + seminar +
                    ", grade=" + grade +
                    ", passing_grade=" + passing_grade +
                    ", credits=" + credits +
                    '}';
        }

        return "Subject{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", laboratory=" + laboratory +
                ", grade=" + grade +
                ", passing_grade=" + passing_grade +
                ", credits=" + credits +
                '}';
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
