package touchyou.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import touchyou.App;
import touchyou.gui.add.*;
import touchyou.util.GuiUtil;
/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class WidgetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234634479725945937L;
	private final int width;
	private final int height;
	private Dimension size;
	private App app;

	/**
	 * Contruct this part
	 * 
	 * @param width
	 * @param height
	 * @param app
	 */
	public WidgetPanel(int width, int height, App app) {
		this.app = app;
		this.width = width;
		this.height = height;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(8,1,0,8));
		// make it easy to fix.
//		setBorder(GuiUtil.getBorder());
		size = new Dimension(width, height / 8);

		add(new ByButton(size , app));
		add(new ByArrow(size , app));
//		add(new ByMacro(size));
	}
}
