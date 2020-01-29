package MouseDraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Canvas.MyCanvas;

public class DrawObjectBlur extends DrawObject {
	
	private static boolean blur;

	public DrawObjectBlur(Graphics g, MouseEvent e) {
		super(g, e);
	}

	@Override
	public Graphics Draws(int x1, int y1, int x2, int y2, Color c) {
		setNewRect(x1,y1,x2,y2);
		Graphics g = this.g;
		Graphics2D g2d  = (Graphics2D)g;
		if(blur) {
			g2d.drawImage(doBlur(), r.x, r.y, r.width, r.height, null);
			blur = false;
		}
		else {
			g2d.setColor(c);
			g2d.drawRect(r.x, r.y, r.width, r.height);
			g2d.dispose();
		}
		
		return (Graphics)g2d;
	}
	
	public static void setBlur() {
		blur = true;
	}
	
	private BufferedImage doBlur() {
		final int PIX_SIZE = 6;
	    BufferedImage origin = new BufferedImage(MyCanvas.bufferedImage.getWidth(null), MyCanvas.bufferedImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
	    try {
			ImageIO.write(MyCanvas.bufferedImage, "png", new File("temp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
	    	File temp = new File("temp.png");
			origin = ImageIO.read(temp);
			temp.delete();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    Raster src = origin.getData();
	    System.out.println("doBlur " + r.x + " " + r.width);
	    WritableRaster dest = src.createCompatibleWritableRaster();
	    if(r.x < 0) r.x = 0;
	    if(r.y < 0) r.y = 0;
	    for(int y = r.y; y < r.y + r.height + PIX_SIZE; y += PIX_SIZE) {
	        for(int x = r.x; x < r.x + r.width + PIX_SIZE; x += PIX_SIZE) {
	            double[] pixel = new double[3];
	            pixel = src.getPixel(x, y, pixel);
	            for(int yd = y; (yd < y + PIX_SIZE) && (yd < src.getHeight()); yd++) {
	                for(int xd = x; (xd < x + PIX_SIZE) && (xd < src.getWidth()); xd++) {
	                    dest.setPixel(xd, yd, pixel);
	                }
	            }
	        }
	    }
	    origin.setData(dest);
		return origin.getSubimage(r.x, r.y, r.width, r.height);
	}
	
	
	
}
