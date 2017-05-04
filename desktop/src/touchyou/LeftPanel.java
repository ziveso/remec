package touchyou;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 987102056675498166L;

	public LeftPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initComponent();
		setAlignmentY(TOP_ALIGNMENT);
	}

	private void initComponent() {
		// create switch top button.
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());

		JLabel profile = new JLabel("PROFILE");
		top.add(profile);
		JLabel action = new JLabel("ACTION");
		top.add(action);

		this.add(top);

		// create bottom left field
		// TODO Leave it blank by now
		// TODO add action listener changing the field;
		JPanel bot = new JPanel();
		JLabel helloworld = new JLabel("HELLOWORLD");
		bot.add(helloworld);
		
		this.add(bot);
	}
}
