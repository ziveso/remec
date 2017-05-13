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
        Controller.getInstance().remoteActivity = this;
    }

    public void createButton(String packet) {
        System.out.println(packet);
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//               String[] command = packet.split(";");
//               String combination = command[0];
//               int mode = Integer.parseInt(command[1]);
//               int width = Integer.parseInt(command[2]);
//               int height = Integer.parseInt(command[3]);
//               int x = Integer.parseInt(command[4]);
//               int y = Integer.parseInt(command[5]);
//               final RelativeLayout r = (RelativeLayout) findViewById(R.id.activity_remote);
//               RelativeLayout.LayoutParams rect = new RelativeLayout.LayoutParams(width, height);
//               rect.leftMargin = x;
//               rect.topMargin = y;
//               final Button button = new Button(RemoteActivity.this);
//               button.setLayoutParams(rect);
//               runOnUiThread(new Runnable() {
//                   @Override
//                   public void run() {
//                       r.addView(button);
//                   }
//               });
//           }
//       }).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Controller.getInstance().closeConnection();
    }


}
