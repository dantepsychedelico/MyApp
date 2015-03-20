package com.myapp;

/**
 * Created by danteubu on 3/15/15.
 * will do: change to singleton class
 */

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class sqliteModel extends SQLiteOpenHelper {
    private static final String dbname = "knock.db";
    private static final int dbversion = 1;
    private SharedPreferences mPrefs;
    private String[] roomsCol = {"roomid", "roomname", "createtime", "alivetime"};
    private String[] msgsCol = {"roomid", "type", "content", "binary", "uid", "time"};

    public sqliteModel(Context context) {
        super(context, dbname, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE rooms(id INTEGER PRIMARY KEY, roomid INTEGER UNIQUE, roomname TEXT NOT NULL, "+
                "createtime INTEGER NOT NULL, alivetime INTEGER NOT NULL);");
        database.execSQL("CREATE TABLE msgs(id INTEGER PRIMARY KEY, roomid INTEGER, type TEXT, content TEXT, "+
                "binary BLOB, uid INTEGER, time INTEGER);");
        database.execSQL("CREATE TABLE persons(id INTEGER PRIMARY KEY, uid INTEGER, name TEXT, image BLOB);");
        database.execSQL("CREATE TABLE roomMembers(id INTEGER PRIMARY KEY, roomid INTEGER, uid INTEGER);");
    }

    public void insertRoom(SQLiteDatabase database, schemaRoom room) {
        ContentValues cv = new ContentValues();
        cv.put("roomid", room.getRoomId());
        cv.put("roomname", room.getRoomName());
        cv.put("createtime", room.getCreateTime());
        cv.put("alivetime", room.getAliveTime());
        database.insert("rooms", "id", cv);
    }
    public List<schemaRoom> getRooms(SQLiteDatabase database) {
        Cursor cursor = database.query("rooms", roomsCol, null, null, null, null, null);
        List<schemaRoom> rooms = new ArrayList<schemaRoom>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            rooms.add(new schemaRoom(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            cursor.moveToNext();
        }
        return rooms;
    }
    public void insertMsg(SQLiteDatabase database, schemaMsg msg, int roomid) {
        ContentValues cv = new ContentValues();
        cv.put("roomid", roomid);
        cv.put("type", msg.getType());
        cv.put("content", msg.getContent());
        cv.put("uid", msg.getUid());
        cv.put("time", msg.getTime());
        database.insert("msgs", "id", cv);
    }
    public List<schemaMsg> getMsg(SQLiteDatabase database, int roomId) {
        Cursor cursor = database.query("msgs", msgsCol, "roomid==?", new String[] {Integer.toString(roomId)}, null, null, "time");
        List<schemaMsg> msgs = new ArrayList<schemaMsg>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            msgs.add(new schemaMsg(cursor.getInt(4), cursor.getString(1), cursor.getString(2), cursor.getInt(5)));
            cursor.moveToNext();
        }
        return msgs;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //pass
    }

}
