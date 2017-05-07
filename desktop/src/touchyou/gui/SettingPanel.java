package touchyou.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import touchyou.util.GuiUtil;

public class SettingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5468288983318943380L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public SettingPanel(int width, int height) {
		this.setPreferredSize(new Dimension(GuiUtil.WIDTH * 3 / 10, GuiUtil.HEIGHT));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Command");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblIcon = new JLabel("Icon");
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

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton btnBrowse = new JButton("Browse");
		GridBagConstraints gbc_btnBrowse = new GridBagConstraints();
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

		JLabel lblMode = new JLabel("Mode");
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
		gbc_rdbtnFollow.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnFollow.gridx = 2;
		gbc_rdbtnFollow.gridy = 6;
		add(rdbtnFollow, gbc_rdbtnFollow);

	}
}
