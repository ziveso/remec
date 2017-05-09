package touchyou.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

import touchyou.gui.ModelPanel;

/**
 * Helper Class for writing the Application in GUI part
 * 
 * @author Thitiwat Thongbor
 *
 */
public class GuiUtil {
	public static int WIDTH = 1000;
	public static int HEIGHT = 500;
	private static int x = 0;
	private static int y = 0;

	/**
	 * get the Factory Border use it for debug. in case of you don't want border
	 * , just return null; otherwise , return
	 * BorderFactory.createLineBorder(Color.BLACK);
	 * 
	 * @return the border
	 */
	public static Border getBorder() {
		return BorderFactory.createLineBorder(Color.BLACK);
	}

	public static Image getImage(String URL) {
		URL url = GuiUtil.class.getResource(URL);
		return new ImageIcon(url).getImage();
	}

	/**
	 * 
	 * @param dimension
	 *            current witdth and height , use getPreferredSize();
	 * @return
	 */
	public static Rectangle getInitBound(Dimension dimension) {
		int max_width = (int) (ModelPanel.getMobile().getWidth() - dimension.getWidth());
		int max_height = ModelPanel.getMobile().getHeight();
		if (x + dimension.getWidth() > max_width) {
			x = 0;
			y += dimension.getHeight();
		}
		if (y + dimension.getHeight() > max_height) {
			y = 0;
		}
		int width = (int) dimension.getWidth();
		x += width;
		return new Rectangle(new Point(x - width, y), dimension);
	}

	public static Color getBackgroundColor() {
		return new Color(72, 72, 72);
	}

	public static Color getForegroundColor() {
		return Color.WHITE;
	}
}
