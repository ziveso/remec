package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import touchyou.util.GuiUtil;

public class Setting extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 155876714346575681L;

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public Setting(int width, int height) {
		setPreferredSize(new Dimension(width, height));

		// make it easier to edit.
		setBorder(GuiUtil.getBorder());
		this.setOpaque(false);
	}
}
