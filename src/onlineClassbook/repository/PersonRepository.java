package onlineClassbook.repository;

import onlineClassbook.config.DatabaseConfiguration;
import onlineClassbook.models.person.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PersonRepository {

    private static final PersonRepository personRepository = new PersonRepository();

    private PersonRepository(){}

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }

    private static final Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

    public void insertPerson(Person person) {
        String preparedSql =
                "INSERT INTO persons (firstName, lastName, sex, birthDate, phoneNumber, email, joinDate) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, person.getFirstName());
            cstmt.setString(2, person.getLastName());
            cstmt.setString(3, person.getSex());
            cstmt.setString(4, person.getBirthDate());
            cstmt.setString(5, person.getPhoneNumber());
            cstmt.setString(6, person.getEmail());
            cstmt.setString(7, person.getJoinDate());
            cstmt.execute();
            System.out.println("Added person with id: " + person.getPersonID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getPersonById(int personID) {
        String selectSql = "SELECT * FROM persons WHERE personID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            pstmt.setInt(1, personID);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStringAllPersons() {
        String selectSql = "SELECT personID, firstName, lastName FROM persons ORDER BY personID";
        return getPrintableList(selectSql);
    }

    private String getPrintableList(String selectSql) {
        StringBuilder result = new StringBuilder("Persons:\n");
        String personString;
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while (true) {
                personString = mapToString(resultSet);
                if(personString == null) {
                    break;
                }
                result.append(personString).append("\n");
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
                return String.valueOf(resultSet.getString(1)) + ": " +
                        resultSet.getString(2) + " " +
                        resultSet.getString(3);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updatePersonName(int personID, String firstName, String lastName) {
        String updateSql = "UPDATE persons SET firstName=?, lastName=? WHERE personID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, personID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated person with id " + String.valueOf(personID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePersonById(int personID) {
        String deleteSql = "DELETE FROM persons WHERE personID=?";
        try {
            PreparedStatement pstmtPerson = databaseConnection.prepareStatement(deleteSql);
            pstmtPerson.setInt(1, personID);
            int rowsUpdated = pstmtPerson.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Delete person with id " + String.valueOf(personID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
