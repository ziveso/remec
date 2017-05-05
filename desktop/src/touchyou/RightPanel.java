package touchyou;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class RightPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6356057022304898254L;

	public RightPanel() {
		this.setLayout(new BorderLayout());
		initComponent();
	}

	private void initComponent() {
		// this is for custume the button
		// TODO
		// use JLabel for now , change it later.
		JLabel phone = new JLabel();

		Image img = new ImageIcon("./src/images/phone.png").getImage();
		// Resize the image
		img = getScaledImage(img, 200, 400);
		phone.setIcon(new ImageIcon(img));
		this.add(phone,BorderLayout.WEST);
		
		JButton sync = new JButton("SYNC");
		sync.setBackground(Color.BLUE);
		this.add(sync,BorderLayout.EAST);
	}

	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}
