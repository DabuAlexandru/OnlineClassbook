package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

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
        Menu menu = new Menu();
        menu.principalMenu();
    }
}
