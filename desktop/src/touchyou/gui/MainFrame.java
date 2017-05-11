package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import touchyou.util.Controller;
import touchyou.util.GuiUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class MainFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -8718047311675995150L;

    // get the width and height from Util class.
    private final int Width = GuiUtil.WIDTH;
    private final int Height = GuiUtil.HEIGHT;

    /**
     * construct MainFrame.
     */
    public MainFrame() {
	setTitle("Touch You 0.1.4 Beta");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(1004, 635);

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem newprofile = new JMenuItem("New Profile");
	file.add(newprofile);
	menubar.add(file);
	setJMenuBar(menubar);

	JPanel workingPanel = new JPanel();
	workingPanel.setBackground(GuiUtil.getBackgroundColor());

	int model_width = Width * 5 / 10;
	int pane_width = Width * 2 / 10;
	GridBagLayout gbl_workingPanel = new GridBagLayout();
	gbl_workingPanel.columnWidths = new int[] { 272, 331, 203, 0 };
	gbl_workingPanel.rowHeights = new int[] { 515, 0 };
	gbl_workingPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
	gbl_workingPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
	workingPanel.setLayout(gbl_workingPanel);
	SettingPanel settingPanel = new SettingPanel();
	settingPanel.setMinimumSize(new Dimension(200, 227));
	GridBagConstraints gbc_settingPanel = new GridBagConstraints();
	gbc_settingPanel.fill = GridBagConstraints.BOTH;
	gbc_settingPanel.insets = new Insets(5, 5, 0, 5);
	gbc_settingPanel.gridx = 0;
	gbc_settingPanel.gridy = 0;
	workingPanel.add(settingPanel, gbc_settingPanel);
	getContentPane().add(workingPanel);
	JPanel Bound = new JPanel();
	Bound.setPreferredSize(new Dimension(model_width, Height));
	// int sideGap = (int) ((GuiUtil.HEIGHT - mobileWidth) / 2.0);
	Bound.setBorder(new EmptyBorder(0, 0, 0, 0));
	Bound.setBackground(Color.decode("#282828")); // almost black;
	Bound.setOpaque(true);
	ModelPanel modelPanel = new ModelPanel();
	Bound.add(modelPanel);
	GridBagConstraints gbc_modelPanel = new GridBagConstraints();
	gbc_modelPanel.insets = new Insets(0, 0, 0, 5);
	gbc_modelPanel.fill = GridBagConstraints.BOTH;
	gbc_modelPanel.gridx = 1;
	gbc_modelPanel.gridy = 0;
	workingPanel.add(Bound, gbc_modelPanel);

	WidgetPanel widgetPanel = new WidgetPanel(pane_width, Height);
	GridBagConstraints gbc_widgetPanel = new GridBagConstraints();
	gbc_widgetPanel.fill = GridBagConstraints.BOTH;
	gbc_widgetPanel.insets = new Insets(5, 0, 0, 0);
	gbc_widgetPanel.gridx = 2;
	gbc_widgetPanel.gridy = 0;
	workingPanel.add(widgetPanel, gbc_widgetPanel);

	StatusPanel statusPanel = new StatusPanel();
	statusPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(128, 128, 128), null, null, null));
	GridBagLayout gridBagLayout = (GridBagLayout) statusPanel.getLayout();
	gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
	getContentPane().add(statusPanel, BorderLayout.SOUTH);

	Controller controller = Controller.getInstance();
	controller.setMainFrame(this);
	controller.setModelPanel(modelPanel);
	controller.setSettingPanel(settingPanel);
	controller.setWidgetPanel(widgetPanel);

	this.pack();
	setLocationRelativeTo(null);
	Controller.getInstance().clear();

    }

}