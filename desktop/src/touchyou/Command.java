package touchyou;

import java.awt.Image;
import java.awt.image.BufferedImage;

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
     * mode 0 = SINGLE_TOUCH <br>
     * mode 1 = FOLLOW
     */
    private int mode;
    private String imagePath;
    private Image image;
    private double width, height, x, y;

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

    public double getY() {
	return y;
    }

    public void setY(double y) {
	this.y = y;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagepath) {
	this.imagePath = imagepath;
    }

    public double getWidth() {
	return width;
    }

    public void setWidth(double width) {
	this.width = width;
    }

    public double getHeight() {
	return height;
    }

    public void setHeight(double height) {
	this.height = height;
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }
    
    @Override
    public String toString() {
	if (combination.trim().isEmpty())
	    return "EMPTY COMMAND";
	StringBuffer name = new StringBuffer();
	String[] text = combination.split(":");
	for (String txt : text) {
	    switch (txt) {
	    case "16":
		name.append("SHIFT");
		break;
	    case "17":
		name.append("ALT");
		break;
	    case "18":
		name.append("CTRL");
		break;
	    case "157":
		name.append("CMD");
		break;
	    default:
		name.append((char) Integer.parseInt(txt));
	    }
	    name.append(" ");
	}
	return name.toString();
    }
}
