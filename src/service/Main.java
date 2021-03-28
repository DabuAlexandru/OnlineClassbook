package service;

import curriculum.Curriculum;
import person.professor.Professor;
import person.student.Student;
import subject.Subject;

import java.util.Scanner;

public class Main {

    public static void printSpaces() {
        System.out.print("\n\n\n\n\n\n\n\n");
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

        Scanner myInput = new Scanner(System.in);
        int option;
        while(true)
        {
            System.out.println(
                    """
                    Onine Classbook:
                    1. Add a new item in the database
                    2. Add some information for an existing item
                    3. Show information
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
                                    0. exit
                                    1. Add a new curriculum
                                    2. Add a new series
                                    3. Add a new group"
                                    4. Add a new student
                                    5. Add a new professor
                                    6. Add a new Subject
                                    7. Add a new Optional Subject
                                    """
                    );
                    option = myInput.nextInt();
                    if(option == 0) {
                        break;
                    }
                    printSpaces();
                }
            }
            else if(option == 2) { // Add some information for an existing item
                while(true) {
                    System.out.println(
                            """
                                    0. exit
                                    1. Add a subject to a student
                                    2. Set a professor to a study class of a subject
                                    3. Set a group to a student
                                    4. Set a series to a group
                                    5. Generate random grades for some students
                                    """
                    );
                    option = myInput.nextInt();
                    if(option == 0) {
                        break;
                    }
                    printSpaces();
                }
            }
            else if(option == 3) { // Show information
                while(true) {
                    System.out.println(
                            """
                                    0. exit
                                    1. Search a student's situation by name
                                    2. Show all information about students in a given Group
                                    3. Show all information about students in a given Series
                                    """);
                    option = myInput.nextInt();
                    if(option == 0) {
                        break;
                    }
                    printSpaces();
                }
            }
            printSpaces();

        }
    }
}
