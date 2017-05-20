package com.example.kongpon_macbook.touchyou;

import com.example.kongpon_macbook.touchyou.client.AbstractClient;


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
        String[] data = ((String) o).split("=");
        String header = data[0];
        String body = data[1];
        switch (header) {
            case "SYNC_REQUEST":
                Controller.getInstance().remoteActivity.sync();
                break;
            case "SYNC_RESPONSE":
                Controller.getInstance().commands.add(body);
                break;
            case "SYNC_END":
                Controller.getInstance().notifyRemoteActivity();
                break;
            default:
                break;
        }

//          if (o instanceof String) {
//            String data = (String) o;
//            if (data.equals("SYNC_REQUEST")) {
//                Controller.getInstance().remoteActivity.sync();
//            } else if (data.contains("SYNC_RESPONSE")) {
//                String command = data.split("=")[1];
//                Controller.getInstance().commands.add(command);
//            } else if (data.equals("SYNC_END")) {
//                Controller.getInstance().notifyRemoteActivity();
//            }
//        }
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
