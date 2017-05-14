package touchyou.gui;

import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import touchyou.util.Controller;

public class MenuBar extends JMenuBar {

    /**
     * 
     */
    private static final long serialVersionUID = -3285079136056165470L;

    public MenuBar() {
	int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
	/**
	 * FILE MENU
	 */
	JMenu file = new JMenu("File");
	JMenuItem newprofile = new JMenuItem("New Profile");
	JMenuItem open = new JMenuItem("Open Profile...");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem saveAs = new JMenuItem("Save As...");
	JMenuItem exit = new JMenuItem("Quit Touch You");
	file.addSeparator();
	file.add(newprofile);
	file.add(open);
	file.addSeparator();
	file.add(save);
	file.add(saveAs);
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
	    saveAs.addActionListener(e -> {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(Controller.getInstance().getProfileName()));
		fileChooser.setApproveButtonText("SAVE");
		int retrival = fileChooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
		    Controller.getInstance().saveAs(fileChooser.getSelectedFile());
		}
	    });
	    newprofile.addActionListener(e -> {
		Icon icon = null;
		String name = (String) JOptionPane.showInputDialog(this, "New Profile Name : ", "Creating New Profile",
			JOptionPane.QUESTION_MESSAGE, icon, null, null);
		if (name != null) {
		    Controller.getInstance().newProfile(name);
		    Controller.getInstance().clear();
		    Controller.getInstance().loadProfile();
		}

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
	JMenuItem undo = new JMenuItem("Undo");
	JMenuItem redo = new JMenuItem("Redo");
	JMenuItem reset = new JMenuItem("Reset");
	edit.add(undo);
	edit.add(redo);
	edit.addSeparator();
	edit.add(reset);
	this.add(edit);

	// Add action to EDIT MENU
	{
	    reset.addActionListener((e) -> {
		Controller.getInstance().clear();
	    });
	}

	/**
	 * PHONE MENU
	 */
	JMenu phone = new JMenu("Phone");
	JMenuItem sync = new JMenuItem("Sync");
	phone.add(sync);
	this.add(phone);

	// add action to Phone Menu
	{
	    sync.addActionListener((e) -> {
//		Controller.getInstance().sync();
	    });
	}

	// add hotkey to Sync
	{
	    sync.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));
	}

	/**
	 * CONNECTION MENU
	 */
	JMenu connection = new JMenu("Connection");
	JMenuItem connect = new JMenuItem("Enable Connection");
	JMenuItem disconnect = new JMenuItem("Disable Connection");
	connection.add(connect);
	connection.add(disconnect);

	// add action to connection
	{
	    connect.addActionListener((e) -> {
		// TODO ENABLE CONNECTION
	    });
	    disconnect.addActionListener((e) -> {
		// TODO DISABLE CONNECTION
	    });
	}

	// add hotkey to connection
	{
	    connect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
	    disconnect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));
	}
	this.add(connection);

    }
}
