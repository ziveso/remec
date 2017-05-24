package touchyou;

import java.util.Arrays;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

import touchyou.util.Controller;

/**
 * A TCP/IP socket server for communication with Android device.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class TCPServer extends AbstractServer {

    private CommandInvoker invoke = new CommandInvoker();

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
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
	super.clientException(client, exception);
	exception.printStackTrace();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
	super.clientDisconnected(client);
	System.out.println("Client disconnected.");
    }

    @Override
    protected void handleMessageFromClient(Object o, ConnectionToClient client) {
	System.out.println("Client: " + o);
	String msg = (String) o;
	String[] commands;
	String[] data = msg.split("=");
	String header = data[0];
	String body = data[1];
	switch (header) {
	case "SYNC_REQUEST":
	    String[] size = body.split(";");
	    Controller.getInstance().sync(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
	    break;
	case "PRESS":
	    commands = body.split(";");
	    System.out.println(Arrays.toString(commands));
	    switch (commands[0]) {
	    case "0":
		invoke.tap(commands[1]);
		break;
	    case "1":
		invoke.press(commands[1]);
		break;
	    }
	    break;
	case "RELEASE":
	    commands = body.split(";");
	    if (commands[0].equals("1"))
		invoke.release(commands[1]);
	    break;
	}
    }

}
