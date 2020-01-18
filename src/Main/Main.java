package Main;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import GUIs.SecondGUI;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		BufferedImage img = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		SecondGUI window = new SecondGUI((Image)img);
		window.setVisible(true);
	}
}
