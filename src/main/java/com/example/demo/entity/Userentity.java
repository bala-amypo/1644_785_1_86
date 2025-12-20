package com.example.demo.entity;


import java.sql.Date;

public class Userentity {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date dob;
    private Date createAt;
    
    public Userentity(Date createAt, Date dob, String email, int id, String name, String password) {
        this.createAt = createAt;
        this.dob = dob;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Userentity() {
}
}