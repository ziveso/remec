package touchyou.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Helper Class for writing the Application in GUI part
 * 
 * @author Thitiwat Thongbor
 *
 */
public class GUIUtil {
    public static int WIDTH = 1000;
    public static int HEIGHT = 500;

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
	return Color.decode("#424242");
    }

    public static Color getOrange() {
	return Color.decode("#FF8019");
    }

    public static Color getForegroundColor() {
	return Color.WHITE;
    }


    public static String extractBytes(Image img) {
	BufferedImage bufferedImage = (BufferedImage) img;
	ByteArrayOutputStream bos = null;
	String[] array = null;
	try {
	    bos = new ByteArrayOutputStream();
	    ImageIO.write(bufferedImage, "png", bos);
	    byte[] bytearray = bos.toByteArray();
	    array = new String[bytearray.length];
	    for (int i = 0; i < bytearray.length; i++) {
		array[i] = String.valueOf(bytearray[i]);
	    }
	} catch (IOException e) {
	    return null;
	} finally {
	    try {
		bos.close();
	    } catch (Exception e) {
	    }
	}
	return String.join(",", array);
    }
}
