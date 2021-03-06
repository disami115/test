package GUIs;

import static java.awt.SystemTray.getSystemTray;
import java.awt.*;
import java.awt.event.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Saves.SaveToFile;
import Screens.Screen;
import Canvas.CanvasPanel;
import Canvas.MyCanvas;
import Canvas.MyCanvas.DrawObjects;
import Canvas.SystemColorChooserPanel;
import MouseDraw.DrawObjectNumberRect;
import MouseDraw.DrawObjectOne;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class SecondGUI extends JFrame implements NativeKeyListener{
	private static final long serialVersionUID = 1L;
	private JButton ScreenButton = new JButton();
	private JButton SaveButton = new JButton();
	private JButton ArrowButton = new JButton();
	private JButton BrushButton = new JButton();
	private JButton Button1 = new JButton();
	private JButton LineButton = new JButton();
	private JButton TextButton = new JButton();
	private JButton BlurButton = new JButton();
	private JButton SaveServButton = new JButton();
	private JButton ColorButton = new JButton();
	private JButton RectangleButton = new JButton();
	private JButton NumberRectButton = new JButton();
	private JSlider SizeSlider = new JSlider(1, 10, 2);
	private JMenu ColorMenu = new JMenu ();
	private static double sizeDrawObject = 2.0;
	public CanvasPanel CanvPan;
	public SelectCoordGui g2 = null;
	public MyCanvas c;
	private SecondGUI g1 = null;
	public Graphics g;
	private static int imdH;
	private static int imdW;
	private TrayIcon icon = null;
	private ImageIcon imgico = null;
	private Color color;
	private boolean isCtrl = false, isTray = false;
	private boolean isPrtScr = false;
	private boolean toggled = false;
	private JMenuBar BP = new JMenuBar();
	private JColorChooser colorChooser = new JColorChooser();
	
	public SecondGUI(Image img) throws IOException, URISyntaxException {
		super("ScreenSaver");
		isPrtScr = false;
		if(isPrtScr) {
			try {
				GlobalScreen.registerNativeHook();
			}
			catch (NativeHookException ex) {
				//System.err.println("There was a problem registering the native hook.");
				//System.err.println(ex.getMessage());
	
				System.exit(1);
			}
		}
		color = Color.black;
		g1 = this;
		GlobalScreen.addNativeKeyListener(this);
		URL url = getClass().getResource("screenshot.png");
		InputStream in = SecondGUI.class.getClassLoader().getResourceAsStream("screenshot.png");
        Image ico = ImageIO.read(in);
        setIconImage(ico);
		c = new MyCanvas(1.0, img);
		CanvPan = new CanvasPanel(true, c);
		this.remove(CanvPan);
		this.add(CanvPan, BorderLayout.CENTER);
		if(g2 == null) g2 = new SelectCoordGui(img, this);
		this.setBounds(Screen.d.width/2 - 400, Screen.d.height/2 - 300, 800, 600);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
            @Override
            public void windowClosing(WindowEvent e) {
            	collapse();
            }

        });
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		SizeSlider.setMajorTickSpacing(1);
		SizeSlider.setPaintLabels(true);
		LineButton.setToolTipText("Линия");
		ArrowButton.setToolTipText("Стрелка");
        ScreenButton.setToolTipText("Сделать скриншот");
        BrushButton.setToolTipText("Кисть");
        Button1.setToolTipText("Круг нумерованный");
        TextButton.setToolTipText("Текст");
        BlurButton.setToolTipText("Пикселизация");
        SaveButton.setToolTipText("Сохранить");
        RectangleButton.setToolTipText("Прямоугольник");
        NumberRectButton.setToolTipText("Прямоугольник нумерованный");
        SaveServButton.setToolTipText("Сохранить на сервер");
        ColorMenu.setToolTipText("Выбрать цвет");
        
		LineButton.setIcon(setIcon("line.png"));
		ArrowButton.setIcon(setIcon("arrow.png"));
        ScreenButton.setIcon(setIcon("screen.png"));
        BrushButton.setIcon(setIcon("brush.png"));
        Button1.setIcon(setIcon(DrawObjectOne.getI()));
        TextButton.setIcon(setIcon("T.png"));
        BlurButton.setIcon(setIcon("blur.png"));
        SaveButton.setIcon(setIcon("save.png"));
        SaveServButton.setIcon(setIcon("server.png"));
        ColorMenu.setIcon(setIcon("color.png"));
        ColorMenu.setOpaque(true);
        ColorMenu.setBackground(new Color(200, 218, 235));
        ColorMenu.setMaximumSize(Button1.getMaximumSize());
        ColorMenu.setBorder(ArrowButton.getBorder());
        RectangleButton.setIcon(setIcon("rect.png"));
        NumberRectButton.setIcon(setIcon(DrawObjectNumberRect.getI()));
		ScreenButton.addActionListener(new ScreenButtonEventListener());
		ArrowButton.addActionListener(new ArrowButtonEventListener());
		Button1.addActionListener(new Button1EventListener());
		LineButton.addActionListener(new LineButtonEventListener());
		TextButton.addActionListener(new TextButtonEventListener());
		BlurButton.addActionListener(new BlurButtonEventListener());
		SaveButton.addActionListener(new SaveButtonEventListener());
		BrushButton.addActionListener(new BrushButtonEventListener());
		SaveServButton.addActionListener(new SaveServButtonEventListener());
		//ColorMenu.addActionListener(new ColorButtonEventListener());
		SizeSlider.addChangeListener(new SizeChangeEventListener());
		RectangleButton.addActionListener(new RectangleButtonEventListener());
		NumberRectButton.addActionListener(new NumberRectButtonEventListener());
		createColorChooser();
		//GridBagConstraints constraints = new GridBagConstraints(); 
	   // constraints.ipadx = 100;
	   // constraints.ipady = 100;
		ColorMenu.add(colorChooser);
		BP.add(ScreenButton);
		BP.add(BrushButton);
		BP.add(LineButton);
		BP.add(ArrowButton);
		BP.add(RectangleButton);
		BP.add(Button1);
		BP.add(NumberRectButton);
		BP.add(TextButton);
		BP.add(BlurButton);
		BP.add(ColorMenu);
		BP.add(SaveButton);
		BP.add(SaveServButton);
		BP.add(SizeSlider);
	    setJMenuBar(BP);
	    
	    ActionListener exitListener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("Exiting...");
	            System.exit(0);
	        }
	    };
	    
	    ActionListener expandListener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	g1.expand();
	        }
	    };
	             
	    
	    if (SystemTray.isSupported()) {
	    	
	    	PopupMenu popup = new PopupMenu();
	 	    MenuItem exitItem = new MenuItem("Выход");
	 	    exitItem.addActionListener(exitListener);
	 	    MenuItem expandItem = new MenuItem("Развернуть");
	 	    expandItem.addActionListener(expandListener);
	 	    popup.add(expandItem);
	 	    popup.add(exitItem);
            icon = new TrayIcon(ico, "hello", popup);
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
                	collapse();
                }

            });
	    }
	    
	}
	
	protected void collapse() {
		isTray = true;
		SecondGUI.this.setVisible(false);
        try {
            getSystemTray().add(icon);
        } catch (AWTException e1) {
            e1.printStackTrace();
        }
	}
	
	public void setNewImage(Image img) {
		c = new MyCanvas(1.0, img);
		this.imdH = img.getHeight(null);
		this.imdW = img.getWidth(null);
		CanvPan = new CanvasPanel(true, c);
		this.add(CanvPan, BorderLayout.CENTER);
		c.setColor(color);
	}
	
	public int[] getImgHW() {
		int[] arr = {this.imdH, this.imdW};
		System.out.println(arr[0] + " " + arr[1]);
		return arr;
	}
	
	public void expand() {
		isTray = false;
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
		//g1.setExtendedState(JFrame.NORMAL);
		g2.setVisible(true);
	}
	

	private void doUnvisible() throws InterruptedException
	{
		if(!isTray) {
			g1.collapse();
			TimeUnit.MILLISECONDS.sleep(150);
		}
		this.setVisible(false);
		this.remove(CanvPan);
		
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
			//if(c.getDrawObject().equals(DrawObjects.DrawObjectOne.toString())) {}
				DrawObjectOne.changeI();
				try {
					Button1.setIcon(setIcon(DrawObjectOne.getI()));
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			c.changeDrawObjects(DrawObjects.DrawObjectOne.toString());
		}
	}

	class NumberRectButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DrawObjectNumberRect.changeI();
			try {
				NumberRectButton.setIcon(setIcon(DrawObjectNumberRect.getI()));
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			c.changeDrawObjects(DrawObjects.DrawObjectNumberRect.toString());
		}
	}

	class TextButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectText.toString());
			GUI textG = new GUI();
			textG.setVisible(true);
			
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
	
	class RectangleButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			c.changeDrawObjects(DrawObjects.DrawObjectRect.toString());
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

	class ColorButtonEventListener implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e) {
			
			//JPopupMenu popup = new JPopupMenu();
			
			
		    //JDialog d = colorChooser.createDialog(null,"Выбор цвета",true,colorChooser,null,null);
		    //item.add(colorChooser);
		    //popup.add(d);
			
		    //colorChooser.showDialog(colorChooser, "", color);
			//d.setVisible(true);
		    //popup.show(c, ColorButton.getX(), ColorButton.getY());
			
			
			
			c.setColor(color);
			
		}
	}
	
	protected void createColorChooser() {
		AbstractColorChooserPanel[] oldPanels = colorChooser.getChooserPanels();
		//JMenuItem item = new JMenuItem("Cut");

		//item.addActionListener(listener);
		
		
	    for (int i = 0; i < oldPanels.length; i++) {
	      String clsName = oldPanels[i].getClass().getName();
	      System.out.println(i + " " + clsName);
	      if (clsName.equals("javax.swing.colorchooser.ColorChooserPanel") || clsName.equals("javax.swing.colorchooser.DefaultSwatchChooserPanel")) {
	    	  colorChooser.removeChooserPanel(oldPanels[i]);
	      }
	    }
	    colorChooser.addChooserPanel(new SystemColorChooserPanel());
        colorChooser.setBounds(ColorButton.getX(), ColorButton.getY()-100, 100, 50);
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.setVisible(true);
	}
	
	class SizeChangeEventListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			sizeDrawObject = ((JSlider) e.getSource()).getValue();
		}
	}
	


	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if(e.getKeyCode() == NativeKeyEvent.VC_CONTROL) isCtrl = true;
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + " " + isCtrl);
		if(e.getKeyCode() == NativeKeyEvent.VC_Z && isCtrl) c.setLastGraphics((Graphics2D) c.getGraphics());
		if(e.getKeyCode() == NativeKeyEvent.VC_Y && isCtrl) c.setNextGraphics((Graphics2D) c.getGraphics());
		if(e.getKeyCode() == NativeKeyEvent.VC_CONTROL) isCtrl = false;
		if(e.getKeyCode() == NativeKeyEvent.VC_PRINTSCREEN) {
			//g1.expand();
			doScreen();
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

	public Color getColor() {
		return color;
	}
	
	public ImageIcon setIcon(String str) throws URISyntaxException, IOException {
		Image timg;
		Image newimg;
		if(str != "") {
			InputStream in = SecondGUI.class.getClassLoader().getResourceAsStream(str);
			Image ico = ImageIO.read(in);
			imgico = new ImageIcon(ico);
	        timg = imgico.getImage() ;
	        newimg = timg.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
	        imgico = new ImageIcon(newimg);
		}
        return imgico;
	}
	
	public static double getSizeDrawObject(){
		return sizeDrawObject;
	}

}
