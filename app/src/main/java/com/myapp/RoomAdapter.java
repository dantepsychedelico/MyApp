package com.myapp;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * Created by danteubu on 3/3/15.
 */
public class RoomAdapter extends ArrayAdapter<ChatRoom> {

    public RoomAdapter(Context context, ArrayList<ChatRoom> rooms) {
       super(context, R.layout.room_fragment, rooms);
    }
}
