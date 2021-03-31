package faculty.series;

import faculty.group.Group;
import person.student.Student;

import java.util.*;

public class Series {
    String name;
    TreeSet<Group> groups;

    public Series() {
        name = "";
        groups = new TreeSet<>();
    }

    public Series(String name) {
        this.name = name;
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
