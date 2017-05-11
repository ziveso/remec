package touchyou.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.add.ComponentMover;
import touchyou.gui.add.ComponentResizer;
import touchyou.util.Controller;
import touchyou.util.GuiUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ModelPanel extends JPanel {

    private MouseAdapter commandMouseAdapter;
    private ComponentMover mover;
    private ComponentResizer resizer;

    /**
     * 
     */
    private static final long serialVersionUID = 3593230878415293635L;

    protected ModelPanel() {
	mover = new ComponentMover();
	mover.setSnapSize(new Dimension(6, 6));
	mover.setDragInsets(new Insets(10, 10, 10, 10));

	resizer = new ComponentResizer();
	resizer.setSnapSize(new Dimension(6, 6));
	commandMouseAdapter = new CommandMouseAdapter();
	this.setOpaque(true);
	setBackground(Color.white);
	setLayout(null); // make it movable , no layout

	/**
	 * pixel phone has 1080 x 1776 resolution.
	 */

	int mobileWidth = 1080 / 4;
	int mobileHeight = 1776 / 4;
	setMobileSize(mobileWidth, mobileHeight);

	// setBorder(new CompoundBorder(GuiUtil.getBorder(), new EmptyBorder(0,
	// sideGap, 0, sideGap)));

    }

    public void setMobileSize(int width, int height) {
	setPreferredSize(new Dimension(width, height));
	this.validate();
    }

    public void updateComponent() {
	repaint();
	revalidate();
    }

    public void addCommand(Command command) {
	JButton commandBtn = new JButton();
	/* Set JButton's behavior */
	commandBtn.setActionCommand(String.valueOf(command.getId()));
	commandBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	commandBtn.setOpaque(true);
	commandBtn.setPreferredSize(new Dimension(50, 50));
	commandBtn.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	commandBtn.addMouseListener(commandMouseAdapter);
	// TODO Add commandBtn to mobile with auto layout

    }

    public void update(Command command) {
	Component[] comps = getComponents();
	for (Component c : comps) {
	    JButton button = (JButton) c;
	    button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
    }

    private final class CommandMouseAdapter extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
	    JButton source = (JButton) e.getSource();

	    Component[] comps = getComponents();
	    for (Component c : comps) {
		JButton button = (JButton) c;
		button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    }
	    String id = source.getActionCommand();
	    source.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
	    Controller.getInstance().update(Controller.getInstance().getCommandById(id));
	}
    }
}