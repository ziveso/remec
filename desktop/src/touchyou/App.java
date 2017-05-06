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
    public static int PORT = 3000;
    public TCPServer server;
    public Profile profile;

    public App() {
	server = new TCPServer(PORT);
    }

    public void sync() {
	// TODO sync commands to mobile device
	server.sendToAllClients("sync request");
    }

    public void save(String path) {
	// TODO write data to .profile file
    }

    public void open(String path) {
	profile = generateProfile(path);
    }

    public Profile getProfile() {
	return profile;
    }

    private Profile generateProfile(String path) {
	// TODO generate Profile object from .profile file
	return null;
    }

    private void run() {
	try {
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
	App app = new App();
	app.run();
	new TouchyouGui(app).run();
    }
}
