package Saves;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;



import javax.imageio.ImageIO;

import GUIs.SaveServGui;

public class SaveToServ{

	public void TrySave(BufferedImage bufferedImage, SaveServGui ssgui) {
		System.out.println(ssgui.getLogin());
		System.out.println(ssgui.getPassword());
		ssgui.setVisible(false);
	}
	
}
