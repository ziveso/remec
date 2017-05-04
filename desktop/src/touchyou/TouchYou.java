package touchyou;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * Touch You Java Application in graphical user interface.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class TouchYou extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8441360007638089460L;

	public TouchYou() {
		initComponents();
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}

	private void initComponents() {
		add(new LeftPanel());
		add(new RightPanel());

	}

	/**
	 * set the visible to be true , show the GUI as Java
	 */
	public void run() {
		setVisible(true);
	}
}
