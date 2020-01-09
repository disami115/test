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
	private static int x1 = 0;
	private static int y1 = 0;
	private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private static int x2 = d.width;
	private static int y2 = d.height;

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
        	image = new Robot().createScreenCapture(new Rectangle(x1, y1, x2-x1,y2-y1));
            SaveScreen(image);
            return image;
        } catch (AWTException e) {
            e.printStackTrace();
        } return null;
        
	}
	
	public static void setFirstXY(int x, int y){
		x1 = x;
		y1 = y;
	}
	
	public static void setSecondXY(int x, int y){
		x2 = x;
		y2 = y;
	}

}
