package com.example.kongpon_macbook.touchyou;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ResourceBundle;

public class RemoteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        System.out.println("On create Remote");
        Controller.getInstance().remoteActivity = this;
        System.out.println(Controller.getInstance().commands);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Controller.getInstance().closeConnection();
    }


}
