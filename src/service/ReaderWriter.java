package service;

import curriculum.Curriculum;
import faculty.Faculty;
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


    public <T> List<T> readFromCSV(String option, String path) throws IOException {
        List<T> objects = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line, actionToAudit = null;
            String[] args;
            br.readLine();
            while((line = br.readLine()) != null) {
                args = line.split(",");
                switch (option.toLowerCase()) {
                    case "student" -> {
                        actionToAudit = "Read from Student CSV";
                        String first_name   = args[0];
                        String last_name    = args[1];
                        String sex          = args[2];
                        String birth_date   = args[3];
                        String phone_number = args[4];
                        String email        = args[5];
                        String join_date    = args[6];
                        int year            = Integer.parseInt(args[7]);
                        int semester        = Integer.parseInt(args[8]);
                        Student student = new Student(first_name, last_name, sex, birth_date, phone_number, email, join_date, year, semester);

                        objects.add((T) student);
                    }
                    case "subject" -> {
                        actionToAudit = "Read from Subject CSV";
                        String name         = args[0];
                        float passingGrade  = Float.parseFloat(args[1]);
                        int credits         = Integer.parseInt(args[2]);
                        Subject subject = new Subject(name, passingGrade, credits);

                        objects.add((T) subject);
                    }
                    case "optional subject" -> {
                        actionToAudit = "Read from OptionalSubject CSV";
                        String name         = args[0];
                        float passingGrade  = Float.parseFloat(args[1]);
                        int credits         = Integer.parseInt(args[2]);
                        boolean graded      = Boolean.parseBoolean(args[3]);
                        int slots_available = Integer.parseInt(args[4]);
                        OptionalSubject optionalSubject = new OptionalSubject(name, passingGrade, credits, graded, slots_available);

                        objects.add((T) optionalSubject);
                    }
                    case "professor" -> {
                        actionToAudit = "Read from Professor CSV";
                        String first_name   = args[0];
                        String last_name    = args[1];
                        String sex          = args[2];
                        String birth_date   = args[3];
                        String phone_number = args[4];
                        String email        = args[5];
                        String join_date    = args[6];
                        String rank         = args[7];
                        int salary          = Integer.parseInt(args[8]);
                        Professor professor = new Professor(first_name, last_name, sex, birth_date, phone_number, email, join_date, rank, salary);

                        objects.add((T) professor);
                    }
                    case "group" -> {
                        actionToAudit = "Read from Group CSV";
                        String name = args[0];
                        Group group = new Group(name);

                        objects.add((T) group);
                    }
                    case "series" -> {
                        actionToAudit = "Read from Series CSV";
                        String name = args[0];
                        Series series = new Series(name);

                        objects.add((T) series);
                    }
                    case "curriculum" -> {
                        actionToAudit = "Read from Curriculum CSV";
                        String major = args[0];
                        int year = Integer.parseInt(args[1]);
                        int semester = Integer.parseInt(args[2]);
                        int req_credits = Integer.parseInt(args[3]);
                        Curriculum curriculum = new Curriculum(major, year, semester, req_credits);

                        objects.add((T) curriculum);
                    }
                    default -> { return null; }
                }
            }
            if(actionToAudit != null) { writeToAudit(actionToAudit, "src/database/Audit.CSV"); }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public <T> void writeToCSV(T obj, String path, Boolean quotes) throws IOException {
        List<String> info = new ArrayList<>();
        String actionToAudit;

        if(obj.getClass() == Student.class || obj.getClass() == Professor.class) {
            info.add(((Person) obj).getFirstName());
            info.add(((Person) obj).getLastName());
            info.add(((Person) obj).getSex());
            info.add(((Person) obj).getBirthDate());
            info.add(((Person) obj).getPhoneNumber());
            info.add(((Person) obj).getEmail());
            info.add(((Person) obj).getJoinDate());
            if(obj.getClass() == Student.class) {
                info.add(String.valueOf((((Student) obj).getYear())));
                info.add(String.valueOf(((Student) obj).getSemester()));
                actionToAudit = "Wrote to Student CSV";
            } else {
                info.add(((Professor) obj).getRank());
                info.add(String.valueOf(((Professor) obj).getSalary()));
                actionToAudit = "Wrote to Professor CSV";
            }
        } else if(obj.getClass() == Subject.class || obj.getClass() == OptionalSubject.class) {
            info.add(((Subject) obj).getName());
            info.add(String.valueOf(((Subject) obj).getPassingGrade()));
            info.add(String.valueOf(((Subject) obj).getCredits()));
            actionToAudit = "Wrote to Subject CSV";
            if(obj.getClass() == OptionalSubject.class) {
                info.add(String.valueOf(((OptionalSubject) obj).isGraded()));
                info.add(String.valueOf(((OptionalSubject) obj).getSlots_available()));
                actionToAudit = "Wrote to OptionalSubject CSV";
            }
        } else if(obj.getClass() == Group.class) {
            actionToAudit = "Wrote to Group CSV";
            info.add(((Group) obj).getName());
        } else if(obj.getClass() == Series.class) {
            actionToAudit = "Wrote to Series CSV";
            info.add(((Series) obj).getName());
        } else if(obj.getClass() == Curriculum.class) {
            actionToAudit = "Wrote to Curriculum CSV";
            info.add(((Curriculum) obj).getMajor());
            info.add(String.valueOf(((Curriculum) obj).getYear()));
            info.add(String.valueOf(((Curriculum) obj).getSemester()));
            info.add(String.valueOf(((Curriculum) obj).getReq_credit()));
        } else {
            return;
        }
        String result;
        if(quotes) {
            result = info.stream()
                    .map(i -> "\"" + i + "\"")
                    .collect(Collectors.joining(","));
        } else {
            result = info.stream()
                    .map(i -> "" + i)
                    .collect(Collectors.joining(","));
        }
        File file = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        if(file.length() == 0){
            if(obj.getClass() == Student.class || obj.getClass() == Professor.class){
                bw.write("FirstName, LastName, Sex, BirthDate, PhoneNumber, Email, JoinDate");
                if(obj.getClass() == Student.class) {
                    bw.write(", Year, Semester");
                } else {
                    bw.write(", Rank, Salary");
                }
            } else if(obj.getClass() == Curriculum.class) {
                bw.write("Major, Year, Semester, RequiredCredits");
            } else {
                bw.write("Name");
                if(obj.getClass() == Subject.class || obj.getClass() == OptionalSubject.class) {
                    bw.write(", PassingGrade, Credits");
                    if(obj.getClass() == OptionalSubject.class) {
                        bw.write(", Graded, SlotsAvailable");
                    }
                }
            }
            bw.newLine();
        }
        bw.write(result);
        bw.newLine();
        bw.close();
        writeToAudit(actionToAudit, "src/database/Audit.CSV");
    }

    public void writeToAudit(String action) throws IOException {
        writeToAudit(action, "src/database/Audit.CSV");
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

}
