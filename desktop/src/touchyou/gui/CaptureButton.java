package touchyou.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import touchyou.App;

public class CaptureButton extends JButton {

    private App app;
    private JFrame mainframe;

    public CaptureButton(App app, Container mainframe, String title) {
	super(title);
	this.app = app;
	this.mainframe = (JFrame) mainframe;
	this.addActionListener(new StartCapture());
    }

    private final class StartCapture implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    mainframe.setState(JFrame.ICONIFIED);
	}
    }
}
