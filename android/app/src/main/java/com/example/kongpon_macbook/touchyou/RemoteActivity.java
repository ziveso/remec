package com.example.kongpon_macbook.touchyou;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class RemoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MainActivity.client.sendToServer("Hi");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
