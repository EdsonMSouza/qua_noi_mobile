package com.uni9.webservices;

import java.io.Serializable;

public class Jobs implements Serializable {
    private int id;
    private int user_id;
    private String description;
    private String date;
    private String type;

    public Jobs() {
    }

    public Jobs(int id, int user_id, String description, String date, String type) {
        this.id = id;
        this.user_id = user_id;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
