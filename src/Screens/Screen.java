package Screens;

import java.io.File;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileSystemView;

public class Screen {
	private static int lx = 0;
	private static int ly = 0;
	private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private static int bx = d.width;
	private static int by = d.height;
	private static Rectangle r = new Rectangle(lx, ly, bx, by);

	public static void SaveScreen(BufferedImage img)
	{
		try {	
			ImageIO.write(img, "png", new File(getHomeDir(), "temp.png"));
		} catch (IOException e) {
			System.out.println("IO exception"+e);
		}
		
	}
	
	private static File getHomeDir() {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		return fsv.getHomeDirectory();
	}

	public static BufferedImage grabScreen() { 
		BufferedImage image = null;
        try {
        	System.out.println(r.x + " " + r.y + " " + " " + r.width + " " + r.height);
        	image = new Robot().createScreenCapture(r);
            SaveScreen(image);
            return image;
        } catch (AWTException e) {
            e.printStackTrace();
        } return null;
        
	}
	
	
	public static void setXYWH(int x1, int x2, int y1, int y2){
		bx = Math.max(x1, x2) ; // bigX
		by = Math.max(y1, y2) ; // bigY
		lx = Math.min(x1, x2); // littleX
		ly = Math.min(y1, y2); // littleY
		System.out.println(lx + " " + ly + " " + " " + bx + " " + by);
		r = new Rectangle(lx, ly, bx-lx, by-ly);
	}
	
}
