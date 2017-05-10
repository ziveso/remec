package touchyou.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.add.ByButton;
import touchyou.gui.add.MouseOver;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JList;

public class WidgetPanel extends JPanel {

	private JPanel listPanel;
	private SettingPanel settingPanel;
	private Map<Integer, JLabel> list = new HashMap<>();

	/**
	 * Create the panel.
	 */
	public WidgetPanel(int width, int height, App app, SettingPanel settingPanel) {
		this.settingPanel = settingPanel;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnNewButton = new ByButton(new Dimension(width, height / 10), app, settingPanel);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 4;
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

		JButton btnShowComponentTree = new JButton("+ Show Component Tree");
		btnShowComponentTree.setPreferredSize(new Dimension(width, 30));
		btnShowComponentTree.setBackground(Color.LIGHT_GRAY);
		btnShowComponentTree.setBorder(BorderFactory.createEmptyBorder());
		btnShowComponentTree.addMouseListener(new MouseOver(btnShowComponentTree));
		btnShowComponentTree.setOpaque(true);
		panel.add(btnShowComponentTree, BorderLayout.NORTH);
		btnShowComponentTree.addActionListener(e -> {
			listPanel.setVisible(!listPanel.isShowing());
		});

		listPanel = new JPanel(new GridLayout(0, 1));
		listPanel.setVisible(false);

		panel.add(listPanel, BorderLayout.CENTER);
	}

	public void addToList(Command command) {
		list.put(command.getId(), new JLabel(command.getCombination()));
		listPanel.add(list.get(command.getId()));
		listPanel.validate();
	}

	public void removeFromList(Command command) {
		int id = command.getId();
		JLabel delete = list.get(id);
		listPanel.remove(delete);
		listPanel.validate();
	}

	public void update(Command command) {
		int id = command.getId();
		JLabel update = list.get(id);
		update.setText(command.getCombination());
		listPanel.validate();
	}
}
