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
	    
	}
    }

    /**
     * Tap a combination.
     * 
     * @param combination
     *            is the combination of key codes to tap
     */
    public void tap(String combination) {
	press(combination);
	release(combination);
    }

    /**
     * Press a combination.
     * 
     * @param combination
     *            is a combination of key code to press
     */
    public void press(String combination) {
	if (combination.trim().isEmpty()) return;
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(Integer::parseInt).forEach(robot::keyPress);
    }

    /**
     * Release a combination.
     * 
     * @param combination
     *            is a combination of key code to release
     */
    public void release(String combination) {
	if (combination.trim().isEmpty()) return;
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(Integer::parseInt).forEach(robot::keyRelease);
    }

}
