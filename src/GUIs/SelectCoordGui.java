package GUIs;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JWindow;
import GUIs.GUI.ButtonEventListener;
import Screens.Screen;

public class SelectCoordGui extends JWindow implements MouseListener, MouseMotionListener{
	
	private static Dimension d = Toolkit.getDefaultToolkit ().getScreenSize ();
	private static int lx = 0;
	private static int ly = 0;
	private static int bx = d.width;
	private static int by = d.height;
	private static int x1 = 0;
	private static int y1 = 0;
	private static int x2 = 0;
	private static int y2 = 0;
	private static boolean isPressed = false;
	private static ImageDraw imgD;
	private static Image img = null;
	private static Rectangle r;
	
	public SelectCoordGui(Image img) {
		this.img = img; 
		addMouseListener(this);
		addMouseMotionListener(this);
		
	    this.setBounds(0, 0, d.width, d.height);
	    imgD = new ImageDraw(img, null);
	    getContentPane().add(imgD);
	    System.out.println("OK");
	}
	
	public static void setXYWH(int x1, int x2, int y1, int y2){
		bx = Math.max(x1, x2); // bigX
		by = Math.max(y1, y2); // bigY
		lx = Math.min(x1, x2) - 1; // littleX
		ly = Math.min(y1, y2) - 1 ; // littleY
		r = new Rectangle(lx, ly, bx-lx, by-ly);
	}
	
	class ImageDraw extends JComponent
	{
	    private Image capture;
	    private Graphics g;
	    private int x = 0;
	    private int y = 0;
	    ImageDraw (Image capture, MouseEvent e) {
	        this.capture = capture;
		    if(isPressed) {
		        this.x = e.getX();
		        this.y = e.getY();
	        }
	    }
	    public void paintComponent(Graphics g) {
	    	Graphics2D g2d  = (Graphics2D)g; 
	    	g2d.drawImage(capture, 0, 0, this);
	    	
	        AlphaComposite composite = AlphaComposite.SrcOver.derive( 0.3f );
            g2d.setComposite( composite );
	    	
            g2d.fillRect(0, 0, d.width, d.height);
	        g2d.dispose();
	    }
	    
	    
	    public void repaint(Graphics g, Image img)
	    {
	    	Graphics2D g2d  = (Graphics2D)g; 
	    	g2d.drawImage(img, 0, 0, this);
	    	setXYWH(x1, this.x, y1, this.y);
	    	g2d.setColor(Color.black);
	        g2d.drawRect(r.x , r.y , r.width, r.height);
	        g2d.dispose();
	    }
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		x1 = e.getX();
		y1 = e.getY();
		//tx = x1;
		//ty = y1;
    }
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		isPressed = false;
		x2 = e.getX() + 1;
        y2 = e.getY() + 1;
        Screen.setXYWH(x1,x2,y1,y2);
        Image img = Screen.grabScreen();
        SecondGUI w = new SecondGUI(img);
		w.setVisible(true);
		this.setVisible(false);
	}
	
	


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		imgD = new ImageDraw(img, e);
		imgD.repaint(getGraphics(), img);
		getContentPane().add(imgD);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
