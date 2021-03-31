package person;

import person.student.Student;
import subject.OptionalSubject;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public abstract class Person implements Comparable<Person>{
    String firstName;
    String lastName;
    String sex;
    String birthDate;
    String phoneNumber;
    String email;
    String joinDate;

    public Person() {
    }

    public Person(String firstName, String lastName, String sex, String birthDate, String phoneNumber, String email, String joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.joinDate = joinDate;
    }

    public void setPerson(){
        Scanner myInput = new Scanner(System.in);

        System.out.println("\n--- Set person ---\n");
        System.out.println("Enter first name: ");
        this.firstName = myInput.nextLine();

        System.out.println("Enter last name: ");
        this.lastName = myInput.nextLine();

        System.out.println("Enter sex: ");
        this.sex = myInput.nextLine();

        System.out.println("Enter birth date: ");
        this.birthDate = myInput.nextLine();

        System.out.println("Enter phone number: ");
        this.phoneNumber = myInput.nextLine();

        System.out.println("Enter email: ");
        this.email = myInput.nextLine();

        System.out.println("Enter join date: ");
        this.joinDate = myInput.nextLine();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public void printAdvancedInfo(){
        System.out.println("Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", joinDate='" + joinDate + '\'' +
                '}');
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) && lastName.equals(person.lastName) && sex.equals(person.sex) && birthDate.equals(person.birthDate) && joinDate.equals(person.joinDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sex, birthDate, joinDate);
    }

    @Override
    public int compareTo(Person o) {
        if (this.getFirstName().equals(o.getFirstName())) {
            return this.getLastName().compareTo(o.getLastName());
        } else {
            return this.getFirstName().compareTo(o.getFirstName());
        }
    }
}
