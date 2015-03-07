package com.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by danteubu on 3/7/15.
 */
public class RoomActivity extends Activity {
    private String roomId = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity);
        roomId = getIntent().getStringExtra("hello.world");
        Log.d("ROOM.LOG", roomId);
    }
}
