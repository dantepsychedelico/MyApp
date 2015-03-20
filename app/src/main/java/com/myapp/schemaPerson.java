package com.myapp;

/**
 * Created by danteubu on 3/15/15.
 */
public class schemaPerson {
    private int uid;
    private String name;
    public schemaPerson(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
