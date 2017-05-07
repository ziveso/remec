package touchyou.gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import touchyou.gui.add.*;
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

		add(new ByButton(size));
		add(new ByArrow(size));
		add(new ByMacro(size));
	}
}
