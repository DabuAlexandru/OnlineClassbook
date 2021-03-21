package person.professor;

import person.Person;

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
}
