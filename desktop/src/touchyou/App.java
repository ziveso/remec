package touchyou;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import touchyou.gui.WelcomeFrame;
import touchyou.util.Controller;
import touchyou.util.GUIUtil;

/**
 * Model for TouchYou application.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class App {
    public static int PORT = 5910;
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
    public void sync(int wFactor, int hFactor) {
	profile.getCommands().forEach(command -> {
	    String combination = command.getCombination();
	    int mode = command.getMode();
	    int width = command.getWidth() * wFactor;
	    int height = command.getHeight() * hFactor;
	    int x = command.getX() * wFactor;
	    int y = command.getY() * hFactor;
	    String img = GUIUtil.extractBytes(command.getImage());
	    String packet = String.format("%s;%d;%d;%d;%d;%d;%s;%s", combination, mode, width, height, x, y,
		    command.getLabel(), img);
	    server.sendToAllClients("SYNC_RESPONSE=" + packet);
	});
	server.sendToAllClients("SYNC_END=0");
    }

    public void save() {
	System.out.println(profile.getDir().getPath());
	save(profile.getDir().getPath());
    }

    /**
     * Save profile data to .profile file.
     * 
     * @param path
     */
    public void save(String path) {
	saveImage(path);

	PrintWriter writer = null;
	try {
	    writer = new PrintWriter(path, "UTF-8");
	    writer.println("name=" + profile.getName());
	    for (Command command : profile.getCommands()) {
		writer.println("id=" + command.getId());
		writer.println("label=" + command.getLabel());
		writer.println("labelmode=" + command.getLableMode());
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

	profile.setDir(new File(path));
	Controller.getInstance().setIsSave(true);
    }

    private void saveImage(String path) {
	path = path.substring(0, path.length() - 8);
	path = path.replaceFirst(profile.getName() + "/", "");
	Iterator<Command> commands = profile.getCommands().iterator();
	while (commands.hasNext()) {
	    // save image
	    Command cmd = commands.next();
	    File dir = new File(path + "/images/");
	    if (dir.exists())
		removeAllFile(dir);
	    dir.mkdirs();
	    if (cmd.getImage() != Command.BLANK_IMAGE) {
		String path2 = "/images/" + cmd.getId() + ".png";
		cmd.setImagePath(path2);
		File output = new File(dir.getPath() + "/" + cmd.getId() + ".png");
		BufferedImage bi = null;
		bi = (BufferedImage) cmd.getImage();
		try {
		    ImageIO.write(bi, "png", output);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	    }
	}
    }

    private void removeAllFile(File dir) {
	for (File file : dir.listFiles()) {
	    file.delete();
	}
    }

    public void createNewProfile(String profileName) {
	profile = new Profile(profileName);
	String filepath = "./profiles/" + profileName + "/" + profileName + ".profile";
	File dir = new File("./profiles/" + profileName);
	dir.mkdir();
	profile.setDir(dir);
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
	open(new File(path));
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
			(c, l) -> c.setLabel(l), // 1
			(c, l) -> c.setLableMode(Integer.parseInt(l)), (c, l) -> c.setCombination(l), // 2
			(c, l) -> c.setMode(Integer.parseInt(l)), // 3
			(c, l) -> c.setImagePath(l), // 4
			(c, l) -> c.setWidth(Integer.parseInt(l)), // 5
			(c, l) -> c.setHeight(Integer.parseInt(l)), // 6
			(c, l) -> c.setX(Integer.parseInt(l)), // 7
			(c, l) -> c.setY(Integer.parseInt(l)) };// 8

		reader = new BufferedReader(new FileReader(file));
		String name = reader.readLine();
		profile = new Profile(name.split("=")[1]);
		String line;
		Command command = new Command();
		for (int i = 0; (line = reader.readLine()) != null; i++) {
		    if (i > 9) {
			i = 0;
			command = new Command();
		    }
		    line = line.split("=")[1];
		    if (line.equals("null")) {
			line = null;
		    }
		    methods[i].run(command, line);
		    if (i == 9) {
			profile.addCommand(command);
		    }
		}
	    } finally {
		reader.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	profile.setDir(file);
	if (!profile.getCommands().isEmpty()) {
	    generateImage(profile);
	}
	return profile;
    }

    private void generateImage(Profile profile) {
	String img_path = profile.getDir().getPath().replace(profile.getName() + ".profile", "");
	List<Command> cmds = profile.getCommands();
	for (Command cmd : cmds) {
	    if (cmd.getImagePath() == null)
		continue;
	    File img = new File(img_path + cmd.getImagePath());
	    System.out.println(img.getPath());
	    BufferedImage buff_img = null;
	    try {
		buff_img = ImageIO.read(img);
		cmd.setImage(buff_img);
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
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

    public void sendSyncRequest() {
	server.sendToAllClients("SYNC_REQUEST=0");
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
