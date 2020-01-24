package MouseDraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectText extends DrawObject {

	private static String txt;
	
	public DrawObjectText(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		Font f = new Font(null, 1, (int) (10 + super.getSizeDrawObject() * 2));
		Graphics2D g = (Graphics2D) this.g;
		g.setColor(c);
		g.setFont(f);
		g.drawString(txt, x2, y2);
		g.dispose();
		return g;
	}
	
	public static void setTxt(String s) {
		txt = s;
	}



}
