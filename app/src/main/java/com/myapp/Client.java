package com.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private Thread sender;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private static final Client client = new Client();
    public static Client getInstance() {return client;}
    private Activity currentActivity;
    private int uid = 0;
    public void send() {
        sender = new Thread(new Runnable(){
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
        sender.start();
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
        sender = new Thread(new Runnable(){
            public void run() {
                try{
                    sock = new Socket("192.168.11.4", 9999);
                    uid = mPrefs.getInt("uid", 0);
                    if (uid == 0) {
                        setReq("method", "new").send();
                    }else {
                        setReq("method", "online").setReq("id", uid).send();
                    }
                    input = new DataInputStream(sock.getInputStream());
                    while (true) {
                        res = new JSONObject(input.readUTF());
                        switch (req.getString("method")) {
                            case "new":
                                mEditor.putInt("uid", res.getInt("id")).commit();
                                break;
                            case "newroom":
                                Intent i = currentActivity.getIntent();
                                i.putExtra("new.room", res.getInt("room"));
                                currentActivity.setResult(currentActivity.RESULT_OK, i);
                                currentActivity.finish();
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
        sender.start();
    }
    public Client renewReq() {
        this.req = new JSONObject();
        return this;
    }
    public Client setSharedPreferences(SharedPreferences mPrefs) {
        if (this.mPrefs == null) {
            this.mPrefs = mPrefs;
            this.mEditor = mPrefs.edit();
        }
        return this;
    }
    public Client setCurrentActivity(Activity activity) {
        this.currentActivity = activity;
        return this;
    }
    public void stop() {
        client.stop();
    }
}

