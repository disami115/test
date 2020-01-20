package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectLine extends DrawObject {

	public DrawObjectLine(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Graphics2D g = (Graphics2D) this.g;
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
		g.dispose();
		return g;
	}

}
