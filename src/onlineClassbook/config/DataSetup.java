package onlineClassbook.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class DataSetup {

    private static final Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

    public void setUp() {

        String createSeriesTableSql = "CREATE TABLE IF NOT EXISTS series (" +
                "seriesID int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(20)," +
                "CONSTRAINT UNIQUE_SERIES UNIQUE (name)" +
                ")";

        String createGroupTableSql = "CREATE TABLE IF NOT EXISTS myGroups ( " +
                "groupID int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(20), " +
                "seriesID int, " +
                "CONSTRAINT UNIQUE_GROUP UNIQUE (name), " +
                "CONSTRAINT FK_GROUP_SERIES FOREIGN KEY (seriesID) REFERENCES Series(seriesID) " +
                "ON DELETE SET NULL" +
                ")";

        String createPersonTableSql = "CREATE TABLE IF NOT EXISTS persons (" +
                "personID int PRIMARY KEY AUTO_INCREMENT, " +
                "firstName varchar(30), " +
                "lastName varchar(30), " +
                "sex varchar(10), " +
                "birthDate varchar(30), " +
                "phoneNumber varchar(15), " +
                "email varchar(40), " +
                "joinDate varchar(30), " +
                "CONSTRAINT UNIQUE_PERSON UNIQUE (firstName, lastName, sex, birthDate, joinDate)" +
                ")";

        String createStudentTableSql = "CREATE TABLE IF NOT EXISTS students (" +
                "personID int PRIMARY KEY AUTO_INCREMENT, " +
                "groupID int, " +
                "year int, " +
                "semester int, " +
                "CONSTRAINT FK_STUDENT_PERSON FOREIGN KEY (personID) REFERENCES persons(personID)" +
                "ON DELETE CASCADE, " +
                "CONSTRAINT FK_STUDENT_GROUP FOREIGN KEY (groupID) REFERENCES myGroups(groupID) " +
                "ON DELETE SET NULL" +
                ")";

        try {
            Statement stmt = databaseConnection.createStatement();
            stmt.execute(createSeriesTableSql);
            stmt.execute(createGroupTableSql);
            stmt.execute(createPersonTableSql);
            stmt.execute(createStudentTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
