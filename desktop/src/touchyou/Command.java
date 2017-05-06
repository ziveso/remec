package touchyou;

public class Command {
    private int id;
    private String combination;
    /**
     * mode 0 = SINGLE_TOUCH <br>
     * mode 1 = FOLLOW
     */
    private int mode;
    private String imagePath;
    private double width, height, x, y;

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

}
