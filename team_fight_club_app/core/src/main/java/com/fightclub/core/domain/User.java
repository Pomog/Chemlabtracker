package com.fightclub.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Long id;
    private String name;
    private String hashPassword;
    private String email;
    private List<Fighter> fighters = new ArrayList<>();

    public User() {
    }

    public User(Long id,
                String name,
                String hashPassword,
                String email,
                List<Fighter> fighters) {
        this.id = id;
        this.name = name;
        this.hashPassword = hashPassword;
        this.email = email;
        this.fighters = fighters;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public void setFighters(List<Fighter> fighters) {
        this.fighters = fighters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(hashPassword, user.hashPassword) && Objects.equals(email, user.email) && Objects.equals(fighters, user.fighters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, hashPassword, email, fighters);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", email='" + email + '\'' +
                ", fighters=" + fighters +
                '}';
    }
}
