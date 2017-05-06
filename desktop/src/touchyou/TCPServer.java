package touchyou;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

/**
 * A TCP/IP socket server for communication with Android device.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class TCPServer extends AbstractServer {

    /**
     * Initialize the server with a given port.
     * 
     * @param port
     *            is the port to listen to
     */
    public TCPServer(int port) {
	super(port);
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
	super.clientConnected(client);
	System.out.println("Client connected.");
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
	super.clientDisconnected(client);
	System.out.println("Client disconnected.");
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	System.out.println("Client: " + msg);
    }

}
