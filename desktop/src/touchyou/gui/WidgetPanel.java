package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import touchyou.App;
import touchyou.gui.add.ByArrow;
import touchyou.gui.add.ByButton;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JTree;

public class WidgetPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public WidgetPanel(int width, int height, App app) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnNewButton = new ByButton(new Dimension(width, height / 10), app, null);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton_2 = new JButton("Show Component Tree");
		panel.add(btnNewButton_2, BorderLayout.NORTH);
	}
}
