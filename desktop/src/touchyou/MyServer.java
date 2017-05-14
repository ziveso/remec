package touchyou;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private static final int PORT = 5910;
    private ServerSocket server;
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;

    public void start() {
	new Thread(() -> {
	    try {
		server = new ServerSocket(PORT);
		/* wait until a client connected */
		client = server.accept();
		in = new DataInputStream(client.getInputStream());
		out = new DataOutputStream(client.getOutputStream());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}).start();
    }

    public void send(String data) {
	if (client.isConnected()) {
	    try {
		out.writeUTF(data);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public String read() {
	if (client.isConnected()) {
	    try {
		return in.readUTF();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return null;
    }

}
