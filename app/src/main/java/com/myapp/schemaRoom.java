package com.myapp;

import android.util.Log;

import java.util.List;

/**
 * Created by danteubu on 3/15/15.
 */
public class schemaRoom {
    private int roomId;
    private String roomName;
    private int createTime;
    private int aliveTime;
    private List<Integer> members;
    public schemaRoom(int roomId, String roomName, int createTime, int aliveTime) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.createTime = createTime;
        this.aliveTime = aliveTime;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public String toString() {
        return roomName;
    }

    public int getCreateTime() {
        return createTime;
    }

    public int getAliveTime() {
        return aliveTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomId() {
        return roomId;
    }
}
