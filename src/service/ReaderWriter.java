package service;

import curriculum.Curriculum;
import faculty.group.Group;
import faculty.series.Series;
import person.Person;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderWriter { // singleton

    private static ReaderWriter readerWriter;

    private ReaderWriter() {}

    public static ReaderWriter getReaderWriter() {
        if (readerWriter == null)
            readerWriter = new ReaderWriter();
        return readerWriter;
    }

    private List<Integer> parseIDList(String ids) {
        ids = ids.substring(1, ids.length() - 1);
        String[] stringListID = ids.split(";");
        List<Integer> listID = new ArrayList<>();
        for(String id : stringListID) {
            listID.add(Integer.parseInt(id));
        }
        return listID;
    }

    private Person parsePerson(String line, String option) {
        String [] args = line.split(",");
        int personID        = Integer.parseInt(args[0]);
        String first_name   = args[1];
        String last_name    = args[2];
        String sex          = args[3];
        String birth_date   = args[4];
        String phone_number = args[5];
        String email        = args[6];
        String join_date    = args[7];
        if(option.equals("Student")) {
            int year        = Integer.parseInt(args[8]);
            int semester    = Integer.parseInt(args[9]);
            return new Student(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date, year, semester);
        } else {
            String rank     = args[8];
            int salary      = Integer.parseInt(args[9]);
            return new Professor(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date, rank, salary);
        }
    }

    private Subject parseSubject(String line, String option) {
        String [] args = line.split(",");
        int subjectID           = Integer.parseInt(args[0]);
        String name             = args[1];
        float passingGrade      = Float.parseFloat(args[2]);
        int credits             = Integer.parseInt(args[3]);
        if(option.equals("OptionalSubject")) {
            boolean graded      = Boolean.parseBoolean(args[4]);
            int slots_available = Integer.parseInt(args[5]);
            return new OptionalSubject(subjectID, name, passingGrade, credits, graded, slots_available);
        }
        return new Subject(subjectID, name, passingGrade, credits);
    }

    private Group parseGroup(String line) {
        String [] args = line.split(",");
        int groupID = Integer.parseInt(args[0]);
        String name = args[1];
        return new Group(groupID, name);
    }

    private Series parseSeries(String line) {
        String [] args = line.split(",");
        int seriesID    = Integer.parseInt(args[0]);
        String name     = args[1];
        return new Series(seriesID, name);
    }

    private Curriculum parseCurriculum(String line) {
        String [] args = line.split(",");
        int curriculumID    = Integer.parseInt(args[0]);
        String major        = args[1];
        int year            = Integer.parseInt(args[2]);
        int semester        = Integer.parseInt(args[3]);
        int req_credits     = Integer.parseInt(args[4]);
        return new Curriculum(curriculumID, major, year, semester, req_credits);
    }

    public <T> List<T> readFromCSV(String option, String path) throws IOException {
        List<T> objects = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            br.readLine();
            T newObject;
            while((line = br.readLine()) != null) {
                switch(option) {
                    case "Student", "Professor" -> {
                        newObject = (T) parsePerson(line, option);
                    }
                    case "Subject", "OptionalSubject" -> {
                        newObject = (T) parseSubject(line, option);
                    }
                    case "Group" -> {
                        newObject = (T) parseGroup(line);
                    }
                    case "Series" -> {
                        newObject = (T) parseSeries(line);
                    }
                    case "Curriculum" -> {
                        newObject = (T) parseCurriculum(line);
                    }
                    default -> { return objects; }
                }
                objects.add(newObject);
            }
            br.close();
            if(objects.size() > 0) { writeToAudit("Read from " + option + " CSV"); }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<String> personToListCSV(String option, Person person) {
        List<String> info = new ArrayList<>();
        info.add(String.valueOf(person.getPersonID()));
        info.add(person.getFirstName());
        info.add(person.getLastName());
        info.add(person.getSex());
        info.add(person.getBirthDate());
        info.add(person.getPhoneNumber());
        info.add(person.getEmail());
        info.add(person.getJoinDate());
        if(option.equals("Student")) {
            info.add(String.valueOf((((Student) person).getYear())));
            info.add(String.valueOf(((Student) person).getSemester()));
        } else {
            info.add(((Professor) person).getRank());
            info.add(String.valueOf(((Professor) person).getSalary()));
        }
        return info;
    }

    public List<String> subjectToListCSV(String option, Subject subject) {
        List<String> info = new ArrayList<>();
        info.add(String.valueOf(subject.getSubjectID()));
        info.add(subject.getName());
        info.add(String.valueOf(subject.getPassingGrade()));
        info.add(String.valueOf(subject.getCredits()));
        if(option.equals("OptionalSubject")) {
            info.add(String.valueOf(((OptionalSubject) subject).isGraded()));
            info.add(String.valueOf(((OptionalSubject) subject).getSlots_available()));
        }
        return info;
    }

    public List<String> groupToListCSV(String option, Group group) {
        List<String> info = new ArrayList<>();
        info.add(String.valueOf(group.getGroupID()));
        info.add(group.getName());
        return info;
    }

    public List<String> seriesToListCSV(String option, Series series) {
        List<String> info = new ArrayList<>();
        info.add(String.valueOf(series.getSeriesID()));
        info.add(series.getName());
        return info;
    }

    public List<String> curriculumToListCSV(String option, Curriculum curriculum) {
        List<String> info = new ArrayList<>();
        info.add(String.valueOf(curriculum.getCurriculumID()));
        info.add(curriculum.getMajor());
        info.add(String.valueOf(curriculum.getYear()));
        info.add(String.valueOf(curriculum.getSemester()));
        info.add(String.valueOf(curriculum.getReqCredit()));
        return info;
    }

    public void initializeCSV(String option, BufferedWriter bw) throws IOException {
        switch (option) {
            case "Student", "Professor" -> {
                bw.write("PersonID, FirstName, LastName, Sex, BirthDate, PhoneNumber, Email, JoinDate");
                if (option.equals("Student")) {
                    bw.write(", Year, Semester");
                } else {
                    bw.write(", Rank, Salary");
                }
            }
            case "Curriculum" -> bw.write("CurriculumID, Major, Year, Semester, RequiredCredits");
            case "Group" -> bw.write("GroupID, Name");
            case "Series" -> bw.write("SeriesID, Name");
            case "Subject", "OptionalSubject" -> {
                bw.write("SubjectID, Name, PassingGrade, Credits");
                if (option.equals("OptionalSubject")) {
                    bw.write(", Graded, SlotsAvailable");
                }
            }
        }
        bw.newLine();
    }

    public <T> void writeToCSV(T obj, String path) throws IOException {
        List<String> info;
        String option = obj.getClass().getSimpleName();

        switch(option) {
            case "Student", "Professor" -> {
                info = personToListCSV(option, (Person) obj);
            }
            case "Subject", "OptionalSubject" -> {
                info = subjectToListCSV(option, (Subject) obj);
            }
            case "Group" -> {
                info = groupToListCSV(option, (Group) obj);
            }
            case "Series" -> {
                info = seriesToListCSV(option, (Series) obj);
            }
            case "Curriculum" -> {
                info = curriculumToListCSV(option, (Curriculum) obj);
            }
            default -> { return; }
        }

        String result;
        result = info.stream()
                .map(i -> "" + i)
                .collect(Collectors.joining(","));
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        if(file.length() == 0){
            initializeCSV(option, bw);
        }
        bw.write(result);
        bw.newLine();
        bw.close();
        writeToAudit("Write to " + option + " CSV");
    }

    public <T> void writeToCSV(List<T> objects, String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.close();
        for(T obj : objects) {
            writeToCSV(obj, path);
        }
    }

    public void writeToAudit(String action) throws IOException {
        writeToAudit(action, "src/database/Audit.csv");
    }

    public void writeToAudit(String action, String path) throws IOException {
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        if(file.length() == 0) {
            bw.write("Action, Timestamp");
            bw.newLine();
        }
        String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        bw.write(action + ", " + timeStamp);
        bw.newLine();
        bw.close();
    }

    public void transferData(String readFrom, String writeTo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(readFrom));
        BufferedWriter bw = new BufferedWriter(new FileWriter(writeTo, false));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

}
