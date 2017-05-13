package com.example.kongpon_macbook.touchyou;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongpon-macbook on 5/13/2017 AD.
 */

public class Controller {
    TCPClient client;
    RemoteActivity remoteActivity;
    MainActivity mainActivity;
    List<String> commands = new ArrayList<>();

    private static Controller controller = new Controller();

    private Controller() {
    }

    public void sendMessage(String msg) {
        try {
            client.sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyRemoteActivity() {
        remoteActivity.update();
    }
    public void closeConnection() {
        try {
            client.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Controller getInstance() {
        return controller;
    }
}
