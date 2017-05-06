package touchyou;

import java.io.IOException;

import touchyou.gui.TouchyouGui;

/**
 * Main class running the touchyou graphical user interface.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class App {

    public static TCPServer server;

    private App() {
	this.run();
	new TouchyouGui().run();
    }

    private void run() {
	try {
	    server = new TCPServer(3000);
	    server.listen();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * create new App , Avoid using static.
     * 
     * @param args
     *            is not used.
     */
    public static void main(String[] args) {
	new App();
    }
}
