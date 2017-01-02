package com.example.danny.prog_mobile_project;

public class Serie {
    int id;
    String state;
    String name;

    public Serie(){ }

    public Serie(int id, String state, String name) {
        this.id = id;
        this.state = state;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}