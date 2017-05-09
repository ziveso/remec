package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import touchyou.App;
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
		int mobileWidth = 300;
		int mobileHeight = 500;
		setMobileSize(mobileWidth, mobileHeight);
		int sideGap = (int) ((GuiUtil.HEIGHT - mobileWidth)/2.0);
		setBorder(new CompoundBorder(GuiUtil.getBorder(), new EmptyBorder(0, sideGap, 0, sideGap)));

		this.setBackground(new Color(40, 40, 40)); // almost black;
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
		mobile.invalidate();
	}
}