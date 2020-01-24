package MouseDraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectOne extends DrawObject {

	private static int i = 0;
	public DrawObjectOne(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}
	
	public static String getI() {
		return i + ".png";
	}
	
	public static void changeI() {
		if(i == 9) i = -1;
		i++;
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Font f = new Font(null, 1, 20);
		Graphics2D g = (Graphics2D) this.g;
		g.setColor(c);
		g.setStroke(new BasicStroke((float) (super.getSizeDrawObject()/2)));
		g.drawOval(x2-9, y2-22, 30, 30);
		g.setFont(f);
		g.drawString(""+i, x2, y2);
		g.dispose();
		return g;
	}

}
