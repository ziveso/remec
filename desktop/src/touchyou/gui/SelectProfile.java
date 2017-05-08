package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import touchyou.App;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class SelectProfile extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5327283923805357692L;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public SelectProfile(int width, int height, App app) {
		this.setPreferredSize(new Dimension(300, 500));
		setLayout(null);

		lblNewLabel = new JLabel("New profile");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setToolTipText("Create new profile");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 217, 288, 16);
		// new Profile TODO!!
		add(lblNewLabel);
	}
}
