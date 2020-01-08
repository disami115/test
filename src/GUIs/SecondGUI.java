package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import Saves.SaveToFile;
import Canvas.CanvasPanel;
import Canvas.MyCanvas;;

public class SecondGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton SaveButton = new JButton("Save Screen");
	private JButton ArrowButton = new JButton("Arrow");
	private JButton Button1 = new JButton("One");
	private JButton Button2 = new JButton("Two");
	private JButton Button3 = new JButton("Three");
	private JButton TextButton = new JButton("Text");
	private JButton BlurButton = new JButton("Blur");
	public MyCanvas c;
	public Graphics g;
	
	public SecondGUI(Image img) {
		 super("ScreenSaver");
		 c = new MyCanvas(1.0, img);
		 CanvasPanel CanvPan = new CanvasPanel(true, c)/* */;
			//Graphics g = new Graphics();
			this.setBounds(200, 200, 800, 400);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLayout(new BorderLayout());
		    this.add(CanvPan, BorderLayout.CENTER);
		    this.setSize(new Dimension(1000, 800));
		    this.setVisible(true);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		    /*Container container = this.getContentPane();
		    container.setLayout(new CardLayout());
		    container.setBounds(0, 0, 100, 100);*/
		    
		    ArrowButton.addActionListener(new ArrowButtonEventListener());
		    Button1.addActionListener(new Button1EventListener());
		    Button2.addActionListener(new Button2EventListener());
		    Button3.addActionListener(new Button3EventListener());
		    TextButton.addActionListener(new TextButtonEventListener());
		    BlurButton.addActionListener(new BlurButtonEventListener());
		    SaveButton.addActionListener(new SaveButtonEventListener());
		    JMenuBar BP = new JMenuBar();
		    BP.add(ArrowButton);
		    BP.add(Button1);
		    BP.add(Button2);
		    BP.add(Button3);
		    BP.add(TextButton);
		    BP.add(BlurButton);
		    BP.add(SaveButton);
	        setJMenuBar(BP);
		    //this.add(c);
		    
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
			c.paintComponent(c.getGraphics());
			
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

}
