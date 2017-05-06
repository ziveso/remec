package com.example.kongpon_macbook.touchyou;

import com.lloseng.ocsf.client.AbstractClient;

/**
 * Created by kongpon-macbook on 5/6/2017 AD.
 */

public class TCPClient extends AbstractClient {
    public TCPClient(String host, int port) {
        super(host, port);
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
    }
}
