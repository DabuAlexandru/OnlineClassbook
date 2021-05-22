package onlineClassbook.models.subject;

import java.util.Objects;
import java.util.Scanner;

/*
It could represent another type of onlineClassbook.model.subject and could do the same things as the normal subjects.
 */

public class OptionalSubject extends Subject { // this class should be the representative of optional and facultative
    private boolean graded; // if it's graded or not (if it matters for the final GPA)
    private int availableSlots; // maximum number of slots

    public OptionalSubject() {
        super();
        this.graded = false;
        this.availableSlots = 10;
    }

    public OptionalSubject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade,
                           float passingGrade, int credits, boolean graded, int availableSlots) {
        super(name, course, seminar, laboratory, grade, passingGrade, credits);
        this.graded = graded;
        this.availableSlots = availableSlots;
    }

    public OptionalSubject(String name, float passingGrade, int credits, boolean graded, int availableSlots) {
        super(name, passingGrade, credits);
        this.graded = graded;
        this.availableSlots = availableSlots;
    }

    public OptionalSubject(int subjectID, String name, float passingGrade, int credits, boolean graded, int availableSlots) {
        super(subjectID, name, passingGrade, credits);
        this.graded = graded;
        this.availableSlots = availableSlots;
    }

    public void setOptionalSubject() {
        Scanner myInput = new Scanner(System.in);
        super.setSubject();
        System.out.print("Is it graded? (Y/N): ");
        String aux;
        aux = myInput.nextLine();
        this.graded = aux.equals("Y") || aux.equals("Yes");
        System.out.print("Slots available: ");
        this.availableSlots = myInput.nextInt();
    }

    public boolean isGraded() {
        return graded;
    }

    public void setGraded(boolean graded) {
        this.graded = graded;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    public int getGrade() {
        if(!graded) {
            return 0;
        }
        return super.getGrade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OptionalSubject that = (OptionalSubject) o;
        return graded == that.graded && availableSlots == that.availableSlots;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), graded, availableSlots);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n*** graded=" + graded +
                ", availableSlots=" + availableSlots;
    }
}
