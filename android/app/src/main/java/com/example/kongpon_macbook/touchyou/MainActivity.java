package com.example.kongpon_macbook.touchyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int PORT = 3000;
    public static final int TIMEOUT = 1000;
    //    public Button connectButton;
    //    public EditText ipEditText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  connectButton = (Button) findViewById(R.id.connectButton);
        //  ipEditText = (EditText) findViewById(R.id.ipEditText);
        listView = (ListView) findViewById(R.id.listView);
        final List<String> availableHost = new ArrayList<>();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Log.d("test" , height + " " + width);
        new Thread(new Runnable() {
            @Override
            public void run() {
//                    String local = InetAddress.getLocalHost().getHostAddress();
//                String local = getLocalIpAddress();
//                String[] ip_component = local.sgplit("\\.");
//                String subnet = ip_component[0] + "." + ip_component[1] + "." + ip_component[2] + ".";
//                String subnet = "192.168.2.";
//                for (int i = 106; i <= 255; i++) {
//                    String host = subnet + i;
//                    try {
//                        Socket socket = new Socket();
//                        // This limits the time allowed to establish a connection in the case
//                        // that the connection is refused or server doesn't exist.
//                        Log.d("Request send", "Requesting " + host);
//                        TCPClient req = new TCPClient(host, PORT, MainActivity.this);
//                        req.openConnection();
//                        // Connected
//                        availableHost.add(String.valueOf(socket.getInetAddress()));
//                    } catch (IOException e) {
//                        // Can't connect
//                    }
//                }
                WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();

                availableHost.add(String.valueOf(wifiInfo.getIpAddress()));
            }
        }).start();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableHost);
        listView.setAdapter(adapter);
    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
}
