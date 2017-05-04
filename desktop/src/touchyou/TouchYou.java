package touchyou;

import javax.swing.JFrame;

/**
 * Touch You Java Application in graphical user interface.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class TouchYou extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8441360007638089460L;

	public TouchYou() {
		initComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initComponents() {

	}

	public void run() {
		this.setVisible(true);
	}
}
