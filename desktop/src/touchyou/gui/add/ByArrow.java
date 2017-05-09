package touchyou.gui.add;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
		this.setOpaque(false);
		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
		arrow.setOpaque(true);
		arrow.setBorder(BorderFactory.createEmptyBorder());
		arrow.setBackground(Color.WHITE);
		arrow.addMouseListener(new MouseOver(arrow));
		// arrow.addActionListener();
		add(arrow);
	}
}
