package MouseDraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class DrawObjectThree extends DrawObject {

	public DrawObjectThree(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Font f = new Font(null, 1, 20);
		Graphics g = this.g;
		g.setColor(c);
		g.drawOval(x2-9, y2-22, 30, 30);
		g.setFont(f);
		g.drawString("3", x2, y2);
		g.dispose();
		return g;
	}

}
