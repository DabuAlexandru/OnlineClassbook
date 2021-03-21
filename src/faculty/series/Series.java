package faculty.series;

import faculty.group.Group;

import java.util.ArrayList;
import java.util.List;

public class Series {
    String name;
    int numberOfGroups;
    List<Group> groups = new ArrayList<Group>();

    public Series() {
    }

    public Series(String name, int numberOfGroups) {
        this.name = name;
        this.numberOfGroups = numberOfGroups;
        for(int i = 0; i < numberOfGroups; i++)
        {
            this.groups.add(new Group());
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
}
