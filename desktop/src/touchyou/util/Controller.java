package touchyou.util;

import java.awt.Component;
import java.io.File;

import touchyou.App;
import touchyou.Command;
import touchyou.UDPBroadcast;
import touchyou.gui.MainFrame;
import touchyou.gui.MobilePanel;
import touchyou.gui.SettingPanel;
import touchyou.gui.StatusPanel;
import touchyou.gui.WidgetPanel;

/**
 * A singleton Controller class that contains every Panel's reference, in order
 * to communicate between each Panel. Every communication must be invoked using
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
    private StatusPanel statusPanel;
    private Command currentCommand;
    private UDPBroadcast broadcaster;
    private int id;
    private boolean isSave;

    private Controller() {
	broadcaster = new UDPBroadcast();
    }

    /**
     * Create new profile with a given name.
     * 
     * @param profileName
     *            is the name of the new profile.
     */
    public void newProfile(String profileName) {
	app.createNewProfile(profileName);
    }

    /**
     * Open a profile.
     * 
     * @param file
     *            is the profile file to open.
     */
    public void openProfile(File file) {
	app.open(file);
    }

    /**
     * Save current profile.
     */
    public void save() {
	app.save();
    }

    /**
     * Save current profile to a specific path.
     * 
     * @param file
     *            is the path to save as.
     */
    public void saveAs(File file) {
	new File(file.getAbsolutePath() + "/").mkdirs();
	String path = file.getAbsolutePath() + "/" + getProfileName() + ".profile";
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
	setIsSave(true);
    }

    public void setIsSave(boolean isSave) {
	this.isSave = isSave;
    }

    public boolean getIsSave() {
	return isSave;
    }

    public void removeCurrentCommand() {
	this.settingPanel.removeCommand(currentCommand);
	this.mobilePanel.removeCommand(currentCommand);
	this.widgetPanel.removeCommand(currentCommand);
	this.app.profile.removeCommand(currentCommand);
    }

    /**
     * Send a sync request to the mobile device. If the message arrived on
     * mobile succesfully, this will invoke {@link #sync(int, int)} method.
     */
    public void sendSyncRequest() {
	app.sendSyncRequest();
    }

    /**
     * Start synchronization with mobile device (should not be called).
     * 
     * @param width
     *            is the width of the device
     * @param height
     *            is the height of the device
     */
    public void sync(int width, int height) {
	int wFactor = (int) ((double) width / mobilePanel.getWidth());
	int hFactor = (int) ((double) height / mobilePanel.getHeight());
	System.out.println(width + " " + height);
	System.out.println(mobilePanel.getWidth() + " " + mobilePanel.getHeight());
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
	statusPanel.clear();
    }

    /**
     * Update every panels to point at the current command.
     */
    public void updateCurrentCommand() {
	if (app.getProfile().getCommands().size() > 0)
	    System.out.println("enable all setting");
	    enableSettingPanel();
	isSave = false;
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
	currentCommand = command;
	updateCurrentCommand();
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
     * Update Command to the profile. (Only use for loading profile).
     */
    private void addCommand(Command command) {
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

    /**
     * Show MainFrame.
     */
    public void showMainFrame() {
	mainFrame.setVisible(true);
    }

    /**
     * Hide MainFrame.
     */
    public void hideMainFrame() {
	mainFrame.setVisible(false);
    }

    public void setApp(App app) {
	this.app = app;
    }

    public void setStatusPanel(StatusPanel statusPanel) {
	this.statusPanel = statusPanel;
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

    public void setRemoteConnection(boolean isOn) {
	statusPanel.setRemoteConnection(isOn);
    }
}
