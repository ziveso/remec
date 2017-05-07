package touchyou.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
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
				MouseListener ml = new Action();
				but.addMouseListener(ml);
				but.addMouseMotionListener((MouseMotionListener) ml);
				ModelPanel.getMobile().add(but);
				but.setBounds(new Rectangle(new Point(50, 50), but.getPreferredSize()));
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

	/**
	 * movable class.
	 * 
	 * @author Thitiwat Thongbor
	 *
	 */
	private class Action extends MouseAdapter {
		private Point myPoint = new Point(0, 0);
		private Point myComponent = new Point(0, 0);

		@Override
		public void mousePressed(MouseEvent e) {
			super.mousePressed(e);
			myPoint = e.getLocationOnScreen();
			myComponent = e.getComponent().getLocation();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			int deltaX = (int) (e.getLocationOnScreen().getX() - myPoint.getX());
			int deltaY = (int) (e.getLocationOnScreen().getY() - myPoint.getY());
			e.getComponent().setLocation((int) (myComponent.getX() + deltaX), (int) (myComponent.getY() + deltaY));
			ModelPanel.updateComponent();
		}
	}
}
