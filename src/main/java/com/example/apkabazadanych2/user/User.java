package com.example.apkabazadanych2.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    public Long ID_User;
    public String Name;
    public String Surname;
    public String Login;
    public String Password;
    public String Email;
    public LocalDate DateOfBirth;

    @Transient
    public Integer Age;

    public User(String name, String surname, String login, String password, String email, LocalDate dateOfBirth) {
        Name = name;
        Surname = surname;
        Login = login;
        Password = password;
        Email = email;
        DateOfBirth = dateOfBirth;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getID_User() {
        return ID_User;
    }

    public void setID_User(Long ID_User) {
        this.ID_User = ID_User;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getAge(){
        return Period.between(DateOfBirth, LocalDate.now()).getYears();
    }
}
