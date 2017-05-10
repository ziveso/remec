package touchyou.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import touchyou.App;
import touchyou.Command;
import touchyou.util.GuiUtil;
import javax.swing.SwingConstants;

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
    private JLabel profilename;
    private JRadioButton rdbtnImportFromComputer;
    private JRadioButton rdbtnCaptureFromScreen;
    private JRadioButton rdbtnSingleTouch;
    private JRadioButton rdbtnFollow;
    private JFrame mainframe;
    private Command currentCommand;

    /**
     * Create the panel.
     */
    public SettingPanel(App app, JFrame mainframe) {
	setOpaque(false);
	this.mainframe = mainframe;
	// setBorder(GuiUtil.getBorder());
	this.app = app;
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 28, 90, 72, 0 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JLabel lblProfile = new JLabel("Profile:");
	lblProfile.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_lblProfile = new GridBagConstraints();
	gbc_lblProfile.anchor = GridBagConstraints.WEST;
	gbc_lblProfile.insets = new Insets(0, 0, 5, 5);
	gbc_lblProfile.gridx = 0;
	gbc_lblProfile.gridy = 0;
	add(lblProfile, gbc_lblProfile);

	profilename = new JLabel("New label");
	profilename.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_profilename = new GridBagConstraints();
	gbc_profilename.gridwidth = 2;
	gbc_profilename.fill = GridBagConstraints.VERTICAL;
	gbc_profilename.anchor = GridBagConstraints.EAST;
	gbc_profilename.insets = new Insets(0, 0, 5, 0);
	gbc_profilename.gridx = 2;
	gbc_profilename.gridy = 0;
	add(profilename, gbc_profilename);

	JLabel lblNewLabel = new JLabel("Command:");
	lblNewLabel.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 1;
	add(lblNewLabel, gbc_lblNewLabel);

	combination = new JTextField();
	combination.setHorizontalAlignment(SwingConstants.TRAILING);
	combination.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyReleased(KeyEvent e) {
		currentCommand.setCombination(combination.getText());
	    }
	});
	combination.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent e) {
//		currentCommand.setCombination(combination.getText());
	    }
	});
	GridBagConstraints gbc_combination = new GridBagConstraints();
	gbc_combination.gridwidth = 2;
	gbc_combination.insets = new Insets(0, 0, 5, 0);
	gbc_combination.fill = GridBagConstraints.BOTH;
	gbc_combination.gridx = 2;
	gbc_combination.gridy = 1;
	add(combination, gbc_combination);
	combination.setColumns(10);

	JLabel lblIcon = new JLabel("Icon:");
	lblIcon.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_lblIcon = new GridBagConstraints();
	gbc_lblIcon.anchor = GridBagConstraints.WEST;
	gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
	gbc_lblIcon.gridx = 0;
	gbc_lblIcon.gridy = 2;
	add(lblIcon, gbc_lblIcon);

	rdbtnImportFromComputer = new JRadioButton("Import From Computer");
	rdbtnImportFromComputer.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnImportFromComputer = new GridBagConstraints();
	gbc_rdbtnImportFromComputer.anchor = GridBagConstraints.WEST;
	gbc_rdbtnImportFromComputer.gridwidth = 2;
	gbc_rdbtnImportFromComputer.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnImportFromComputer.gridx = 2;
	gbc_rdbtnImportFromComputer.gridy = 2;
	add(rdbtnImportFromComputer, gbc_rdbtnImportFromComputer);

	iconpath = new JTextField();
	iconpath.setEditable(false);
	GridBagConstraints gbc_iconpath = new GridBagConstraints();
	gbc_iconpath.insets = new Insets(0, 0, 5, 5);
	gbc_iconpath.fill = GridBagConstraints.BOTH;
	gbc_iconpath.gridx = 2;
	gbc_iconpath.gridy = 3;
	add(iconpath, gbc_iconpath);
	iconpath.setColumns(10);

	JButton btnBrowse = new JButton("Browse");
	GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
	gbc_btnBrowse.fill = GridBagConstraints.BOTH;
	gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
	gbc_btnBrowse.gridx = 3;
	gbc_btnBrowse.gridy = 3;
	add(btnBrowse, gbc_btnBrowse);

	rdbtnCaptureFromScreen = new JRadioButton("Screen Capture");
	rdbtnCaptureFromScreen.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnCaptureFromScreen = new GridBagConstraints();
	gbc_rdbtnCaptureFromScreen.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnCaptureFromScreen.anchor = GridBagConstraints.WEST;
	gbc_rdbtnCaptureFromScreen.gridwidth = 2;
	gbc_rdbtnCaptureFromScreen.gridx = 2;
	gbc_rdbtnCaptureFromScreen.gridy = 4;
	add(rdbtnCaptureFromScreen, gbc_rdbtnCaptureFromScreen);

	JButton btnCapture = new CaptureButton(app, mainframe, "Capture");
	GridBagConstraints gbc_btnCapture = new GridBagConstraints();
	gbc_btnCapture.insets = new Insets(0, 0, 5, 0);
	gbc_btnCapture.fill = GridBagConstraints.BOTH;
	gbc_btnCapture.weightx = 10.0;
	gbc_btnCapture.gridwidth = 2;
	gbc_btnCapture.gridx = 2;
	gbc_btnCapture.gridy = 5;
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
	lblMode.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_lblMode = new GridBagConstraints();
	gbc_lblMode.anchor = GridBagConstraints.WEST;
	gbc_lblMode.insets = new Insets(0, 0, 5, 5);
	gbc_lblMode.gridx = 0;
	gbc_lblMode.gridy = 6;
	add(lblMode, gbc_lblMode);

	rdbtnSingleTouch = new JRadioButton("Single Touch");
	rdbtnSingleTouch.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		currentCommand.setMode(0);
	    }
	});
	rdbtnSingleTouch.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnSingleTouch = new GridBagConstraints();
	gbc_rdbtnSingleTouch.insets = new Insets(0, 0, 5, 0);
	gbc_rdbtnSingleTouch.anchor = GridBagConstraints.WEST;
	gbc_rdbtnSingleTouch.gridwidth = 2;
	gbc_rdbtnSingleTouch.gridx = 2;
	gbc_rdbtnSingleTouch.gridy = 6;
	add(rdbtnSingleTouch, gbc_rdbtnSingleTouch);

	rdbtnFollow = new JRadioButton("Follow");
	rdbtnFollow.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		currentCommand.setMode(1);
	    }
	});
	rdbtnFollow.setForeground(GuiUtil.getForegroundColor());
	GridBagConstraints gbc_rdbtnFollow = new GridBagConstraints();
	gbc_rdbtnFollow.gridwidth = 2;
	gbc_rdbtnFollow.anchor = GridBagConstraints.WEST;
	gbc_rdbtnFollow.gridx = 2;
	gbc_rdbtnFollow.gridy = 7;
	add(rdbtnFollow, gbc_rdbtnFollow);

	ButtonGroup modeGroup = new ButtonGroup();
	modeGroup.add(rdbtnSingleTouch);
	modeGroup.add(rdbtnFollow);

    }

    public void update(Command command) {
	this.currentCommand = command;
	this.combination.setText(command.getCombination());
	this.iconpath.setText(command.getImagePath());
	switch (command.getMode()) {
	case 0:
	    rdbtnSingleTouch.setSelected(true);
	    break;
	case 1:
	    rdbtnFollow.setSelected(true);
	    break;
	default:
	    break;
	}
    }
}
