package touchyou.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import touchyou.App;
import touchyou.Command;
import touchyou.util.GuiUtil;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ModelPanel extends JPanel {

	private static JPanel mobile;
	private App app;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3593230878415293635L;

	protected ModelPanel(int width, int height, App app) {
		this.app = app;
		mobile = new JPanel();
		mobile.setBackground(Color.white);
		mobile.setLayout(null); // make it movable , no layout

		/**
		 * pixel phone has 1080 x 1776 resolution.
		 */

		int mobileWidth = 1080 / 4;
		int mobileHeight = 1776 / 4;
		setMobileSize(mobileWidth, mobileHeight);
		int sideGap = (int) ((GuiUtil.HEIGHT - mobileWidth) / 2.0);
		// setBorder(new CompoundBorder(GuiUtil.getBorder(), new EmptyBorder(0,
		// sideGap, 0, sideGap)));
		setBorder(new EmptyBorder(0, sideGap, 0, sideGap));
		this.setBackground(Color.decode("#282828")); // almost black;
		setOpaque(true);
		add(mobile);
	}

	public void setMobileSize(int width, int height) {
		mobile.setPreferredSize(new Dimension(width, height));
		this.validate();
	}

	public static JPanel getMobile() {
		return mobile;
	}

	public static void updateComponent() {
		mobile.repaint();
		mobile.revalidate();
	}

	public void update(Command command) {
		Component[] comps = ModelPanel.getMobile().getComponents();
		for (Component c : comps) {
			JButton button = (JButton) c;
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

	}
}