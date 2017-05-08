package touchyou.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import touchyou.App;
import touchyou.Command;
/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class SettingPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -5468288983318943380L;
    private App app;
    private JTextField iconpath;
    private JTextField combination;

    /**
     * Create the panel.
     */
    public SettingPanel(App app) {
	this.app = app;
	this.setPreferredSize(new Dimension(331, 600));
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 28, 0, 112, 0, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JLabel lblNewLabel = new JLabel("Command:");
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 0;
	add(lblNewLabel, gbc_lblNewLabel);

	combination = new JTextField();
	GridBagConstraints gbc_combination = new GridBagConstraints();
	gbc_combination.gridwidth = 3;
	gbc_combination.insets = new Insets(0, 0, 5, 0);
	gbc_combination.fill = GridBagConstraints.HORIZONTAL;
	gbc_combination.gridx = 2;
	gbc_combination.gridy = 0;
	add(combination, gbc_combination);
	combination.setColumns(10);

	JLabel lblIcon = new JLabel("Icon:");
	GridBagConstraints gbc_lblIcon = new GridBagConstraints();
	gbc_lblIcon.anchor = GridBagConstraints.WEST;
	gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
	gbc_lblIcon.gridx = 0;
	gbc_lblIcon.gridy = 1;
	add(lblIcon, gbc_lblIcon);

	JRadioButton rdbtnImportFromComputer = new JRadioButton("Import From Computer");
	GridBagConstraints gbc_rdbtnImportFromComputer = new GridBagConstraints();
	gbc_rdbtnImportFromComputer.anchor = GridBagConstraints.WEST;
	gbc_rdbtnImportFromComputer.gridwidth = 3;
	gbc_rdbtnImportFromComputer.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnImportFromComputer.gridx = 2;
	gbc_rdbtnImportFromComputer.gridy = 1;
	add(rdbtnImportFromComputer, gbc_rdbtnImportFromComputer);

	iconpath = new JTextField();
	GridBagConstraints gbc_iconpath = new GridBagConstraints();
	gbc_iconpath.gridwidth = 2;
	gbc_iconpath.insets = new Insets(0, 0, 5, 5);
	gbc_iconpath.fill = GridBagConstraints.HORIZONTAL;
	gbc_iconpath.gridx = 2;
	gbc_iconpath.gridy = 2;
	add(iconpath, gbc_iconpath);
	iconpath.setColumns(10);

	JButton btnBrowse = new JButton("Browse");
	GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
	gbc_btnBrowse.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
	gbc_btnBrowse.gridx = 4;
	gbc_btnBrowse.gridy = 2;
	add(btnBrowse, gbc_btnBrowse);

	JRadioButton rdbtnCaptureFromScreen = new JRadioButton("Screen Capture");
	GridBagConstraints gbc_rdbtnCaptureFromScreen = new GridBagConstraints();
	gbc_rdbtnCaptureFromScreen.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnCaptureFromScreen.anchor = GridBagConstraints.WEST;
	gbc_rdbtnCaptureFromScreen.gridwidth = 3;
	gbc_rdbtnCaptureFromScreen.gridx = 2;
	gbc_rdbtnCaptureFromScreen.gridy = 3;
	add(rdbtnCaptureFromScreen, gbc_rdbtnCaptureFromScreen);

	JButton btnCapture = new JButton("Capture");
	GridBagConstraints gbc_btnCapture = new GridBagConstraints();
	gbc_btnCapture.insets = new Insets(0, 0, 5, 0);
	gbc_btnCapture.fill = GridBagConstraints.HORIZONTAL;
	gbc_btnCapture.weightx = 10.0;
	gbc_btnCapture.gridwidth = 3;
	gbc_btnCapture.anchor = GridBagConstraints.WEST;
	gbc_btnCapture.gridx = 2;
	gbc_btnCapture.gridy = 4;
	add(btnCapture, gbc_btnCapture);

	ButtonGroup iconGroup = new ButtonGroup();
	iconGroup.add(rdbtnImportFromComputer);
	iconGroup.add(rdbtnCaptureFromScreen);
	rdbtnImportFromComputer.addActionListener(e -> {
	    iconpath.setEnabled(true);
	    btnBrowse.setEnabled(true);
	    btnCapture.setEnabled(false);
	});
	rdbtnCaptureFromScreen.addActionListener(e -> {
	    iconpath.setEnabled(false);
	    btnBrowse.setEnabled(false);
	    btnCapture.setEnabled(true);
	});

	JLabel lblMode = new JLabel("Mode:");
	GridBagConstraints gbc_lblMode = new GridBagConstraints();
	gbc_lblMode.anchor = GridBagConstraints.WEST;
	gbc_lblMode.insets = new Insets(0, 0, 5, 5);
	gbc_lblMode.gridx = 0;
	gbc_lblMode.gridy = 5;
	add(lblMode, gbc_lblMode);

	JRadioButton rdbtnSingleTouch = new JRadioButton("Single Touch");
	GridBagConstraints gbc_rdbtnSingleTouch = new GridBagConstraints();
	gbc_rdbtnSingleTouch.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnSingleTouch.anchor = GridBagConstraints.WEST;
	gbc_rdbtnSingleTouch.gridwidth = 3;
	gbc_rdbtnSingleTouch.gridx = 2;
	gbc_rdbtnSingleTouch.gridy = 5;
	add(rdbtnSingleTouch, gbc_rdbtnSingleTouch);

	JRadioButton rdbtnFollow = new JRadioButton("Follow");
	GridBagConstraints gbc_rdbtnFollow = new GridBagConstraints();
	gbc_rdbtnFollow.gridwidth = 3;
	gbc_rdbtnFollow.anchor = GridBagConstraints.WEST;
	gbc_rdbtnFollow.gridx = 2;
	gbc_rdbtnFollow.gridy = 6;
	add(rdbtnFollow, gbc_rdbtnFollow);
	
	ButtonGroup modeGroup = new ButtonGroup();
	modeGroup.add(rdbtnSingleTouch);
	modeGroup.add(rdbtnFollow);

    }
    
    public void update(Command command){
    	this.combination.setText(command.getCombination());
    }
}
