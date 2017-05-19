package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import touchyou.Command;
import touchyou.gui.add.MouseOver;
import touchyou.util.Controller;
import touchyou.util.GUIUtil;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class WidgetPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3158099123782561762L;
    private DefaultListModel<Command> model;
    private JList<Command> list;

    /**
     * Create the panel.
     */
    public WidgetPanel(int width, int height) {
	this.setPreferredSize(new Dimension(200, 334));
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);
	
	JButton btnNewButton = new JButton("Add Button");
	btnNewButton.setPreferredSize(new Dimension(width, height / 10));
	btnNewButton.setOpaque(true);
	btnNewButton.setBorder(BorderFactory.createEmptyBorder());
	btnNewButton.addMouseListener(new MouseOver(btnNewButton));
	btnNewButton.addActionListener(e -> Controller.getInstance().addCommand());
	GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
	gbc_btnNewButton.fill = GridBagConstraints.BOTH;
	gbc_btnNewButton.gridwidth = 4;
	gbc_btnNewButton.gridx = 0;
	gbc_btnNewButton.gridy = 0;
	add(btnNewButton, gbc_btnNewButton);

	JPanel panel = new JPanel();
	panel.setPreferredSize(new Dimension(width, height * 9 / 10));
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.gridwidth = 4;
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 2;
	add(panel, gbc_panel);
	panel.setLayout(new BorderLayout(0, 0));

	JLabel lblComponentTree = new JLabel("Component Tree");
	lblComponentTree.setHorizontalAlignment(SwingConstants.CENTER);
	lblComponentTree.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	lblComponentTree.setOpaque(true);
	lblComponentTree.setBackground(GUIUtil.getBackgroundColor());
	lblComponentTree.setForeground(GUIUtil.getForegroundColor());
	lblComponentTree.setBorder(BorderFactory.createEtchedBorder());
	panel.add(lblComponentTree, BorderLayout.NORTH);

	list = new JList<>();
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setFont(new Font("Arial", Font.PLAIN, 16));
	model = new DefaultListModel<>();
	list.setModel(model);
	list.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectedValue() != null)
		    Controller.getInstance().update(list.getSelectedValue());
	    }
	});
	JScrollPane scrollPane = new JScrollPane(list);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	panel.add(scrollPane, BorderLayout.CENTER);
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
	list.clearSelection();
	model.clear();
    }

}
