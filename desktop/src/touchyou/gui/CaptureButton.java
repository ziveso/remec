package touchyou.gui;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import touchyou.util.Controller;

public class CaptureButton extends JButton {

    public CaptureButton() {
	super("Capture");
	this.addActionListener(new StartCapture());
    }

    private final class StartCapture implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    SwingUtilities.invokeLater(() -> {
		Controller.getInstance().hideMainFrame();
		new CaptureFrame().setVisible(true);
	    });
	}
    }

    private final class CaptureFrame extends JDialog {
	private Point start;
	private Rectangle rect;

	public CaptureFrame() {
	    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    this.setUndecorated(true);
	    this.setBackground(new Color(0, 0, 0, 0.001f));
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
			try {
			    rect.y += CaptureFrame.this.getLocationOnScreen().getY();
			    System.out.println(CaptureFrame.this.getLocationOnScreen().getY());
			    BufferedImage img = new Robot().createScreenCapture(rect);
			    Controller.getInstance().getCurrentCommand().setImage(img);
			    Controller.getInstance().updateCurrentCommand();
			} catch (AWTException e1) {
			    JOptionPane.showMessageDialog(CaptureFrame.this,
				    "Failed to capture the screen with error: " + e1.getMessage(), "Info",
				    JOptionPane.WARNING_MESSAGE);
			    e1.printStackTrace();
			}
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
			Controller.getInstance().showMainFrame();
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
