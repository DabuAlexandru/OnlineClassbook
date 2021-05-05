package subject;

import java.util.Objects;
import java.util.Scanner;

/*
It could represent another type of subject and could do the same things as the normal subjects.
 */

public class OptionalSubject extends Subject { // this class should be the representative of optional and facultative
    boolean graded; // if it's graded or not (if it matters for the final GPA)
    int slots_available; // maximum number of slots

    public OptionalSubject() {
        this.graded = false;
        this.slots_available = 10;
    }

    public OptionalSubject(String name, StudyClass course, StudyClass seminar, StudyClass laboratory, int grade,
                           float passing_grade, int credits, boolean graded, int slots_available) {
        super(name, course, seminar, laboratory, grade, passing_grade, credits);
        this.graded = graded;
        this.slots_available = slots_available;
    }

    public OptionalSubject(String name, float passing_grade, int credits, boolean graded, int slots_available) {
        super(name, passing_grade, credits);
        this.graded = graded;
        this.slots_available = slots_available;
    }

    public void setOptionalSubject() {
        Scanner myInput = new Scanner(System.in);
        super.setSubject();
        System.out.print("Is it graded? (Y/N): ");
        String aux;
        aux = myInput.nextLine();
        this.graded = aux.equals("Y") || aux.equals("Yes");
        System.out.print("Slots available: ");
        this.slots_available = myInput.nextInt();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OptionalSubject that = (OptionalSubject) o;
        return graded == that.graded && slots_available == that.slots_available;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), graded, slots_available);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n*** graded=" + graded +
                ", slots_available=" + slots_available;
    }
}
