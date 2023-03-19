package lv.javaguru.java2.bankapp;

import java.util.Objects;

 public class User {
    private String name;
    private String surname;
    private String gender;
    private int age;

    public User(String name, String surname, String gender, int age) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
    }


    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getGender() {
        return gender;
    }

    int getAge() {
        return age;
    }

    void setName(String name) {
        this.name = name;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setGender(String gender) {
        this.gender = gender;
    }

    void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, gender, age);
    }

    @Override
    public String toString() {
        return "lv.javaguru.java2.bankapp.User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}







