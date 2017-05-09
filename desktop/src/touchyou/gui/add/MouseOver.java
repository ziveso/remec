package touchyou.gui.add;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseOver extends MouseAdapter {

	private Component component;
	private Font currentFont;

	public MouseOver(Component component) {
		this.component = component;
		this.currentFont = component.getFont();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		component.setFont(new Font(currentFont.getName(), 0, currentFont.getSize() + 2));
		component.setBackground(Color.GRAY);
		component.setForeground(Color.white);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		component.setForeground(Color.BLACK);
		component.setBackground(Color.WHITE);
		component.setFont(currentFont);
	}
}
