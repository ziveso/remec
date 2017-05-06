package touchyou;

import java.io.IOException;

import com.lloseng.ocsf.server.AbstractServer;
import com.lloseng.ocsf.server.ConnectionToClient;

public class TCPServer extends AbstractServer {
    public static void main(String[] args) throws IOException {
	TCPServer server = new TCPServer(3000);
	server.listen();
    }

    public TCPServer(int port) {
	super(port);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
	System.out.println(msg);
    }

}
