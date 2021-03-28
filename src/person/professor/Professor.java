package person.professor;

import person.Person;

import java.util.Scanner;

enum AcademicRank { Professor, Reader, Lecturer, Assistant }

public class Professor extends Person{

    AcademicRank rank;
    int salary;

    public Professor() {
    }

    public Professor(String first_name, String last_name, String sex, String birth_date, String phone_number, String email, String join_date, AcademicRank rank, int salary) {
        super(first_name, last_name, sex, birth_date, phone_number, email, join_date);
        this.rank = rank;
        this.salary = salary;
    }

    public Professor(String first_name, String last_name, String birth_date, String join_date, String email, AcademicRank rank, int salary) {

        this.rank = rank;
        this.salary = salary;
    }

    public void setProfessor() {
        Scanner myInput = new Scanner(System.in);

        System.out.println("Enter rank: ");
        String aux = myInput.nextLine();
        switch (aux) {
            case "Professor" -> this.rank = AcademicRank.Professor;
            case "Reader" -> this.rank = AcademicRank.Reader;
            case "Lecturer" -> this.rank = AcademicRank.Lecturer;
            case "Assistant" -> this.rank = AcademicRank.Assistant;
            default -> {
                System.out.println("invalid rank ::: the rank was set to Assistant");
                this.rank = AcademicRank.Assistant;
            }
        }

        System.out.println("Enter salary: ");
        this.salary = myInput.nextInt();
    }

    public AcademicRank getRank() {
        return rank;
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
        return super.toString() +
                "\nProfessor{" +
                "rank=" + rank +
                ", salary=" + salary +
                '}';
    }
}
