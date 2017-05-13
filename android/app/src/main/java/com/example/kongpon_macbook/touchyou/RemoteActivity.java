package com.example.kongpon_macbook.touchyou;

import android.app.Activity;
import android.os.Bundle;

public class RemoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Controller.getInstance().sendMessage("Hi");
            }
        }).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Controller.getInstance().closeConnection();
    }
}
