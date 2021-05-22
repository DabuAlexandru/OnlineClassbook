package onlineClassbook.models.person.professor;

import onlineClassbook.models.person.Person;

import java.util.Objects;
import java.util.Scanner;

enum AcademicRank { Professor, Reader, Lecturer, Assistant }

public class Professor extends Person{

    private AcademicRank rank;
    private int salary;

    public Professor() {
        super();
    }

    public Professor(String first_name, String last_name, String sex, String birth_date, String phone_number,
                     String email, String join_date, String rank, int salary) {
        super(first_name, last_name, sex, birth_date, phone_number, email, join_date);
        setRank(rank);
        this.salary = salary;
    }

    public Professor(int personID, String first_name, String last_name, String sex, String birth_date, String phone_number,
                     String email, String join_date, String rank, int salary) {
        super(personID, first_name, last_name, sex, birth_date, phone_number, email, join_date);
        setRank(rank);
        this.salary = salary;
    }

    public Professor(AcademicRank rank, int salary) {
        this.rank = rank;
        this.salary = salary;
    }

    public void setProfessor() {
        super.setPerson();
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter rank: ");
        String aux = myInput.nextLine();
        setRank(aux);

        System.out.println("Enter salary: ");
        this.salary = myInput.nextInt();
    }

    public void setRank(String rank) {
        switch (rank) {
            case "Professor" -> this.rank = AcademicRank.Professor;
            case "Reader" -> this.rank = AcademicRank.Reader;
            case "Lecturer" -> this.rank = AcademicRank.Lecturer;
            case "Assistant" -> this.rank = AcademicRank.Assistant;
            default -> {
                System.out.println("invalid rank ::: the rank was set to Assistant");
                this.rank = AcademicRank.Assistant;
            }
        }
    }

    public String getRank() {
        return switch (this.rank) {
            case Professor -> "Professor";
            case Reader -> "Reader";
            case Lecturer -> "Lecturer";
            case Assistant -> "Assistant";
        };
    }

    public void setRank(AcademicRank rank) {
        this.rank = rank;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + rank;
    }
}
