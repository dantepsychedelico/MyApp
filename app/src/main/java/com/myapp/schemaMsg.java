package com.myapp;

/**
 * Created by danteubu on 3/16/15.
 */
public class schemaMsg {
    private int uid;
    private String type;
    private String content;
    private int time;
    public schemaMsg(int uid, String type, String content, int time) {
        this.uid = uid;
        this.type = type;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return content;
    }

    public int getUid() {
        return uid;
    }
    public String getType() {
        return type;
    }
    public String getContent() {
        return content;
    }
    public int getTime() {
        return time;
    }
}
