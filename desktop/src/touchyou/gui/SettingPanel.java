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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public SettingPanel(App app) {
		this.app = app;
		this.setPreferredSize(new Dimension(331, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 28, 0, 0, 112, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Command:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		combination = new JTextField();
		GridBagConstraints gbc_combination = new GridBagConstraints();
		gbc_combination.gridwidth = 4;
		gbc_combination.insets = new Insets(0, 0, 5, 0);
		gbc_combination.fill = GridBagConstraints.HORIZONTAL;
		gbc_combination.gridx = 2;
		gbc_combination.gridy = 1;
		add(combination, gbc_combination);
		combination.setColumns(10);

		JLabel lblIcon = new JLabel("Icon:");
		GridBagConstraints gbc_lblIcon = new GridBagConstraints();
		gbc_lblIcon.anchor = GridBagConstraints.WEST;
		gbc_lblIcon.insets = new Insets(0, 0, 5, 5);
		gbc_lblIcon.gridx = 0;
		gbc_lblIcon.gridy = 2;
		add(lblIcon, gbc_lblIcon);

		JRadioButton rdbtnImportFromComputer = new JRadioButton("Import From Computer");
		GridBagConstraints gbc_rdbtnImportFromComputer = new GridBagConstraints();
		gbc_rdbtnImportFromComputer.anchor = GridBagConstraints.WEST;
		gbc_rdbtnImportFromComputer.gridwidth = 4;
		gbc_rdbtnImportFromComputer.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnImportFromComputer.gridx = 2;
		gbc_rdbtnImportFromComputer.gridy = 2;
		add(rdbtnImportFromComputer, gbc_rdbtnImportFromComputer);

		iconpath = new JTextField();
		GridBagConstraints gbc_iconpath = new GridBagConstraints();
		gbc_iconpath.gridwidth = 3;
		gbc_iconpath.insets = new Insets(0, 0, 5, 5);
		gbc_iconpath.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconpath.gridx = 2;
		gbc_iconpath.gridy = 3;
		add(iconpath, gbc_iconpath);
		iconpath.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
		gbc_btnBrowse.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBrowse.insets = new Insets(0, 0, 5, 0);
		gbc_btnBrowse.gridx = 5;
		gbc_btnBrowse.gridy = 3;
		add(btnBrowse, gbc_btnBrowse);

		JRadioButton rdbtnCaptureFromScreen = new JRadioButton("Screen Capture");
		GridBagConstraints gbc_rdbtnCaptureFromScreen = new GridBagConstraints();
		gbc_rdbtnCaptureFromScreen.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnCaptureFromScreen.anchor = GridBagConstraints.WEST;
		gbc_rdbtnCaptureFromScreen.gridwidth = 4;
		gbc_rdbtnCaptureFromScreen.gridx = 2;
		gbc_rdbtnCaptureFromScreen.gridy = 4;
		add(rdbtnCaptureFromScreen, gbc_rdbtnCaptureFromScreen);

		JButton btnCapture = new JButton("Capture");
		GridBagConstraints gbc_btnCapture = new GridBagConstraints();
		gbc_btnCapture.insets = new Insets(0, 0, 5, 0);
		gbc_btnCapture.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCapture.weightx = 10.0;
		gbc_btnCapture.gridwidth = 4;
		gbc_btnCapture.anchor = GridBagConstraints.WEST;
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
		GridBagConstraints gbc_lblMode = new GridBagConstraints();
		gbc_lblMode.anchor = GridBagConstraints.WEST;
		gbc_lblMode.insets = new Insets(0, 0, 5, 5);
		gbc_lblMode.gridx = 0;
		gbc_lblMode.gridy = 6;
		add(lblMode, gbc_lblMode);

		JRadioButton rdbtnSingleTouch = new JRadioButton("Single Touch");
		GridBagConstraints gbc_rdbtnSingleTouch = new GridBagConstraints();
		gbc_rdbtnSingleTouch.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnSingleTouch.anchor = GridBagConstraints.WEST;
		gbc_rdbtnSingleTouch.gridwidth = 4;
		gbc_rdbtnSingleTouch.gridx = 2;
		gbc_rdbtnSingleTouch.gridy = 6;
		add(rdbtnSingleTouch, gbc_rdbtnSingleTouch);

		JRadioButton rdbtnFollow = new JRadioButton("Follow");
		GridBagConstraints gbc_rdbtnFollow = new GridBagConstraints();
		gbc_rdbtnFollow.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFollow.gridwidth = 4;
		gbc_rdbtnFollow.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFollow.gridx = 2;
		gbc_rdbtnFollow.gridy = 7;
		add(rdbtnFollow, gbc_rdbtnFollow);

		ButtonGroup modeGroup = new ButtonGroup();
		modeGroup.add(rdbtnSingleTouch);
		modeGroup.add(rdbtnFollow);

		JLabel lblSize = new JLabel("Size:");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblSize.gridx = 0;
		gbc_lblSize.gridy = 9;
		add(lblSize, gbc_lblSize);

		JLabel lblWidth = new JLabel("width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 4;
		gbc_lblWidth.gridy = 9;
		add(lblWidth, gbc_lblWidth);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 9;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblHeight = new JLabel("height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 4;
		gbc_lblHeight.gridy = 10;
		add(lblHeight, gbc_lblHeight);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 10;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JButton btnApply = new JButton("APPLY");
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 4;
		gbc_btnApply.gridy = 14;
		add(btnApply, gbc_btnApply);

		JButton btnCancel = new JButton("CANCEL");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 14;
		add(btnCancel, gbc_btnCancel);

	}

	public void update(Command command) {
		this.combination.setText(command.getCombination());
	}
}
