package touchyou.gui.add;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.ModelPanel;
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

    public ByButton(Dimension size, App app) {
	super("Button");
	this.addActionListener(e -> {
	    JButton but = new JButton();
	    MouseListener ml = new MouseMotion();
	    Command com = new Command();
	    com.setCombination("ALT + F4");
	    // TODO set id
	    app.getProfile().addCommand(com);
	    but.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    but.setOpaque(true);
	    but.addMouseListener(ml);
	    but.addMouseMotionListener((MouseMotionListener) ml);
	    but.setPreferredSize(new Dimension(50, 50));
	    but.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
		    Component[] comps = ModelPanel.getMobile().getComponents();
		    for (Component c : comps) {
			JButton button = (JButton) c;
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    }
		    but.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		}
	    });
	    ModelPanel.getMobile().add(but);
	    but.setBounds(GuiUtil.getInitBound(but.getPreferredSize()));
	    ModelPanel.updateComponent();
	});
	this.setPreferredSize(size);
    }
}