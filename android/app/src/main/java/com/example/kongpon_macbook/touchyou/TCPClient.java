package com.example.kongpon_macbook.touchyou;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.kongpon_macbook.touchyou.client.AbstractClient;


/**
 * Created by kongpon-macbook on 5/6/2017 AD.
 */

public class TCPClient extends AbstractClient {

    public TCPClient(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(final Object o) {
        final String packet = (String) o;
        System.out.println(o);
        System.out.println(Controller.getInstance().commands.add((String) o));
    }


    @Override
    protected void connectionClosed() {
        super.connectionClosed();
        System.out.println("Connection closed");
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();
    }
}
