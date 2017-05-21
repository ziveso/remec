package touchyou.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import touchyou.util.Controller;
import touchyou.util.GUIUtil;

public class StatusPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 2861841184923249614L;
    private SyncButton syncButton;

    /**
     * Create the panel.
     */
    public StatusPanel() {
	setBackground(GUIUtil.getBackgroundColor());
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 137, 200, 0 };
	gridBagLayout.rowHeights = new int[] { 53, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JPanel panel = new JPanel();
	panel.setBorder(new EmptyBorder(5, 5, 5, 5));
	panel.setOpaque(false);
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.insets = new Insets(0, 0, 0, 5);
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 0;
	add(panel, gbc_panel);
	panel.setLayout(new GridLayout(0, 1, 0, 0));

	JPanel panel_1 = new JPanel();
	panel_1.setOpaque(false);
	panel.add(panel_1);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[] { 145, 37, 38, 0, 0 };
	gbl_panel_1.rowHeights = new int[] { 20, 0 };
	gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
	gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel_1.setLayout(gbl_panel_1);

	JLabel lblRemoteConnection = new JLabel("Remote Connection:");
	GridBagConstraints gbc_lblRemoteConnection = new GridBagConstraints();
	gbc_lblRemoteConnection.anchor = GridBagConstraints.WEST;
	gbc_lblRemoteConnection.insets = new Insets(0, 0, 0, 5);
	gbc_lblRemoteConnection.fill = GridBagConstraints.VERTICAL;
	gbc_lblRemoteConnection.gridx = 0;
	gbc_lblRemoteConnection.gridy = 0;
	panel_1.add(lblRemoteConnection, gbc_lblRemoteConnection);
	lblRemoteConnection.setForeground(Color.WHITE);

	JRadioButton rdbtnOn = new JRadioButton("On");
	rdbtnOn.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnOn = new GridBagConstraints();
	gbc_rdbtnOn.anchor = GridBagConstraints.WEST;
	gbc_rdbtnOn.insets = new Insets(0, 0, 0, 5);
	gbc_rdbtnOn.gridx = 1;
	gbc_rdbtnOn.gridy = 0;
	rdbtnOn.addActionListener(e -> {
	    Controller.getInstance().startBroadcast();
	});
	panel_1.add(rdbtnOn, gbc_rdbtnOn);

	JRadioButton rdbtnOff = new JRadioButton("Off");
	rdbtnOff.setForeground(Color.WHITE);
	GridBagConstraints gbc_rdbtnOff = new GridBagConstraints();
	gbc_rdbtnOff.anchor = GridBagConstraints.WEST;
	gbc_rdbtnOff.insets = new Insets(0, 0, 0, 5);
	gbc_rdbtnOff.gridx = 2;
	gbc_rdbtnOff.gridy = 0;
	rdbtnOff.addActionListener(e -> {
	    Controller.getInstance().stopBroadcast();
	});
	panel_1.add(rdbtnOff, gbc_rdbtnOff);

	ButtonGroup connectionGroup = new ButtonGroup();
	connectionGroup.add(rdbtnOff);
	connectionGroup.add(rdbtnOn);
	rdbtnOff.setSelected(true);

	JLabel lblYourIpAddress = new JLabel("Your IP Address: ");
	lblYourIpAddress.setForeground(Color.WHITE);
	panel.add(lblYourIpAddress);

	JLabel lblConnectionStatus = new JLabel("Connection Status: -");
	lblConnectionStatus.setForeground(Color.WHITE);
	panel.add(lblConnectionStatus);

	JLabel lblConnectedFrom = new JLabel("Connected From: -");
	lblConnectedFrom.setForeground(Color.WHITE);
	panel.add(lblConnectedFrom);

	syncButton = new SyncButton(200, 50);
	syncButton.setBackground(Color.WHITE);
	syncButton.setBorder(new EmptyBorder(20, 20, 20, 20));
	GridBagConstraints gbc_syncButton = new GridBagConstraints();
	gbc_syncButton.fill = GridBagConstraints.VERTICAL;
	gbc_syncButton.insets = new Insets(10, 10, 10, 10);
	gbc_syncButton.anchor = GridBagConstraints.EAST;
	gbc_syncButton.gridx = 1;
	gbc_syncButton.gridy = 0;
	add(syncButton, gbc_syncButton);
	new Thread(() -> {
	    try {
		InetAddress localHost = InetAddress.getLocalHost();
		SwingUtilities.invokeLater(() -> {
		    lblYourIpAddress.setText(lblYourIpAddress.getText() + localHost.getHostAddress());
		});
	    } catch (UnknownHostException e) {
		e.printStackTrace();
	    }
	}).start();

    }

    public void clear() {
	syncButton.setEnabled(false);
    }
}
