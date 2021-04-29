package faculty;

import curriculum.Curriculum;
import faculty.group.Group;
import faculty.series.Series;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import javax.swing.text.html.Option;
import java.util.*;

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

        public Specialization(String name, int budgetaryCapacity, int taxCapacity, int taxPrice) {
            this.name = name;
            this.budgetaryCapacity = budgetaryCapacity;
            this.taxCapacity = taxCapacity;
            this.taxPrice = taxPrice;
        }

        public void setSpecialization() {
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
    Set<Specialization> specializations = new HashSet<>();

    List<Curriculum> curricula = new ArrayList<>();
    List<Student> students = new ArrayList<>();
    List<Subject> subjects = new ArrayList<>();
    List<OptionalSubject> optionalSubjects = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();
    List<Group> groups = new ArrayList<>();
    List<Series> series = new ArrayList<>();

    private static Faculty faculty;

    private Faculty(String name) {
        this.name = name;
    }

    public static Faculty getFaculty(String name) {
        if (faculty == null)
            faculty = new Faculty(name);
        return faculty;
    }

    public void addSpecialization(String name, int budgetaryCapacity, int taxCapacity, int taxPrice){
        specializations.add(new Specialization(name, budgetaryCapacity, taxCapacity, taxPrice));
    }

    // get number of elements in a given list
    public int getNumOfCurricula(){ return curricula.size(); }
    public int getNumOfStudents(){ return students.size(); }
    public int getNumOfSubjects(){ return subjects.size(); }
    public int getNumOfOptionalSubjects(){ return optionalSubjects.size(); }
    public int getNumOfProfessors(){ return professors.size(); }
    public int getNumOfGroups(){ return groups.size(); }
    public int getNumOfSeries(){ return series.size(); }

    // get an object of a list
    public Curriculum getCurriculum(int index){ return curricula.get(index); }
    public Student getStudent(int index){ return students.get(index); }
    public Subject getSubject(int index){ return subjects.get(index); }
    public OptionalSubject getOptionalSubject(int index){ return optionalSubjects.get(index); }
    public Professor getProfessor(int index){ return professors.get(index); }
    public Group getGroup(int index){ return groups.get(index); }
    public Series getSeries(int index){ return series.get(index); }

    // remove an element of a list
    public void removeCurriculum(int index){
        curricula.remove(index);
    }
    public void removeCurriculum(Curriculum curriculum){
        curricula.remove(curriculum);
    }

    public void removeStudent(int index){
        Student student = students.get(index);
        removeStudent(student);
    }
    public void removeStudent(Student student){
        Group group = student.getGroup();
        group.removeStudent(student);
        students.remove(student);
    }

    public void removeSubject(int index){
        Subject subject = getSubject(index);
        removeSubject(subject);
    }
    public void removeSubject(Subject subject){
        for(Student student : this.students) {
            student.removeSubject(subject);
        }
        for(Curriculum curriculum : this.curricula) {
            curriculum.getObligatory().remove(subject);
        }
        this.subjects.remove(subject);
    }

    public void removeOptionalSubject(int index){ optionalSubjects.remove(index); }
    public void removeOptionalSubject(OptionalSubject optionalSubject){ optionalSubjects.remove(optionalSubject); }

    public void removeProfessor(int index){
        Professor professor = getProfessor(index);
        removeProfessor(professor);
    }
    public void removeProfessor(Professor professor){
        for(Subject subject : this.subjects) {
            if(subject.getCourse().getProfessor() == professor){
                subject.getCourse().setProfessor(null);
            }
            if(subject.getSeminar().getProfessor() == professor){
                subject.getSeminar().setProfessor(null);
            }
            if(subject.getLaboratory().getProfessor() == professor){
                subject.getLaboratory().setProfessor(null);
            }
        }
        professors.remove(professor);
    }

    public void removeGroup(int index){
        Group group = getGroup(index);
        removeGroup(group);
    }
    public void removeGroup(Group group){
        TreeSet<Student> students = group.getStudents();
        for(Student student : students){
            student.setGroup(null);
            group.removeStudent(student);
        }
        this.groups.remove(group);
    }

    public void removeSeries(int index){
        Series series = getSeries(index);
        removeSeries(series);
    }
    public void removeSeries(Series series){
        TreeSet<Group> groups = series.getGroups();
        for(Group group : groups) {
            group.setSeries(null);
            series.removeGroup(group);
        }
        this.series.remove(series);
    }

    // add elements
    public void addCurriculum() {
        Curriculum newCurriculum = new Curriculum();
        newCurriculum.setCurriculum();
        curricula.add(newCurriculum);
    }

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

    public void printCurricula(){
        int i = 0;
        for (Curriculum curriculum : curricula) {
            System.out.println(++i + ": " + curriculum);
        }
    }

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

    // print specific items

    public void printGroupsOfSeries(Series series){
        TreeSet<Group> groups = series.getGroups();
        int i = 0;
        for(Group group : groups){
            System.out.println(++i + ": " + group);
        }
    }

    public void printStudentsOfGroup(Group group){
        TreeSet<Student> students = group.getStudents();
        int i = 0;
        for(Student student : students){
            System.out.println(++i + ": " + student);
        }
    }

    public void printSubjectsOfStudent(Student student){
        Set<Subject> subjects = student.getSubjects();
        int i = 0;
        for(Subject subject : subjects){
            System.out.println(++i + ": " + subject);
        }
    }

    public void printObligatorySubjectsOfCurriculum(Curriculum curriculum) {
        int i = 0;
        Set<Subject> subjects = curriculum.getObligatory();
        for (Subject subject : subjects) {
            System.out.println(++i + ": " + subject);
        }
    }

    public void printOptionalSubjectsOfCurriculum(Curriculum curriculum) {
        int i = 0;
        Set<OptionalSubject> optionalSubjects = curriculum.getOptional();
        for (OptionalSubject optionalSubject : optionalSubjects) {
            System.out.println(++i + ": " + optionalSubject);
        }
    }

    public void printAllSubjectsOfCurriculum(Curriculum curriculum){
        if(curriculum.getNumOfObligatory() > 0) {
            System.out.println("---- Obligatory ----");
            printObligatorySubjectsOfCurriculum(curriculum);
        }
        if(curriculum.getNumOfOptional() > 0) {
            System.out.println("---- Optional ----");
            printOptionalSubjectsOfCurriculum(curriculum);
        }
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                '}';
    }
}
