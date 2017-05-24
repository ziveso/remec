package touchyou.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import touchyou.Command;

/**
 * Helper Class for writing the Application in GUI part
 * 
 * @author Thitiwat Thongbor
 *
 */
public class GUIUtil {
    public static int WIDTH = 1000;
    public static int HEIGHT = 500;
    public static String version = "1.0.1 beta";

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

    public static Color getBackgroundColor() {
	return Color.decode("#424242");
    }

    public static Color getOrange() {
	return Color.decode("#FF8019");
    }

    public static Color getForegroundColor() {
	return Color.WHITE;
    }

    public static Image resize(Image img, int newW, int newH) {
	return img.getScaledInstance(newW, newH, Image.SCALE_DEFAULT);
    }

    public static String extractBytes(Image img) {
	if (img == Command.BLANK_IMAGE)
	    return "0";
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
