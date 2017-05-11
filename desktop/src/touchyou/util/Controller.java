package touchyou.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.*;
import touchyou.gui.add.ComponentMover;
import touchyou.gui.add.ComponentResizer;

public class Controller {
    private static Controller controller = new Controller();
    private App app;
    private SettingPanel settingPanel;
    private WidgetPanel widgetPanel;
    private ModelPanel modelPanel;
   

    private int id;

    private Controller() {

    }

    public static Controller getInstance() {
	return controller;
    }

    public SettingPanel getSettingPanel() {
	return settingPanel;
    }

    public void setSettingPanel(SettingPanel settingPanel) {
	this.settingPanel = settingPanel;
    }

    public WidgetPanel getWidgetPanel() {
	return widgetPanel;
    }

    public void setWidgetPanel(WidgetPanel widgetPanel) {
	this.widgetPanel = widgetPanel;
    }

    public ModelPanel getModelPanel() {
	return modelPanel;
    }

    public void setModelPanel(ModelPanel modelPanel) {
	this.modelPanel = modelPanel;
    }

    /**
     * Update
     */
    public void update(Command command) {
	settingPanel.update(command);
	widgetPanel.update(command);
	modelPanel.update(command);
    }

    public void addCombination() {
	/* Create new Command */
	Command command = new Command();
	command.setId(id++);
	app.getProfile().addCommand(command);
	/* notify other Panels */
	modelPanel.addCommand(command);
	widgetPanel.addCommand(command);
	/* Update every panels to point at the same Command */
	update(command);
    }

  
}
