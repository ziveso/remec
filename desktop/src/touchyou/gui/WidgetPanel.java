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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JList;

public class WidgetPanel extends JPanel {

    private JList<String> list;
    private SettingPanel settingPanel;

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

	JButton btnShowComponentTree = new JButton("+ Show Component Tree");
	btnShowComponentTree.setPreferredSize(new Dimension(width, 30));
	btnShowComponentTree.setBackground(Color.LIGHT_GRAY);
	btnShowComponentTree.setBorder(BorderFactory.createEmptyBorder());
	btnShowComponentTree.addMouseListener(new MouseOver(btnShowComponentTree));
	btnShowComponentTree.setOpaque(true);
	panel.add(btnShowComponentTree, BorderLayout.NORTH);
	btnShowComponentTree.addActionListener(e -> {
	    list.setVisible(!list.isShowing());
	    // TODO
	    // List<Command> all_comp = app.getProfile().getCommands();
	    // for(Command comp : all_comp){
	    // System.out.println(comp.getCombination());
	    // }
	});

	DefaultListModel<String> lm = new DefaultListModel<>();
	list = new JList<>(lm);
	list.setVisible(false);

	panel.add(list, BorderLayout.CENTER);
    }
}
