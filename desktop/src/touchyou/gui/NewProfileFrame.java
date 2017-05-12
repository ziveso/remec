package touchyou.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import touchyou.util.Controller;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NewProfileFrame extends JFrame {
    private JTextField textField;

    public NewProfileFrame() {
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 2 };
	gridBagLayout.rowHeights = new int[] { 0, 0, 2 };
	gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
	getContentPane().setLayout(gridBagLayout);

	JLabel lblNewProfileName = new JLabel("New Profile Name : ");
	GridBagConstraints gbc_lblNewProfileName = new GridBagConstraints();
	gbc_lblNewProfileName.insets = new Insets(0, 0, 5, 5);
	gbc_lblNewProfileName.gridx = 1;
	gbc_lblNewProfileName.gridy = 0;
	getContentPane().add(lblNewProfileName, gbc_lblNewProfileName);

	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.insets = new Insets(0, 0, 5, 0);
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.gridx = 3;
	gbc_textField.gridy = 0;
	getContentPane().add(textField, gbc_textField);
	textField.setColumns(10);

	JButton btnOk = new JButton("OK");
	btnOk.addActionListener((e) -> {
	    if (textField.getText().length() <= 3) {
		textField.setText("Invalid Profile Name.");
	    } else {
		SwingUtilities.invokeLater(() -> {
		    Controller.getInstance().newProfile(textField.getText());
		    new MainFrame().setVisible(true);
		    this.dispose();
		});
	    }
	});
	GridBagConstraints gbc_btnOk = new GridBagConstraints();
	gbc_btnOk.insets = new Insets(0, 0, 0, 5);
	gbc_btnOk.gridx = 1;
	gbc_btnOk.gridy = 1;
	getContentPane().add(btnOk, gbc_btnOk);

	JButton btnCancel = new JButton("CANCEL");
	btnCancel.addActionListener((e) -> {
	    this.dispose();
	});
	GridBagConstraints gbc_btnCancel = new GridBagConstraints();
	gbc_btnCancel.gridx = 3;
	gbc_btnCancel.gridy = 1;
	getContentPane().add(btnCancel, gbc_btnCancel);

	this.pack();
    }
}
