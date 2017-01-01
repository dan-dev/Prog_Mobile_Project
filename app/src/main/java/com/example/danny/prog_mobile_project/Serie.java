package com.example.danny.prog_mobile_project;

/**
 * Created by Danny on 31/12/2016.
 */

public class Serie {
    int id;
    String state;

    public Serie(){ }

    public Serie(int id, String state) {
        this.id = id;
        this.state = state;
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




}
