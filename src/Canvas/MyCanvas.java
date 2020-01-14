package Canvas;

import javax.swing.*;

import GUIs.SecondGUI;
import MouseDraw.DrawObject;
import MouseDraw.DrawObjectArrow;
import MouseDraw.DrawObjectBlur;
import MouseDraw.DrawObjectBrush;
import MouseDraw.DrawObjectOne;
import MouseDraw.DrawObjectTwo;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
 
 
public class MyCanvas extends JComponent implements MouseWheelListener, MouseMotionListener, MouseListener  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double zoom = 1.0;
    private Image img;
    public static final double SCALE_STEP = 0.05d;
    private Dimension initialSize;
    private Point origin;
    private double previousZoom = zoom;
    private AffineTransform tx = new AffineTransform();
    private double scrollX = 0d;
    private double scrollY = 0d;
    public boolean txt = false;
    public static BufferedImage bufferedImage = null;
    public static Graphics g;
    protected SecondGUI SecG;
    public MouseEvent e;
    public Graphics2D g2s;
    private boolean isMouseDragged = false;
    private DrawObject drawObj;
	public DrawObjects dOs;
    
    public enum DrawObjects{
    	DrawObjectBrush,
    	DrawObjectArrow,
    	DrawObjectOne,
    	DrawObjectTwo,
    	DrawObjectBlur
    }
    public MyCanvas(double zoom, Image img) {
        this.zoom = zoom;
        this.img = img;
        bufferedImage = new BufferedImage(this.img.getWidth(null), this.img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setAutoscrolls(true);
        
    }
    
    public void changeDrawObjects(String s) {
    	dOs = DrawObjects.valueOf(s);
		
    }
 
    private void setDrawObject(DrawObjects dOs, Graphics2D g2s) {
    	switch(dOs){
    		case DrawObjectBrush: 
    			this.drawObj = new DrawObjectBrush(g2s, this.e);
    			System.out.println("set DrawObjectPoint");
    			break;
    		case DrawObjectArrow: 
    			this.drawObj = new DrawObjectArrow(g2s, this.e);
    			System.out.println("set DrawObjectPoint");
    			break;
    		case DrawObjectOne: 
    			this.drawObj = new DrawObjectOne(g2s, this.e);
    			System.out.println("set DrawObjectPoint");
    			break;
    		case DrawObjectTwo: 
    			this.drawObj = new DrawObjectTwo(g2s, this.e);
    			System.out.println("set DrawObjectPoint");
    			break;	
    		case DrawObjectBlur: 
    			this.drawObj = new DrawObjectBlur(g2s, this.e);
    			System.out.println("set DrawObjectPoint");
    			break;	
		default:
			break;
    	}
    }
    
    public Dimension getInitialSize() {
        return initialSize;
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2s = bufferedImage.createGraphics();
        //this.g2s = g2s;
        g2s.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
		if(isMouseDragged) {
			setDrawObject(dOs,g2s);
			if(drawObj != null) g2s = (Graphics2D) drawObj.Draws(Color.black);
			System.out.println(e.getX() + " " + e.getY());
		}
		img = bufferedImage;
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
        // if you need to draw changing non-static images, do it here
        
        
    }
    
    

    public void paintComp(Graphics g, MouseEvent e) {

    }
 
    public BufferedImage getBufferedImage(Graphics g) {
    	zoom = 1;
    	paintComponent(g);
        return bufferedImage;
    }
    
    @Override
    public void setSize(Dimension size) {
        super.setSize(size);
        if (initialSize == null) {
            this.initialSize = size;
        }
    }
 
    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (initialSize == null) {
            this.initialSize = preferredSize;
        }
    }
 
    public void mouseWheelMoved(MouseWheelEvent e) {
        double zoomFactor = - SCALE_STEP*e.getPreciseWheelRotation()*zoom;
        zoom = Math.abs(zoom + zoomFactor);
        int tzoom = (int)(zoom*100);
        zoom = tzoom / 100.0;
        Dimension d = new Dimension(
                (int)(initialSize.width*zoom),
                (int)(initialSize.height*zoom));
            setPreferredSize(d);
            setSize(d);
            validate();
            followMouseOrCenter(e);
            translate(e);
            repaint();
        previousZoom = zoom;
    }
 
    private void translate(MouseWheelEvent e) {
        Rectangle realView = getVisibleRect();
        Point2D p1 = e.getPoint();
        Point2D p2 = null;
        try {
            p2 = tx.inverseTransform(p1, null);
        } catch (NoninvertibleTransformException ex) {
            ex.printStackTrace();
            return;
        }
        Dimension d = getSize();
        if (d.getWidth() <= realView.getWidth() && d.getHeight() <= realView.getHeight()) {
            tx.setToIdentity();
            tx.translate(p1.getX(), p1.getY());
            tx.scale(zoom, zoom);
            tx.translate(-p2.getX(), -p2.getY());
        } else {
            tx.setToIdentity();
            tx.scale(zoom, zoom);
        }

    }
 
 
    public void followMouseOrCenter(MouseWheelEvent e) {
        Point2D point = e.getPoint();
        Rectangle visibleRect = getVisibleRect();
 
        scrollX = point.getX()/previousZoom*zoom - (point.getX()-visibleRect.getX());
        scrollY = point.getY()/previousZoom*zoom - (point.getY()-visibleRect.getY());
 
        visibleRect.setRect(scrollX, scrollY, visibleRect.getWidth(), visibleRect.getHeight());
        scrollRectToVisible(visibleRect);
    }
 
    
    
    public void mouseDragged(MouseEvent e) {
    	isMouseDragged = true;
    	this.e = e;
    	//Graphics g = getGraphics();
    	//g.drawOval(e.getX(), e.getY(), 10, 10);
    	//this.paintComponent(g);
    	//DrawObject dObj = new DrawObjectPoint(g, e);
    	//Graphics2D g = bufferedImage.createGraphics();
    	this.paintComponent(this.getGraphics());
    	//this.paint(g);
    	this.paintComponent(this.getGraphics());
        if (origin != null) {
            int deltaX = origin.x - e.getX();
            int deltaY = origin.y - e.getY();
            Rectangle view = getVisibleRect();
            view.x += deltaX;
            view.y += deltaY;
            scrollRectToVisible(view);
        }
    }
 
    public void mouseMoved(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }
 
    public void mousePressed(MouseEvent e) {
        origin = new Point(e.getPoint());
    }
 
    public void mouseReleased(MouseEvent e) {
        isMouseDragged = false;
    }
 
    public void mouseEntered(MouseEvent e) {
 
    }
 
    public void mouseExited(MouseEvent e) {
 
    }


}
