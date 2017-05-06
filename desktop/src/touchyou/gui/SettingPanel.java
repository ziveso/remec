package touchyou.gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import touchyou.util.GuiUtil;

/**
 * Left side of the program.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class SettingPanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 155876714346575681L;

	/**
	 * 
	 * @param width
	 * @param height
	 */
	public SettingPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));

		// make it easier to edit.
		setBorder(GuiUtil.getBorder());
	}

}
