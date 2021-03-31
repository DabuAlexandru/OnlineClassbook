package subject;

import java.util.Objects;
import java.util.Scanner;

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
