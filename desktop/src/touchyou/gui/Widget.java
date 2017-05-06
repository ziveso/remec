package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import touchyou.util.GuiUtil;

public class Widget extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234634479725945937L;
	private final int width;
	private final int height;

	/**
	 * Contruct this part
	 * 
	 * @param width
	 * @param height
	 */
	public Widget(int width, int height) {
		this.width = width;
		this.height = height;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		// make it easy to fix.
		setBorder(GuiUtil.getBorder());
		initComponent();
	}

	/**
	 * create everything that is needed by program.
	 */
	private void initComponent() {
		Dimension size = new Dimension(width, height / 15);

		// TODO Add action click to expand.

		JButton button = new JButton("Button");
		button.setPreferredSize(size);
		add(button);

		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
		add(arrow);

		JButton macro = new JButton("macro");
		macro.setPreferredSize(size);
		add(macro);
	}
}
