package touchyou.gui.add;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import touchyou.gui.MainFrame;
import touchyou.gui.ModelPanel;
import touchyou.util.GuiUtil;

public class ByButton extends JPanel {

	private JLabel hint;
	private JTextField command_key;
	private JButton add;
	private boolean visible = true;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391021197211750760L;

	public ByButton(Dimension size) {
		super(new GridLayout(0, 1));

		JButton button = new JButton("Button");
		hint = new JLabel("Command : ");
		command_key = new JTextField(1);
		add = new JButton("add");
		add.addActionListener(e -> {
			JButton but = new JButton(command_key.getText().toString());
			MouseListener ml = new MouseMotion();
			but.addMouseListener(ml);
			but.addMouseMotionListener((MouseMotionListener) ml);
			ModelPanel.getMobile().add(but);
			but.setBounds(GuiUtil.getInitBound(but.getPreferredSize()));
			ModelPanel.updateComponent();
		});

		button.setPreferredSize(size);
		button.addActionListener((e) -> {
			visible = setActionVisible(visible);
		});
		this.add(button);
	}

	private boolean setActionVisible(boolean visible) {
		if (visible) {
			add(hint);
			add(command_key);
			add(add);
		} else {
			remove(hint);
			remove(command_key);
			remove(add);
		}
		MainFrame.getWidget().validate();
		return !visible;
	}
}
