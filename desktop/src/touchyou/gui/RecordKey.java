package touchyou.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import touchyou.Command;
import touchyou.util.Controller;

public class RecordKey implements KeyListener {
    private JTextField combination;
    private StringBuffer string;
    private int pressing = 0;
    private Command currentCommand;

    public RecordKey(JTextField combination , Command command) {
	this.combination = combination;
	this.currentCommand = command;
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (pressing == 0) {
	    string = new StringBuffer();
	}
	pressing++;
	switch (e.getKeyCode()) {
	case 16:
	    string.append("Shift +");
	    break;
	case 17:
	    string.append("Ctrl +");
	    break;
	case 18:
	    string.append("Alt +");
	    break;
	default:
	    string.append(e.getKeyChar() + " +");
	}
	combination.setText(string.toString());
    }

    @Override
    public void keyReleased(KeyEvent e) {
	pressing--;
	if (pressing == 0) {
	    string.deleteCharAt(string.length() - 1);
	    combination.setText(string.toString());
	    Controller.getInstance().update(currentCommand);
	    string = null;
	}
    }

}
