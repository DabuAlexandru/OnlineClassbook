package subject;

public class OptionalSubject extends Subject {
    boolean graded = true;
    int slots_available;

    public OptionalSubject() {
    }

    public OptionalSubject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade, float passing_grade, int credits, boolean graded, int slots_available) {
        super(name, course, seminar, laboratory, grade, passing_grade, credits);
        this.graded = graded;
        this.slots_available = slots_available;
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public int getSlots_available() {
        return slots_available;
    }

    public void setSlots_available(int slots_available) {
        this.slots_available = slots_available;
    }

    public int getGrade() {
        if(!graded)
        {
            return 0;
        }
        return grade;
    }
}
