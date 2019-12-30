package Screens;

import java.io.File;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.AWTException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileSystemView;

public class Screen {
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
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            SaveScreen(image);
            return image;
        } catch (AWTException e) {
            e.printStackTrace();
        } return null;
        
	}

}
