package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import touchyou.App;
import touchyou.util.GuiUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -8718047311675995150L;

    private App app;

    // get the width and height from Util class.
    private final int Width = GuiUtil.WIDTH;
    private final int Height = GuiUtil.HEIGHT;

    /**
     * construct MainFrame.
     */
    public MainFrame(App app) {
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(Width, Height);
	this.app = app;
	initComponent();
    }

    /**
     * create layout.
     */
    private void initComponent() {
	JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)) {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 5082511633852881405L;

	    @Override
	    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(GuiUtil.getImage("/images/background.jpg"), 0, 0, null);
	    }
	};
	int setting_width = Width * 3 / 10;
	int model_width = Width * 5 / 10;
	int pane_width = Width * 2 / 10;
	pane.add(new SettingPanel(setting_width, Height));
	pane.add(new ModelPanel(model_width, Height));

	JPanel widget = new JPanel(new BorderLayout(0, 0));
	widget.setOpaque(false); // make in transparent
	widget.add(new WidgetPanel(pane_width, Height * 7 / 10), BorderLayout.NORTH);
	widget.add(new SyncButton(pane_width, Height * 3 / 10, app), BorderLayout.SOUTH);

	pane.add(widget);
	super.add(pane);
    }
}
