package com.example.greenery.model;

import javax.persistence.*;

@Entity
@Table(name = "auth_data")
public class AuthData {

    public AuthData() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_data_id")
    public Integer id;

    @Column(name = "login")
    public String login;
    @Column(name = "password")
    public String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}