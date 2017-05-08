package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import touchyou.App;

public class WelcomeFrame extends JFrame {

    private JPanel contentPane;

    private App app;

    /**
     * Create the frame.
     */
    public WelcomeFrame(App app) {
	this.app = app;
	setMinimumSize(new Dimension(650, 400));
	setTitle("Touch You Pro");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 650, 210);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	// GridBagLayout gbl_contentPane = new GridBagLayout();
	// gbl_contentPane.columnWidths = new int[]{171, 0};
	// gbl_contentPane.rowHeights = new int[]{125, 0, 0};
	// gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	// gbl_contentPane.rowWeights = new double[]{1.0, 1.0,
	// Double.MIN_VALUE};
	// contentPane.setLayout(gbl_contentPane);
	contentPane.setLayout(new BorderLayout());

	JPanel panel = new JPanel();
	panel.setBorder(new EmptyBorder(5, 5, 10, 5));
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.insets = new Insets(0, 0, 5, 0);
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 0;
	contentPane.add(panel, BorderLayout.NORTH);

	JLabel lblNewLabel_2 = new JLabel("Welcome to Touch You Pro 2017");
	lblNewLabel_2.setFont(new Font("Lantinghei TC", Font.PLAIN, 30));
	panel.add(lblNewLabel_2);

	JPanel panel_1 = new JPanel();
	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
	gbc_panel_1.fill = GridBagConstraints.BOTH;
	gbc_panel_1.gridx = 0;
	gbc_panel_1.gridy = 1;
	contentPane.add(panel_1, BorderLayout.CENTER);
	GridBagLayout gbl_panel_1 = new GridBagLayout();
	gbl_panel_1.columnWidths = new int[] { 244, 112, 0 };
	gbl_panel_1.rowHeights = new int[] { 0, 0 };
	gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
	gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	panel_1.setLayout(gbl_panel_1);

	JPanel listPanel = new JPanel();
	GridBagConstraints gbc_listPanel = new GridBagConstraints();
	gbc_listPanel.fill = GridBagConstraints.BOTH;
	gbc_listPanel.insets = new Insets(0, 0, 0, 5);
	gbc_listPanel.gridx = 0;
	gbc_listPanel.gridy = 0;
	panel_1.add(listPanel, gbc_listPanel);
	listPanel.setBorder(new LineBorder(new Color(192, 192, 192)));
	listPanel.setLayout(new BorderLayout(0, 0));

	JList list = new JList();
	listPanel.add(list);

	JLabel lblNewLabel_1 = new JLabel("Profile List");
	lblNewLabel_1.setOpaque(true);
	lblNewLabel_1.setBackground(new Color(192, 192, 192));
	lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel_1.setBorder(new EmptyBorder(4, 4, 4, 4));
	listPanel.add(lblNewLabel_1, BorderLayout.NORTH);

	JPanel menuPanel = new JPanel();
	GridBagConstraints gbc_menuPanel = new GridBagConstraints();
	gbc_menuPanel.fill = GridBagConstraints.BOTH;
	gbc_menuPanel.gridx = 1;
	gbc_menuPanel.gridy = 0;
	panel_1.add(menuPanel, gbc_menuPanel);
	menuPanel.setBorder(new LineBorder(new Color(192, 192, 192)));
	menuPanel.setLayout(new BorderLayout(0, 0));

	JLabel lblNewLabel = new JLabel("Quick Start");
	lblNewLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
	lblNewLabel.setOpaque(true);
	lblNewLabel.setBackground(new Color(192, 192, 192));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	menuPanel.add(lblNewLabel, BorderLayout.NORTH);

	JPanel panel_2 = new JPanel();
	menuPanel.add(panel_2, BorderLayout.CENTER);
	panel_2.setLayout(new GridLayout(0, 1, 0, 20));

	JButton btnNewButton_1 = new JButton("Create a new Touch You profile");
	btnNewButton_1.addActionListener(e -> {
	    SwingUtilities.invokeLater(() -> {
		new TouchyouGui(app).run();
	    });
	    this.dispose();
	});
	panel_2.add(btnNewButton_1);

	JButton btnNewButton_2 = new JButton("Open existing Touch You profile");
	panel_2.add(btnNewButton_2);

	JButton btnNewButton_3 = new JButton("Download custom profile from Touch You cloud");
	panel_2.add(btnNewButton_3);

	JButton btnNewButton = new JButton("Settings");
	panel_2.add(btnNewButton);
    }

}
