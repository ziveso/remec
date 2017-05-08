package touchyou.gui.add;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		System.out.println(myComponent);
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
