package touchyou.gui.add;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.ModelPanel;
import touchyou.gui.SettingPanel;
import touchyou.util.GuiUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByButton extends JButton {

    /**
     * 
     */
    private static final long serialVersionUID = 8391021197211750760L;
    public int id;
    private App app;
    private ComponentMover mover;
    private ComponentResizer resizer;
    private MouseAdapter commandMouseAdapter;
    private SettingPanel settingPanel;

    public ByButton(Dimension size, App app, SettingPanel settingPanel) {
	super("Button");
	this.settingPanel = settingPanel;
	this.app = app;
	this.commandMouseAdapter = new CommandMouseAdapter();
	this.setBorder(BorderFactory.createEmptyBorder());
	this.setBackground(Color.WHITE);
	this.addMouseListener(new MouseOver(this));
	this.setOpaque(true);
	mover = new ComponentMover();
	mover.setSnapSize(new Dimension(6, 6));
	mover.setDragInsets(new Insets(10, 10, 10, 10));
	resizer = new ComponentResizer();
	resizer.setSnapSize(new Dimension(6, 6));
	this.addActionListener(e -> {
	    JButton but = new CommandButton();
	    resizer.registerComponent(but);
	    ModelPanel.getMobile().add(but);
	    ModelPanel.updateComponent();
	});
	this.setPreferredSize(size);
    }

    private final class CommandButton extends JButton {

	public CommandButton() {
	    Command command = new Command();
	    this.setActionCommand(id + "");
	    command.setId(id++);
	    app.getProfile().addCommand(command);
	    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    this.setOpaque(true);
	    this.addMouseListener(mover);
	    this.setPreferredSize(new Dimension(50, 50));
	    this.addMouseListener(commandMouseAdapter);
	    this.setBounds(GuiUtil.getInitBound(this.getPreferredSize()));
	    this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    this.addFocusListener(new FocusListener() {

		@Override
		public void focusLost(FocusEvent e) {
		    System.out.println("lost focus");
		}

		@Override
		public void focusGained(FocusEvent e) {
		    // TODO Auto-generated method stub

		}
	    });
	}

    }

    private final class CommandMouseAdapter extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
	    JButton source = (JButton) e.getSource();
	    Component[] comps = ModelPanel.getMobile().getComponents();
	    for (Component c : comps) {
		JButton button = (JButton) c;
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    }
	    System.out.println(source.getActionCommand());
	    source.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	    settingPanel.update(app.getProfile().getCommand(Integer.parseInt(source.getActionCommand())));
	}
    }

}
