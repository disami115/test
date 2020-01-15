package Canvas;

import javax.swing.*;

import Canvas.MyCanvas.DrawObjects;
import GUIs.SecondGUI;
import MouseDraw.DrawObject;
import MouseDraw.DrawObjectArrow;
import MouseDraw.DrawObjectBlur;
import MouseDraw.DrawObjectBrush;
import MouseDraw.DrawObjectOne;
import MouseDraw.DrawObjectText;
import MouseDraw.DrawObjectThree;
import MouseDraw.DrawObjectTwo;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
 
 
public class MyCanvas extends JComponent implements MouseWheelListener, MouseMotionListener, MouseListener  {
	
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
    public static BufferedImage bufferedImage = null;
    public static Graphics g;
    protected SecondGUI SecG;
    public MouseEvent e;
    public Graphics2D lastG;
    private boolean isMouseDragged = false;
    private DrawObject drawObj;
	public DrawObjects dOs;
	private boolean isPressed = false;
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;
	private boolean isReleased = false;
    
    public enum DrawObjects{
    	DrawObjectBrush,
    	DrawObjectArrow,
    	DrawObjectOne,
    	DrawObjectTwo,
    	DrawObjectThree,
    	DrawObjectBlur,
    	DrawObjectText
    }
    public MyCanvas(double zoom, Image img) {
        this.zoom = zoom;
        this.img = img;
        bufferedImage = new BufferedImage(this.img.getWidth(null), this.img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setAutoscrolls(true);
        changeDrawObjects(DrawObjects.DrawObjectBrush.toString());
        setDrawObject(dOs, (Graphics2D) this.getGraphics());
        
    }
    
    public void changeDrawObjects(String s) {
    	dOs = DrawObjects.valueOf(s);
		
    }
 
    private void setDrawObject(DrawObjects dOs, Graphics2D g2s) {
    	switch(dOs){
    		case DrawObjectBrush: 
    			this.drawObj = new DrawObjectBrush(g2s, this.e);
    			break;
    		case DrawObjectArrow: 
    			this.drawObj = new DrawObjectArrow(g2s, this.e);
    			break;
    		case DrawObjectOne: 
    			this.drawObj = new DrawObjectOne(g2s, this.e);
    			break;
    		case DrawObjectTwo: 
    			this.drawObj = new DrawObjectTwo(g2s, this.e);
    			break;
    		case DrawObjectThree: 
    			this.drawObj = new DrawObjectThree(g2s, this.e);
    			break;
    		case DrawObjectBlur: 
    			this.drawObj = new DrawObjectBlur(g2s, this.e);
    			break;
    		case DrawObjectText: 
    			this.drawObj = new DrawObjectText(g2s, this.e);
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
        Graphics2D g2d = (Graphics2D) g.create();
        g2s.drawImage(img, 0, 0, img.getWidth(null), img.getHeight(null), null);
        //g2s.transform(tx);
        g2d.transform(tx);
        if(drawObj != null){
	        setDrawObject(dOs,g2s);
	        System.out.println(dOs.name());
			DrawObject.setNewRect(x1, y1, x2, y2);
	        if(isPressed && dOs.name() == "DrawObjectBrush") {
					g2s = (Graphics2D) drawObj.Draws(x1, y1, x2, y2, Color.red);
			}
	        else if(isReleased) g2s = (Graphics2D) drawObj.Draws(x1, y1, x2, y2, Color.red);
        }
        super.paintComponent(g2s);
        g2s.dispose();
		img = bufferedImage;
        if (img != null) {
            g2d.drawImage(img, 0, 0, this);
        }
    }
  
    protected void repaint(Graphics g, MouseEvent e) {
    	super.paintComponent(g);
        x2 = e.getX();
		y2 = e.getY();

		Graphics2D g2d  = (Graphics2D)g; 
    	g2d.drawImage(img, 0, 0, this);
    	DrawObject.setNewRect(x1, y1, x2, y2);
    	setDrawObject(dOs,g2d);
        g2d = (Graphics2D) drawObj.Draws(x1, y1, x2, y2, Color.red);
        g2d.transform(tx);
        g2d.dispose();
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
       /* double zoomFactor = - SCALE_STEP*e.getPreciseWheelRotation()*zoom;
        zoom = Math.abs(zoom + zoomFactor);
        int tzoom = (int)(zoom*100);
        zoom = tzoom / 100.0;
        System.out.println(zoom);
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
        */
    }
 
    private void translate(MouseWheelEvent e) {
        /*Rectangle realView = getVisibleRect();
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
*/
    }
 
 
    public void followMouseOrCenter(MouseWheelEvent e) {
       /* Point2D point = e.getPoint();
        Rectangle visibleRect = getVisibleRect();
 
        scrollX = point.getX()/previousZoom*zoom - (point.getX()-visibleRect.getX());
        scrollY = point.getY()/previousZoom*zoom - (point.getY()-visibleRect.getY());
 
        visibleRect.setRect(scrollX, scrollY, visibleRect.getWidth(), visibleRect.getHeight());
        scrollRectToVisible(visibleRect);*/
    }
 
    
    
    public void mouseDragged(MouseEvent e) {
    	isMouseDragged = true;
    	x2 = e.getX();
        y2 = e.getY();
        DrawObject.setNewRect(x1, y1, x2, y2);
        setDrawObject(dOs, (Graphics2D) this.getGraphics());
        if(isPressed && dOs.name() == "DrawObjectBrush") {
        	this.paintComponent(this.getGraphics());
        }
        else this.repaint(this.getGraphics(), e);
    	
    	
        /*if (origin != null) {
            int deltaX = origin.x - e.getX();
            int deltaY = origin.y - e.getY();
            Rectangle view = getVisibleRect();
            view.x += deltaX;
            view.y += deltaY;
            scrollRectToVisible(view);
        }*/
    }
 
    public void mouseMoved(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }
 
    public void mousePressed(MouseEvent e) {
    	isPressed = true;
		x1 = e.getX();
		y1 = e.getY();
        origin = new Point(e.getPoint());
    }
 
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
        isMouseDragged = false;
        x2 = e.getX();
        y2 = e.getY();
        DrawObject.setNewRect(x1, y1, x2, y2);
        isReleased  = true;
        //this.paintComponents(this.getGraphics());
        this.paintComponent(this.getGraphics());
        isReleased = false;
        System.out.println("Released");
    }
 
    public void mouseEntered(MouseEvent e) {
 
    }
 
    public void mouseExited(MouseEvent e) {
 
    }


}
