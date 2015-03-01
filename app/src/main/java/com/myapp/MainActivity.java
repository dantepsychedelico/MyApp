package com.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabs = (TabHost)findViewById(R.id.tabHost);

        tabs.setup();
        TabHost.TabSpec spec1 = tabs.newTabSpec("tag1");

        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Frined Lists");
        tabs.addTab(spec1);

        TabHost.TabSpec spec2 = tabs.newTabSpec("tag2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Create Chart");
        tabs.addTab(spec2);
    }
}
