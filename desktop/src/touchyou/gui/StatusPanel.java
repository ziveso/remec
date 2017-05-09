package touchyou.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import touchyou.App;
import touchyou.util.GuiUtil;
import java.awt.Color;

public class StatusPanel extends JPanel {

    /**
     * Create the panel.
     */
    public StatusPanel() {
	setBackground(GuiUtil.getBackgroundColor());
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[] { 137, 200, 0 };
	gridBagLayout.rowHeights = new int[] { 53, 0 };
	gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
	gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	setLayout(gridBagLayout);

	JPanel panel = new JPanel();
	panel.setOpaque(false);
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.insets = new Insets(0, 0, 0, 5);
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 0;
	add(panel, gbc_panel);
	panel.setLayout(new GridLayout(0, 1, 0, 0));

	JLabel lblYourIpAddress = new JLabel("Your IP Address: ");
	lblYourIpAddress.setForeground(Color.WHITE);
	panel.add(lblYourIpAddress);

	JLabel lblConnectionStatus = new JLabel("Connection Status: -");
	lblConnectionStatus.setForeground(Color.WHITE);
	panel.add(lblConnectionStatus);

	JLabel lblConnectedFrom = new JLabel("Connected From: -");
	lblConnectedFrom.setForeground(Color.WHITE);
	panel.add(lblConnectedFrom);

	SyncButton syncButton = new SyncButton(200, 50, (App) null);
	syncButton.setBackground(Color.WHITE);
	syncButton.setBorder(new EmptyBorder(20, 20, 20, 20));
	GridBagConstraints gbc_syncButton = new GridBagConstraints();
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

}
