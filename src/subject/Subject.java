package subject;

import person.professor.Professor;

import java.util.Scanner;

public class Subject {

    protected static class StudyClass {
        Professor professor;
        float weight; // percentage of the final grade
        float grade; // the grade of the student
        float passing_grade; // the minimum grade for the class

        public StudyClass() {
        }

        public StudyClass(Professor professor, float weight, float grade, float passing_grade) {
            this.professor = professor;
            this.weight = weight;
            this.grade = grade;
            this.passing_grade = passing_grade;
        }

        public void setStudyClass()
        {
            professor = new Professor();
            Scanner myInput = new Scanner(System.in);

            System.out.println("Enter weight: ");
            this.weight = myInput.nextFloat();

            System.out.println("Enter passing_grade: ");
            this.passing_grade = myInput.nextFloat();
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
    }

    String name;

    StudyClass course = null;
    StudyClass seminar = null;
    StudyClass laboratory = null;

    int grade = 1;
    float passing_grade = 5;
    int credits = 1;

    public Subject() {
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

    public void setSubject() {
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter name: ");
        this.name = myInput.nextLine();

        System.out.println("Course: ");
        this.course = new StudyClass();

        System.out.println("Does it have a seminar? (Y/N)");
        String aux;
        aux = myInput.nextLine();
        if(aux.equals("Y") || aux.equals("Yes"))
        {
            System.out.println("Seminar:");
            this.seminar = new StudyClass();
        }
        else
        {
            System.out.println("Laboratory:");
            this.laboratory = new StudyClass();
        }
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
}
