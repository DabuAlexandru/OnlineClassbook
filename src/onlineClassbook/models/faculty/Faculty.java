package onlineClassbook.models.faculty;

import onlineClassbook.models.curriculum.Curriculum;
import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.faculty.series.Series;
import onlineClassbook.models.person.professor.Professor;
import onlineClassbook.models.person.student.Student;
import onlineClassbook.models.subject.OptionalSubject;
import onlineClassbook.models.subject.Subject;

import java.util.*;

public class Faculty { // singleton

    public static class Specialization {
        private String name;
        private int budgetaryCapacity;
        private int taxCapacity;
        private int taxPrice;

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

    private String name;
    private Set<Specialization> specializations = new HashSet<>();

    LinkedHashSet<Curriculum> curricula = new LinkedHashSet<>();
    LinkedHashSet<Student> students = new LinkedHashSet<>();
    LinkedHashSet<Subject> subjects = new LinkedHashSet<>();
    LinkedHashSet<OptionalSubject> optionalSubjects = new LinkedHashSet<>();
    LinkedHashSet<Professor> professors = new LinkedHashSet<>();
    LinkedHashSet<Group> groups = new LinkedHashSet<>();
    LinkedHashSet<Series> series = new LinkedHashSet<>();

    public void setCurricula(List<Curriculum> curricula) {
        this.curricula = new LinkedHashSet<>(curricula);
    }

    public void setStudents(List<Student> students) {
        this.students = new LinkedHashSet<>(students);
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = new LinkedHashSet<>(subjects);
    }

    public void setOptionalSubjects(List<OptionalSubject> optionalSubjects) {
        this.optionalSubjects = new LinkedHashSet<>(optionalSubjects);
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = new LinkedHashSet<>(professors);
    }

    public void setGroups(List<Group> groups) {
        this.groups = new LinkedHashSet<>(groups);
    }

    public void setSeries(List<Series> series) {
        this.series = new LinkedHashSet<>(series);
    }

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

    public <Obj> Obj getObject(LinkedHashSet<Obj> collection, int index) {
        int i = 0;
        for(Obj obj : collection) {
            if(i == index){
                return obj;
            }
            i++;
        }
        return null;
    }

    // get an object of a list
    public Curriculum getCurriculum(int index){ return getObject(curricula, index); }
    public Student getStudent(int index){ return getObject(students, index); }
    public Subject getSubject(int index){ return getObject(subjects, index); }
    public OptionalSubject getOptionalSubject(int index){ return getObject(optionalSubjects, index); }
    public Professor getProfessor(int index){ return getObject(professors, index); }
    public Group getGroup(int index){ return getObject(groups, index); }
    public Series getSeries(int index){ return getObject(series, index); }

    // remove an element of a list
    public void removeCurriculum(Curriculum curriculum){
        curricula.remove(curriculum);
    }

    public void removeStudent(int index){
        Student student = getStudent(index);
        removeStudent(student);
    }
    public void removeStudent(Student student){
        Group group = student.getGroup();
        if(group != null) { group.removeStudent(student); }
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

    public void removeOptionalSubject(int index){
        OptionalSubject optionalSubject = getOptionalSubject(index);
        removeOptionalSubject(optionalSubject);
    }
    public void removeOptionalSubject(OptionalSubject optionalSubject){
        for(Student student : this.students) {
            student.removeSubject(optionalSubject);
        }
        for(Curriculum curriculum : this.curricula) {
            curriculum.getOptional().remove(optionalSubject);
        }
        this.optionalSubjects.remove(optionalSubject);
    }

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
        Series series = group.getSeries();
        if(series != null) { series.removeGroup(group); }
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
    public Curriculum addCurriculum() {
        Curriculum newCurriculum = new Curriculum();
        newCurriculum.setCurriculum();
        curricula.add(newCurriculum);
        return newCurriculum;
    }

    public Student addStudent(){
        Student newStudent = new Student();
        newStudent.setStudent();
        students.add(newStudent);
        return newStudent;
    }

    public Subject addSubject(){
        Subject newSubject = new Subject();
        newSubject.setSubject();
        subjects.add(newSubject);
        return newSubject;
    }

    public OptionalSubject addOptionalSubject(){
        OptionalSubject newOptionalSubject = new OptionalSubject();
        newOptionalSubject.setOptionalSubject();
        optionalSubjects.add(newOptionalSubject);
        return newOptionalSubject;
    }

    public Professor addProfessor(){
        Professor newProfessor = new Professor();
        newProfessor.setProfessor();
        professors.add(newProfessor);
        return newProfessor;
    }

    public Group addGroup(){
        Group newGroup = new Group();
        newGroup.setGroup();
        groups.add(newGroup);
        return newGroup;
    }

    public Series addSeries() {
        Series newSeries = new Series();
        newSeries.setSeries();
        series.add(newSeries);
        return newSeries;
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

    public void printGroupsOfSeriesByID(Series series){
        List<Group> groups = new ArrayList<>(series.getGroups());
        groups.sort(Comparator.comparing(Group::getGroupID));
        for(Group group : groups){
            System.out.println(group.getGroupID() + ": " + group);
        }
    }

    public void printStudentsOfGroupByID(Group group){
        List<Student> students = new ArrayList<>(group.getStudents());
        students.sort(Comparator.comparing(Student::getPersonID));
        for(Student student : students){
            System.out.println(student.getPersonID() + ": " + student);
        }
    }

    public void printSubjectsOfStudentByID(Student student){
        List<Subject> subjects = new ArrayList<>(student.getSubjects());
        subjects.sort(Comparator.comparing(Subject::getSubjectID));
        for(Subject subject : subjects){
            System.out.println(subject.getSubjectID() + ": " + subject);
        }
    }

    public void printObligatorySubjectsOfCurriculumByID(Curriculum curriculum) {
        List<Subject> subjects = new ArrayList<>(curriculum.getObligatory());
        subjects.sort(Comparator.comparing(Subject::getSubjectID));
        for(Subject subject : subjects){
            System.out.println(subject.getSubjectID() + ": " + subject);
        }
    }

    public void printOptionalSubjectsOfCurriculumByID(Curriculum curriculum) {
        List<OptionalSubject> optionalSubjects = new ArrayList<>(curriculum.getOptional());
        optionalSubjects.sort(Comparator.comparing(Subject::getSubjectID));
        for (OptionalSubject optionalSubject : optionalSubjects) {
            System.out.println(optionalSubject.getSubjectID() + ": " + optionalSubject);
        }
    }

    // get specific items

    public Subject getSubjectOfStudent(Student student, int subjectID) {
        return student.getSubjectByID(subjectID);
    }

    public Student getStudentOfGroup(Group group, int personID) {
        return group.getStudentByID(personID);
    }

    public Group getGroupOfSeries(Series series, int groupID) {
        return series.getGroupByID(groupID);
    }

    public Subject getObligatoryOfCurriculum(Curriculum curriculum, int subjectID) {
        return curriculum.getObligatoryByID(subjectID);
    }

    public OptionalSubject getOptionalOfCurriculum(Curriculum curriculum, int subjectID) {
        return curriculum.getOptionalByID(subjectID);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                '}';
    }
}
