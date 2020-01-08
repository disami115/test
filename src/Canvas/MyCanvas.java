package Canvas;

import javax.swing.*;
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
    AffineTransform tx = new AffineTransform();
    private double scrollX = 0d;
    private double scrollY = 0d;
    public boolean txt = false;
    public BufferedImage bufferedImage = null;
    public MyCanvas(double zoom, Image img) {
        this.zoom = zoom;
        this.img = img;
        bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setAutoscrolls(true);
    }
 
    
    
    public Dimension getInitialSize() {
        return initialSize;
    }
 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);	
        //Graphics2D g2 = (Graphics2D) g;
        Graphics2D g2d = (Graphics2D) g.create();
        Graphics2D g2s = bufferedImage.createGraphics();
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.transform(tx);
        g2s.drawImage(this.img, 1, 1, this.img.getWidth(null), this.img.getHeight(null), null);
        if(txt) g2s.drawString("Hello", 100, 100);
        g2s.transform(tx);
        g2s.dispose();
        g2d.drawImage(bufferedImage, 1, 1, this.img.getWidth(null), this.img.getHeight(null), null);
        g2d.dispose();
    }
 
    public BufferedImage getBufferedImage(Graphics g) {
    	super.paintComponent(g);	
        //Graphics2D g2 = (Graphics2D) g;
        Graphics2D g2d = (Graphics2D) g.create();
        Graphics2D g2s = bufferedImage.createGraphics();
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.transform(tx);
        g2s.drawImage(this.img, 1, 1, this.img.getWidth(null), this.img.getHeight(null), null);
        if(txt) g2s.drawString("Hello", 100, 100);
        zoom = 1;
        g2s.transform(tx);
        g2s.dispose();
        g2d.drawImage(bufferedImage, 1, 1, this.img.getWidth(null), this.img.getHeight(null), null);
        g2d.dispose();
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
        System.out.println("zoomfac:" + zoom);
        System.out.println("zoom:" + zoom);
        zoom = Math.abs(zoom + zoomFactor);
        int tzoom = (int)(zoom*100);
        zoom = tzoom / 100.0;
        Dimension d = new Dimension(
                (int)(initialSize.width*zoom),
                (int)(initialSize.height*zoom));
//        if (d.getWidth() >= realView.getWidth() && d.getHeight() >= realView.getHeight()) {
            setPreferredSize(d);
            setSize(d);
            validate();
            followMouseOrCenter(e);
//        }
 
        //Here we calculate transform for the canvas graphics to scale relative to mouse
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
            //Zooming and translating relative to the mouse position
            tx.setToIdentity();
            tx.translate(p1.getX(), p1.getY());
            tx.scale(zoom, zoom);
            tx.translate(-p2.getX(), -p2.getY());
        } else {
            //Only zooming, translate is not needed because scrollRectToVisible works;
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
 
    }
 
    public void mouseEntered(MouseEvent e) {
 
    }
 
    public void mouseExited(MouseEvent e) {
 
    }


}
