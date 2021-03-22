package service;

import curriculum.Curriculum;
import person.student.Student;

public class Main {

    /*
    * 1. get some information for a given student
    * 2. get grades for it's subjects
    * 3. get a list of students for a given subject
    * 4. get GPA or weighted GPA for a student
    * 5. get a list of students sorted by GPA
    */

    public static void main(String[] args) {
        Curriculum curriculum = new Curriculum("MATE", 2, 2, 340, 1, 1, 1);
        Student student = new Student();
        System.out.println(student);
        curriculum.setCurriculum(student);
        System.out.println(student);
    }
}
