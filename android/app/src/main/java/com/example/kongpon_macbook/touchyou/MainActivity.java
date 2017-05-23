package com.example.kongpon_macbook.touchyou;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int PORT = 5910;
    static ProgressDialog pd;
    private final List<Host> availableHost = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<Host> adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller.getInstance().mainActivity = this;
        /* Initialize views */
        setTitle("Select a Host Computer");
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                findServers();
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        adapter = new ArrayAdapter<Host>(this, android.R.layout.simple_list_item_2, android.R.id.text1, availableHost) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text2.setTextColor(Color.GRAY);

                text1.setText(availableHost.get(position).getName());
                text2.setText(availableHost.get(position).getAddress());

                return view;
            }
        };
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Host host = (Host) parent.getAdapter().getItem(position);
                System.out.println(host);
                connect(host);
            }
        });
        pd = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            final InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
            final View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(mView);
            final EditText editText = (EditText) mView.findViewById(R.id.userInputDialog);

            builder.setCancelable(true)
                    .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            String address = editText.getText().toString();
                            connect(new Host(null, address));
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            dialogBox.cancel();
                        }
                    });

            AlertDialog dialog = builder.create();
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    editText.post(new Runnable() {
                        @Override
                        public void run() {
                            keyboard.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                        }
                    });
                }
            });
            dialog.show();
            editText.requestFocus();

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void findServers() {
        new AsyncTask<String, Integer, Host[]>() {
            @Override
            protected void onPreExecute() {
                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            protected Host[] doInBackground(String... params) {
                return ServerFinder.getAvailableHost();
            }

            @Override
            protected void onPostExecute(Host[] strings) {
                super.onPostExecute(strings);
                System.out.println(Arrays.toString(strings));
                System.out.println("On post execution");
                availableHost.clear();
                availableHost.addAll(Arrays.asList(strings));
                if (availableHost.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Could not find any host", Toast.LENGTH_LONG).show();
                    availableHost.add(new Host("Could not find any available servers", "Swipe down to refresh"));
                }
                adapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        }.execute();
    }

    private void connect(Host host) {
        if (host.getAddress() == null) return;
        pd.setTitle("Connecting to " + host.toString());
        pd.setMessage("Connecting...");
        pd.show();
        new AsyncTask<Host, Void, Void>() {
            @Override
            protected Void doInBackground(Host... params) {
                TCPClient client = new TCPClient(params[0].getAddress(), PORT);
                try {
                    System.out.println("Start connecting");
                    client.openConnection();
                    Intent intent = new Intent(MainActivity.this, RemoteActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    Controller.getInstance().client = client;
                    pd.dismiss();
                    System.out.println("Changing page");
                    startActivity(intent);
                } catch (IOException e) {
                    System.out.println("Connection Timeout");
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                System.out.println("Done connecting");
                pd.dismiss();
            }
        }.execute(host);
    }
}
