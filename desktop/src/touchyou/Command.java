package touchyou;

public class Command {
    private int id;
    private String combination;
    private Mode mode;

    public Command(int id) {
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

    public Mode getMode() {
	return mode;
    }

    public void setMode(Mode mode) {
	this.mode = mode;
    }

}
