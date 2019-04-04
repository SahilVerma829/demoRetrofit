package com.example.ramesh.dempretrofit;

public class Model {
    public String id;
    public String name,lname,email,dob,gender,password,contect;

    public Model(String id, String name, String lname, String email, String dob, String gender, String password, String contect) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        this.contect = contect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContect() {
        return contect;
    }

    public void setContect(String contect) {
        this.contect = contect;
    }
}

