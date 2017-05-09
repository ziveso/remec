package touchyou.gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JEditorPane;

public class AboutPanel extends JPanel {

    /**
     * Create the panel.
     */
    public AboutPanel() {
    	setLayout(new GridLayout(1, 0, 0, 0));
    	
    	JPanel panel = new JPanel();
    	panel.setBorder(new EmptyBorder(5, 5, 5, 5));
    	add(panel);
    	GridBagLayout gbl_panel = new GridBagLayout();
    	gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
    	gbl_panel.rowHeights = new int[]{0, 0, 0, 103, 0, 0};
    	gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    	gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    	panel.setLayout(gbl_panel);
    	
    	JLabel label = new JLabel("Touch You Remote Shortcut Application");
    	GridBagConstraints gbc_label = new GridBagConstraints();
    	gbc_label.anchor = GridBagConstraints.WEST;
    	gbc_label.insets = new Insets(0, 0, 5, 0);
    	gbc_label.gridx = 3;
    	gbc_label.gridy = 0;
    	panel.add(label, gbc_label);
    	
    	JLabel label_1 = new JLabel("Version: 0.1.4 Beta");
    	GridBagConstraints gbc_label_1 = new GridBagConstraints();
    	gbc_label_1.anchor = GridBagConstraints.WEST;
    	gbc_label_1.insets = new Insets(0, 0, 5, 0);
    	gbc_label_1.gridx = 3;
    	gbc_label_1.gridy = 1;
    	panel.add(label_1, gbc_label_1);
    	
    	JLabel label_2 = new JLabel("Contributors:");
    	GridBagConstraints gbc_label_2 = new GridBagConstraints();
    	gbc_label_2.anchor = GridBagConstraints.WEST;
    	gbc_label_2.insets = new Insets(0, 0, 5, 0);
    	gbc_label_2.gridx = 3;
    	gbc_label_2.gridy = 2;
    	panel.add(label_2, gbc_label_2);
    	
    	JPanel panel_1 = new JPanel();
    	GridBagConstraints gbc_panel_1 = new GridBagConstraints();
    	gbc_panel_1.fill = GridBagConstraints.BOTH;
    	gbc_panel_1.insets = new Insets(0, 0, 5, 0);
    	gbc_panel_1.gridx = 3;
    	gbc_panel_1.gridy = 3;
    	panel.add(panel_1, gbc_panel_1);
    	GridBagLayout gbl_panel_1 = new GridBagLayout();
    	gbl_panel_1.columnWidths = new int[]{0, 0, 0};
    	gbl_panel_1.rowHeights = new int[]{0, 0};
    	gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
    	panel_1.setLayout(gbl_panel_1);
    	
    	JLabel label_3 = new JLabel("Image Here");
    	GridBagConstraints gbc_label_3 = new GridBagConstraints();
    	gbc_label_3.fill = GridBagConstraints.BOTH;
    	gbc_label_3.insets = new Insets(0, 0, 0, 5);
    	gbc_label_3.gridx = 0;
    	gbc_label_3.gridy = 0;
    	panel_1.add(label_3, gbc_label_3);
    	
    	JEditorPane txtpnKongponCharanwattanakitEmail = new JEditorPane();
    	txtpnKongponCharanwattanakitEmail.setContentType("text/html");
    	txtpnKongponCharanwattanakitEmail.setText("<html>\nKongpon Charanwattanakit<br>\nEmail: jackykongpon@gmail.com<br>\nGitHub: <a href=\"https://github.com/kykungz\">https://github.com/kykungz</a><br>\n</html>");
    	txtpnKongponCharanwattanakitEmail.setOpaque(false);
    	txtpnKongponCharanwattanakitEmail.setEditable(false);
    	GridBagConstraints gbc_txtpnKongponCharanwattanakitEmail = new GridBagConstraints();
    	gbc_txtpnKongponCharanwattanakitEmail.gridx = 1;
    	gbc_txtpnKongponCharanwattanakitEmail.gridy = 0;
    	panel_1.add(txtpnKongponCharanwattanakitEmail, gbc_txtpnKongponCharanwattanakitEmail);
    	
    	JPanel panel_2 = new JPanel();
    	GridBagConstraints gbc_panel_2 = new GridBagConstraints();
    	gbc_panel_2.fill = GridBagConstraints.BOTH;
    	gbc_panel_2.gridx = 3;
    	gbc_panel_2.gridy = 4;
    	panel.add(panel_2, gbc_panel_2);
    	GridBagLayout gbl_panel_2 = new GridBagLayout();
    	gbl_panel_2.columnWidths = new int[]{0, 0, 0};
    	gbl_panel_2.rowHeights = new int[]{0, 0};
    	gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    	gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
    	panel_2.setLayout(gbl_panel_2);
    	
    	JLabel label_4 = new JLabel("Image Here");
    	GridBagConstraints gbc_label_4 = new GridBagConstraints();
    	gbc_label_4.fill = GridBagConstraints.BOTH;
    	gbc_label_4.insets = new Insets(0, 0, 0, 5);
    	gbc_label_4.gridx = 0;
    	gbc_label_4.gridy = 0;
    	panel_2.add(label_4, gbc_label_4);
    	
    	JTextPane textPane12 = new JTextPane();
    	textPane12.setContentType("text/html");
    	textPane12.setText("<html>\nThitiwat Thongbor<br>\nEmail: thitiwat.tho1@ku.th<br>\nGitHub: <a href=\"https://github.com/thitgorn\">https://github.com/thitgorn</a><br>\n</html>");
    	textPane12.setOpaque(false);
    	textPane12.setEditable(false);
    	GridBagConstraints gbc_textPane12 = new GridBagConstraints();
    	gbc_textPane12.gridx = 1;
    	gbc_textPane12.gridy = 0;
    	panel_2.add(textPane12, gbc_textPane12);

    }

}
