package onlineClassbook.menu;

import onlineClassbook.models.curriculum.Curriculum;
import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.faculty.series.Series;
import onlineClassbook.models.person.professor.Professor;
import onlineClassbook.models.person.student.Student;
import onlineClassbook.models.subject.OptionalSubject;
import onlineClassbook.models.subject.Subject;

import java.io.IOException;

public class CSVMenu extends Menu {

    public CSVMenu() throws IOException {
        super();
        readData();
    }

    public void readData() throws IOException {
        faculty.setStudents(readerWriter.readFromCSV("Student", "src/onlineClassbook/csvFiles/persistence/Student.csv"));
        faculty.setProfessors(readerWriter.readFromCSV("Professor", "src/onlineClassbook/csvFiles/persistence/Professor.csv"));
        faculty.setSubjects(readerWriter.readFromCSV("Subject", "src/onlineClassbook/csvFiles/persistence/Subject.csv"));
        faculty.setOptionalSubjects(readerWriter.readFromCSV("OptionalSubject", "src/onlineClassbook/csvFiles/persistence/OptionalSubject.csv"));
        faculty.setGroups(readerWriter.readFromCSV("Group", "src/onlineClassbook/csvFiles/persistence/Group.csv"));
        faculty.setSeries(readerWriter.readFromCSV("Series", "src/onlineClassbook/csvFiles/persistence/Series.csv"));
        faculty.setCurricula(readerWriter.readFromCSV("Curriculum", "src/onlineClassbook/csvFiles/persistence/Curriculum.csv"));
    }

    public void rollback() throws IOException {
        audit.writeToAudit("Rollback database to the backup version");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Student.csv", "src/onlineClassbook/csvFiles/persistence/Student.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Professor.csv", "src/onlineClassbook/csvFiles/persistence/Professor.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Subject.csv", "src/onlineClassbook/csvFiles/persistence/Subject.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/OptionalSubject.csv", "src/onlineClassbook/csvFiles/persistence/OptionalSubject.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Group.csv", "src/onlineClassbook/csvFiles/persistence/Group.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Series.csv", "src/onlineClassbook/csvFiles/persistence/Series.csv");
        readerWriter.transferData("src/onlineClassbook/csvFiles/backup/Curriculum.csv", "src/onlineClassbook/csvFiles/persistence/Curriculum.csv");
        readData();
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
                            9. Rollback to backup database
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
            } else if (option == 9) { // Rollback to backup database
                rollback();
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
        System.out.println("\nChoose a student:");
        faculty.printStudentsOfGroupByID(group);
        System.out.print("personID: ");
        int personID = myInput.nextInt();
        return faculty.getStudentOfGroup(group, personID);
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
        System.out.println("Choose a subject:");
        faculty.printSubjectsOfStudentByID(student);
        System.out.print("subjectID: ");
        int subjectID = myInput.nextInt();
        return faculty.getSubjectOfStudent(student, subjectID);
    }

    private Subject chooseSubject(Curriculum curriculum){
        System.out.println("Choose a subject:");
        faculty.printObligatorySubjectsOfCurriculumByID(curriculum);
        System.out.print("subjectID: ");
        int subjectID = myInput.nextInt();
        return faculty.getObligatoryOfCurriculum(curriculum, subjectID);
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
        System.out.println("Choose an optional subject:");
        faculty.printOptionalSubjectsOfCurriculumByID(curriculum);
        System.out.print("subjectID: ");
        int subjectID = myInput.nextInt();
        return faculty.getOptionalOfCurriculum(curriculum, subjectID);
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
        System.out.println("Choose a group:");
        faculty.printGroupsOfSeriesByID(series);
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        return faculty.getGroupOfSeries(series, groupID);
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
                audit.writeToAudit("Add Student to list");
                readerWriter.writeToCSV(student, "src/onlineClassbook/csvFiles/persistence/Student.csv");
            } else if (option == 2) { // Add a new subject
                Subject subject = faculty.addSubject();
                audit.writeToAudit("Add Subject to list");
                readerWriter.writeToCSV(subject, "src/onlineClassbook/csvFiles/persistence/Subject.csv");
            } else if (option == 3) { // Add a new optional subject
                OptionalSubject optionalSubject = faculty.addOptionalSubject();
                audit.writeToAudit("Add OptionalSubject to list");
                readerWriter.writeToCSV(optionalSubject, "src/onlineClassbook/csvFiles/persistence/OptionalSubject.csv");
            } else if (option == 4) { // Add a new professor
                Professor professor = faculty.addProfessor();
                audit.writeToAudit("Add Professor to list");
                readerWriter.writeToCSV(professor, "src/onlineClassbook/csvFiles/persistence/Professor.csv");
            } else if (option == 5) { // Add a new group
                Group group = faculty.addGroup();
                audit.writeToAudit("Add Group to list");
                readerWriter.writeToCSV(group, "src/onlineClassbook/csvFiles/persistence/Group.csv");
            } else if (option == 6) { // Add a new series
                Series series = faculty.addSeries();
                audit.writeToAudit("Add Series to list");
                readerWriter.writeToCSV(series, "src/onlineClassbook/csvFiles/persistence/Series.csv");
            } else if (option == 7) { // Add a new curriculum
                Curriculum curriculum = faculty.addCurriculum();
                audit.writeToAudit("Add Curriculum to list");
                readerWriter.writeToCSV(curriculum, "src/onlineClassbook/csvFiles/persistence/Curriculum.csv");
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
                        audit.writeToAudit("Add Subject to Student");
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
                            audit.writeToAudit("Set Professor to Course of Subject");
                        } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                            subject.getSeminar().setProfessor(professor);
                            audit.writeToAudit("Set Professor to Seminar of Subject");
                        } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                            subject.getLaboratory().setProfessor(professor);
                            audit.writeToAudit("Set Professor to Laboratory of Subject");
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
                        audit.writeToAudit("Add Student to Group");
                    }
                }
            } else if (option == 4) { // Set a series to a group
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup();
                    if(group != null) {
                        series.addGroup(group);
                        audit.writeToAudit("Set Series to a Group");
                    }
                }
            } else if (option == 5) { // Add a subject to a curriculum
                Subject subject = chooseSubject();
                if(subject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addObligatory(subject);
                        audit.writeToAudit("Add Subject to Curriculum");
                    }
                }
            } else if (option == 6) { // Add an optional subject to a curriculum
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    Curriculum curriculum = chooseCurriculum();
                    if(curriculum != null) {
                        curriculum.addOptional(optionalSubject);
                        audit.writeToAudit("Add OptionalSubject to Curriculum");
                    }
                }
            } else if (option == 7) { // Set a curriculum to a student
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                        audit.writeToAudit("Set curriculum to student");
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
                        audit.writeToAudit("Remove Subject from Student");
                    }
                }
            } else if (option == 2) { // Remove a professor from a study class of a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    System.out.println("Choose one (course/seminar/laboratory):");
                    String choice = myInput.next();
                    if ("course".contains(choice.toLowerCase())) {
                        subject.getCourse().setProfessor(null);
                        audit.writeToAudit("Remove Professor from Course of Subject");
                    } else if (subject.getSeminar().isValid() && "seminar".contains(choice.toLowerCase())) {
                        subject.getSeminar().setProfessor(null);
                        audit.writeToAudit("Remove Professor from Seminar of Subject");
                    } else if (subject.getLaboratory().isValid() && "laboratory".contains(choice.toLowerCase())) {
                        subject.getLaboratory().setProfessor(null);
                        audit.writeToAudit("Remove Professor from Laboratory of Subject");
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
                        audit.writeToAudit("Remove Student from Group");
                    }
                }
            } else if (option == 4) { // Remove a group from a series
                Series series = chooseSeries();
                if(series != null) {
                    Group group = chooseGroup(series);
                    if(group != null) {
                        series.removeGroup(group);
                        audit.writeToAudit("Remove Group from Series");
                    }
                }
            } else if (option == 5) { // Remove a subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Subject subject = chooseSubject(curriculum);
                    if(subject != null) {
                        curriculum.removeObligatory(subject);
                        audit.writeToAudit("Remove Subject from Curriculum");
                    }
                }
            } else if (option == 6) { // Remove an optional subject from a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    OptionalSubject optionalSubject = chooseOptionalSubject(curriculum);
                    if(optionalSubject != null) {
                        curriculum.removeOptional(optionalSubject);
                        audit.writeToAudit("Remove OptionalSubject from Curriculum");
                    }
                }
            } else if (option == 7) { // Remove a curriculum from a student
                // ... TO DO
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    Student student = chooseStudent();
                    if(student != null) {
                        curriculum.appendCurriculum(student);
                        audit.writeToAudit("Remove Curriculum from Student");
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
                    audit.writeToAudit("Print Groups of Series in sorted matter");
                }
            } else if (option == 2) { // Print all students of a group in a sorted matter
                Group group = chooseGroup();
                if(group != null) {
                    faculty.printStudentsOfGroup(group);
                    audit.writeToAudit("Print Students of Group in sorted matter");
                }
            } else if (option == 3) { // Print all subjects of a student
                Student student = chooseStudent();
                if(student != null) {
                    faculty.printSubjectsOfStudent(student);
                    audit.writeToAudit("Print Subjects of Student");
                }
            } else if (option == 4) { // Print all subjects of a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.printAllSubjectsOfCurriculum(curriculum);
                    audit.writeToAudit("Print Subjects and OptionalSubjects of Curriculum");
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
                    audit.writeToAudit("Remove Student from list");
                }
            } else if (option == 2) { // Remove a subject
                Subject subject = chooseSubject();
                if(subject != null) {
                    faculty.removeSubject(subject);
                    audit.writeToAudit("Remove Subject from list");
                }
            } else if (option == 3) { // Remove an optional subject
                OptionalSubject optionalSubject = chooseOptionalSubject();
                if(optionalSubject != null) {
                    faculty.removeOptionalSubject(optionalSubject);
                    audit.writeToAudit("Remove OptionalSubject from list");
                }
            } else if (option == 4) { // Remove a professor
                Professor professor = chooseProfessor();
                if(professor != null) {
                    faculty.removeProfessor(professor);
                    audit.writeToAudit("Remove Professor from list");
                }
            } else if (option == 5) { // Remove a group
                Group group = chooseGroup();
                if(group != null) {
                    faculty.removeGroup(group);
                    audit.writeToAudit("Remove Group from list");
                }
            } else if (option == 6) { // Remove a series
                Series series = chooseSeries();
                if(series != null) {
                    faculty.removeSeries(series);
                    audit.writeToAudit("Remove Series from list");
                }
            } else if (option == 7) { // Remove a curriculum
                Curriculum curriculum = chooseCurriculum();
                if(curriculum != null) {
                    faculty.removeCurriculum(curriculum);
                    audit.writeToAudit("Remove Curriculum from list");
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
                audit.writeToAudit("Print Students from list");
            } else if (option == 2) { // Print all subjects
                faculty.printSubjects();
                audit.writeToAudit("Print Subjects from list");
            } else if (option == 3) { // Print all optional subjects
                faculty.printOptionalSubjects();
                audit.writeToAudit("Print OptionalSubjects from list");
            } else if (option == 4) { // Print all professors
                faculty.printProfessors();
                audit.writeToAudit("Print Professors from list");
            } else if (option == 5) { // Print all groups
                faculty.printGroups();
                audit.writeToAudit("Print Groups from list");
            } else if (option == 6) { // Print all series
                faculty.printSeries();
                audit.writeToAudit("Print Series from list");
            } else if (option == 7) { // Print all curricula
                faculty.printCurricula();
                audit.writeToAudit("Print Curricula from list");
            }
            printSpaces(3);
        }
    }
}
