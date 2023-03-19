package com.fightclub.core.domain.command;

import java.util.Objects;

public class RegisterCommand {

    private String name;
    private String password;
    private String repeatPassword;
    private String email;

    public RegisterCommand(String name,
                           String password,
                           String repeatPassword,
                           String email) {
        this.name = name;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    public RegisterCommand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterCommand that = (RegisterCommand) o;
        return name.equals(that.name) && password.equals(that.password) && repeatPassword.equals(that.repeatPassword) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, repeatPassword, email);
    }

    @Override
    public String toString() {
        return "RegisterCommand{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
