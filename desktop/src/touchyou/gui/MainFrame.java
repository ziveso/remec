package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import touchyou.App;
import touchyou.util.GuiUtil;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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

    private App app;

    // get the width and height from Util class.
    private final int Width = GuiUtil.WIDTH;
    private final int Height = GuiUtil.HEIGHT;


    /**
     * construct MainFrame.
     */
    public MainFrame(App app) {
    	setTitle("Touch You 0.1.4 Beta");
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setSize(Width, Height);
	this.app = app;
	this.setResizable(false);

	JMenuBar menubar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem newprofile = new JMenuItem("New Profile");
	file.add(newprofile);
	menubar.add(file);
	this.setJMenuBar(menubar);

	JPanel pane = new JPanel(new FlowLayout());
	pane.setBackground(GuiUtil.getBackgroundColor());

	int model_width = Width * 5 / 10;
	int pane_width = Width * 2 / 10;
	SettingPanel settingPanel = new SettingPanel(app, this);
	pane.add(settingPanel);
	ModelPanel modelPanel = new ModelPanel(model_width, Height, app);
	pane.add(modelPanel);

	WidgetPanel widgetPanel = new WidgetPanel(pane_width, Height, app);
	pane.add(widgetPanel);
	super.add(pane);

	JPanel panel = new JPanel();
	getContentPane().add(panel, BorderLayout.SOUTH);
	GridBagLayout gbl_panel = new GridBagLayout();
	gbl_panel.columnWidths = new int[] { 485, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	gbl_panel.rowHeights = new int[] { 16, 0 };
	gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
	gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
	panel.setLayout(gbl_panel);

	JLabel lblNewLabel = new JLabel("New label");
	GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
	gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
	gbc_lblNewLabel.gridx = 0;
	gbc_lblNewLabel.gridy = 0;
	panel.add(lblNewLabel, gbc_lblNewLabel);
	SyncButton syncButton = new SyncButton(pane_width, Height / 10, app);
	GridBagConstraints gbc_syncButton = new GridBagConstraints();
	gbc_syncButton.gridx = 17;
	gbc_syncButton.gridy = 0;
	panel.add(syncButton, gbc_syncButton);

	this.pack();
	setLocationRelativeTo(null);
    }

//    public static void updateWidget() {
//	widgetPanel.validate();
//    }
}