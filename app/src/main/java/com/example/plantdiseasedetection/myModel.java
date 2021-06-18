package com.example.plantdiseasedetection;

public class myModel {

    String name;
    String email;
    String password;
    String rollno;
    String skill;
    public myModel() {   }
    public myModel(String name,String email,String password,String rollno, String skill)
    {      this.name = name; this.email=email;this.password=password;this.rollno=rollno;  this.skill=skill; }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String password) {
        this.rollno = rollno;
    }

   }
