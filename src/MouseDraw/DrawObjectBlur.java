package MouseDraw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class DrawObjectBlur extends DrawObject {
	
	public DrawObjectBlur(Graphics g, MouseEvent e) {
		super(g, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		setNewRect(x1,y1,x2,y2);
		Graphics g = this.g;
		Graphics2D g2d  = (Graphics2D)g;
		g2d.setColor(Color.red);
		//AlphaComposite composite = AlphaComposite.SrcOver.derive( 0.9f );
        //g2d.setComposite( composite );
        g2d.fillRect(r.x, r.y, r.width, r.height);
		g2d.dispose();
		return (Graphics)g2d;
	}
	
	
}
