package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

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
	
	
}
