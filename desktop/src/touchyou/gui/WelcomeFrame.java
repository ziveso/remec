package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import touchyou.util.Controller;
import touchyou.util.GuiUtil;

public class WelcomeFrame extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public WelcomeFrame() {
	setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	Color foreground = GuiUtil.getForegroundColor();
	setTitle("Touch You Pro");
	setLocation(new Point(0, 0));
	setResizable(false);
	setMinimumSize(new Dimension(650, 400));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 650, 210);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(72, 72, 72));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout());

	JPanel panel = new JPanel();
	panel.setBackground(new Color(72, 72, 72));
	panel.setBorder(new EmptyBorder(5, 5, 10, 5));
	GridBagConstraints gbc_panel = new GridBagConstraints();
	gbc_panel.insets = new Insets(0, 0, 5, 0);
	gbc_panel.fill = GridBagConstraints.BOTH;
	gbc_panel.gridx = 0;
	gbc_panel.gridy = 0;
	contentPane.add(panel, BorderLayout.NORTH);

	JLabel lblNewLabel_2 = new JLabel("Welcome to Touch You Pro 2017");
	lblNewLabel_2.setForeground(foreground);
	lblNewLabel_2.setFont(new Font("Lantinghei TC", Font.PLAIN, 30));
	panel.add(lblNewLabel_2);

	JPanel panel_1 = new JPanel();
	panel_1.setBackground(new Color(72, 72, 72));
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
	listPanel.setLayout(new BorderLayout(0, 0));

	JList<String> list = new JList();
	list.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
	list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	DefaultListModel<String> model = new DefaultListModel<>();
	list.setModel(model);
	for (String f : new File("./profiles/").list()) {
	    if (f.contains(".profile")) {
		model.addElement(f);
	    }
	}
	listPanel.add(new JScrollPane(list));

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
	menuPanel.setLayout(new BorderLayout(0, 0));

	JLabel lblNewLabel = new JLabel("Quick Start");
	lblNewLabel.setBorder(new EmptyBorder(4, 4, 4, 4));
	lblNewLabel.setOpaque(true);
	lblNewLabel.setBackground(new Color(192, 192, 192));
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	menuPanel.add(lblNewLabel, BorderLayout.NORTH);

	JPanel panel_2 = new JPanel();
	panel_2.setBackground(new Color(245, 245, 245));
	panel_2.setBorder(new EmptyBorder(20, 20, 20, 20));
	menuPanel.add(panel_2, BorderLayout.CENTER);
	panel_2.setLayout(new GridLayout(0, 1, 0, 10));

	MouseAdapter mouseAdapter = new MouseAdapter() {
	    @Override
	    public void mouseEntered(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setBackground(Color.LIGHT_GRAY);
	    }

	    @Override
	    public void mouseExited(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setBackground(Color.decode("#f5f5f5"));
	    }
	};

	JButton btnNew = new JButton("Create a new Touch You profile");
	btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnNew.setHorizontalAlignment(SwingConstants.LEFT);
	btnNew.setBorder(new EmptyBorder(0, 20, 0, 20));
	btnNew.setOpaque(true);
	btnNew.setBackground(new Color(245, 245, 245));
	btnNew.addActionListener(e -> {
	    new NewProfileFrame().setVisible(true);
	});
	btnNew.addMouseListener(mouseAdapter);
	panel_2.add(btnNew);

	JButton btnOpen = new JButton("Open existing Touch You profile");
	btnOpen.addActionListener(e -> {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new File("./profiles/"));
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setFileFilter(new FileNameExtensionFilter("Profile files (*.profile)", "profile"));
	    int result = fileChooser.showOpenDialog(this);
	    if (result == JFileChooser.APPROVE_OPTION) {
		Controller.getInstance().openProfile(fileChooser.getSelectedFile());
		runMainFrame();
	    }
	});
	btnOpen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnOpen.setHorizontalAlignment(SwingConstants.LEFT);
	btnOpen.setBorder(new EmptyBorder(0, 20, 0, 20));
	btnOpen.setOpaque(true);
	btnOpen.setBackground(new Color(245, 245, 245));
	btnOpen.addMouseListener(mouseAdapter);
	panel_2.add(btnOpen);

	JButton btnDownload = new JButton("Download profile from Touch You Community");
	btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnDownload.setHorizontalAlignment(SwingConstants.LEFT);
	btnDownload.setBorder(new EmptyBorder(0, 20, 0, 20));
	btnDownload.setOpaque(true);
	btnDownload.setBackground(new Color(245, 245, 245));
	btnDownload.addMouseListener(mouseAdapter);
	panel_2.add(btnDownload);

	JButton btnSetting = new JButton("About");
	btnSetting.addActionListener(e -> {
	    JOptionPane pane = new JOptionPane(new AboutPanel(), JOptionPane.INFORMATION_MESSAGE);
	    pane.createDialog(this, "About").setVisible(true);

	});
	btnSetting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	btnSetting.setHorizontalAlignment(SwingConstants.LEFT);
	btnSetting.setBorder(new EmptyBorder(0, 20, 0, 20));
	btnSetting.setOpaque(true);
	btnSetting.setBackground(new Color(245, 245, 245));
	btnSetting.addMouseListener(mouseAdapter);
	panel_2.add(btnSetting);

	JLabel lblNewLabel_3 = new JLabel("");
	lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
	panel_2.add(lblNewLabel_3);

	JLabel lblThisProjectIs = new JLabel("Version: 0.1.4 Beta");
	lblThisProjectIs.setBorder(new EmptyBorder(0, 6, 0, 6));
	lblThisProjectIs.setOpaque(true);
	lblThisProjectIs.setBackground(new Color(245, 245, 245));
	lblThisProjectIs.setHorizontalAlignment(SwingConstants.RIGHT);
	menuPanel.add(lblThisProjectIs, BorderLayout.SOUTH);

	setLocationRelativeTo(null);
    }

    private void runMainFrame() {
	SwingUtilities.invokeLater(() -> {
	    new MainFrame().setVisible(true);
	    this.dispose();
	});
    }
}
