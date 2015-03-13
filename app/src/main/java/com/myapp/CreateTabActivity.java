package com.myapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by danteubu on 3/7/15.
 */
public class CreateTabActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_tab_activity);
        final Button mCreate = (Button) findViewById(R.id.mCreate);
        Client.getInstance().setCurrentActivity(this);

        mCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Client.getInstance().setReq("method", "newroom").send();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

}
