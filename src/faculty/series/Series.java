package faculty.series;

import faculty.group.Group;
import person.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Series {
    String name;
    int numberOfGroups;
    List<Group> groups;

    public Series() {
        name = "";
        numberOfGroups = 0;
        groups = new ArrayList<Group>();
    }

    public Series(String name, int numberOfGroups) {
        this.name = name;
        this.numberOfGroups = numberOfGroups;
        setGroups();
    }

    public void setSeries()
    {
        Scanner myInput = new Scanner(System.in);
        System.out.println("Series:");
        System.out.print("name = ");
        this.name = myInput.nextLine();
        System.out.println();
    }

    public void setGroups() {
        if(numberOfGroups == 0) {
            Scanner myInput = new Scanner(System.in);
            System.out.print("numberOfGroups = ");
            this.numberOfGroups = myInput.nextInt();
        }
        for(int i = 0; i < numberOfGroups; i++) {
            Group group = new Group();
            group.setGroup();
            this.groups.add(group);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                '}';
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
