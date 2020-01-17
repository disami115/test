package MouseDraw;

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
		g.setColor(Color.black);
		rotate(g, 10, x2, y2);
		findPoint(x, y, x2, y2);
		g.drawLine(x, y, x2, y2);
		rotate(g,-21, x2, y2);
		g.drawLine(x, y, x2, y2);
		rotate(g, 10, x2, y2);
		g.drawLine(x1, y1, x2, y2);
		g.dispose();
		return g;
	}
	
	public void rotate (Graphics2D g, int deg, int x1, int y1){
		g.rotate(Math.toRadians(deg), x1, y1);
	}
	
	public void findPoint(int x, int y, int x2, int y2) {
		this.x = (int) ((x + 3*x2)/4);
		this.y = (int) ((y + 3*y2)/4);
	}
	
}


