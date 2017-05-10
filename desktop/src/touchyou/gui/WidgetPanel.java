package touchyou.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.add.ByButton;
import touchyou.gui.add.MouseOver;
import touchyou.util.GuiUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;

public class WidgetPanel extends JPanel {

	private static JPanel listPanel;
	private SettingPanel settingPanel;
	private static Map<Integer, JLabel> list = new HashMap<>();

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
		btnShowComponentTree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				btnShowComponentTree.setFont(new Font(btnShowComponentTree.getFont().toString(), 0,
						btnShowComponentTree.getFont().getSize() + 2));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				btnShowComponentTree.setFont(new Font(btnShowComponentTree.getFont().toString(), 0,
						btnShowComponentTree.getFont().getSize() - 2));
			}
		});
		btnShowComponentTree.setOpaque(true);
		panel.add(btnShowComponentTree, BorderLayout.NORTH);
		btnShowComponentTree.addActionListener(e -> {
			boolean isShowing = listPanel.isShowing();
			if (isShowing) {
				btnShowComponentTree.setBackground(Color.LIGHT_GRAY);
				btnShowComponentTree.setText("+ Show Component tree");
			} else {
				btnShowComponentTree.setBackground(Color.green);
				btnShowComponentTree.setText("- Show Component tree");
			}
			listPanel.setVisible(!isShowing);
		});

		listPanel = new JPanel(new GridLayout(20, 1, 0, 2));
		listPanel.setVisible(false);

		panel.add(listPanel, BorderLayout.CENTER);
	}

	public static void addToList(Command command) {
		JLabel lb = new JLabel(command.getCombination());
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBorder(GuiUtil.getBorder());
		list.put(command.getId(), lb);
		listPanel.add(list.get(command.getId()));
		listPanel.validate();
	}

	public static void removeFromList(Command command) {
		int id = command.getId();
		JLabel delete = list.get(id);
		listPanel.remove(delete);
		listPanel.validate();
	}

	public static void updateCombination(Command command) {
		int id = command.getId();
		JLabel update = list.get(id);
		update.setText(command.getCombination());
		listPanel.validate();
	}
}
