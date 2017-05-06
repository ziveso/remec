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
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(Width, Height);
		initComponent();
	}

	/**
	 * create layout.
	 */
	private void initComponent() {
		JPanel jp = new JPanel(new BorderLayout(0, 0)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8955978584032699840L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(GuiUtil.getImage("/images/background.jpg"), 0, 0, null);
			}
		};
		jp.add(new EditAction(Width * 3 / 10, Height), BorderLayout.WEST);
		jp.add(new ConfigAction(Width * 7 / 10, Height), BorderLayout.CENTER);
		super.add(jp);
	}
}
