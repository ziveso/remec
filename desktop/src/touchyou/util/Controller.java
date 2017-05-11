package touchyou.util;

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
import javax.swing.SwingConstants;

import touchyou.App;
import touchyou.Command;
import touchyou.gui.*;
import touchyou.gui.add.ComponentMover;
import touchyou.gui.add.ComponentResizer;

public class Controller {
	private static Controller controller = new Controller();
	private Map<Integer, JLabel> list = new HashMap<>();
	private SettingPanel settingPanel;
	private WidgetPanel widgetPanel;
	private ModelPanel modelPanel;
	private int id;
	private ComponentMover mover;
	private App app;

	private Controller() {
	}

	public static Controller getInstance() {
		return controller;
	}

	public SettingPanel getSettingPanel() {
		return settingPanel;
	}

	public void setSettingPanel(SettingPanel settingPanel) {
		this.settingPanel = settingPanel;
	}

	public WidgetPanel getWidgetPanel() {
		return widgetPanel;
	}

	public void setWidgetPanel(WidgetPanel widgetPanel) {
		this.widgetPanel = widgetPanel;
	}

	public ModelPanel getModelPanel() {
		return modelPanel;
	}

	public void setModelPanel(ModelPanel modelPanel) {
		this.modelPanel = modelPanel;
	}

	/**
	 * Update
	 */
	public void update(Command command) {
		settingPanel.update(command);
		// TODO Update
		widgetPanel.update(command);
		modelPanel.update(command);
	}

	public void addCombination() {
		mover = new ComponentMover();
		mover.setSnapSize(new Dimension(6, 6));
		mover.setDragInsets(new Insets(10, 10, 10, 10));
		// make component resizable
		ComponentResizer resizer = new ComponentResizer();
		resizer.setSnapSize(new Dimension(6, 6));

		// JButton creating command.
		CommandButton but = new CommandButton();
		// add component to resizable list.
		resizer.registerComponent(but);
		// add to mobile panel
		ModelPanel.getMobile().add(but);
		// refresh the mobile panel
		// ModelPanel.updateComponent();
		//
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
		widgetPanel.addListPanel(lb);
		// update the screen
		// update();

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

			 update(app.getProfile().getCommand(ID);
		}
	}

	private final class CommandButton extends JButton {
		protected Command command;

		CommandButton() {
			command = new Command();
			this.setActionCommand(id + "");
			command.setId(id++);
			app.getProfile().addCommand(command);
			MouseAdapter commandMouseAdapter = new CommandMouseAdapter();
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
			
//			Component[] comps = ModelPanel.getMobile().getComponents();
//			for (Component c : comps) {
//				JButton button = (JButton) c;
//				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//			}
			int ID = Integer.parseInt(source.getActionCommand());
			source.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			Controller.getInstance().update(app.getProfile().getCommand(ID));

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
}
