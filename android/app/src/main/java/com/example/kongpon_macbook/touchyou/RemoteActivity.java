package com.example.kongpon_macbook.touchyou;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ResourceBundle;

public class RemoteActivity extends Activity {
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        System.out.println("On create Remote");
        Controller.getInstance().remoteActivity = this;
        pd = new ProgressDialog(this);
        pd.setTitle("Synchronizing");
        pd.setMessage("Downloading content...");
    }

    public void update() {
        pd.show();
        for (String commands : Controller.getInstance().commands) {
            String[] command = commands.split(";");
            String combination = command[0];
            int mode = Integer.parseInt(command[1]);
            int width = Integer.parseInt(command[2]);
            int height = Integer.parseInt(command[3]);
            int x = Integer.parseInt(command[4]);
            int y = Integer.parseInt(command[5]);
            final RelativeLayout r = (RelativeLayout) findViewById(R.id.activity_remote);
            RelativeLayout.LayoutParams rect = new RelativeLayout.LayoutParams(width, height);
            rect.leftMargin = x;
            rect.topMargin = y;
            final Button button = new Button(RemoteActivity.this);
            button.setLayoutParams(rect);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    r.addView(button);
                }
            });
        }
        pd.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Controller.getInstance().closeConnection();
    }


}
