package Saves;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;

import GUIs.SaveServGui;
import Screens.Screen;

public class SaveToServ{

	public String TrySave(BufferedImage bufferedImage, SaveServGui ssgui) throws ClientProtocolException, IOException {
		MyHttpPost hp = new MyHttpPost();
		ssgui.setVisible(false);
		return hp.post(bufferedImage, ssgui.getLogin(), ssgui.getPassword());
	}
	
}
