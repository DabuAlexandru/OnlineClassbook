package onlineClassbook.repository;

import onlineClassbook.config.DatabaseConfiguration;
import onlineClassbook.models.faculty.group.Group;
import onlineClassbook.models.faculty.series.Series;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private static final GroupRepository groupRepository = new GroupRepository();
    private static final SeriesRepository seriesRepository = SeriesRepository.getSeriesRepository();

    private GroupRepository() {}

    public static GroupRepository getGroupRepository() {
        return groupRepository;
    }

    private static final Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

    public void insertGroup(Group group) {
        String preparedSql = "INSERT INTO myGroups (name, seriesID) VALUES(?, ?)";
        try {
            CallableStatement cstmt = databaseConnection.prepareCall(preparedSql);
            cstmt.setString(1, group.getName());
            if(group.getSeries() == null) {
                cstmt.setNull(2, Types.INTEGER);
            } else {
                cstmt.setInt(2, group.getSeries().getSeriesID());
            }
            cstmt.execute();
            System.out.println("Added " + group.toString() + " to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void bulkInsertGroup(List<Group> groupList) {
        for(Group group : groupList) {
            insertGroup(group);
        }
    }

    public List<Group> readFromDB() {
        String selectSql = "SELECT * FROM myGroups";
        List<Group> groupList = new ArrayList<>();
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while(true) {
                Group group = mapToGroup(resultSet);
                if(group == null) {
                    break;
                }
                groupList.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    public Group getGroupById(int groupID) {
        String selectSql = "SELECT * FROM myGroups WHERE groupID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            pstmt.setInt(1, groupID);
            ResultSet resultSet = pstmt.executeQuery();
            return mapToGroup(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getStringAllGroups() {
        String selectSql = "SELECT groupID, name, seriesID FROM myGroups ORDER BY groupID";
        return getPrintableList(selectSql);
    }

    public String getStringGroupsOfSeries(int seriesID) {
        String selectSql
                = "SELECT groupID, name, seriesID FROM myGroups WHERE seriesID = "
                + String.valueOf(seriesID) + " ORDER BY groupID";
        return getPrintableList(selectSql);
    }

    private String getPrintableList(String selectSql) {
        StringBuilder result = new StringBuilder("Groups:\n");
        String groupString;
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(selectSql);
            ResultSet resultSet = pstmt.executeQuery();
            while (true) {
                groupString = mapToString(resultSet);
                if(groupString == null) {
                    break;
                }
                result.append(groupString).append("\n");
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
                Series series = seriesRepository.getSeriesById(resultSet.getInt("seriesID"));
                String result = String.valueOf(resultSet.getString("groupID")) + ": group " +
                        resultSet.getString("name");
                if(series == null) {
                    return result;
                }
                return result + " :: " + series.toString();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateGroupName(int groupID, String name) {
        String updateSql = "UPDATE myGroups SET name=? WHERE groupID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setString(1, name);
            pstmt.setInt(2, groupID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated group with id " + String.valueOf(groupID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSeries(int groupID, int seriesID) {
        Series series = seriesRepository.getSeriesById(seriesID);
        if (series == null) {
            return;
        }
        String updateSql = "UPDATE myGroups SET seriesID=? WHERE groupID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(updateSql);
            pstmt.setInt(1, seriesID);
            pstmt.setInt(2, groupID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Updated group with id " + String.valueOf(groupID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroupById(int groupID) {
        String deleteSql = "DELETE FROM myGroups WHERE groupID=?";
        try {
            PreparedStatement pstmt = databaseConnection.prepareStatement(deleteSql);
            pstmt.setInt(1, groupID);
            int rowsUpdated = pstmt.executeUpdate();
            if(rowsUpdated > 0) {
                System.out.println("Delete group with id " + String.valueOf(groupID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Group mapToGroup(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            seriesRepository.getSeriesById(resultSet.getInt(3));
            return new Group(resultSet.getInt(1),
                    resultSet.getString(2),
                    seriesRepository.getSeriesById(resultSet.getInt(3)));
        }
        return null;
    }
}
