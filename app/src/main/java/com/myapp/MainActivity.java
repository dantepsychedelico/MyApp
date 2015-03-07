package com.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.activity_main);
        if ( fragment == null ) {
            fragment = new RoomListFragment();
            fm.beginTransaction().add(R.id.activity_main, fragment)
                    .commit();
        }

//        TabHost tabs = (TabHost)findViewById(android.R.id.tabhost);
//        tabs.setup();
//
//        TabHost.TabSpec spec1 = tabs.newTabSpec("tab1");
//        spec1.setIndicator("Chat Lists");
//        spec1.setContent(R.id.tab1);
//        tabs.addTab(spec1);
//
//        TabHost.TabSpec spec2 = tabs.newTabSpec("tab2");
//        spec2.setIndicator("Create Chat");
//        spec2.setContent(R.id.tab2);
//        tabs.addTab(spec2);
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
           i.putExtra("hello.world", 7);
           startActivityForResult(i, 0);
           return true;
       }else {
           return super.onOptionsItemSelected(item);
       }
    }
}
