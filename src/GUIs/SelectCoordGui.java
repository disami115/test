package GUIs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
	
	private static int x1 = 0;
	private static int y1 = 0;
	private static int x2 = 0;
	private static int y2 = 0;
	private Point origin;
	
	public SelectCoordGui(Image img) {
		addMouseListener(this);
		Dimension d = Toolkit.getDefaultToolkit ().getScreenSize ();
	    this.setBounds(0, 0, d.width, d.height);
	    getContentPane().add(new ImageDraw(img));
	    System.out.println("OK");
	}
	
	class ImageDraw extends JComponent
	{
	    private Image capture;
	    ImageDraw (Image capture) {
	        this.capture = capture;
	    }
	    public void paintComponent(Graphics g) {
	        g.drawImage(capture, 0, 0, this);
	        g.drawString("sometext", 100, 100);
	        g.dispose();
	    }
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		//System.out.println(origin.x + " " + origin.y);
		System.out.println(x1 + " " + y1);
		Screen.setFirstXY(x1, y1);
    }
	

	@Override
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
        y2 = e.getY();
        System.out.println(x2 + "and" + y2);
        Screen.setSecondXY(x2, y2);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
