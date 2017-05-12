package touchyou;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;

/**
 * A class responsible for translating key combinations into actual key events.
 * 
 * @author Kongpon Charanwattanakit
 *
 */
public class CommandInvoker {
    private Robot robot;

    /**
     * Initialize a new CommandInvoker.
     */
    public CommandInvoker() {
	try {
	    robot = new Robot();
	} catch (AWTException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Invoke a combination.
     * 
     * @param combination
     *            is the combination of key codes to invoke
     */
    public void invoke(String combination) {
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(e -> String.valueOf(e)).forEach(this::invoke);
    }

    /**
     * Invoke a combination
     * 
     * @param keycodes
     *            is array of key codes to invoke
     */
    public void invoke(int... keycodes) {
	Arrays.stream(keycodes).forEach(keycode -> robot.keyPress(keycode));
	Arrays.stream(keycodes).forEach(keycode -> robot.keyRelease(keycode));
    }
}
