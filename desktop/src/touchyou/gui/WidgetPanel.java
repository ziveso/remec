package touchyou.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import touchyou.util.GuiUtil;

/**
 * This in the part of Config zone , right field. this class provide mobile part
 * , adding part and SYNC part.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class WidgetPanel extends JPanel {
    /**
     * defalut serial ID.
     */
    private static final long serialVersionUID = -2228795784703450005L;
    // the width and height scale from component.
    private final int width;
    private final int height;

    /**
     * Construct the right part , mobile , add , syn.
     * 
     * @param width
     *            is the width of right part in INTEGER.
     * @param height
     *            is the height of this part in INTEGER.
     */
    public WidgetPanel(int width, int height) {
	this.width = width;
	this.height = height;
	setPreferredSize(new Dimension(width, height));
	setOpaque(false);
	// make it easy to edit
	setBorder(GuiUtil.getBorder());
	setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	initComponent();
    }

    /**
     * create the Mobile Class , Adding action and , Sync. this is just create ,
     * does not do anything.
     */
    private void initComponent() {
	JPanel config = new JPanel(new BorderLayout()) {
	    /**
	     * 
	     */
	    private static final long serialVersionUID = -5949522480753700728L;

	    @Override
	    public void setOpaque(boolean isOpaque) {
		super.setOpaque(false);
	    }
	};

	config.add(new AddAction(width * 3 / 10 - 1, height * 7 / 10 - 1), BorderLayout.NORTH);
	config.add(new SyncButton(width * 3 / 10 - 1, height * 3 / 10 - 1), BorderLayout.SOUTH);
	add(config, 1);
    }

    /**
     * Inner Class responsible for everything in Adding Button in the right
     * part. edit this part.
     */
    private class AddAction extends JPanel {
	private static final long serialVersionUID = -3638537105795998283L;
	private final int width;
	private final int height;

	/**
	 * Contruct this part
	 * 
	 * @param width
	 * @param height
	 */
	public AddAction(int width, int height) {
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
	    Dimension size = new Dimension(width, height / 15);

	    // TODO Add action click to expand.

	    JButton button = new JButton("Button");
	    button.setPreferredSize(size);
	    add(button);

	    JButton arrow = new JButton("Arrow");
	    arrow.setPreferredSize(size);
	    add(arrow);

	    JButton macro = new JButton("macro");
	    macro.setPreferredSize(size);
	    add(macro);
	}
    }
}