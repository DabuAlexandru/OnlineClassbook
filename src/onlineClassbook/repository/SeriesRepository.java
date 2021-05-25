package onlineClassbook.repository;

import onlineClassbook.config.DatabaseConfiguration;
import onlineClassbook.models.faculty.series.Series;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeriesRepository {

    private static final SeriesRepository seriesRepository = new SeriesRepository();

    private SeriesRepository(){}

    public static SeriesRepository getSeriesRepository() {
        return seriesRepository;
    }

    private static final Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

    public void insertSeries(Series series) {
        String preparedSql =
                "INSERT INTO series (name) VALUES(?)";
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, series.getName());
            cstmt.execute();
            System.out.println("Added " + series.toString() + " to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertSeries(List<Series> seriesList) {
        for(Series series : seriesList) {
            insertSeries(series);
        }
    }

    public List<Series> readFromDB() {
        String selectSql = "SELECT * FROM series";
        List<Series> seriesList = new ArrayList<>();
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while(true) {
                Series series = mapToSeries(resultSet);
                if(series == null) {
                    break;
                }
                seriesList.add(series);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seriesList;
    }

    public String getStringAllSeries() {
        String selectSql = "SELECT seriesID, name FROM series ORDER BY seriesID";
        return getPrintableList(selectSql);
    }

    private String getPrintableList(String selectSql) {
        StringBuilder result = new StringBuilder("Series:\n");
        String seriesString;
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while (true) {
                seriesString = mapToString(resultSet);
                if(seriesString == null) {
                    break;
                }
                result.append(seriesString).append("\n");
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
                return String.valueOf(resultSet.getInt("seriesID")) + ": series " +
                        resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Series getSeriesById(int seriesID) {
        String selectSql = "SELECT * FROM series WHERE seriesID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            pstmt.setInt(1, seriesID);
            ResultSet resultSet = pstmt.executeQuery();
            return mapToSeries(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSeriesName(int seriesID, String name) {
        String updateSql = "UPDATE series SET name=? WHERE seriesID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setString(1, name);
            pstmt.setInt(2, seriesID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated series with id " + String.valueOf(seriesID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeriesById(int seriesID) {
        String deleteSql = "DELETE FROM series WHERE seriesID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(deleteSql);
            pstmt.setInt(1, seriesID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Delete series with id " + String.valueOf(seriesID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Series mapToSeries(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            return new Series(resultSet.getInt("seriesID"),
                    resultSet.getString("name"));
        }
        return null;
    }
}
