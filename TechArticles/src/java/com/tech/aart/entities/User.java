/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.aart.entities;

import java.sql.Timestamp;

/**
 *
 * @author risha
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String about;
    private Timestamp dateTime;
    private String profile;

    //Constructor getting data including id parameter
    public User(int id, String name, String email, String password, String gender, String about, Timestamp dateTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;
        this.dateTime = dateTime;
    }

    //Default parameter
    public User() {

    }

    //Constructor to get data excluding id parameter
    public User(String name, String email, String password, String gender, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;
    }

//getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

}
