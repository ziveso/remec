package touchyou.gui.add;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.ModelPanel;
import touchyou.gui.SettingPanel;
import touchyou.gui.WidgetPanel;
import touchyou.util.GuiUtil;

/**
 * 
 * manage and update every affect with combination button.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class CombinationFactory {
	// let's use SingleTon
	private static CombinationFactory cf = null;

	// tree component
	private JPanel listPanel;
	private Map<Integer, JLabel> list = new HashMap<>();

	// model
	private App app;
	private SettingPanel settingPanel;
	private ComponentMover mover;
	private ComponentResizer resizer;
	private MouseAdapter commandMouseAdapter;
	public int id;

	private CombinationFactory() {
		this.commandMouseAdapter = new CommandMouseAdapter();
	}

	public static CombinationFactory getInstance() {
		if (cf == null) {
			cf = new CombinationFactory();
		}
		return cf;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public void setSettingPanel(SettingPanel settingPanel) {
		this.settingPanel = settingPanel;
	}

	public void addCombination() {
		// model part
		// make component movable
		mover = new ComponentMover();
		mover.setSnapSize(new Dimension(6, 6));
		mover.setDragInsets(new Insets(10, 10, 10, 10));
		// make component resizable
		resizer = new ComponentResizer();
		resizer.setSnapSize(new Dimension(6, 6));

		// JButton creating command.
		CommandButton but = new CommandButton();
		// add component to resizable list.
		resizer.registerComponent(but);
		// add to mobile panel
		ModelPanel.getMobile().add(but);
		// refresh the mobile panel
		ModelPanel.updateComponent();

		// get the command from command button
		Command command = but.command;

		// create JLabel for component tree
		JLabel lb = new JLabel(command.getCombination());
		// add mouse action
		lb.addMouseListener(new ClickComponentTree());
		// set color
		lb.setOpaque(true);
		// align center
		lb.setHorizontalAlignment(SwingConstants.CENTER);
		lb.setBorder(GuiUtil.getBorder());
		// put the command matches by lb in the list.
		list.put(command.getId(), lb);
		// add it!.
		listPanel = WidgetPanel.getListPanel();
		listPanel.add(list.get(command.getId()));
		// update the screen
		listPanel.validate();

	}

	// TODO , not finish yet.

	public void removeCombination(Command command) {
		int id = command.getId();
		JLabel delete = list.get(id);
		listPanel.remove(delete);
		listPanel.validate();
	}

	/**
	 * update text in component tree and , update the Button text.
	 * 
	 * @param command
	 *            of button.
	 */
	public void updateCombination(Command command) {
		int id = command.getId(); // id of button.

		// update component tree
		JLabel update = list.get(id);
		update.setText(command.getCombination());

		// update component in mobile. passing by id.
		JButton comps = (JButton) ModelPanel.getMobile().getComponent(id);
		comps.setText(command.getCombination());

		listPanel.validate();
		ModelPanel.getMobile().validate();
	}

	protected final class CommandButton extends JButton {
		protected Command command;

		CommandButton() {
			command = new Command();
			this.setActionCommand(id + "");
			command.setId(id++);
			app.getProfile().addCommand(command);

			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			this.setOpaque(true);
			this.addMouseListener(mover);
			this.setPreferredSize(new Dimension(50, 50));
			this.addMouseListener(commandMouseAdapter);
			this.setBounds(GuiUtil.getInitBound(this.getPreferredSize()));
			this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		}

	}

	private final class CommandMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JButton source = (JButton) e.getSource();
			Component[] comps = ModelPanel.getMobile().getComponents();
			for (Component c : comps) {
				JButton button = (JButton) c;
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
			int ID = Integer.parseInt(source.getActionCommand());
			source.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			settingPanel.update(app.getProfile().getCommand(ID));

			// update component tree.
			for (Integer key : list.keySet()) {
				JLabel trees = list.get(key);
				if (key == ID) {
					trees.setBackground(Color.blue);
				} else {
					trees.setBackground(null);
				}
			}
		}
	}

	private final class ClickComponentTree extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JLabel press = (JLabel) e.getSource();
			// Find Id
			int ID = -1; // imposible value

			// change color in list. and get ID
			for (Integer key : list.keySet()) {
				if (press == list.get(key)) {
					ID = key;
				}
				list.get(key).setBackground(null);
			}
			press.setBackground(Color.blue);

			// select in mobile panel.
			Component[] comps = ModelPanel.getMobile().getComponents();
			for (Component c : comps) {
				JButton button = (JButton) c;
				if (ID == Integer.parseInt(button.getActionCommand())) {
					button.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				} else {
					button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}

			settingPanel.update(app.getProfile().getCommand(ID));
		}
	}
}
