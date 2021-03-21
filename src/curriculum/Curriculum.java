package curriculum;

import subject.OptionalSubject;
import subject.Subject;

public class Curriculum {
    String major;
    int year;
    int semester;
	int req_credit;

    int num_of_obligatory_subjects;
    int num_of_optional_subjects;
    int num_of_facultative_subjects;

    Subject[] obligatory;
    OptionalSubject[] optional;
    OptionalSubject[] facultative;
}
