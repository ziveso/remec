package touchyou.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import touchyou.App;
import touchyou.Profile;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.CardLayout;
import java.awt.Color;

public class SelectProfile extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5327283923805357692L;
	private JLabel lblNewLabel;
	private App app;
	private CardLayout currentCard;
	private JPanel currentPane;

	/**
	 * Create the panel.
	 */
	public SelectProfile(int width, int height, App app, CardLayout currentCard, JPanel currentPane) {
		this.currentPane = currentPane;
		this.currentCard = currentCard;
		this.setPreferredSize(new Dimension(300, 500));
		setLayout(null);
		this.app = app;
		lblNewLabel = new JLabel("New profile");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setToolTipText("Create new profile");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 217, 288, 16);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				newProfile();
			}
		});
		add(lblNewLabel);
	}

	public void newProfile() {
		// TODO New profile

		currentCard.next(currentPane);
	}
}
