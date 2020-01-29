package MouseDraw;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import GUIs.SecondGUI;
import Screens.XYWH;

public abstract class DrawObject {
	
	protected Graphics g = null;
	protected MouseEvent e = null;
	protected static Rectangle r = null;
	protected static double SizeDrawObject = 2;
	
	public DrawObject(Graphics g, MouseEvent e){
		
		this.g = g;
		this.e = e;
		SizeDrawObject = SecondGUI.getSizeDrawObject();
	}
	
	abstract public Graphics Draws(int x1, int y1, int x2, int y2, Color c);
	
	public static void setNewRect(int x1, int y1, int x2, int y2) {
		r = XYWH.setXYWH(x1, x2, y1, y2);
	}
	
	public double getSizeDrawObject() {
		return SizeDrawObject;
	}
	
	public static void drawShade (Graphics2D g2d, Rectangle rr, Color shadeColor, int width )
	{/*
	  Composite comp = g2d.getComposite ();
	  Stroke old = g2d.getStroke ();
	  width = width * 2;
	  for ( int i = width; i >= 2; i -= 2 )
	  {
	    float opacity = ( float ) ( width - i ) / ( width - 1 );
	    g2d.setColor ( shadeColor );
	    g2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, opacity ) );
	    g2d.setStroke ( new BasicStroke ( i ) );
	    g2d.draw ( rr );
	  }
	  g2d.setStroke ( old );
	  g2d.setComposite ( comp );*/
	}
	
	
}
