package touchyou.util;

import java.awt.Component;
import java.io.File;

import javax.swing.SwingUtilities;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.MainFrame;
import touchyou.gui.ModelPanel;
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
/**
 * @author Kongpon Charanwattanakit
 *
 */
public class Controller {
    private static Controller controller = new Controller();
    private App app;
    private MainFrame mainFrame;
    private SettingPanel settingPanel;
    private WidgetPanel widgetPanel;
    private ModelPanel modelPanel;
    private Command currentCommand;
    private int id;

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
	modelPanel.clear();
    }

    /**
     * Update every panels to point at the current command.
     */
    public void updateCurrentCommand() {
	settingPanel.update(currentCommand);
	widgetPanel.update(currentCommand);
	modelPanel.update(currentCommand);
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
	modelPanel.update(command);
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
	modelPanel.addCommand(command);
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
	System.out.println(app.getProfile());
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

    public void setModelPanel(ModelPanel modelPanel) {
	this.modelPanel = modelPanel;
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

    public void loadProfile() {
	app.getProfile().getCommands().forEach((e) -> {
	    if (e.getId() > id) {
		id = e.getId();
	    }
	});
	app.getProfile().getCommands().forEach(this::addCommand);
    }

}
