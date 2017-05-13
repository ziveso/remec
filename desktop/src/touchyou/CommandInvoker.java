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
     * Tap a combination.
     * 
     * @param combination
     *            is the combination of key codes to tap
     */
    public void tap(String combination) {
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(e -> Integer.parseInt(e)).forEach(this::tap);
    }

    /**
     * Tap a combination.
     * 
     * @param keycodes
     *            is array of key codes to tap
     */
    public void tap(int... keycodes) {
	press(keycodes);
	release(keycodes);
    }

    /**
     * Press a combination.
     * 
     * @param combination
     *            is a combination of key code to press
     */
    public void press(String combination) {
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(e -> Integer.parseInt(e)).forEach(this::press);
    }

    /**
     * Press a combination.
     * 
     * @param keycodes
     *            is array of key codes to press
     */
    public void press(int... keycodes) {
	Arrays.stream(keycodes).forEach(keycode -> robot.keyPress(keycode));
    }

    /**
     * Release a combination.
     * 
     * @param combination
     *            is a combination of key code to release
     */
    public void release(String combination) {
	String[] keys = combination.split(":");
	Arrays.stream(keys).map(e -> Integer.parseInt(e)).forEach(this::release);
    }

    /**
     * Release a combination.
     * 
     * @param keycodes
     *            is array of key codes to press
     */
    public void release(int... keycodes) {
	Arrays.stream(keycodes).forEach(keycode -> robot.keyPress(keycode));
    }
}
