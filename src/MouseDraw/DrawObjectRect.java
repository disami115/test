package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class DrawObjectRect extends DrawObject {

	public DrawObjectRect(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Graphics g = this.g;
		int d = (int)super.getSizeDrawObject();
		((Graphics2D) g).setStroke(new BasicStroke((float) (d)));
		g.setColor(c);
		//RoundRectangle2D rr = new RoundRectangle2D();
		DrawObject.setNewRect(x1, y1, x2, y2);
		
		g.drawRect(r.x, r.y, r.width, r.height);
		g.setColor(Color.lightGray);
		((Graphics2D) g).setStroke(new BasicStroke(d/2));
		g.drawRect((int) (r.x-(3*d/4)), r.y-(3*d/4), r.width+4*d/3, r.height+4*d/3);
		g.dispose();
		return g;
	}

}
