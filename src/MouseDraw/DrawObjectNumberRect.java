package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectNumberRect extends DrawObject {

	private static int i;
	public DrawObjectNumberRect(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}
	
	public static String getI() {
		return "r" + i + ".png";
	}
	
	public static void changeI() {
		if(i == 9) i = -1;
		i++;
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Font f = new Font(null, 1, 20);
		Graphics g = this.g;
		((Graphics2D) g).setStroke(new BasicStroke((float) (super.getSizeDrawObject())));
		g.setColor(c);
		g.setFont(f);
		DrawObject.setNewRect(x1, y1, x2, y2);
		g.drawRect(r.x, r.y, r.width, r.height);
		g.drawString(""+i, r.x + 10, r.y + 25);
		g.dispose();
		return g;
	}

}
