package service;

import curriculum.Curriculum;
import faculty.Faculty;
import faculty.group.Group;
import faculty.series.Series;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    Faculty faculty;
    ReaderWriter readerWriter;
    Scanner myInput;
    int option;

    public Menu() throws IOException {
        faculty = Faculty.getFaculty("FMI");
        readerWriter = ReaderWriter.getReaderWriter();
        faculty.addSpecialization("Mathematics", 100, 50, 1000);
        faculty.addSpecialization("Informatics", 200, 50, 1500);
        myInput = new Scanner(System.in);
        faculty.setStudents(readerWriter.readFromCSV("student", "src/database/Student.CSV"));
        faculty.setProfessors(readerWriter.readFromCSV("professor", "src/database/Professor.CSV"));
        faculty.setSubjects(readerWriter.readFromCSV("subject", "src/database/Subject.CSV"));
        faculty.setOptionalSubjects(readerWriter.readFromCSV("optional subject", "src/database/OptionalSubject.CSV"));
        faculty.setGroups(readerWriter.readFromCSV("group", "src/database/Group.CSV"));
        faculty.setSeries(readerWriter.readFromCSV("series", "src/database/Series.CSV"));
        faculty.setCurricula(readerWriter.readFromCSV("curriculum", "src/database/Curriculum.CSV"));
        option = 0;
    }

    public static void printSpaces(int n) {
        for(int i = 0; i < n; i++){
            System.out.print('\n');
        }
    }

    public void principalMenu() throws IOException {
        int option;
        while (true) {
            System.out.println(
                    """
                            Online Classbook:
                            1. Add a new item in the database
                            2. Add some information for an existing item
                            3. Remove some information from an existing item
                            4. Show specific information of an item
                            5. Remove an item from the database
                            6. Show all elements of a category
                            0. Exit
                            """
            );

            option = myInput.nextInt();
            if (option == 0) { // exit the loop
                break;
            } else if (option == 1) { // Add a new item in the database
                addElementsMenu();
            } else if (option == 2) { // Add some information for an existing item
                addInformationMenu();
            } else if (option == 3) { // Remove some information from an existing item
                removeInformationMenu();
            } else if (option == 4) { // Show specific information of an item
                showSpecificInformationMenu();
            } else if (option == 5) { // Remove an item from the database
                removeElementsMenu();
            } else if (option == 6) { // Show all elements of a category
                showElementsMenu();
            }
            printSpaces(8);
        }
    }

    private int getValidIndex(int n) {
        int index;
        if (n == 0) {
            System.out.println("No items available");
            return -1;
        }
        do {
            System.out.print("index: ");
            index = myInput.nextInt() - 1;
        } while (index < 0 || index > n - 1);
        return index;
    }

    private Curriculum chooseCurriculum(){
        int n, index;
        System.out.println("\nChoose a curriculum:");
        faculty.printCurricula();
        n = faculty.getNumOfCurricula();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getCurriculum(index);
    }

    private Student chooseStudent(){
        int n, index;
        System.out.println("\nChoose a student:");
        faculty.printStudents();
        n = faculty.getNumOfStudents();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getStudent(index);
    }

    private Student chooseStudent(Group group) {
        int n, index;
        System.out.println("\nChoose a student:");
        faculty.printStudentsOfGroup(group);
        n = group.getNumOfStudents();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getStudentOfGroup(group, index);
    }

    private Subject chooseSubject(){
        int n, index;
        System.out.println("Choose a subject:");
        faculty.printSubjects();
        n = faculty.getNumOfSubjects();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getSubject(index);
    }

    private Subject chooseSubject(Student student){
        int n, index;
        System.out.println("Choose a subject:");
        faculty.printSubjectsOfStudent(student);
        n = student.getNumOfSubjects();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getSubjectOfStudent(student, index);
    }

    private Subject chooseSubject(Curriculum curriculum){
        int n, index;
        System.out.println("Choose a subject:");
        faculty.printObligatorySubjectsOfCurriculum(curriculum);
        n = curriculum.getNumOfObligatory();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getObligatoryOfCurriculum(curriculum, index);
    }

    private OptionalSubject chooseOptionalSubject(){
        int n, index;
        System.out.println("Choose an optional subject:");
        faculty.printOptionalSubjects();
        n = faculty.getNumOfOptionalSubjects();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getOptionalSubject(index);
    }

    private OptionalSubject chooseOptionalSubject(Curriculum curriculum){
        int n, index;
        System.out.println("Choose an optional subject:");
        faculty.printOptionalSubjectsOfCurriculum(curriculum);
        n = curriculum.getNumOfOptional();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getOptionalOfCurriculum(curriculum, index);
    }

    private Professor chooseProfessor(){
        int n, index;
        System.out.println("Choose a professor:");
        faculty.printProfessors();
        n = faculty.getNumOfSubjects();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getProfessor(index);
    }

    private Group chooseGroup(){
        int n, index;
        System.out.println("Choose a group:");
        faculty.printGroups();
        n = faculty.getNumOfGroups();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getGroup(index);
    }

    private Group chooseGroup(Series series){
        int n, index;
        System.out.println("Choose a group:");
        faculty.printGroupsOfSeries(series);
        n = series.getNumOfGroups();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getGroupOfSeries(series, index);
    }

    private Series chooseSeries(){
        int n, index;
        System.out.println("Choose a series:");
        faculty.printSeries();
        n = faculty.getNumOfSeries();
        index = getValidIndex(n);
        if(index == -1) { return null; }
        return faculty.getSeries(index);
    }

    public void addElementsMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add a new item in the database
                            1. Add a new student
                            2. Add a new subject
                            3. Add a new optional subject
                            4. Add a new professor
                            5. Add a new group
                            6. Add a new series
                            7. Add a new curriculum
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) { // back to the start menu
                break;
            } else if (option == 1) { // Add a new student
                Student student = faculty.addStudent();
                readerWriter.writeToAudit("Add Student to list");
                readerWriter.writeToCSV(student, "src/database/Student.CSV", false);
            } else if (option == 2) { // Add a new subject
                Subject subject = faculty.addSubject();
                readerWriter.writeToAudit("Add Subject to list");
                readerWriter.writeToCSV(subject, "src/database/Subject.CSV", false);
            } else if (option == 3) { // Add a new optional subject
                OptionalSubject optionalSubject = faculty.addOptionalSubject();
                readerWriter.writeToAudit("Add OptionalSubject to list");
                readerWriter.writeToCSV(optionalSubject, "src/database/OptionalSubject.CSV", false);
            } else if (option == 4) { // Add a new professor
                Professor professor = faculty.addProfessor();
                readerWriter.writeToAudit("Add Professor to list");
                readerWriter.writeToCSV(professor, "src/database/Professor.CSV", false);
            } else if (option == 5) { // Add a new group
                Group group = faculty.addGroup();
                readerWriter.writeToAudit("Add Group to list");
                readerWriter.writeToCSV(group, "src/database/Group.CSV", false);
            } else if (option == 6) { // Add a new series
                Series series = faculty.addSeries();
                readerWriter.writeToAudit("Add Series to list");
                readerWriter.writeToCSV(series, "src/database/Series.CSV", false);
            } else if (option == 7) { // Add a new curriculum
                Curriculum curriculum = faculty.addCurriculum();
                readerWriter.writeToAudit("Add Curriculum to list");
                readerWriter.writeToCSV(curriculum, "src/database/Curriculum.CSV", false);
            }
            printSpaces(3);
        }
    }

    public void addInformationMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add some information for an existing item
                            1. Add a subject to a student
                            2. Set a professor to a study class of a subject
                            3. Set a group to a student
                            4. Set a series to a group
                            5. Add a subject to a curriculum
                            6. Add an optional subject to a curriculum
                            7. Set a curriculum to a student
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Add a subject to a student
                Subject subject = chooseSubject();
                if(subject != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        student.addSubject(subject);
                        readerWriter.writeToAudit("Add Subject to Student");
                    }
                }
            } else if (option == 2) { // Set a professor to a study class of a subject
                Professor professor = chooseProfessor();
                if(professor != null) {
                    Subject subject = chooseSubject();
                    if(subject != null) {
                        System.out.println("Choose one (course/seminar/laboratory):");
                        String choice = myInput.next();
                        if ("course".contains(choice.toLowerCase())) {
                            subject.getCourse().setProfessor(professor);
                            readerWriter.writeToAudit("Set Professor to Course of Subject");
                        } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                            subject.getSeminar().setProfessor(professor);
                            readerWriter.writeToAudit("Set Professor to Seminar of Subject");
                        } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                            subject.getLaboratory().setProfessor(professor);
                            readerWriter.writeToAudit("Set Professor to Laboratory of Subject");
                        } else {
                            System.out.println("Invalid input!!!");
                        }
                    }
                }
            } else if (option == 3) { // Add a student to a group
                Group group = chooseGroup();
                if(group != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        group.addStudent(student);
                        readerWriter.writeToAudit("Add Student to Group");
                    }
                }
            } else if (option == 4) { // Set a series to a group
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup();
                    if(group != null) {
                        series.addGroup(group);
                        readerWriter.writeToAudit("Set Series to a Group");
                    }
                }
            } else if (option == 5) { // Add a subject to a curriculum
                Subject subject = chooseSubject();
                if(subject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addObligatory(subject);
                        readerWriter.writeToAudit("Add Subject to Curriculum");
                    }
                }
            } else if (option == 6) { // Add an optional subject to a curriculum
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addOptional(optionalSubject);
                        readerWriter.writeToAudit("Add OptionalSubject to Curriculum");
                    }
                }
            } else if (option == 7) { // Set a curriculum to a student
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                        readerWriter.writeToAudit("Set curriculum to student");
                    }
                }
            }
            printSpaces(3);
        }
    }

    public void removeInformationMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add some information for an existing item
                            1. Remove a subject from a student
                            2. Remove a professor from a study class of a subject
                            3. Remove a student from a group
                            4. Remove a group from a series
                            5. Remove a subject from a curriculum
                            6. Remove an optional subject from a curriculum
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Remove a subject from a student
                Student student = chooseStudent();
                if(student != null) {
                    Subject subject = chooseSubject(student);
                    if(subject != null) {
                        student.removeSubject(subject);
                        readerWriter.writeToAudit("Remove Subject from Student");
                    }
                }
            } else if (option == 2) { // Remove a professor from a study class of a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    System.out.println("Choose one (course/seminar/laboratory):");
                    String choice = myInput.next();
                    if ("course".contains(choice.toLowerCase())) {
                        subject.getCourse().setProfessor(null);
                        readerWriter.writeToAudit("Remove Professor from Course of Subject");
                    } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                        subject.getSeminar().setProfessor(null);
                        readerWriter.writeToAudit("Remove Professor from Seminar of Subject");
                    } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                        subject.getLaboratory().setProfessor(null);
                        readerWriter.writeToAudit("Remove Professor from Laboratory of Subject");
                    } else {
                        System.out.println("Invalid input!!!");
                    }
                }
            } else if (option == 3) { // Remove a student from a group
                Group group = chooseGroup();
                if(group != null) {
                    Student student = chooseStudent(group);
                    if(student != null) {
                        group.removeStudent(student);
                        readerWriter.writeToAudit("Remove Student from Group");
                    }
                }
            } else if (option == 4) { // Remove a group from a series
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup(series);
                    if(group != null) {
                        series.removeGroup(group);
                        readerWriter.writeToAudit("Remove Group from Series");
                    }
                }
            } else if (option == 5) { // Remove a subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Subject subject = chooseSubject(curriculum);
                    if(subject != null) {
                        curriculum.removeObligatory(subject);
                        readerWriter.writeToAudit("Remove Subject from Curriculum");
                    }
                }
            } else if (option == 6) { // Remove an optional subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    OptionalSubject optionalSubject = chooseOptionalSubject(curriculum);
                    if(optionalSubject != null) {
                        curriculum.removeOptional(optionalSubject);
                        readerWriter.writeToAudit("Remove OptionalSubject from Curriculum");
                    }
                }
            } else if (option == 7) { // Remove a curriculum from a student
                // ... TO DO
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                        readerWriter.writeToAudit("Remove Curriculum from Student");
                    }
                }
            }
            printSpaces(3);
        }
    }

    public void showSpecificInformationMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add some information for an existing item
                            1. Print all groups of a series in a sorted matter (by name)
                            2. Print all students of a group in a sorted matter (by firstName and lastName)
                            3. Print all subjects of a student
                            4. Print all subjects of a curriculum
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Print all groups of a series in a sorted matter
                Series series = chooseSeries();
                if(series != null) {
                    faculty.printGroupsOfSeries(series);
                    readerWriter.writeToAudit("Print Groups of Series in sorted matter");
                }
            } else if (option == 2) { // Print all students of a group in a sorted matter
                Group group = chooseGroup();
                if(group != null) {
                    faculty.printStudentsOfGroup(group);
                    readerWriter.writeToAudit("Print Students of Group in sorted matter");
                }
            } else if (option == 3) { // Print all subjects of a student
                Student student = chooseStudent();
                if(student != null) {
                    faculty.printSubjectsOfStudent(student);
                    readerWriter.writeToAudit("Print Subjects of Student");
                }
            } else if (option == 4) { // Print all subjects of a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.printAllSubjectsOfCurriculum(curriculum);
                    readerWriter.writeToAudit("Print Subjects and OptionalSubjects of Curriculum");
                }
            }
            printSpaces(3);
        }
    }

    public void removeElementsMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add a new item in the database
                            1. Remove a student
                            2. Remove a subject
                            3. Remove an optional subject
                            4. Remove a professor
                            5. Remove a group
                            6. Remove a series
                            7. Remove a curriculum
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) { // back to the start menu
                break;
            } else if (option == 1) { // Remove a student
                Student student = chooseStudent();
                if(student != null) {
                    faculty.removeStudent(student);
                    readerWriter.writeToAudit("Remove Student from list");
                }
            } else if (option == 2) { // Remove a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    faculty.removeSubject(subject);
                    readerWriter.writeToAudit("Remove Subject from list");
                }
            } else if (option == 3) { // Remove an optional subject
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    faculty.removeOptionalSubject(optionalSubject);
                    readerWriter.writeToAudit("Remove OptionalSubject from list");
                }
            } else if (option == 4) { // Remove a professor
                Professor professor = chooseProfessor();
                if(professor != null) {
                    faculty.removeProfessor(professor);
                    readerWriter.writeToAudit("Remove Professor from list");
                }
            } else if (option == 5) { // Remove a group
                Group group = chooseGroup();
                if(group != null) {
                    faculty.removeGroup(group);
                    readerWriter.writeToAudit("Remove Group from list");
                }
            } else if (option == 6) { // Remove a series
                Series series = chooseSeries();
                if(series != null) {
                    faculty.removeSeries(series);
                    readerWriter.writeToAudit("Remove Series from list");
                }
            } else if (option == 7) { // Remove a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.removeCurriculum(curriculum);
                    readerWriter.writeToAudit("Remove Curriculum from list");
                }
            }
            printSpaces(3);
        }
    }

    public void showElementsMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Add some information for an existing item
                            1. Print all students
                            2. Print all subjects
                            3. Print all optional subjects
                            4. Print all professors
                            5. Print all groups
                            6. Print all series
                            7. Print all curricula
                            0. back to the start menu
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Print all students
                faculty.printStudents();
                readerWriter.writeToAudit("Print Students from list");
            } else if (option == 2) { // Print all subjects
                faculty.printSubjects();
                readerWriter.writeToAudit("Print Subjects from list");
            } else if (option == 3) { // Print all optional subjects
                faculty.printOptionalSubjects();
                readerWriter.writeToAudit("Print OptionalSubjects from list");
            } else if (option == 4) { // Print all professors
                faculty.printProfessors();
                readerWriter.writeToAudit("Print Professors from list");
            } else if (option == 5) { // Print all groups
                faculty.printGroups();
                readerWriter.writeToAudit("Print Groups from list");
            } else if (option == 6) { // Print all series
                faculty.printSeries();
                readerWriter.writeToAudit("Print Series from list");
            } else if (option == 7) { // Print all curricula
                faculty.printCurricula();
                readerWriter.writeToAudit("Print Curricula from list");
            }
            printSpaces(3);
        }
    }
}
