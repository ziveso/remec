package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.add.MouseOver;
import touchyou.util.Controller;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class WidgetPanel extends JPanel {

    private Map<Integer, JLabel> trees = new HashMap<>();
    private JPanel listPanel;

    /**
     * Create the panel.
     */
    public WidgetPanel(int width, int height) {
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);
	JButton btnNewButton = new JButton("Add Button");
	btnNewButton.setOpaque(true);
	btnNewButton.setBorder(BorderFactory.createEmptyBorder());
	btnNewButton.setPreferredSize(new Dimension(width, height / 10));
	btnNewButton.addMouseListener(new MouseOver(btnNewButton));
	btnNewButton.addActionListener(e -> Controller.getInstance().addCommand());
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

    public void addCommand(Command command) {
	int id = command.getId();
	JLabel addlb = new JLabel(command.getCombination());
	addlb.setOpaque(true);
	addlb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	addlb.setHorizontalAlignment(SwingConstants.CENTER);
	addlb.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		Controller.getInstance().update(command);
	    }
	});
	trees.put(id, addlb);
	listPanel.add(addlb);
    }

    public void update(Command command) {
	if (command == null) {
	    return;
	}
	int id = command.getId();
	for (Integer key : trees.keySet()) {
	    JLabel labels = trees.get(key);
	    if (key == id) {
		if (!labels.getText().equals(command.getCombination())) {
		    labels.setText(command.getCombination());
		}
		labels.setBackground(Color.blue);
	    } else {
		labels.setBackground(null);
	    }
	}
	this.validate();
    }

}
