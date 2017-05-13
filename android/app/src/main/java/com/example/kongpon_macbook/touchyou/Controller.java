package com.example.kongpon_macbook.touchyou;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;

/**
 * Created by kongpon-macbook on 5/13/2017 AD.
 */

public class Controller {
    TCPClient client;
    RemoteActivity remoteActivity;
    MainActivity mainActivity;

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

    public void returnToMainActivity() {
        remoteActivity.onBackPressed();
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
