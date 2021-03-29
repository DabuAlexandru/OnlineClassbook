package person;

import subject.OptionalSubject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Person {
    String first_name;
    String last_name;
    String sex;
    String birth_date;
    String phone_number;
    String email;
    String join_date;

    public Person() {
    }

    public Person(String first_name, String last_name, String sex, String birth_date, String phone_number, String email, String join_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.sex = sex;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.join_date = join_date;
    }

    public void setPerson(){
        Scanner myInput = new Scanner(System.in);

        System.out.println("\n--- Set person ---\n");
        System.out.println("Enter first name: ");
        this.first_name = myInput.nextLine();

        System.out.println("Enter last name: ");
        this.last_name = myInput.nextLine();

        System.out.println("Enter sex: ");
        this.sex = myInput.nextLine();

        System.out.println("Enter birth date: ");
        this.birth_date = myInput.nextLine();

        System.out.println("Enter phone number: ");
        this.phone_number = myInput.nextLine();

        System.out.println("Enter email: ");
        this.email = myInput.nextLine();

        System.out.println("Enter join date: ");
        this.join_date = myInput.nextLine();
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", sex='" + sex + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", join_date='" + join_date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return first_name.equals(person.first_name) && last_name.equals(person.last_name) && sex.equals(person.sex) && birth_date.equals(person.birth_date) && join_date.equals(person.join_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, sex, birth_date, join_date);
    }
}
