package touchyou.gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import touchyou.App;

public class CaptureButton extends JButton {

    private App app;
    private JFrame mainframe;

    public CaptureButton(App app, JFrame mainframe, String title) {
	super(title);
	this.app = app;
	this.mainframe = mainframe;
	this.addActionListener(new StartCapture());
    }

    private final class StartCapture implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    SwingUtilities.invokeLater(() -> {
		mainframe.setVisible(false);
		new CaptureFrame().setVisible(true);
	    });
	}
    }

    private final class CaptureFrame extends JDialog {
	private Point start, stop;
	private Rectangle rect;

	public CaptureFrame() {
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setUndecorated(true);
	    this.setBackground(new Color(0, 0, 0, 0.01f));
	    this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
	    this.add(new Drawer());

	    this.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
		    switch (e.getKeyCode()) {
		    case KeyEvent.VK_ESCAPE:
			CaptureFrame.this.dispose();
			break;
		    case KeyEvent.VK_ENTER:
			CaptureFrame.this.dispose();
			break;
		    default:
			break;
		    }
		}
	    });

	    this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosed(WindowEvent e) {
		    SwingUtilities.invokeLater(() -> {
			mainframe.setVisible(true);
		    });
		}
	    });
	}

	private class Drawer extends JPanel {
	    public Drawer() {
		this.setOpaque(true);
		this.setBackground(new Color(0, 0, 0, 0.1f));
		this.addMouseMotionListener(new MouseMotionAdapter() {
		    @Override
		    public void mouseDragged(MouseEvent e) {
			rect = new Rectangle(start);
			rect.add(e.getPoint());
			repaint();
		    }
		});
		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
			start = e.getPoint();
		    }

		    @Override
		    public void mouseReleased(MouseEvent e) {
			stop = e.getPoint();
		    }
		});
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.decode("#c91e46"));
		BasicStroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f,
			new float[] { 6 }, 0.0f);
		g2d.setStroke(dashedStroke);
		if (rect != null) {
		    g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		    g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	    }
	}
    }

}
