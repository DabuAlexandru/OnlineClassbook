package service;

import curriculum.Curriculum;
import faculty.Faculty;
import faculty.group.Group;
import faculty.series.Series;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.util.Scanner;

public class Menu {

    Faculty faculty;
    Scanner myInput;
    int option;

    public Menu(){
        faculty = Faculty.getFaculty("FMI");
        faculty.addSpecialization("Mathematics", 100, 50, 1000);
        faculty.addSpecialization("Informatics", 200, 50, 1500);
        myInput = new Scanner(System.in);
        option = 0;
    }

    public static void printSpaces(int n) {
        for(int i = 0; i < n; i++){
            System.out.print('\n');
        }
    }

    public void principalMenu(){
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

    public void addElementsMenu(){
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
                faculty.addStudent();
            } else if (option == 2) { // Add a new subject
                faculty.addSubject();
            } else if (option == 3) { // Add a new optional subject
                faculty.addOptionalSubject();
            } else if (option == 4) { // Add a new professor
                faculty.addProfessor();
            } else if (option == 5) { // Add a new group
                faculty.addGroup();
            } else if (option == 6) { // Add a new series
                faculty.addSeries();
            } else if (option == 7) { // Add a new curriculum
                faculty.addCurriculum();
            }
            printSpaces(3);
        }
    }

    public void addInformationMenu(){
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
                        } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                            subject.getSeminar().setProfessor(professor);
                        } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                            subject.getLaboratory().setProfessor(professor);
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
                    }
                }
            } else if (option == 4) { // Set a series to a group
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup();
                    if(group != null) {
                        series.addGroup(group);
                    }
                }
            } else if (option == 5) { // Add a subject to a curriculum
                Subject subject = chooseSubject();
                if(subject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addObligatory(subject);
                    }
                }
            } else if (option == 6) { // Add an optional subject to a curriculum
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addOptional(optionalSubject);
                    }
                }
            } else if (option == 7) { // Set a curriculum to a student
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                    }
                }
            }
            printSpaces(3);
        }
    }

    public void removeInformationMenu(){
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
                    }
                }
            } else if (option == 2) { // Remove a professor from a study class of a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    System.out.println("Choose one (course/seminar/laboratory):");
                    String choice = myInput.next();
                    if ("course".contains(choice.toLowerCase())) {
                        subject.getCourse().setProfessor(null);
                    } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                        subject.getSeminar().setProfessor(null);
                    } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                        subject.getLaboratory().setProfessor(null);
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
                    }
                }
            } else if (option == 4) { // Remove a group from a series
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup(series);
                    if(group != null) {
                        series.removeGroup(group);
                    }
                }
            } else if (option == 5) { // Remove a subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Subject subject = chooseSubject(curriculum);
                    if(subject != null) {
                        curriculum.removeObligatory(subject);
                    }
                }
            } else if (option == 6) { // Remove an optional subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    OptionalSubject optionalSubject = chooseOptionalSubject(curriculum);
                    if(optionalSubject != null) {
                        curriculum.removeOptional(optionalSubject);
                    }
                }
            } else if (option == 7) { // Remove a curriculum from a student
                // ... TO DO
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                    }
                }
            }
            printSpaces(3);
        }
    }

    public void showSpecificInformationMenu(){
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
                }
            } else if (option == 2) { // Print all students of a group in a sorted matter
                Group group = chooseGroup();
                if(group != null) {
                    faculty.printStudentsOfGroup(group);
                }
            } else if (option == 3) { // Print all subjects of a student
                Student student = chooseStudent();
                if(student != null) {
                    faculty.printSubjectsOfStudent(student);
                }
            } else if (option == 4) { // Print all subjects of a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.printAllSubjectsOfCurriculum(curriculum);
                }
            }
            printSpaces(3);
        }
    }

    public void removeElementsMenu(){
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
                }
            } else if (option == 2) { // Remove a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    faculty.removeSubject(subject);
                }
            } else if (option == 3) { // Remove an optional subject
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    faculty.removeOptionalSubject(optionalSubject);
                }
            } else if (option == 4) { // Remove a professor
                Professor professor = chooseProfessor();
                if(professor != null) {
                    faculty.removeProfessor(professor);
                }
            } else if (option == 5) { // Remove a group
                Group group = chooseGroup();
                if(group != null) {
                    faculty.removeGroup(group);
                }
            } else if (option == 6) { // Remove a series
                Series series = chooseSeries();
                if(series != null) {
                    faculty.removeSeries(series);
                }
            } else if (option == 7) { // Remove a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.removeCurriculum(curriculum);
                }
            }
            printSpaces(3);
        }
    }

    public void showElementsMenu(){
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
            } else if (option == 2) { // Print all subjects
                faculty.printSubjects();
            } else if (option == 3) { // Print all optional subjects
                faculty.printOptionalSubjects();
            } else if (option == 4) { // Print all professors
                faculty.printProfessors();
            } else if (option == 5) { // Print all groups
                faculty.printGroups();
            } else if (option == 6) { // Print all series
                faculty.printSeries();
            } else if (option == 7) { // Print all curricula
                faculty.printCurricula();
            }
            printSpaces(3);
        }
    }
}
