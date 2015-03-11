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
    private String roomId = new String();
    public FragmentManager fm;
    public ChatListFragment chatlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_activity);
        roomId = getIntent().getStringExtra("roomid");

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
                clientSocket cs = new clientSocket();
                Thread cThread = new Thread(cs);
                cs.setReq("method", "chat");
                cs.setReq("id", MainActivity.uid);
                cs.setReq("room", Integer.parseInt(roomId.replace("room", "")));
                cs.setReq("text", text.toString());
                cThread.start();
                chatlist.addChat(text.toString());
                text.clear();
            }
        });

    }
}
