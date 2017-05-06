package com.example.kongpon_macbook.touchyou;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.lloseng.ocsf.client.AbstractClient;

/**
 * Created by kongpon-macbook on 5/6/2017 AD.
 */

public class TCPClient extends AbstractClient {

    private Context context;

    public TCPClient(String host, int port, Context context) {
        super(host, port);
        this.context = context;
    }

    @Override
    protected void handleMessageFromServer(Object o) {

    }

    @Override
    protected void connectionClosed() {
        super.connectionClosed();
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();
        Intent intent = new Intent(context, RemoteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }
}
