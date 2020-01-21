package GUIs;


import static java.awt.SystemTray.getSystemTray;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import Saves.SaveToFile;
import Screens.Screen;
import Canvas.CanvasPanel;
import Canvas.MyCanvas;
import Canvas.MyCanvas.DrawObjects;
import MouseDraw.DrawObjectOne;
import javax.swing.JFrame;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class SecondGUI extends JFrame implements NativeKeyListener{
	private static final long serialVersionUID = 1L;
	private JButton ScreenButton = new JButton("Скриншот");
	private JButton SaveButton = new JButton("Сохранить");
	private JButton ArrowButton = new JButton("Стрелка");
	private JButton BrushButton = new JButton("Кисть");
	private JButton Button1 = new JButton(DrawObjectOne.getI());
	private JButton LineButton = new JButton("Линия");
	private JButton TextButton = new JButton("Текст");
	private JButton BlurButton = new JButton("Замазать");
	private JButton SaveServButton = new JButton("Сохранить на сервер");
	private JButton OpenButton = new JButton("Open");
	public CanvasPanel CanvPan;
	public SelectCoordGui g2 = null;
	public MyCanvas c;
	private SecondGUI g1 = null;
	public Graphics g;
	private static int imdH;
	private static int imdW;
	private TrayIcon icon = null;
	
	public SecondGUI(Image img) throws IOException {
		super("ScreenSaver");
		/*try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}*/
		g1 = this;
		GlobalScreen.addNativeKeyListener(this);
		File fIco = new File("screenshot.png");
        Image ico = ImageIO.read(fIco);
        setIconImage(ico);
		c = new MyCanvas(1.0, img);
		CanvPan = new CanvasPanel(true, c);
		this.remove(CanvPan);
		this.add(CanvPan, BorderLayout.CENTER);
		if(g2 == null) g2 = new SelectCoordGui(img, this);
		this.setBounds(Screen.d.width/2 - 400, Screen.d.height/2 - 300, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScreenButton.addActionListener(new ScreenButtonEventListener());
		ArrowButton.addActionListener(new ArrowButtonEventListener());
		Button1.addActionListener(new Button1EventListener());
		LineButton.addActionListener(new LineButtonEventListener());
		TextButton.addActionListener(new TextButtonEventListener());
		BlurButton.addActionListener(new BlurButtonEventListener());
		SaveButton.addActionListener(new SaveButtonEventListener());
		OpenButton.addActionListener(new OpenButtonEventListener());
		BrushButton.addActionListener(new BrushButtonEventListener());
		SaveServButton.addActionListener(new SaveServButtonEventListener());
		JMenuBar BP = new JMenuBar();
		BP.add(ScreenButton);
		BP.add(BrushButton);
		BP.add(LineButton);
		BP.add(ArrowButton);
		BP.add(Button1);
		BP.add(TextButton);
		BP.add(BlurButton);
		BP.add(SaveButton);
		BP.add(SaveServButton);
	    setJMenuBar(BP);
	    if (SystemTray.isSupported()) {
            icon = new TrayIcon(ico);
            icon.setImageAutoSize(true);
            icon.setToolTip("ScreenSaver");
            icon.addActionListener(new ActionListener() {
            	
                public void actionPerformed(ActionEvent e) {
                	g1.expand();
                }

            });
            addWindowListener(new WindowAdapter() {

                @Override
                public void windowIconified(WindowEvent e) {
                	SecondGUI.this.setVisible(false);
                    try {
                        getSystemTray().add(icon);
                    } catch (AWTException e1) {
                        e1.printStackTrace();
                    }
                }

            });
	    }
	}
	
	public void setNewImage(Image img) {
		c = new MyCanvas(1.0, img);
		this.imdH = img.getHeight(null);
		this.imdW = img.getWidth(null);
		CanvPan = new CanvasPanel(true, c);
		this.add(CanvPan, BorderLayout.CENTER);
	}
	
	public int[] getImgHW() {
		int[] arr = {this.imdH, this.imdW};
		System.out.println(arr[0] + " " + arr[1]);
		return arr;
	}
	
	public void expand() {
		SecondGUI.this.setVisible(true);
    	SecondGUI.this.setExtendedState(SecondGUI.NORMAL);
        getSystemTray().remove(icon);
	}
	
	public void doScreen() {
		try {
			doUnvisible();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Screen.setXYWH(0, Screen.d.width, 0, Screen.d.height);
		g2.setDefault(Screen.grabScreen(), this);
		g2.setVisible(true);
	}
	

	private void doUnvisible() throws InterruptedException
	{
		this.remove(CanvPan);
		this.setVisible(false);
		TimeUnit.SECONDS.sleep(1);
	}
	
	class ScreenButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				doScreen();
		}
	}
	
	class ArrowButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Arrow");
			c.changeDrawObjects(DrawObjects.DrawObjectArrow.toString());
		}
	}
	
	class Button1EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println(c.getDrawObject() + " " + DrawObjects.DrawObjectOne.toString());
			if(c.getDrawObject().equals(DrawObjects.DrawObjectOne.toString())) {
				DrawObjectOne.changeI();
				Button1.setText(DrawObjectOne.getI());
			}
			c.changeDrawObjects(DrawObjects.DrawObjectOne.toString());
		}
	}

	class TextButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectText.toString());
		}
	}
	
	class BrushButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectBrush.toString());
			
		}
	}
	
	class BlurButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectBlur.toString());
		}
	}
	
	class LineButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectLine.toString());
		}
	}

	class SaveServButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SaveServGui ssgui = new SaveServGui(c);
			ssgui.setVisible(true);
		}
	}
	
	class SaveButtonEventListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			SaveToFile s = new SaveToFile();
			s.TrySave(c.getBufferedImage(c.getGraphics()));
			System.out.println("It's Saved!");
		}
	}

	class OpenButtonEventListener implements ActionListener {
	
		public void actionPerformed(ActionEvent e) {
		}
	}


	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		if(e.getKeyCode() == NativeKeyEvent.VC_PRINTSCREEN) {
			this.expand();
			doScreen();
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
