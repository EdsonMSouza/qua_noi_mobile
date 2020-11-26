package com.uni9.webservices;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String user;
    private String password;
    private String type;

    public User() {
    }

    public User(int id, String name, String user, String password, String type) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.password = password;
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return id + "\n" + name + "\n";
    }
}
