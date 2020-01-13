package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import Saves.SaveToFile;
import Screens.Screen;
import Canvas.CanvasPanel;
import Canvas.MyCanvas;;

public class SecondGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton ScreenButton = new JButton("Screen");
	private JButton SaveButton = new JButton("Save Screen");
	private JButton ArrowButton = new JButton("Arrow");
	private JButton Button1 = new JButton("One");
	private JButton Button2 = new JButton("Two");
	private JButton Button3 = new JButton("Three");
	private JButton TextButton = new JButton("Text");
	private JButton BlurButton = new JButton("Blur");
	private JButton OpenButton = new JButton("Open");
	private CanvasPanel CanvPan;
	public SelectCoordGui g2 = null;
	public MyCanvas c;
	
	public Graphics g;
	
	public SecondGUI(Image img) {
		super("ScreenSaver");
		c = new MyCanvas(1.0, img);
		CanvPan = new CanvasPanel(true, c);
		this.remove(CanvPan);
		this.add(CanvPan, BorderLayout.CENTER);
		if(g2 == null) g2 = new SelectCoordGui(img, this);
		this.setBounds(Screen.d.width/6, Screen.d.height/6, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScreenButton.addActionListener(new ScreenButtonEventListener());
		ArrowButton.addActionListener(new ArrowButtonEventListener());
		Button1.addActionListener(new Button1EventListener());
		Button2.addActionListener(new Button2EventListener());
		Button3.addActionListener(new Button3EventListener());
		TextButton.addActionListener(new TextButtonEventListener());
		BlurButton.addActionListener(new BlurButtonEventListener());
		SaveButton.addActionListener(new SaveButtonEventListener());
		OpenButton.addActionListener(new OpenButtonEventListener());
		JMenuBar BP = new JMenuBar();
		BP.add(ScreenButton);
		BP.add(ArrowButton);
		BP.add(Button1);
		BP.add(Button2);
		BP.add(Button3);
		BP.add(TextButton);
		BP.add(BlurButton);
		BP.add(SaveButton);
		BP.add(OpenButton);
	    setJMenuBar(BP);
	}
	
	public void setNewImage(Image img) {
		
		c = new MyCanvas(1.0, img);
		CanvPan = new CanvasPanel(true, c);
		this.add(CanvPan, BorderLayout.CENTER);
	}
	
	public void doScreen() {
		try {
			doUnvisible();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("sec gui");
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
		}
	}
	
	class Button1EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("1");
		}
	}

	class Button2EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("2");
		}
	}
	
	class Button3EventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("3");
		}
	}
	
	class TextButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.txt = true;
			System.out.println("Text");
		}
	}
	
	class BlurButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Blur");
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
	
			//SaveToFile s = new SaveToFile();
			
			
			//c.remove(CanvPan);
			
			//BufferedImage i = s.TryOpen();
			//Screen.SaveScreen(i, "open");
			//g.drawImage((Image)i, i.getWidth(), i.getHeight(), null);
			//g.dispose();
			//c.paintComponent(g);
			//c.paint(g);
			
		}
}

}
