package touchyou.gui;

import touchyou.App;

/**
 * create the GUI for TouchYou JAVA Application.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class TouchyouGui {
    private App app;

    public TouchyouGui(App app) {
	this.app = app;
    }

    /**
     * run the GUI.
     */
    public void run() {
	new MainFrame(app).setVisible(true);
    }
}
