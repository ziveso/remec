package touchyou;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;

public class CommandInvoker {
    private Robot robot;

    public CommandInvoker() {
	try {
	    robot = new Robot();
	} catch (AWTException e) {
	    e.printStackTrace();
	}
    }

    public void invoke(String combination) {
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(e -> String.valueOf(e)).forEach(this::invoke);
    }

    public void invoke(int... keycodes) {
	Arrays.stream(keycodes).forEach(keycode -> robot.keyPress(keycode));
	Arrays.stream(keycodes).forEach(keycode -> robot.keyRelease(keycode));
    }
}
