package subject;

import person.professor.Professor;

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
    }

    String name;

    StudyClass course;
    StudyClass seminar;
    StudyClass laboratory;

    int grade = 1;
    float passing_grade = 5;
    int credits = 1;

    public Subject() {
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

    public float getPassing_grade() {
        return passing_grade;
    }

    public void setPassing_grade(int passing_grade) {
        this.passing_grade = passing_grade;
    }

    public int CalculateGrade()
    {
        return (int)(course.getWeightedGrade()
                + seminar.getWeightedGrade()
                + laboratory.getWeightedGrade());
    }

    public void setGrade(float course_grade, float seminar_grade, float laboratory_grade) {
        course.setGrade(course_grade);
        seminar.setGrade(seminar_grade);
        laboratory.setGrade(laboratory_grade);
        this.grade = CalculateGrade();
    }

    public int getCredits() {
        return grade * credits; // num of credits times the grade
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }


}
