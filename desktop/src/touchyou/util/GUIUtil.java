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

import touchyou.gui.MobilePanel;

/**
 * Helper Class for writing the Application in GUI part
 * 
 * @author Thitiwat Thongbor
 *
 */
public class GUIUtil {
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
	URL url = GUIUtil.class.getResource(URL);
	return new ImageIcon(url).getImage();
    }

    public static Color getBackgroundColor() {
	// return new Color(72, 72, 72);
	return Color.decode("#424242");
    }
    public static Color getOrange() {
	return Color.decode("#FF8019");
    }

    public static Color getForegroundColor() {
	return Color.WHITE;
    }
}
