package touchyou.gui;

import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import touchyou.util.Controller;

public class MenuBar extends JMenuBar {

    public MenuBar() {
	int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
	/**
	 * FILE MENU
	 */
	JMenu file = new JMenu("File");
	JMenuItem open = new JMenuItem("OPEN");
	JMenuItem save = new JMenuItem("SAVE");
	JMenuItem saveAs = new JMenuItem("SAVE AS");
	JMenuItem newprofile = new JMenuItem("New Profile");
	JMenuItem exit = new JMenuItem("EXIT");
	file.add(open);
	file.add(save);
	file.add(saveAs);
	file.addSeparator();
	file.add(newprofile);
	file.addSeparator();
	file.add(exit);
	this.add(file);

	// add action to FILE MENU
	{
	    save.addActionListener(e -> {
		Controller.getInstance().save();
		System.out.println("save");
	    });
	    open.addActionListener(e -> {
		// Controller.getInstance().clear();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("./profiles/"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Profile files (*.profile)", "profile"));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    Controller.getInstance().openProfile(fileChooser.getSelectedFile());
		    Controller.getInstance().clear();
		    Controller.getInstance().loadProfile();
		}
	    });
	    // TODO SAVE AS
	    saveAs.addActionListener(e -> {

	    });
	    // TODO NEW PROFILE
	    newprofile.addActionListener(e -> {

	    });
	    exit.addActionListener((e) -> System.exit(0));
	}

	// add hotkey to FILE MENU
	{
	    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut));
	    saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut | InputEvent.SHIFT_MASK));
	    newprofile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcut | InputEvent.SHIFT_MASK));
	}

	/**
	 * EDIT MENU
	 */
	JMenu edit = new JMenu("Edit");
	JMenuItem undo = new JMenuItem("UNDO");
	JMenuItem redo = new JMenuItem("REDO");
	JMenuItem reset = new JMenuItem("RESET");
	edit.add(undo);
	edit.add(redo);
	edit.addSeparator();
	edit.add(reset);
	this.add(edit);

	/**
	 * PHONE MENU
	 */
	JMenu phone = new JMenu("Phone");
	JMenuItem sync = new JMenuItem("SYNC");
	phone.add(sync);
	this.add(phone);

	/**
	 * CONNECTION MENU
	 */
	JMenu connection = new JMenu("Connection");
	JMenuItem connect = new JMenuItem("Enable Connection");
	JMenuItem disconnect = new JMenuItem("Disable Connection");
	connection.add(connect);
	connection.add(disconnect);
	this.add(connection);

    }
}
