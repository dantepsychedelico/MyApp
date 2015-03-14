package com.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by danteubu on 3/7/15.
 */
public class CreateTabActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_tab_activity);
        final Button mCreate = (Button)findViewById(R.id.mCreate);
        final Button mAdd = (Button)findViewById(R.id.mAdd);
        final EditText mAddRoomId = (EditText)findViewById(R.id.editRoomId);
        final Editable text = mAddRoomId.getText();
        Client.getInstance().setCurrentActivity(this);

        mCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Client.getInstance().setReq("method", "newroom").send();
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Client.getInstance().setReq("method", "join")
                        .setReq("room", Integer.parseInt(text.toString())).send();
                text.clear();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

}
