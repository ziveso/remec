package touchyou.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import touchyou.util.GuiUtil;

public class WidgetPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234634479725945937L;
	private final int width;
	private final int height;
	private Dimension size;

	/**
	 * Contruct this part
	 * 
	 * @param width
	 * @param height
	 */
	public WidgetPanel(int width, int height) {
		this.width = width;
		this.height = height;
		setOpaque(false);
		setPreferredSize(new Dimension(width, height));
		// make it easy to fix.
		setBorder(GuiUtil.getBorder());
		initComponent();
	}

	/**
	 * create everything that is needed by program.
	 */
	private void initComponent() {
		size = new Dimension(width, height / 15);

		// TODO Add action click to expand.

		JPanel button_field = new ButtonField();
		add(button_field);

		JPanel arrow_field = new JPanel();
		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
		arrow_field.add(arrow);
		add(arrow_field);

		JPanel macro_field = new JPanel();
		JButton macro = new JButton("macro");
		macro.setPreferredSize(size);
		macro_field.add(macro);
		add(macro_field);
	}

	private void updateComponent() {
		validate();
	}

	private class ButtonField extends JPanel {

		private JLabel hint;
		private JTextField command_key;
		private JButton add;
		private boolean visible = true;

		/**
		 * 
		 */
		private static final long serialVersionUID = 8391021197211750760L;

		ButtonField() {
			super(new GridLayout(0, 1));

			JButton button = new JButton("Button");
			hint = new JLabel("Command : ");
			command_key = new JTextField(1);
			add = new JButton("add");
			add.addActionListener(e -> {
				JButton but = new JButton(command_key.getText().toString());
				// TODO ADD COMMAND TO BUTTON
				// but.addActionListener();
				ModelPanel.getMobile().add(but);
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
			updateComponent();
			return !visible;
		}
	}
}
