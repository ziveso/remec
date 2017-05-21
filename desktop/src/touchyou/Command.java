package touchyou;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command class contains essential information of a command button.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class Command {
    public static final Image BLANK_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    private int id;
    private String combination;
    private String label = "";
    private int lableMode;
    /**
     * mode 0 = TAP <br>
     * mode 1 = FOLLOW
     */
    private int mode;
    private String imagePath;
    private Image image = BLANK_IMAGE;
    private int width, height, x, y;

    public int getLableMode() {
	return lableMode;
    }

    public void setLableMode(int lableMode) {
	this.lableMode = lableMode;
    }

    public void setLabel(String label) {
	this.label = label;
    }

    public String getLabel() {
	return label;
    }

    public Image getImage() {
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
