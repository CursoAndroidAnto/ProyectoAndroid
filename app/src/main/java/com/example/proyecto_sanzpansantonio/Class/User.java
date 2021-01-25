package com.example.proyecto_sanzpansantonio.Class;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer Id;
    private String Username;
    private String Name;
    private String LastName;
    private String Password;
    private String Mail;
    private Integer BDate;

    public User(String username, String name, String lastName, String password, String mail, Integer bdate) {
        Username = username;
        Name = name;
        LastName = lastName;
        Password = password;
        Mail = mail;
        BDate = bdate;
    }
    public User() {
    }
    public Integer getId() {
        return Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public Integer getBDate() {
        return BDate;
    }

    public void setBDate(Integer BDate) {
        this.BDate = BDate;
    }
}
