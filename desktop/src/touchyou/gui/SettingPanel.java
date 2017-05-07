package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import touchyou.App;
import touchyou.util.GuiUtil;

public class SettingPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 155876714346575681L;

    private App app;
    private JTextField combination;
    private JRadioButton local;
    private JTextField path;
    private JButton browse;
    private JRadioButton capture;
    private JButton startCapture;
    private JRadioButton mode0;
    private JRadioButton mode1;

    /**
     * 
     * @param width
     * @param height
     */
    public SettingPanel(App app, int width, int height) {
	this.app = app;
	setPreferredSize(new Dimension(width, height));
	// make it easier to edit.
	setBorder(GuiUtil.getBorder());
	this.setOpaque(false);
	this.setLayout(new GridBagLayout());
	initComponents();
    }

    private void initComponents() {
	combination = new JTextField(8);
	local = new JRadioButton("Import image from your computer");
	path = new JTextField(6);
	path.setEditable(false);
	browse = new JButton("Browse");
	browse.addActionListener((e) -> {
	    JFileChooser chooser = new JFileChooser();
	    chooser.showOpenDialog(this);
	});
	capture = new JRadioButton("Screen capture");
	startCapture = new JButton("Capture");
	startCapture.addActionListener((e) -> {

	});
	ButtonGroup iconGroup = new ButtonGroup();
	iconGroup.add(local);
	iconGroup.add(capture);
	mode0 = new JRadioButton("Single touch");
	mode1 = new JRadioButton("Follow");
	ButtonGroup modeGroup = new ButtonGroup();
	modeGroup.add(mode0);
	modeGroup.add(mode1);

	GridBagConstraints cons = new GridBagConstraints();
	cons.gridx = 0;
	cons.gridy = 0;
	this.add(new JLabel("Command:"), cons);
	cons.gridx++;
	this.add(combination, cons);
	cons.gridy++;
	this.add(local, cons);
	cons.gridx--;

    }
}
