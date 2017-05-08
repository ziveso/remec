package touchyou.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import touchyou.util.GuiUtil;
/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ModelPanel extends JPanel {

	private final String Image_URL = "/images/phone.png";
	private Image img;
	private static JPanel mobile;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3593230878415293635L;

	protected ModelPanel(int width, int height) {
		// now 489 * 599
		setPreferredSize(new Dimension(width, height));
		setBorder(GuiUtil.getBorder());
		setOpaque(false);
		initcomponent();
	}

	private void initcomponent() {
		mobile = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		};
		
		mobile.setLayout(null); // make it movable , no layout
		img = GuiUtil.getImage(Image_URL);
		setMobileSize(300, 500);
		add(mobile);
	}

	public void setMobileSize(int width, int height) {
		mobile.setPreferredSize(new Dimension(width, height));
		img = getScaledImage(img, width, height);
		this.validate();
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	public static JPanel getMobile() {
		return mobile;
	}

	public static void updateComponent() {
		mobile.invalidate();
	}
}