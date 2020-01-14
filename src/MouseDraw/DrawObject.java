package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public abstract class DrawObject {
	
	protected Graphics g = null;
	protected MouseEvent e = null;
	
	
	
	public DrawObject(Graphics g, MouseEvent e){
		
		this.g = g;
		this.e = e;
	}
	
	abstract public Graphics Draws(Color c);
	
	
}
