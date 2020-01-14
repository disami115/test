package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class DrawObjectOne extends DrawObject {

	public DrawObjectOne(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(Color c) {
		MouseEvent e = this.e;
		Graphics g = this.g;
		g.setColor(c);
		g.drawOval(e.getX(), e.getY(), 30, 30);
		g.drawString("1", e.getX()+10, e.getY()+15);
		g.dispose();
		return g;
	}

}
