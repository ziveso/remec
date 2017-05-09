package touchyou.gui.add;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
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
		JButton macro = new JButton("Macro");
		this.setOpaque(false);
		macro.setOpaque(true);
		macro.setPreferredSize(size);
		macro.setBorder(BorderFactory.createEmptyBorder());
		macro.setBackground(Color.WHITE);
		macro.addMouseListener(new MouseOver(macro));
		add(macro);
	}
}
