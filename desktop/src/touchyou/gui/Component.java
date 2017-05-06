package touchyou.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import touchyou.util.GuiUtil;

/**
 * This class does like a body in HTML. contain everything.
 * 
 * @author Thitiwat Thongbor
 *
 */

public class Component extends JFrame {

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
	public Component() {
		super.setSize(Width, Height);
		super.setLayout(new BorderLayout());
		initComponent();
	}

	/**
	 * create layout.
	 */
	private void initComponent() {
		super.add(new EditAction(Width * 3 / 10, Height), BorderLayout.WEST);
		super.add(new ConfigAction(Width * 7 / 10, Height), BorderLayout.CENTER);
	}
}
