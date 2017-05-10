package touchyou.gui.add;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import touchyou.App;
import touchyou.gui.SettingPanel;

/**
 * Add more action button by clicking it.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class AddButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8391021197211750760L;
	private CombinationFactory cf = CombinationFactory.getInstance();

	public AddButton(Dimension size, App app, SettingPanel settingPanel) {
		super("Button");
		cf.setSettingPanel(settingPanel);
		cf.setApp(app);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MouseOver(this));
		this.setOpaque(true);
		this.addActionListener(e -> {
			cf.addCombination();
		});
		this.setPreferredSize(size);
	}

}
