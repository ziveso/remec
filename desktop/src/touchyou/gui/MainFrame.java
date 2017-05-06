package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import touchyou.util.GuiUtil;

/**
 * This class does like a body in HTML. contain everything.
 * 
 * @author Thitiwat Thongbor
 *
 */

public class MainFrame extends JFrame {

    /**
     * The serial ID from JFrame.
     */
    private static final long serialVersionUID = 6580441202945279239L;
    // get the width and height from Util class.
    private final int Width = GuiUtil.WIDTH;
    private final int Height = GuiUtil.HEIGHT;

    /**
     * construct component.
     */
    public MainFrame() {
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(Width, Height);
	initComponent();
    }

    /**
     * create layout.
     */
    private void initComponent() {
	super.add(new SettingPanel(Width * 3 / 10, Height), BorderLayout.WEST);
	super.add(new ModelPanel(Width * 7 / 10 - 1, Height - 1), 0);
	super.add(new WidgetPanel(Width * 7 / 10, Height), BorderLayout.CENTER);
    }
}
