package touchyou;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import touchyou.gui.WelcomeFrame;
import touchyou.util.Controller;

/**
 * Model for TouchYou application.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class App {
    public static int PORT = 3000;
    public TCPServer server;
    public Profile profile;

    /**
     * Initialize TouchYou server.
     */
    public App() {
	server = new TCPServer(PORT);
	System.out.println("Server Running on port: " + PORT);
    }

    /**
     * Transfer profile data to mobile device.
     */
    public void sync() {
	// TODO sync commands to mobile device
	server.sendToAllClients("sync request");
	server.sendToAllClients(profile);
    }

    public void save() {
	save("./profiles/" + profile.getName() + ".profile");
    }

    /**
     * Save profile data to .profile file.
     * 
     * @param path
     */
    public void save(String path) {
	PrintWriter writer = null;
	try {
	    writer = new PrintWriter(path, "UTF-8");
	    writer.println("name=" + profile.getName());
	    for (Command command : profile.getCommands()) {
		writer.println("id=" + command.getId());
		writer.println("com=" + command.getCombination());
		writer.println("mo=" + command.getMode());
		writer.println("img=" + command.getImagePath());
		writer.println("w=" + command.getWidth());
		writer.println("h=" + command.getHeight());
		writer.println("x=" + command.getX());
		writer.println("y=" + command.getY());
	    }
	} catch (FileNotFoundException | UnsupportedEncodingException e) {
	    e.printStackTrace();
	} finally {
	    writer.close();
	}
    }

    public void createNewProfile(String profileName) {
	profile = new Profile(profileName);
	String filepath = "./profiles/" + profileName + ".profile";
	save(filepath);
	open(filepath);
    }

    /**
     * Set profile to a given .profile file path.
     * 
     * @param path
     *            is the .profile file path
     */
    public void open(String path) {
	profile = generateProfile(new File(path));
    }

    /**
     * Set profile to a given .profile file.
     * 
     * @param file
     *            is the .profile file
     */
    public void open(File file) {
	profile = generateProfile(file);
    }

    /**
     * Return Profile object from .profile file.
     * 
     * @param file
     *            is the .profile file
     * @return a Profile instance
     */
    private Profile generateProfile(File file) {
	BufferedReader reader = null;
	Profile profile = null;
	try {
	    try {
		CommandSetter[] methods = { (c, l) -> c.setId(Integer.parseInt(l)), // 0
			(c, l) -> c.setCombination(l), // 1
			(c, l) -> c.setMode(Integer.parseInt(l)), // 2
			(c, l) -> c.setImagePath(l), // 3
			(c, l) -> c.setWidth(Double.parseDouble(l)), // 4
			(c, l) -> c.setHeight(Double.parseDouble(l)), // 5
			(c, l) -> c.setX(Double.parseDouble(l)), // 6
			(c, l) -> c.setY(Double.parseDouble(l)) };// 7

		reader = new BufferedReader(new FileReader(file));
		String name = reader.readLine();
		profile = new Profile(name.split("=")[1]);
		String line;
		Command command = new Command();
		for (int i = 0; (line = reader.readLine()) != null; i++) {
		    if (i > 7) {
			i = 0;
			command = new Command();
		    }
		    line = line.split("=")[1];
		    methods[i].run(command, line);
		    if (i == 7) {
			profile.addCommand(command);
		    }
		}
	    } finally {
		reader.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return profile;
    }

    /**
     * Returns current profile.
     * 
     * @return profile instance
     */
    public Profile getProfile() {
	return profile;
    }

    /**
     * Start listening on the server's port.
     */
    private void enableConnection() {
	try {
	    server.listen();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Stop listening on the server's port.
     */
    private void disableConnection() {
	try {
	    server.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void allowConnection(boolean choice) {
	if (choice && server.isClosed()) {
	    enableConnection();
	} else if (!choice) {
	    disableConnection();
	}
    }

    /**
     * Main method.
     * 
     * @param args
     *            is not used.
     */
    public static void main(String[] args) {
	try {
	    System.setProperty("apple.laf.useScreenMenuBar", "true");
	    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
		| UnsupportedLookAndFeelException e) {
	    e.printStackTrace();
	}
	App app = new App();
	Controller.getInstance().setApp(app);
	new WelcomeFrame().setVisible(true);
    }
}

/**
 * Helper interface for generating Command objects.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
interface CommandSetter {
    /**
     * Manage the command with a given data.
     * 
     * @param c
     *            is the command to work with
     * @param line
     *            is the data to be used
     */
    public void run(Command c, String line);
}
