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
    private ComponentMover mover;
    private ComponentResizer resizer;
    private MouseAdapter commandMouseAdapter;

    private int id;

    private Controller() {
	mover = new ComponentMover();
	mover.setSnapSize(new Dimension(6, 6));
	mover.setDragInsets(new Insets(10, 10, 10, 10));

	resizer = new ComponentResizer();
	resizer.setSnapSize(new Dimension(6, 6));

	commandMouseAdapter = new CommandMouseAdapter();
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
	/*
	 * Create JButton and register to ComponentResizer and ComponentMover
	 */
	JButton commandBtn = new JButton();
	resizer.registerComponent(commandBtn);
	mover.registerComponent(commandBtn);
	/* Set JButton's behavior */
	commandBtn.setActionCommand(String.valueOf(id));
	commandBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	commandBtn.setOpaque(true);
	commandBtn.setPreferredSize(new Dimension(50, 50));
	commandBtn.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	commandBtn.addMouseListener(commandMouseAdapter);
	/* Create new Command */
	Command command = new Command();
	command.setId(id++);
	app.getProfile().addCommand(command);
	/* notify other Panels */
	modelPanel.addToMobile(commandBtn);
	update(command);

    }

    private final class CommandMouseAdapter extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
	    JButton source = (JButton) e.getSource();

	    Component[] comps = modelPanel.getMobile().getComponents();
	    for (Component c : comps) {
		JButton button = (JButton) c;
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    }
	    int ID = Integer.parseInt(source.getActionCommand());
	    source.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	    Controller.getInstance().update(app.getProfile().getCommand(ID));
	}
    }
}
