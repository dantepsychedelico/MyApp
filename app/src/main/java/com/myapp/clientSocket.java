package com.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by danteubu on 3/8/15.
 */
//public class clientSocket implements Runnable{
//    private Socket sock;
//    private JSONObject res;
//    private JSONObject req = new JSONObject();
//    private SharedPreferences mPrefs;
//    private SharedPreferences.Editor mEditor;
//    private Activity activity;
//    public void run() {
//        try {
//            sock = new Socket("192.168.11.4", 9999);
//            OutputStream outToServer = sock.getOutputStream();
//            DataOutputStream out = new DataOutputStream(outToServer);
//            out.writeUTF(req.toString());
//            InputStream inFromServer = sock.getInputStream();
//            DataInputStream in = new DataInputStream(inFromServer);
//            res = new JSONObject(in.readUTF());
//            Log.d("in.readUTF", res.toString());
//            if (req.getString("method").equals("new")) {
//                MainActivity.uid = getUid();
//                mEditor.putInt("uid", getUid()).commit();
//            }
//            if (req.getString("method").equals("newroom")) {
//                Intent i = activity.getIntent();
//                i.putExtra("new.room", res.getInt("room"));
//                activity.setResult(activity.RESULT_OK, i);
//                activity.finish();
//            }
//            if (req.getString("chat").equals("chat")) {
//                // add chat to chatlist
//            }
//        } catch (IOException e) {
//            Log.d("IOException", e.toString());
//        } catch (JSONException e) {
//            Log.d("JSONException", e.toString());
//        }
//    }
//    public void setReq(String key, String value) {
//        try {
//            req.put(key, value);
//        }catch (JSONException e) {
//            Log.d("JSONException", e.toString());
//        }
//    }
//    public void setReq(String key, int value) {
//        try {
//            req.put(key, value);
//        }catch (JSONException e) {
//            Log.d("JSONException", e.toString());
//        }
//    }
//    public void setReq(String key, boolean value) {
//        try {
//            req.put(key, value);
//        }catch (JSONException e) {
//            Log.d("JSONException", e.toString());
//        }
//    }
//    public int getUid() {
//        try {
//            return res.getInt("id");
//        } catch (JSONException e) {
//            Log.d("JSONException", e.toString());
//            return 0;
//        }
//    }
//    public void setPrefs(SharedPreferences sharedpreferences) {
//        mPrefs = sharedpreferences;
//        mEditor = mPrefs.edit();
//    }
//    public void setActivity(Activity act) {
//        activity = act;
//    }
//}
