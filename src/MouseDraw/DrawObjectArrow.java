package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class DrawObjectArrow extends DrawObject {

	public DrawObjectArrow(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Graphics2D g = (Graphics2D) this.g;
		Rectangle2D r2 = new Rectangle2D.Double(r.x, r.y, r.width, Math.sqrt(x1 * x1 + x2 * x2));
		g.setColor(Color.blue);
		double a;
		double b;
		double h;
		double cx1,cy1;
		//g.rotate(135*Math.PI/180);
		//g.draw(r2);
		a = Math.sqrt(r.width * r.width + r.height * r.height);
		//b = Math.sqrt(a*a + 100 - 141.42 * a);
		h = Math.sqrt(50);
		cx1 =  (x2+h);
		cy1 =  (y2+(a-h));
		System.out.println(cx1 + " " + cy1);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(Color.black);
		g.drawLine(x2, y2, (int)cx1, (int)cy1);
		//g.drawRect(r.x, r.y, Math.sqrt(x1 * x1 + x2 * x2), r.height);
		//g.fillOval(e.getX(), e.getY(), 10, 10);
		g.dispose();
		return g;
	}



}
