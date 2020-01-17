package Saves;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class MyHttpPost {
	
	public String post(BufferedImage bufferedImage, String l, String p) throws ClientProtocolException, IOException
	{
		ArrayList<String> s = new ArrayList<>();
		String str = "";
		HttpPost httppost = new HttpPost("http://localhost/main/index.php");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("username", l, ContentType.TEXT_PLAIN);
		builder.addTextBody("password", p, ContentType.TEXT_PLAIN);
		File file = new File("my_file.png");
		ImageIO.write(bufferedImage, "png", file);
		builder.addBinaryBody("image", new FileInputStream(file),ContentType.APPLICATION_OCTET_STREAM,file.getName());
	    HttpEntity multipart = builder.build();
		httppost.setEntity(multipart); 
	    try (CloseableHttpResponse response = httpclient.execute(httppost)){
		    HttpEntity responseEntity = response.getEntity();    
		    try (Scanner sc = new Scanner(responseEntity.getContent())) {
				while(sc.hasNext()) {
				   s.add(sc.nextLine());
				}
				if(s.size() == 3) {
					str = s.get(0) + " " +  s.get(1) +"\n" + s.get(2);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s.get(1)),null);
				}
				else str = s.get(0);
			}
	    }
	    return str;
	}

}