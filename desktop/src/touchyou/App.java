package touchyou;

/**
 * Main class running the touchyou graphical user interface.
 * 
 * @author Thitiwat Thongbor
 *
 */
public class App {
	private TouchYou gui;
	
	private App(){
		gui = new TouchYou();
		gui.run();
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
