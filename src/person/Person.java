package person;

public abstract class Person {
    String first_name;
    String last_name;
    String birth_date;
    String join_date;
    String email;

    public Person() {
    }

    public Person(String first_name, String last_name, String birth_date, String join_date, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.join_date = join_date;
        this.email = email;
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

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
