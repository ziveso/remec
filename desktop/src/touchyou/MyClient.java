package touchyou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MyClient {
    private static final int PORT = 5910;
    private static final int TIMEOUT = 8000;
    private Socket server;
    private DataInputStream in;
    private DataOutputStream out;

    public void connect(String host) {
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		InetSocketAddress inet = new InetSocketAddress(host, PORT);
		server = new Socket();
		try {
		    server.connect(inet, TIMEOUT);
		    in = new DataInputStream(server.getInputStream());
		    out = new DataOutputStream(server.getOutputStream());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
    }

    public void send(String data) {
	if (server.isConnected()) {
	    try {
		out.writeUTF(data);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public String read() {
	if (server.isConnected()) {
	    try {
		return in.readUTF();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return null;
    }
}
