package com.example.apkabazadanych2.user;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class Users {
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

    public Users(Long ID_User, String name, String surname, String login, String password, String email) {
        this.ID_User = ID_User;
        this.Name = name;
        this.Surname = surname;
        this.Login = login;
        this.Password = password;
        this.Email = email;
    }

    public Users() {

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
}
