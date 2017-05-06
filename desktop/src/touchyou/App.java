package touchyou;

import touchyou.gui.TouchyouGui;

/**
 * Main class running the touchyou graphical user interface.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class App {
	
	private App(){
		new TouchyouGui().run();;
	}
	
	/**
	 * create new App , Avoid using static.
	 * 
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		new App();
	}
}
