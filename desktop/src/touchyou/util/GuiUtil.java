package touchyou.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Helper Class for writing the Application in GUI part
 * 
 * @author Thitiwat Thongbor
 *
 */
public class GuiUtil {
	public static int WIDTH = 1000;
	public static int HEIGHT = 600;

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
}
