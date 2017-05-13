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

    private Context context;
    private boolean sync;
    private int syncState;

    public TCPClient(String host, int port, Context context) {
        super(host, port);
        this.context = context;
    }

    @Override
    protected void handleMessageFromServer(final Object o) {
        final String packet = (String) o;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Controller.getInstance().remoteActivity.createButton("adsf");
            }
        }.execute();
    }


    @Override
    protected void connectionClosed() {
        super.connectionClosed();
        System.out.println("Connection closed");
        Controller.getInstance().returnToMainActivity();
    }

    @Override
    protected void connectionEstablished() {
        super.connectionEstablished();
        Intent intent = new Intent(context, RemoteActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Controller.getInstance().client = this;
        MainActivity.pd.dismiss();
        context.startActivity(intent);
    }
}
