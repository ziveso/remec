package touchyou.gui.add;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByMacro extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114481235415036695L;

	public ByMacro(Dimension size) {
		JButton macro = new JButton("macro");
		this.setOpaque(false);
		macro.setPreferredSize(size);
		add(macro);
	}
}
