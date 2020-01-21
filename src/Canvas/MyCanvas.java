package Canvas;

import javax.swing.*;

import GUIs.SecondGUI;
import MouseDraw.DrawBrushLine;
import MouseDraw.DrawObject;
import MouseDraw.DrawObjectArrow;
import MouseDraw.DrawObjectBlur;
import MouseDraw.DrawObjectBrush;
import MouseDraw.DrawObjectLine;
import MouseDraw.DrawObjectOne;
import MouseDraw.DrawObjectText;
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
    private AffineTransform tx = new AffineTransform();
    public static BufferedImage bufferedImage = null;
    public static Graphics g;
    protected SecondGUI SecG;
    public MouseEvent e;
    public Graphics2D lastG;
    private DrawObject drawObj;
	public DrawObjects dOs;
	private boolean isPressed = false;
	private int x1 = 0;
	private int y1 = 0;
	private int x2 = 0;
	private int y2 = 0;
	private double scrollX = 0d;
	private double scrollY = 0d;
	private boolean isReleased = false;
	private double previousZoom;
	private int rx1;
	private int ry1;
	private MyCanvas mc = null;
	private int lastX = 0;
	private int lastY = 0;
    
    public enum DrawObjects{
    	DrawObjectBrush,
    	DrawObjectArrow,
    	DrawObjectOne,
    	DrawObjectLine,
    	DrawObjectBlur,
    	DrawObjectText
    }
    public MyCanvas(double zoom, Image img) {
        this.zoom = zoom;
        this.img = img;
        mc = this;
        bufferedImage = new BufferedImage(this.img.getWidth(null), this.img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        //setAutoscrolls(true);
        changeDrawObjects(DrawObjects.DrawObjectBrush.toString());
        setDrawObject(dOs, (Graphics2D) this.getGraphics());
    	//Dimension d = new Dimension(
        //        (int)(img.getWidth(null)*zoom),
       //         (int)(img.getHeight(null)*zoom));
    	//System.out.println(d.height + " " + d.width + "repaint");
       // setPreferredSize(d);
       // setSize(d);
        initialSize = new Dimension(
                  (int)(img.getWidth(null)*zoom),
                  (int)(img.getHeight(null)*zoom));
        Dimension d = new Dimension(
                (int)(initialSize.width*zoom),
                (int)(initialSize.height*zoom));
            setPreferredSize(d);
            setSize(d);
        
    }
    
    public String getDrawObject() {
    	return ""+dOs.name();
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
    		case DrawObjectLine: 
    			this.drawObj = new DrawObjectLine(g2s, this.e);
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
        g2s.drawImage(img, 0, 0, null);
        g2d.transform(tx);
        g2s.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //�����������
        g2s.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if(drawObj != null){
	        setDrawObject(dOs,g2s);
	        if(isPressed && dOs.name() == "DrawObjectBrush") {
					
					if(lastX != 0 && lastY != 0) 
						{
						System.out.println(x2 + " " + y2 + " " + lastX + " " + lastY);
						DrawBrushLine bl = new DrawBrushLine(g2s,e);
						g2s = (Graphics2D) bl.Draws(lastX, lastY, x2, y2, Color.red);
						}
					lastX = x2;
					lastY = y2;
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
		x1 = rx1;
		y1 = ry1;
		
		Graphics2D g2d  = (Graphics2D)g;
    	g2d.drawImage(img, 0, 0, (int)(img.getWidth(null)* zoom), (int)(img.getHeight(null)*zoom), this);
    	setDrawObject(dOs,g2d);
    	g2d = (Graphics2D) drawObj.Draws((int)(x1 * zoom), (int)(y1 *zoom), x2, y2, Color.red);
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
 
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double zoomFactor = - SCALE_STEP*e.getPreciseWheelRotation()*zoom;
        if(Math.abs(zoom + zoomFactor) > 1) zoom = 1;//Math.abs(zoom + zoomFactor);
        else zoom = 1;
        System.out.println(e);
        Dimension d = null;
        
        	d = new Dimension((int)(initialSize.width*zoom),(int)(initialSize.height*zoom));
       
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
       
        System.out.println(d.getWidth() + " " + realView.getWidth());
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
        if(e.getWheelRotation() == 1)
        {
        	scrollX = 0;///previousZoom*zoom - (1-visibleRect.getX());
            scrollY = 0;///previousZoom*zoom - (1-visibleRect.getY());
        }
        scrollX = point.getX()/previousZoom*zoom - (point.getX()-visibleRect.getX());
        scrollY = point.getY()/previousZoom*zoom - (point.getY()-visibleRect.getY());
        System.out.println(scrollX + " " + scrollY + " " + visibleRect.getWidth() + " " + visibleRect.getHeight());
        visibleRect.setRect(scrollX, scrollY, visibleRect.getWidth(), visibleRect.getHeight());
        scrollRectToVisible(visibleRect);
    }
 
    
    
    public void mouseDragged(MouseEvent e) {
    	x2 = (int) (e.getX() / zoom);
        y2 = (int) (e.getY() / zoom);
        DrawObject.setNewRect(x1, y1, x2, y2);
        setDrawObject(dOs, (Graphics2D) this.getGraphics());
        if(isPressed && dOs.name() == "DrawObjectBrush") {
        	this.paintComponent(this.getGraphics());
        }
        else {
    		this.repaint(this.getGraphics(), e);
        }
    }
 
    public void mouseMoved(MouseEvent e) {
    }
 
    public void mouseClicked(MouseEvent e) {
    }
 
    public void mousePressed(MouseEvent e) {
    	isPressed = true;
    	
		x1 = (int) (e.getX() / zoom);
		y1 = (int) (e.getY() / zoom);
		rx1 = x1;
		ry1 = y1;
		System.out.println(rx1 + " " + ry1);
    }
 
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
        x2 = (int) (e.getX() / zoom);
        y2 = (int) (e.getY() / zoom);
        System.out.println("mR");
        DrawObject.setNewRect(x1, y1, x2, y2);
        isReleased  = true;
        this.paintComponent(this.getGraphics());
        isReleased = false;
        lastX = 0;
        lastY = 0;
        System.out.println("Released");
    }
 
    public void mouseEntered(MouseEvent e) {
 
    }
 
    public void mouseExited(MouseEvent e) {
 
    }
}
