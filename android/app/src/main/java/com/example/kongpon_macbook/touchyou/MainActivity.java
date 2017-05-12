package com.example.kongpon_macbook.touchyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
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
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
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
    private int i;
    private String hostName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  connectButton = (Button) findViewById(R.id.connectButton);
        //  ipEditText = (EditText) findViewById(R.id.ipEditText);
        listView = (ListView) findViewById(R.id.listView);
        List<String> availableHost = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableHost);
        listView.setAdapter(adapter);
        findAvailableHost(availableHost);
    }

    private void findAvailableHost(final List<String> availableHost) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeout = 300;
                String myip = wifiIpAddress(MainActivity.this);
                String subnet = myip.substring(0, myip.lastIndexOf('.') + 1);
                Socket req;
                for (int i = 100; i < 120; i++) {
                    req = new Socket();
                    try {
                        req.setSoTimeout(300);
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    InetSocketAddress host = new InetSocketAddress(subnet + i, PORT);
                    hostName = host.getHostName();
                    try {
                        req.connect(host, timeout);

                        runOnUiThread(new Runnable() {
                            public void run() {
                                availableHost.add(hostName);
                            }
                        });
                    } catch (IOException e) {
                        try {
                            req.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "couldn't connect to " + hostName, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        }).

                start();

    }


    protected String wifiIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endian if needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
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
