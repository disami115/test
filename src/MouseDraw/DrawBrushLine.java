package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

public class DrawBrushLine extends DrawObject {

private DrawObjectBrush p = null;

	public DrawBrushLine(Graphics g, MouseEvent e) {
		super(g, e);
		p =  new DrawObjectBrush(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Graphics g = this.g;
		((Graphics2D) g).setStroke(new BasicStroke((float) (super.getSizeDrawObject())));
		g.setColor(c);
		g.drawLine(x1+5, y1+5, x2+5, y2+5);
		//g = p.Draws(x1, y1, c);
		
		g.dispose();
		return g;
	}

}
