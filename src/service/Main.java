package service;

import curriculum.Curriculum;
import faculty.Faculty;
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

        Faculty faculty = Faculty.getFaculty("FMI", 2);

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
                                    1. Add a new student
                                    2. Add a new subject
                                    3. Add a new optional subject
                                    4. Add a new professor
                                    5. Add a new group
                                    6. Add a new series
                                    7. Add a new curriculum
                                    0. back to the start menu
                                    """
                    );
                    option = myInput.nextInt();
                    if(option == 0) { // back to the start menu
                        break;
                    }
                    else if(option == 1){ // Add a new student
                        faculty.addStudent();
                    }
                    else if(option == 2){ // Add a new subject
                        faculty.addSubject();
                    }
                    else if(option == 3){ // Add a new optional subject
                        faculty.addOptionalSubject();
                    }
                    else if(option == 4){ // Add a new professor
                        faculty.addProfessor();
                    }
                    else if(option == 5){ // Add a new group
                        faculty.addGroup();
                    }
                    else if(option == 6){ // Add a new series
                        faculty.addSeries();
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
                                    5. Print all groups
                                    6. Print all series
                                    0. back to the start menu
                                    """
                    );
                    option = myInput.nextInt();
                    if (option == 0) {
                        break;
                    } else if (option == 1) { // Print all students
                        faculty.printStudents();
                    } else if (option == 2) { // Print all subjects
                        faculty.printSubjects();
                    } else if (option == 3) { // Print all optional subjects
                        faculty.printOptionalSubjects();
                    } else if (option == 4) { // Print all professors
                        faculty.printProfessors();
                    } else if (option == 5) { // Print all groups
                        faculty.printGroups();
                    } else if (option == 6) { // Print all series
                        faculty.printSeries();
                    }
                    printSpaces(3);
                }
            }
            printSpaces(8);
        }
    }
}
