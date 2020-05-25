package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class User {

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer userId;

    @Column(name = "role")
    public String role;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "second_name")
    public String secondName;

    @Column(name = "auth_data_id")
    public Integer authDataId;

    public Integer getUserId() {
        return userId;
    }

    public void setuID(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAuthDataId() {
        return authDataId;
    }

    public void setAuthDataId(Integer authDataId) {
        this.authDataId = authDataId;
    }
}
