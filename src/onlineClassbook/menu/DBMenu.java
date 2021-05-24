package onlineClassbook.menu;

import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.faculty.series.Series;
import onlineClassbook.models.person.student.Student;
import onlineClassbook.repository.GroupRepository;
import onlineClassbook.repository.PersonRepository;
import onlineClassbook.repository.SeriesRepository;
import onlineClassbook.repository.StudentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DBMenu extends Menu {

    public static final SeriesRepository seriesRepository = SeriesRepository.getSeriesRepository();
    public static final GroupRepository groupRepository = GroupRepository.getGroupRepository();
    public static final PersonRepository personRepository = PersonRepository.getPersonRepository();
    public static final StudentRepository studentRepository = StudentRepository.getStudentRepository();

    public DBMenu() throws IOException {
        super();
    }

    public void principalMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Online Classbook:
                            1. Series menu
                            2. Group menu
                            3. Person menu
                            4. Student menu
                            0. exit
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            }
            switch (option) {
                case 1 -> seriesMenu();
                case 2 -> groupMenu();
                case 3 -> personMenu();
                case 4 -> studentMenu();
            }
        }
    }

    public void seriesMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Series DB context
                            1. Insert a series to DB
                            2. Fetch a series from DB
                            3. Update a series name
                            4. Delete a series
                            5. Print all series
                            6. Print all groups of series by id
                            0. exit
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Insert a series to DB
                insertSeries();
            } else if (option == 2) { // Fetch a series from DB
                System.out.println(fetchSeries());
            } else if (option == 3) { // Update a series name
                updateSeriesName();
            } else if (option == 4) { // Delete a series
                deleteSeries();
            } else if (option == 5) { // Print all series
                System.out.println(seriesRepository.getStringAllSeries());
            } else if (option == 6) { // Print all groups of series by id
                getGroupsOfSeries();
            }
            printSpaces(3);
        }
    }

    private void insertSeries() {
        Series series = new Series();
        series.setSeries();
        seriesRepository.insertSeries(series);
    }

    private Series fetchSeries() {
        System.out.println(seriesRepository.getStringAllSeries());
        System.out.print("seriesID: ");
        int seriesID = myInput.nextInt();
        return seriesRepository.getSeriesById(seriesID);
    }

    private void updateSeriesName() {
        System.out.println(seriesRepository.getStringAllSeries());
        System.out.print("seriesID: ");
        int seriesID = myInput.nextInt();
        System.out.print("new name: ");
        String name = myInput.next();
        seriesRepository.updateSeriesName(seriesID, name);
    }

    private void deleteSeries() {
        System.out.println(seriesRepository.getStringAllSeries());
        System.out.print("seriesID: ");
        int seriesID = myInput.nextInt();
        seriesRepository.deleteSeriesById(seriesID);
    }

    private void getGroupsOfSeries() {
        System.out.println(seriesRepository.getStringAllSeries());
        System.out.print("seriesID: ");
        int seriesID = myInput.nextInt();
        System.out.println(groupRepository.getStringGroupsOfSeries(seriesID));
    }

    public void groupMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Series DB context
                            1. Insert a group to DB
                            2. Fetch a group from DB
                            3. Update the name of a group
                            4. Set the group to a series
                            5. Delete a group
                            6. Print all groups
                            7. Print students of a group by ID
                            0. exit
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Insert a group to DB
                insertGroup();
            } else if (option == 2) { // Fetch a group from DB
                System.out.println(fetchGroup());
            } else if (option == 3) { // Update the name of a group
                updateGroupName();
            } else if (option == 4) {
                setGroupToSeries();
            } else if (option == 5) { // Delete a group
                deleteGroup();
            } else if (option == 6) { // Print all groups
                System.out.println(groupRepository.getStringAllGroups());
            } else if (option == 7) { // Print students of a group by ID
                getStudentsOfGroup();
            }
            printSpaces(3);
        }
    }

    private void insertGroup() {
        Group group = new Group();
        group.setGroup();
        groupRepository.insertGroup(group);
    }

    private Group fetchGroup() {
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        return groupRepository.getGroupById(groupID);
    }

    private void updateGroupName() {
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        System.out.print("new name: ");
        String name = myInput.next();
        groupRepository.updateGroupName(groupID, name);
    }

    private void setGroupToSeries() {
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        printSpaces(3);
        System.out.println(seriesRepository.getStringAllSeries());
        System.out.print("seriesID: ");
        int seriesID = myInput.nextInt();
        groupRepository.updateSeries(groupID, seriesID);
    }

    private void deleteGroup() {
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        groupRepository.deleteGroupById(groupID);
    }

    private void getStudentsOfGroup() {
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        System.out.println(studentRepository.getStringStudentsOfGroup(groupID));
    }

    public void personMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Person DB context
                            1. Fetch a string of a person from DB
                            2. Update the name of a person
                            3. Delete a person
                            4. Print all persons
                            0. exit
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Fetch the string of a person from DB
                System.out.println(fetchPerson());
            } else if (option == 2) { // Update the name of a person
                updatePersonName();
            } else if (option == 3) { // Delete a person
                deletePerson();
            } else if (option == 4) { // Print all persons
                System.out.println(personRepository.getStringAllPersons());
            }
            printSpaces(3);
        }
    }

    private String fetchPerson() {
        System.out.println(personRepository.getStringAllPersons());
        System.out.print("personID: ");
        int personID = myInput.nextInt();
        return personRepository.mapToString(personRepository.getPersonById(personID));
    }

    private void updatePersonName() {
        System.out.println(personRepository.getStringAllPersons());
        System.out.print("personID: ");
        int personID = myInput.nextInt();
        System.out.print("new firstName: ");
        String firstName = myInput.next();
        System.out.print("new lastName: ");
        String lastName = myInput.next();
        personRepository.updatePersonName(personID, firstName, lastName);
    }

    private void deletePerson() {
        System.out.println(personRepository.getStringAllPersons());
        System.out.print("personID: ");
        int personID = myInput.nextInt();
        personRepository.deletePersonById(personID);
    }

    public void studentMenu() throws IOException {
        while (true) {
            System.out.println(
                    """
                            Series DB context
                            1. Insert a student to DB
                            2. Fetch a student from DB
                            3. Update the year and semester of a student
                            4. Delete a student
                            5. Print all students
                            6. Set a student to a group by ID
                            0. exit
                            """
            );
            option = myInput.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) { // Insert a student to DB
                insertStudent();
            } else if (option == 2) { // Fetch a student from DB
                System.out.println(fetchStudent());
            } else if (option == 3) { // Update the year and semester of a student
                updateStudentName();
            } else if (option == 4) { // Delete a student
                deleteStudent();
            } else if (option == 5) { // Print all students
                System.out.println(studentRepository.getStringAllStudents());
            } else if (option == 6) { // Set a student to a group by ID
                setStudentToGroup();
            }
            printSpaces(3);
        }
    }

    private void insertStudent() {
        Student student = new Student();
        student.setStudent();
        studentRepository.insertStudent(student);
    }

    private Student fetchStudent() {
        System.out.println(studentRepository.getStringAllStudents());
        System.out.print("studentID: ");
        int studentID = myInput.nextInt();
        return studentRepository.getStudentById(studentID);
    }

    private void updateStudentName() {
        System.out.println(studentRepository.getStringAllStudents());
        System.out.print("studentID: ");
        int studentID = myInput.nextInt();
        System.out.print("new year: ");
        int year = myInput.nextInt();
        System.out.print("new semester: ");
        int semester = myInput.nextInt();
        studentRepository.updateYearAndSemester(studentID, year, semester);
    }

    private void deleteStudent() {
        System.out.println(studentRepository.getStringAllStudents());
        System.out.print("studentID: ");
        int studentID = myInput.nextInt();
        studentRepository.deleteStudentById(studentID);
    }

    private void setStudentToGroup() {
        System.out.println(studentRepository.getStringAllStudents());
        System.out.print("personID: ");
        int personID = myInput.nextInt();
        printSpaces(3);
        System.out.println(groupRepository.getStringAllGroups());
        System.out.print("groupID: ");
        int groupID = myInput.nextInt();
        studentRepository.updateGroup(personID, groupID);
    }
}
