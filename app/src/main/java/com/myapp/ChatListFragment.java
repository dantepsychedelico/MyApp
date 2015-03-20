package com.myapp;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ChatListFragment extends ListFragment {
    private ArrayList<String> chats = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private sqliteController dbCtrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbCtrl = Client.getDbCtrl();
        chats = (ArrayList) dbCtrl.getMsgs(((RoomActivity)getActivity()).getRoomId());
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, chats);
        setListAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.chat_list, parent, false);
        return v;
    }

    public void addChat(schemaMsg msg, int roomId) {
        dbCtrl.addMsg(msg, roomId);
        adapter.add(msg.toString());
    }
}
