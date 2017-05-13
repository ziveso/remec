package com.example.kongpon_macbook.touchyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    //    public Button connectButton;
    //    public EditText ipEditText;
    private ListView listView;
    final List<String> availableHost = new ArrayList<>();
    ArrayAdapter<String> adapter;
    public static TCPClient client;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  connectButton = (Button) findViewById(R.id.connectButton);
        //  ipEditText = (EditText) findViewById(R.id.ipEditText);

        /* Initialize views */
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findServers();
            }
        });
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, availableHost);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String host = (String) parent.getAdapter().getItem(position);
                System.out.println(host);
                connect(host);
            }
        });

        findServers();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_item) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Connect to Host");
            builder.setMessage("Enter host IP Address:");
            builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void findServers() {
        swipe.setRefreshing(true);
        new AsyncTask<String, Integer, String[]>() {
            @Override
            protected String[] doInBackground(String... params) {
                return ServerFinder.getAvailableHost();
            }

            @Override
            protected void onPostExecute(String[] strings) {
                super.onPostExecute(strings);
                System.out.println(Arrays.toString(strings));
                System.out.println("On post execution");
                availableHost.addAll(Arrays.asList(strings));
                adapter.notifyDataSetChanged();
                if (availableHost.isEmpty())
                    Toast.makeText(MainActivity.this, "Cannot find any host", Toast.LENGTH_SHORT).show();
                swipe.setRefreshing(false);

            }
        }.execute();
    }


    private void connect(String host) {
        new AsyncTask<String, Integer, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                client = new TCPClient(params[0], PORT, MainActivity.this);
                try {
                    client.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(host);
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
