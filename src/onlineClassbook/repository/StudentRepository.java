package onlineClassbook.repository;

import onlineClassbook.config.DatabaseConfiguration;
import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.person.student.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static final StudentRepository studentRepository = new StudentRepository();
    private static final PersonRepository personRepository = PersonRepository.getPersonRepository();
    private static final GroupRepository groupRepository = GroupRepository.getGroupRepository();

    private StudentRepository() {}

    public static StudentRepository getStudentRepository() {
        return studentRepository;
    }

    private static final Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

    public void insertStudent(Student student) {
        String preparedSql =
                "INSERT INTO students (groupID, year, semester) VALUES(?, ?, ?)";
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            personRepository.insertPerson(student);
            if(student.getGroup() == null) {
                cstmt.setNull(1, Types.INTEGER);
            } else {
                cstmt.setInt(1, student.getGroup().getGroupID());
            }
            cstmt.setInt(2, student.getYear());
            cstmt.setInt(3, student.getSemester());
            cstmt.execute();
            System.out.println("Added student " + student.toString() + " to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertStudent(List<Student> studentList) {
        for(Student student : studentList) {
            insertStudent(student);
        }
    }

    public List<Student> readFromDB() {
        String selectSql = "SELECT * FROM students";
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while(true) {
                Student student = mapToStudent(resultSet);
                if(student == null) {
                    break;
                }
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public Student getStudentById(int personID) {
        String selectSql = "SELECT * FROM students WHERE personID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            pstmt.setInt(1, personID);
            ResultSet resultSet = pstmt.executeQuery();
            return mapToStudent(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStringAllStudents() {
        String selectSql = "SELECT personID, year, semester, groupID FROM students ORDER BY personID";
        return getPrintableList(selectSql);
    }

    public String getStringStudentsOfGroup(int groupID) {
        String selectSql
                = "SELECT personID, year, semester, groupID FROM students WHERE groupID = "
                + String.valueOf(groupID) + " ORDER BY personID";
        return getPrintableList(selectSql);
    }

    private String getPrintableList(String selectSql) {
        StringBuilder result = new StringBuilder("Student:\n");
        String studentString;
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while (true) {
                studentString = mapToString(resultSet);
                if(studentString == null) {
                    break;
                }
                result.append(studentString).append("\n");
            }
            return String.valueOf(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String mapToString(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                ResultSet person = personRepository.getPersonById(resultSet.getInt(1));
                if(person.next()) {
                    String result = String.valueOf(resultSet.getString("personID")) + ": " +
                            person.getString("firstName") + " " +
                            person.getString("lastName") + " year " +
                            resultSet.getString("year") + " semester " +
                            resultSet.getString("semester");
                    Group group = groupRepository.getGroupById(resultSet.getInt("groupID"));
                    if (group == null) {
                        return result;
                    }
                    return result + " :: " + group.toString();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateYearAndSemester(int personID, int year, int semester) {
        String updateSql = "UPDATE students SET year=?, semester=? WHERE personID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, semester);
            pstmt.setInt(3, personID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated student with id " + String.valueOf(personID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(int personID, int groupID) {
        Group group = groupRepository.getGroupById(groupID);
        if (group == null) {
            return;
        }
        String updateSql = "UPDATE students SET groupID=? WHERE personID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setInt(1, groupID);
            pstmt.setInt(2, personID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated student with id " + String.valueOf(personID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(int personID) {
        personRepository.deletePersonById(personID);
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            ResultSet person = personRepository.getPersonById(resultSet.getInt(1));
            if(person.next()) {
                return new Student(person.getInt("personID"),
                        person.getString("firstName"),
                        person.getString("lastName"),
                        person.getString("sex"),
                        person.getString("birthDate"),
                        person.getString("phoneNumber"),
                        person.getString("email"),
                        person.getString("joinDate"),
                        resultSet.getInt("year"),
                        resultSet.getInt("semester"),
                        groupRepository.getGroupById(resultSet.getInt("groupID")));
            }
        }
        return null;
    }
}
