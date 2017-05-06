package touchyou;

import java.io.IOException;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class TCPServer extends AbstractServer {

    public TCPServer(int port) {
	super(port);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
	super.clientConnected(client);
	System.out.println("Client connected.");
	// Only 1 remote allowed.
	this.stopListening();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
	super.clientDisconnected(client);
	System.out.println("Client disconnected.");
	try {
	    this.listen();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	System.out.println("Client: " + msg);
    }

}
