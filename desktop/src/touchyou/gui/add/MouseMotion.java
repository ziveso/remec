package touchyou.gui.add;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sun.javafx.geom.Rectangle;

import touchyou.gui.ModelPanel;

/**
 * 
 * @author Thitiwat Thongbor
 *
 */
public final class MouseMotion extends MouseAdapter {
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
		// e.getComponent().setLocation();
		drag(e);
	}

	private void drag(MouseEvent currentMouse) {
		Component comp = currentMouse.getComponent();
		int deltaX = (int) (currentMouse.getLocationOnScreen().getX() - myPoint.getX());
		int deltaY = (int) (currentMouse.getLocationOnScreen().getY() - myPoint.getY());
		int maxWidth = ModelPanel.getMobile().getWidth() - comp.getWidth();
		int maxHeight = ModelPanel.getMobile().getHeight() - comp.getHeight();
		int moveX = (int) (myComponent.getX() + deltaX);
		int moveY = (int) (myComponent.getY() + deltaY);
		int minWidth = 0;
		int minHeight = 0;
		if (comp.getX() < minWidth && (comp.getY() > minHeight && comp.getY() + comp.getHeight() < maxHeight)) {
			// x = 0
			// y = current y;
			// x is out on left.
			// y already in scope.
			move(comp, minWidth, moveY);
		} else if (comp.getX() < minWidth && comp.getY() < minHeight) {
			// x = 0
			// y = 0
			// x , y mouse is out on top left.
			move(comp, minWidth, maxHeight);
		} else if (comp.getX() < minWidth && comp.getY() > maxHeight) {
			// x = 0
			// y = max y - comp height
			// x , y mouse is out on bot left
			move(comp, minWidth, maxHeight);
		} else if (comp.getX() > maxWidth && (comp.getY() > minHeight && comp.getY() < maxHeight)) {
			// x = max width
			// y = current y
			// x is out on right
			// y in scope
			move(comp, maxWidth, moveY);
		} else if (comp.getX() > maxWidth && comp.getY() < minHeight) {
			// x = max width
			// y = 0
			// x , y is out at top right
			move(comp, maxWidth, minHeight);
		} else if (comp.getX() > maxWidth && comp.getY() > maxHeight) {
			// x = max width
			// y = max height
			// x , y is out at bot right
			move(comp, maxWidth, maxHeight);
		} else if (comp.getY() < minHeight && (comp.getX() > minWidth && comp.getX() < maxWidth)) {
			// x = current x
			// y = 0
			// x is on the pane
			// y is out at top
			move(comp, moveX, minHeight);
		} else if (comp.getY() > maxHeight && (comp.getX() > minWidth && comp.getX() < maxWidth)) {
			// x = current x
			// y = max height
			// x is on the pane
			// y is out at bot
			move(comp, moveX, maxHeight);
		} else {
			// move freely
			move(comp, moveX, moveY);
		}
		ModelPanel.updateComponent();
	}

	private void move(Component component, int x, int y) {
		component.setLocation(x, y);
	}
}
