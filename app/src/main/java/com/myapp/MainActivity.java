package com.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
// import android.os.Handler;
//import org.json.JSONException;
//import java.io.IOException;

public class MainActivity extends Activity {
    static public int uid = 0;
    public FragmentManager fm;
    public RoomListFragment roomlist;
//    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences mPrefs = getSharedPreferences("label", 0);
        uid = mPrefs.getInt("uid", 0);

        clientSocket cs = new clientSocket();
        cs.setPrefs(mPrefs);
        Thread cThread = new Thread(cs);
        if (uid==0) {
            cs.setReq("method", "new");
        } else {
            cs.setReq("method", "online");
            cs.setReq("id", uid);
        }
        cThread.start();

        fm = getFragmentManager();
        roomlist = (RoomListFragment)fm.findFragmentById(R.id.activity_main);
        if ( roomlist == null ) {
            roomlist = new RoomListFragment();
            fm.beginTransaction().add(R.id.activity_main, roomlist)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult (int req, int res, Intent data) {
        if (res == RESULT_OK) {
            int roomid = data.getIntExtra("new.room", 0);
            Log.d("**roomid on main**", roomid + "");
            roomlist.addRoom(roomid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == R.id.menu_add) {
           Intent i = new Intent(this, CreateTabActivity.class);
           startActivityForResult(i, 0);
//           startActivity(i);
           return true;
       }else {
           return super.onOptionsItemSelected(item);
       }
    }

}
