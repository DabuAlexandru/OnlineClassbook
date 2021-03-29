package faculty;

import faculty.group.Group;
import faculty.series.Series;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faculty { // singleton

    protected static class Specialization {
        String name;
        int budgetaryCapacity;
        int taxCapacity;
        int taxPrice;

        int num_of_budgetary = 0;   // number of students that are part of the budgetary plan
        int num_of_tax = 0;         // number of students that are part of the tax plan

        public Specialization() {
            setSpecialization();
        }

        public void setSpecialization()
        {
            Scanner myInput = new Scanner(System.in);

            System.out.println("\n--- Set specialization ---\n");
            System.out.println("Enter name:");
            this.name = myInput.nextLine();

            System.out.println("Enter number of designated slots for budgetary programme: ");
            this.budgetaryCapacity = myInput.nextInt();

            System.out.println("Enter number of designated slots for tax programme: ");
            this.budgetaryCapacity = myInput.nextInt();

            System.out.println("Enter tax price: ");
            this.taxPrice = myInput.nextInt();
        }

        public void supplementBudgetaryCapacity(int amount) {
            this.budgetaryCapacity += amount;
            this.taxCapacity -= amount;
        }

        public void supplementTaxCapacity(int amount) {
            this.budgetaryCapacity -= amount;
            this.taxCapacity += amount;
        }

        public void modifyCapacity(int budgetaryCapacityAmount, int taxCapacityAmount) {
            this.budgetaryCapacity += budgetaryCapacityAmount;
            this.taxCapacity += taxCapacityAmount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBudgetaryCapacity() {
            return budgetaryCapacity;
        }

        public void setBudgetaryCapacity(int budgetaryCapacity) {
            this.budgetaryCapacity = budgetaryCapacity;
        }

        public int getTaxCapacity() {
            return taxCapacity;
        }

        public void setTaxCapacity(int taxCapacity) {
            this.taxCapacity = taxCapacity;
        }

        public int getTaxPrice() {
            return taxPrice;
        }

        public void setTaxPrice(int taxPrice) {
            this.taxPrice = taxPrice;
        }
    }

    String name;
    int numOfSpecializations;
    List<Specialization> specializations = new ArrayList<Specialization>();

    List<Student> students = new ArrayList<>();
    List<Subject> subjects = new ArrayList<>();
    List<OptionalSubject> optionalSubjects = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();
    List<Group> groups = new ArrayList<>();
    List<Series> series = new ArrayList<>();

    private static Faculty faculty;

    private Faculty(String name, int numOfSpecializations) {
        this.name = name;
        this.numOfSpecializations = numOfSpecializations;
        for(int i = 0; i < numOfSpecializations; i++)
        {
            specializations.add(new Specialization());
        }
    }

    public static Faculty getFaculty(String name, int numOfSpecializations) {
        if (faculty == null)
            faculty = new Faculty(name, numOfSpecializations);
        return faculty;
    }

    // add elements

    public void addStudent(){
        Student newStudent = new Student();
        newStudent.setStudent();
        students.add(newStudent);
    }

    public void addSubject(){
        Subject newSubject = new Subject();
        newSubject.setSubject();
        subjects.add(newSubject);
    }

    public void addOptionalSubject(){
        OptionalSubject newOptionalSubject = new OptionalSubject();
        newOptionalSubject.setOptionalSubject();
        optionalSubjects.add(newOptionalSubject);
    }

    public void addProfessor(){
        Professor newProfessor = new Professor();
        newProfessor.setProfessor();
        professors.add(newProfessor);
    }

    public void addGroup(){
        Group newGroup = new Group();
        newGroup.setGroup();
        groups.add(newGroup);
    }

    public void addSeries() {
        Series newSeries = new Series();
        newSeries.setSeries();
        series.add(newSeries);
    }

    // print elements

    public void printStudents(){
        int i = 0;
        for (Student student : students) {
            System.out.println(++i + ": " + student);
        }
    }

    public void printSubjects(){
        int i = 0;
        for (Subject subject : subjects) {
            System.out.println(++i + ": " + subject);
        }
    }

    public void printOptionalSubjects(){
        int i = 0;
        for (OptionalSubject optionalSubject : optionalSubjects) {
            System.out.println(++i + ": " + optionalSubject);
        }
    }

    public void printProfessors(){
        int i = 0;
        for (Professor professor : professors) {
            System.out.println(++i + ": " + professor);
        }
    }

    public void printSeries(){
        int i = 0;
        for (Series objSeries : series) {
            System.out.println(++i + ": " + objSeries);
        }
    }

    public void printGroups(){
        int i = 0;
        for (Group group : groups) {
            System.out.println(++i + ": " + group);
        }
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                '}';
    }
}
