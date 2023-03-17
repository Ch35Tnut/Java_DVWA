package com.chestnut.entity;

import java.sql.Timestamp;

public class User {
    private Integer user_id;
    private String first_name;
    private String last_name;
    private String user;
    private String password;
    private String avatar;
    private Timestamp last_login;
    private Integer filed_login;

    public User(Integer user_id, String first_name, String last_name, String user, String password, String avatar, Timestamp last_login, Integer filed_login) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.user = user;
        this.password = password;
        this.avatar = avatar;
        this.last_login = last_login;
        this.filed_login = filed_login;
    }

    public User() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    public Integer getFiled_login() {
        return filed_login;
    }

    public void setFiled_login(Integer filed_login) {
        this.filed_login = filed_login;
    }
}
