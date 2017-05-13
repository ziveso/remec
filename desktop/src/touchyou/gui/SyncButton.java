package touchyou.gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import touchyou.App;
import touchyou.gui.add.MouseOver;
import touchyou.util.Controller;

/**
 * Let's do the action in this Class.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class SyncButton extends JButton {

	/**
	 * serial ID
	 */
	private static final long serialVersionUID = 2648304339901249894L;

	/**
	 * Construct Sync Button
	 * 
	 * @param width
	 * @param height
	 */
	public SyncButton(int width, int height) {
		super("SYNC");
		this.setPreferredSize(new Dimension(width, height));
		this.addActionListener((e) -> Controller.getInstance().sync());
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setOpaque(true);
		this.addMouseListener(new MouseOver(this));
	}
}
