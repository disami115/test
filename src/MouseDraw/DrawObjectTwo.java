package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class DrawObjectTwo extends DrawObject {

	public DrawObjectTwo(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(Color c) {
		MouseEvent e = this.e;
		Graphics g = this.g;
		g.setColor(c);
		//g.fillOval(e.getX(), e.getY(), 10, 10);
		g.dispose();
		return g;
	}

}
