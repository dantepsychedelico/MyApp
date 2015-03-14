package com.myapp;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by danteubu on 3/2/15.
 */
public class RoomListFragment extends ListFragment {
    private ArrayList<String> rooms = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, rooms);
        setListAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.room_list, parent, false);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getActivity(), RoomActivity.class);
        i.putExtra("roomid", (String)(getListAdapter()).getItem(position));
        startActivityForResult(i, 0);
    }

    public void addRoom(int roomid) {
        adapter.add("room"+roomid);
    }
}
