package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import touchyou.Command;
import touchyou.gui.add.MouseOver;
import touchyou.util.Controller;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class WidgetPanel extends JPanel {

    private DefaultListModel<Command> model;
    private JList<Command> list;

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

	JLabel lblComponentTree = new JLabel("Component Tree");
	panel.add(lblComponentTree, BorderLayout.NORTH);

	list = new JList<>();
	model = new DefaultListModel<>();
	list.setModel(model);
	list.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		Controller.getInstance().update(list.getSelectedValue());
	    }
	});
	panel.add(list, BorderLayout.CENTER);
    }

    public void addCommand(Command command) {
	model.addElement(command);
    }

    public void update(Command command) {
	if (command == null) {
	    return;
	}
	list.setSelectedValue(command, true);
	this.validate();
    }

    public void clear() {
	model.clear();
	this.validate();
    }

}
