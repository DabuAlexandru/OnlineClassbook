package curriculum;

import person.student.Student;
import subject.OptionalSubject;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class Curriculum {
    String major;
    int year;
    int semester;
	int req_credit;

    int num_of_obligatory_subjects;
    int num_of_optional_subjects;
    int num_of_facultative_subjects;

    List<Subject> obligatory = new ArrayList<>();
    List<OptionalSubject> optional = new ArrayList<>();
    List<OptionalSubject> facultative = new ArrayList<>();

    public Curriculum(String major, int year, int semester, int req_credit,
                      int num_of_obligatory_subjects, int num_of_optional_subjects, int num_of_facultative_subjects) {
        this.major = major;
        this.year = year;
        this.semester = semester;
        this.req_credit = req_credit;
        this.num_of_obligatory_subjects = num_of_obligatory_subjects;
        this.num_of_optional_subjects = num_of_optional_subjects;
        this.num_of_facultative_subjects = num_of_facultative_subjects;

        for(int i = 0; i < num_of_obligatory_subjects; i++)
        {
            obligatory.add(new Subject());
        }

        for(int i = 0; i < num_of_optional_subjects; i++)
        {
            optional.add(new OptionalSubject());
        }

        for(int i = 0; i < num_of_facultative_subjects; i++)
        {
            facultative.add(new OptionalSubject());
        }
    }

    public void setCurriculum(Student student){
        if((student.getYear() == 0) || (student.getSemester() == 0))
        {
            student.setYear(this.year);
            student.setSemester(this.semester);
        }
        else if((student.getYear() == year) && (student.getSemester() == semester))
        {
            student.addSubjects(obligatory);

            for(OptionalSubject optSubj : optional) {
                student.addSubject(new Subject(optSubj));
            }

            for(OptionalSubject facultSubj : facultative) {
                student.addSubject(new Subject(facultSubj));
            }
        }
        else
        {
            System.out.println("!!!Student year or semester doesn't correspond to the curriculum!!!");
        }
    }
}
