package touchyou.gui.add;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByArrow extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7527425268579123369L;

	public ByArrow(Dimension size) {
		super(new GridLayout(0, 1));
		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
//		arrow.addActionListener();
		add(arrow);
	}
}
