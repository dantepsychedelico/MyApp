package com.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by danteubu on 3/15/15.
 */
public class sqliteController {
    private SQLiteDatabase db;
    private sqliteModel dbModel;

    public sqliteController(Context context) {
        dbModel = new sqliteModel(context);
    }
    public void open(){
        db = dbModel.getWritableDatabase();
    }
    public schemaRoom roomCreate(int roomid, String roomname, int createtime, int alivetime) {
        schemaRoom room = new schemaRoom(roomid, roomname, createtime, alivetime);
        dbModel.insertRoom(db, room);
        return room;
    }
    public List<schemaRoom> getRooms() {
        if (db==null) {
            open();
        }
        return dbModel.getRooms(db);
    }
    public void roomRead() {
    }
    public void roomUpdate() {
    }
    public void roomDelete() {
    }
    public void addMsg(schemaMsg msg, int roomid) {
        dbModel.insertMsg(db, msg, roomid);
    }
    public List<schemaMsg> getMsgs(int roomId) {
        return dbModel.getMsg(db, roomId);
    }
    public void personCreate() {
    }
}
