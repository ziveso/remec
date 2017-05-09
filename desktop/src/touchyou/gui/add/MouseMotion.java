package touchyou.gui.add;

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
		int deltaX = (int) (e.getLocationOnScreen().getX() - myPoint.getX());
		int deltaY = (int) (e.getLocationOnScreen().getY() - myPoint.getY());

		if (isOnScreen(deltaX, deltaY, e)) {
			e.getComponent().setLocation((int) (myComponent.getX() + deltaX), (int) (myComponent.getY() + deltaY));
		}
		ModelPanel.updateComponent();
	}

	private boolean isOnScreen(int deltaX, int deltaY, MouseEvent e) {
		int maxWidth = ModelPanel.getMobile().getWidth() - e.getComponent().getWidth();
		int maxHeight = ModelPanel.getMobile().getHeight() - e.getComponent().getHeight();
		return (myComponent.getX() + deltaX >= 0) && (myComponent.getX() + deltaX <= maxWidth)
				&& (myComponent.getY() + deltaY >= 0) && (myComponent.getY() + deltaY <= maxHeight);
	}
}
