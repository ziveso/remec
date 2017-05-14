package com.example.kongpon_macbook.touchyou;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ResourceBundle;

public class RemoteActivity extends Activity {
    ProgressDialog pd;
    RelativeLayout r;
    private static final CommandClickListener listener = new CommandClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);
        r = (RelativeLayout) findViewById(R.id.activity_remote);
        System.out.println("On create Remote");
        Controller.getInstance().remoteActivity = this;
        pd = new ProgressDialog(this);
        pd.setTitle("Synchronizing");
        pd.setMessage("Downloading content...");
        sync();

    }

    public void sync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Point p = new Point();
                getWindowManager().getDefaultDisplay().getSize(p);
                String size = p.x + ";" + p.y;
                Controller.getInstance().sendMessage("SYNC_REQUEST=" + size);
            }
        }).start();
    }


    public void update() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pd.show();
                r.removeAllViews();
                for (String commands : Controller.getInstance().commands) {
                    String[] command = commands.split(";");
                    String combination = command[0];
                    int mode = Integer.parseInt(command[1]);
                    int width = Integer.parseInt(command[2]);
                    int height = Integer.parseInt(command[3]);
                    int x = Integer.parseInt(command[4]);
                    int y = Integer.parseInt(command[5]);
                    String text = command[6];
                    RelativeLayout.LayoutParams rect = new RelativeLayout.LayoutParams(width, height);
                    rect.leftMargin = x;
                    rect.topMargin = y;
                    final Button button = new Button(RemoteActivity.this);
                    button.setLayoutParams(rect);
                    button.setTag(mode + ";" + combination);
                    button.setOnTouchListener(listener);
                    button.setText(text);
                    r.addView(button);
                }
                Controller.getInstance().commands.clear();
                System.out.println("sync finished");
                pd.dismiss();
            }
        });
    }

    private static final class CommandClickListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Button button = (Button) v;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Controller.getInstance().sendMessage("PRESS="+button.getTag());
                    break;
                case MotionEvent.ACTION_UP:
                    Controller.getInstance().sendMessage("RELEASE="+button.getTag());
                    break;
            }
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Controller.getInstance().closeConnection();
    }


}
