package com.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by danteubu on 3/12/15.
 */
public class Client{
    private Socket sock;
    private DataOutputStream output;
    private DataInputStream input;
    private JSONObject req = new JSONObject();
    private JSONObject res;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private static final Client client = new Client();
    public static Client getInstance() {return client;}
    private Activity currentActivity;
    private String activityClass;
    private static sqliteController dbCtrl;
    private int uid = 0;
    public void send() {
        Thread thread = new Thread(new Runnable(){
            public void run() {
                try{
                    setReq("id", uid);
                    output = new DataOutputStream(sock.getOutputStream());
                    output.writeUTF(req.toString());
                } catch (IOException e) {
                    Log.d("IOException", e.toString());
                }
            }
        });
        thread.start();
    }

    public Client setReq(String key, String value) {
        try {
            req.put(key, value);
            return this;
        }catch (JSONException e) {
            Log.d("JSONException", e.toString());
            return this;
        }
    }
    public Client setReq(String key, int value) {
        try {
            req.put(key, value);
            return this;
        }catch (JSONException e) {
            Log.d("JSONException", e.toString());
            return this;
        }
    }
    public Client setReq(String key, boolean value) {
        try {
            req.put(key, value);
            return this;
        }catch (JSONException e) {
            Log.d("JSONException", e.toString());
            return this;
        }
    }

    public void startup() {
        Thread thread = new Thread(new Runnable(){
            public void run() {
                try{
//                    sock = new Socket("122.116.90.83", 30000);
                    sock = new Socket("192.168.11.4", 30000);
                    uid = mPrefs.getInt("uid", 0);
                    if (uid == 0) {
                        setReq("method", "new").send();
                    }else {
                        setReq("method", "online").setReq("id", uid).send();
                    }
                    input = new DataInputStream(sock.getInputStream());
                    while (true) {
                        res = new JSONObject(input.readUTF());
                        Log.d("res", res.toString());
                        switch (res.getString("method")) {
                            case "new":
                                uid = res.getInt("id");
                                mEditor.putInt("uid", res.getInt("id")).commit();
                                break;
                            case "newroom":
                            case "join":
                                Intent i = currentActivity.getIntent();
                                i.putExtra("roomid", res.getInt("roomid"))
                                        .putExtra("roomname", res.getString("roomname"))
                                        .putExtra("createtime", res.getInt("createtime"))
                                        .putExtra("alivetime", res.getInt("alivetime"));
                                dbCtrl.roomCreate(res.getInt("roomid"), res.getString("roomname"),
                                        res.getInt("createtime"), res.getInt("alivetime"));
                                currentActivity.setResult(currentActivity.RESULT_OK, i);
                                currentActivity.finish();
                                break;
                            case "chat":
                                if (uid != res.getInt("id")) {
                                    if (activityClass.equals("com.myapp.RoomActivity")) {
                                        final RoomActivity roomActivity = (RoomActivity) currentActivity;
                                        roomActivity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    schemaMsg msg = new schemaMsg(res.getInt("id"), res.getString("type"),
                                                            res.getString("content"), res.getInt("time"));
                                                    roomActivity.chatlist.addChat(msg, res.getInt("roomid"));
                                                } catch (JSONException e) {
                                                    Log.d("JSONException", e.toString());
                                                }
                                            }
                                        });
                                    } else {
                                        schemaMsg msg = new schemaMsg(res.getInt("id"), res.getString("type"),
                                                res.getString("content"), res.getInt("time"));
                                        dbCtrl.addMsg(msg, res.getInt("roomid"));
                                    }
                                }
                                break;
                            case "check":
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IOException e) {
                    Log.d("IOException", e.toString());
                } catch (JSONException e) {
                    Log.d("JSONException", e.toString());
                }
            }
        });
        thread.start();
    }
    public Client setSharedPreferences(SharedPreferences mPrefs) {
        if (this.mPrefs == null) {
            this.mPrefs = mPrefs;
            this.mEditor = mPrefs.edit();
        }
        return this;
    }
    public Client setdbCtrl(sqliteController dbCtrl) {
        this.dbCtrl = dbCtrl;
        return this;
    }
    static public sqliteController getDbCtrl() {
        return dbCtrl;
    }
    public Client setCurrentActivity(Activity activity, String activityClass) {
        this.currentActivity = activity;
        this.activityClass = activityClass;
        return this;
    }
    public Client clearReq() {
        req = new JSONObject();
        return this;
    }
    public void stop() {
        client.stop();
    }
}

