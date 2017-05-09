package touchyou.gui.add;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public ByArrow(Dimension size, App app) {
		super(new GridLayout(0, 1));
		this.app = app;
		this.setOpaque(false);
		JButton arrow = new JButton("Arrow");
		arrow.setPreferredSize(size);
		arrow.setOpaque(true);
		arrow.setBorder(BorderFactory.createEmptyBorder());
		arrow.setBackground(Color.WHITE);
		arrow.addMouseListener(new MouseOver(arrow));
		arrow.addActionListener(new Toggle());
		add(arrow);
	}

	private class Toggle implements ActionListener {
		private boolean isToggle = false;
		@Override
		public void actionPerformed(ActionEvent e) {
			isToggle = !isToggle;
			if(isToggle){
				addButton("<" , "V" , ">" , "^");
				validate();
			}
		}
		
		private void addButton(String ... Strings){
			for(String string : Strings){
				JButton jb = new JButton(string);
				jb.setPreferredSize(new Dimension(getWidth(), 500));
				add(jb);
			}
		}

	}
}
