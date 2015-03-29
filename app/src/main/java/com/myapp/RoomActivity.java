package com.myapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.Callable;

/**
 * Created by danteubu on 3/7/15.
 */
public class RoomActivity extends Activity {
    private int roomId;
    public FragmentManager fm;
    public ChatListFragment chatlist;
//    private sqliteController dbCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        dbCtrl = new sqliteController(this);
//        dbCtrl.open();
        setContentView(R.layout.room_activity);
        roomId = getIntent().getIntExtra("roomid", 0);

        fm = getFragmentManager();
        chatlist = (ChatListFragment)fm.findFragmentById(R.id.chat_list);
        if ( chatlist == null ) {
            chatlist = new ChatListFragment();
            fm.beginTransaction().add(R.id.room_activity, chatlist)
                    .commit();
        }
        final Button bSend = (Button)findViewById(R.id.buttonSend);
        final EditText inputText = (EditText)findViewById(R.id.input_text);
        final Editable text = inputText.getText();
        bSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Client.getInstance()
                        .clearReq()
                        .setReq("method", "chat")
                        .setReq("mtype", "text")
                        .setReq("roomid", roomId)
                        .setReq("content", text.toString()).send();
                chatlist.addChat(new schemaMsg(roomId, "content", text.toString(), 0), roomId);
                text.clear();
            }
        });
    }

    @Override
    protected void onPause() {
        Log.d("pause", "***");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("stop", "***");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("resume", "***");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.d("start", "***");
        Client.getInstance().setCurrentActivity(this, this.getClass().getName());
        super.onStart();
    }

    public int getRoomId () {
        return roomId;
    }
}
