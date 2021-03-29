package service;

import curriculum.Curriculum;
import faculty.group.Group;
import faculty.series.Series;
import person.professor.Professor;
import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void printSpaces(int n) {
        for(int i = 0; i < n; i++){
            System.out.print('\n');
        }
    }

    /*
    * 1. get some information for a given student
    * 2. get grades for it's subjects
    * 3. get a list of students for a given subject
    * 4. get GPA or weighted GPA for a student
    * 5. get a list of students sorted by GPA
    */

    public static void main(String[] args) {
//        Curriculum curriculum = new Curriculum("MATE", 2, 2, 340, 1, 1, 1);
//        Student student = new Student();
//        System.out.println(student);
//        curriculum.setCurriculum(student);
//        System.out.println(student);
//        Professor prof = new Professor();
//        System.out.println(prof);

//        Subject subj = new Subject();
//        subj.setCourseWeight(0.5f);
//        subj.setSeminarWeight(0.5f);
//        subj.setCourseGrade(5.5f);
//        subj.setSeminarGrade(3.49f);
//        System.out.println("Grade: " + subj.calculateGrade());
//
//        Subject subj2 = new Subject();
//        subj2.setSubject();
//        subj2.setGrades();
//        System.out.println("Grade: " + subj2.calculateGrade());

        List<Student> students = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        List<OptionalSubject> optionalSubjects = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        List<Series> series = new ArrayList<>();

        Scanner myInput = new Scanner(System.in);
        int option;
        while(true)
        {
            System.out.println(
                    """
                        Online Classbook:
                        1. Add a new item in the database
                        2. Add some information for an existing item
                        3. Show specific information
                        4. Show all elements of a cateogry
                        0. Exit
                        """
            );

            option = myInput.nextInt();
            if(option == 0) { // exit the loop
                break;
            }
            else if(option == 1) { // Add a new item in the database
                while(true) {
                    System.out.println(
                            """
                                    Add a new item in the database
                                    1. Add a new curriculum
                                    2. Add a new series
                                    3. Add a new group
                                    4. Add a new student
                                    5. Add a new professor
                                    6. Add a new Subject
                                    7. Add a new Optional Subject
                                    0. back to the start menu
                                    """
                    );
                    option = myInput.nextInt();
                    if(option == 0) { // back to the start menu
                        break;
                    }
                    else if(option == 1){ // Add a new curriculum

                    }
                    else if(option == 2){ // Add a new series
                        Series newSeries = new Series();
                        newSeries.setSeries();
                        series.add(newSeries);
                    }
                    else if(option == 3){ // Add a new group
                        Group newGroup = new Group();
                        newGroup.setGroup();
                        groups.add(newGroup);
                    }
                    else if(option == 4){ // Add a new student
                        Student newStudent = new Student();
                        newStudent.setStudent();
                        students.add(newStudent);
                    }
                    else if(option == 5){ // Add a new professor
                        Professor newProfessor = new Professor();
                        newProfessor.setProfessor();
                        professors.add(newProfessor);
                    }
                    else if(option == 6){ // Add a new Subject
                        Subject newSubject = new Subject();
                        newSubject.setSubject();
                        subjects.add(newSubject);
                    }
                    else if(option == 7){ // Add a new Optional Subject
                        OptionalSubject newOptionalSubject = new OptionalSubject();
                        newOptionalSubject.setOptionalSubject();
                        optionalSubjects.add(newOptionalSubject);
                    }
                    printSpaces(3);
                }
            }
            else if(option == 2) { // Add some information for an existing item
                while(true) {
                    System.out.println(
                            """
                                    Add some information for an existing item
                                    1. Add a subject to a student
                                    2. Set a professor to a study class of a subject
                                    3. Set a group to a student
                                    4. Set a series to a group
                                    5. Generate random grades for some students
                                    0. back to the start menu
                                    """
                    );
                    option = myInput.nextInt();
                    if(option == 0) {
                        break;
                    }
                    else if(option == 1){ // Add a subject to a student

                    }
                    else if(option == 2){ // Set a professor to a study class of a subject

                    }
                    else if(option == 3){ // Set a group to a student

                    }
                    else if(option == 4){ // Set a series to a group

                    }
                    else if(option == 5){ // Generate random grades for some students

                    }
                    printSpaces(3);
                }
            }
            else if(option == 3) { // Show information
                while(true) {
                    System.out.println(
                            """
                                    Show information
                                    1. Search a student's situation by name
                                    2. Show all information about students in a given Group
                                    3. Show all information about students in a given Series
                                    0. back to the start menu
                                    """);
                    option = myInput.nextInt();
                    if(option == 0) {
                        break;
                    }
                    else if(option == 1){

                    }
                    else if(option == 2){

                    }
                    else if(option == 3){

                    }
                    printSpaces(3);
                }
            }
            else if(option == 4)
            {
                while(true) {
                    System.out.println(
                            """
                                    Add some information for an existing item
                                    1. Print all students
                                    2. Print all subjects
                                    3. Print all optional subjects
                                    4. Print all professors
                                    5. Print all series
                                    6. Print all groups
                                    0. back to the start menu
                                    """
                    );
                    option = myInput.nextInt();
                    if (option == 0) {
                        break;
                    } else if (option == 1) { // Print all students
                        for (Student student : students) {
                            System.out.println(student);
                        }
                    } else if (option == 2) { // Print all subjects
                        for (Subject subject : subjects) {
                            System.out.println(subject);
                        }
                    } else if (option == 3) { // Print all optional subjects
                        for (OptionalSubject optionalSubject : optionalSubjects) {
                            System.out.println(optionalSubject);
                        }
                    } else if (option == 4) { // Print all professors
                        for (Professor professor : professors) {
                            System.out.println(professor);
                        }
                    } else if (option == 5) { // Print all series
                        for (Series objSeries : series) {
                            System.out.println(objSeries);
                        }
                    } else if (option == 6) { // Print all groups
                        for (Group group : groups) {
                            System.out.println(group);
                        }
                    }
                    printSpaces(3);
                }
            }
            printSpaces(8);
        }
    }
}
