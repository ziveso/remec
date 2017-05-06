package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JButton;

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
		super.setPreferredSize(new Dimension(width, height));
	}
}
