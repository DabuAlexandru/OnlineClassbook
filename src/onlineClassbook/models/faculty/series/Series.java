package onlineClassbook.models.faculty.series;

import onlineClassbook.models.faculty.group.Group;

import java.util.*;

import static java.lang.Math.max;

public class Series {
    private static int counter = 0;
    private final int seriesID;
    private String name;
    private TreeSet<Group> groups;

    public Series() {
        counter += 1;
        seriesID = counter;
        name = "";
        groups = new TreeSet<>();
    }

    public Series(String name) {
        counter += 1;
        this.seriesID = counter;
        this.name = name;
        groups = new TreeSet<>();
    }

    public Series(int seriesID, String name) {
        counter = max(counter, seriesID);
        this.seriesID = seriesID;
        this.name = name;
        groups = new TreeSet<>();
    }

    public Series(int seriesID, String name, TreeSet<Group> groups) {
        counter = max(counter, seriesID);
        this.seriesID = seriesID;
        this.name = name;
        this.groups = groups;
    }

    public void setSeries()
    {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Series:");
        System.out.print("name = ");
        this.name = myInput.nextLine();
        System.out.println();
    }

    public void addGroup(Group group){
        if(group.getSeries() == null){
            group.setSeries(this);
            this.groups.add(group);
        }
    }

    public void removeGroup(Group group){
        if(group.getSeries() == this){
            group.setSeries(null);
            this.groups.remove(group);
        }
    }

    public Group getGroupByID(int groupID) {
        for(Group group : groups) {
            if(group.getGroupID() == groupID) {
                return group;
            }
        }
        return null;
    }

    public int getSeriesID() {
        return seriesID;
    }

    public int getNumOfGroups() {
        return groups.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeSet<Group> getGroups() {
        return groups;
    }

    public void setGroups(TreeSet<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "series " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return name.equals(series.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
