package com.example.kongpon_macbook.touchyou;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.kongpon_macbook.touchyou.client.AbstractClient;

import java.util.ResourceBundle;


/**
 * Created by kongpon-macbook on 5/6/2017 AD.
 */

public class TCPClient extends AbstractClient {

    public TCPClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object o) {
        System.out.println(o);
        String data = (String) o;
        if (data.equals("SYNC_REQUEST")) {
            Controller.getInstance().remoteActivity.sync();
        }
        else if (data.contains("SYNC_RESPONSE")) {
            String command = data.split("=")[1];
            Controller.getInstance().commands.add(command);
        } else if (data.equals("SYNC_END")) {
            Controller.getInstance().notifyRemoteActivity();
        }
    }


    @Override
    protected void connectionClosed() {
        super.connectionClosed();
        System.out.println("Connection closed");
        Controller.getInstance().remoteActivity.finish();
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();
    }
}
