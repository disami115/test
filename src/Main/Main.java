package Main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import GUIs.SecondGUI;

public class Main {

	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		SecondGUI window = new SecondGUI((Image)img);
		window.setVisible(true);
	}
}
