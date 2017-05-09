package touchyou.gui.add;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import touchyou.App;
import touchyou.gui.MainFrame;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public class ByArrow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7527425268579123369L;
	private App app;
	private Dimension size;
	private JButton left, right, up, down;
	private int DEFAULT_HEIGHT;
	JPanel subpanel;

	public ByArrow(Dimension size, App app) {
		super(new BorderLayout());
		this.size = size;
		this.app = app;
		this.setOpaque(false);
		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
		arrow.setOpaque(true);
		arrow.setBorder(BorderFactory.createEmptyBorder());
		arrow.setBackground(Color.WHITE);
		arrow.addMouseListener(new MouseOver(arrow));
		arrow.addActionListener(new Toggle());
		add(arrow, BorderLayout.NORTH);

		subpanel = new JPanel(new GridLayout(2, 2));
		left = new JButton("<");
		right = new JButton(">");
		up = new JButton("^");
		down = new JButton("V");
		DEFAULT_HEIGHT = ImageObserver.HEIGHT;
		add(subpanel, BorderLayout.CENTER);
	}

	private class Toggle implements ActionListener {
		private boolean isToggle = false;

		@Override
		public void actionPerformed(ActionEvent e) {
			isToggle = !isToggle;
			if (isToggle) {
				addComp();
			} else {
				removeComp();
			}
			MainFrame.updateWidget();
		}

		private void addComp() {
			subpanel.add(left);
			subpanel.add(right);
			subpanel.add(up);
			subpanel.add(down);
		}

		private void removeComp() {
			subpanel.remove(left);
			subpanel.remove(right);
			subpanel.remove(up);
			subpanel.remove(down);
		}
	}
}
