package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectArrow extends DrawObject {

	private int x, y;
	
	public DrawObjectArrow(Graphics g, MouseEvent e) {
		super(g, e);
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		x = x1;
		y = y1;
		Graphics2D g = (Graphics2D) this.g;
		double a = Math.max(y1, y2) - Math.min(y1, y2);
		double b = Math.max(x1, x2) - Math.min(x1, x2);
		int d = (int) Math.sqrt(a*a + b*b);
		int yd = y-d;
		double ang = Math.asin(a/d);
		double dang = Math.toRadians(90);
		g.setColor(Color.black);
		if(x > x2 && y >= y2) g.rotate(ang-dang, x, y);
		if(x1 >= x2 && y1 < y2) g.rotate(-dang-ang, x, y);
		if(x1 < x2 && y1 >= y2) g.rotate(dang-ang, x, y);
		if(x1 < x2 && y1 < y2) g.rotate(ang+dang, x, y);
		g.setStroke(new BasicStroke(3.0f));
		g.drawLine(x, y, x, y-d);
		if(d > 20) {
			g.drawLine(x, yd, x - 10, yd + 20);
			g.drawLine(x, yd, x + 10, yd + 20);
		}
		else {
			g.drawLine(x, yd, x - (int)(d/2), yd + d);
			g.drawLine(x, yd, x + (int)(d/2), yd + d);
		}
		g.dispose();
		return g;
	}
}


