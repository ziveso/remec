package touchyou.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.SwingUtilities;

import touchyou.App;
import touchyou.Command;
import touchyou.UDPBroadcast;
import touchyou.gui.MainFrame;
import touchyou.gui.MobilePanel;
import touchyou.gui.SettingPanel;
import touchyou.gui.WidgetPanel;

/**
 * A Controller class that contains every Panel's reference, in order to
 * communicate between each Panel. Every communication must be invoked using
 * this class.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class Controller {
    private static Controller controller = new Controller();
    private App app;
    private MainFrame mainFrame;
    private SettingPanel settingPanel;
    private WidgetPanel widgetPanel;
    private MobilePanel mobilePanel;
    private Command currentCommand;
    private UDPBroadcast broadcaster;
    private int id;

    private Controller() {
	broadcaster = new UDPBroadcast();
    }

    public void sync(int width, int height) {
	int wFactor = (int)((double)width/mobilePanel.getWidth());
	int hFactor = (int)((double)height/mobilePanel.getHeight());
	System.out.println(width+" "+height);
	System.out.println(mobilePanel.getWidth()+ " "+mobilePanel.getHeight());
	System.out.println(wFactor + " " + hFactor);
	app.sync(wFactor, hFactor);
    }

    /**
     * Allow android devices to search for this computer.
     */
    public void startBroadcast() {
	broadcaster.setBroadcast(true);
	app.allowConnection(true);
    }

    /**
     * Stop UDP broadcasting, android devices would not be able to search for
     * this computer.
     */
    public void stopBroadcast() {
	broadcaster.setBroadcast(false);
	app.allowConnection(false);
    }

    /**
     * Check if a component can be placed at this mouse location.
     * 
     * @param current
     *            is the subject component
     * @param mouse
     *            is current mouse position on the mobilePanel
     * @return true if is placable, false otherwise
     */
    public boolean placeable(Component current, Point mouse) {
	for (Component c : mobilePanel.getComponents()) {
	    if (current != c) {
		double width = current.getWidth();
		double height = current.getHeight();
		int x = (int) (mouse.getX() - width / 2);
		int y = (int) (mouse.getY() - height / 2);
		Rectangle invisibleRect = new Rectangle(x, y, (int) width, (int) height);
		if (invisibleRect.intersects(c.getBounds())) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Return an instance of the Controller.
     * 
     * @return Controller instance
     */
    public static Controller getInstance() {
	return controller;
    }

    private void enableSettingPanel() {
	for (Component c : settingPanel.getComponents()) {
	    c.setEnabled(true);
	}
    }

    /**
     * Clear every panels.
     */
    public void clear() {
	settingPanel.clear();
	widgetPanel.clear();
	mobilePanel.clear();
    }

    /**
     * Update every panels to point at the current command.
     */
    public void updateCurrentCommand() {
	settingPanel.update(currentCommand);
	widgetPanel.update(currentCommand);
	mobilePanel.update(currentCommand);
    }

    /**
     * Update every panels to point at the same Command.
     * 
     * @param command
     *            is the command that is being dealt with
     */
    public void update(Command command) {
	if (app.getProfile().getCommands().size() > 0)
	    enableSettingPanel();
	settingPanel.update(command);
	widgetPanel.update(command);
	mobilePanel.update(command);
	currentCommand = command;
    }

    /**
     * Add new Command to the profile.
     */
    public void addCommand() {
	/* Create new Command */
	Command command = new Command();
	command.setCombination(" ");
	command.setId(++id);
	command.setWidth(50);
	command.setHeight(50);
	app.getProfile().addCommand(command);
	addCommand(command);
    }

    /**
     * Update Command to the profile.
     */
    public void addCommand(Command command) {
	/* notify other Panels */
	mobilePanel.addCommand(command);
	widgetPanel.addCommand(command);
	update(command);
    }

    public Command getCommandById(String id) {
	return app.getProfile().getCommand(Integer.parseInt(id));
    }

    public Command getCurrentCommand() {
	return currentCommand;
    }

    public String getProfileName() {
	return app.getProfile().getName();
    }

    public void showMainFrame() {
	SwingUtilities.invokeLater(() -> mainFrame.setVisible(true));
    }

    public void hideMainFrame() {
	SwingUtilities.invokeLater(() -> mainFrame.setVisible(false));
    }

    public void setApp(App app) {
	this.app = app;
    }

    public void setMainFrame(MainFrame mainFrame) {
	this.mainFrame = mainFrame;
    }

    public void setSettingPanel(SettingPanel settingPanel) {
	this.settingPanel = settingPanel;
    }

    public void setWidgetPanel(WidgetPanel widgetPanel) {
	this.widgetPanel = widgetPanel;
    }

    public void setMobilePanel(MobilePanel mobilePanel) {
	this.mobilePanel = mobilePanel;
    }

    public void newProfile(String profileName) {
	app.createNewProfile(profileName);
    }

    public void openProfile(File file) {
	app.open(file);
    }

    public void save() {
	app.save();
    }

    public void saveAs(File file) {
	String path = file.getAbsolutePath() + ".profile";
	app.save(path);
    }

    public void loadProfile() {
	settingPanel.setProfileName(app.getProfile().getName());
	app.getProfile().getCommands().forEach((e) -> {
	    if (e.getId() > id) {
		id = e.getId();
	    }
	});
	app.getProfile().getCommands().forEach(this::addCommand);
    }

    public Point getInitLocation(Command command) {
	Point xy = new Point(0, 0);
	return xy;
    }
}
