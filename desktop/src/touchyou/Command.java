package touchyou;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.KeyStroke;

import com.sun.webkit.dom.KeyboardEventImpl;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Command class contains essential information of a command button.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class Command {
    private int id;
    private String combination;
    /**
     * mode 0 = TAP <br>
     * mode 1 = FOLLOW
     */
    private int mode;
    private String imagePath;
    private Image image;
    private int width, height, x, y;

    public Image getImage() {
	if (image == null) {
	    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	}
	return image;
    }

    public void setImage(Image image) {
	this.image = image;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    public String getCombination() {
	return combination;
    }

    public void setCombination(String combination) {
	this.combination = combination;
    }

    public int getMode() {
	return mode;
    }

    public void setMode(int mode) {
	this.mode = mode;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagepath) {
	this.imagePath = imagepath;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    @Override
    public String toString() {
	if (combination.trim().isEmpty())
	    return "EMPTY COMMAND";
	String[] array = combination.split(":");
	List<String> keycodeText = Arrays.stream(array).map(keycode -> {
	    return KeyEvent.getKeyText(Integer.parseInt(keycode));
	}).collect(Collectors.toList());

	return String.join("", keycodeText);
    }
}
