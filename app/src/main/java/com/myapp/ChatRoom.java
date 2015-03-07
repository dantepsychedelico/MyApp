package com.myapp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by danteubu on 3/1/15.
 */
public class ChatRoom {
    public String member;
    public int roomId;
    public ArrayList<String> history = new ArrayList<>();
    public int deadTime;
    public ChatRoom() {

    }
    public String getLast() {
        if ( history.size() != 0 ) {
            return history.get(history.size()-1);
        } else {
            return "";
        }
    }
}
