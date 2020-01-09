package GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import GUIs.SecondGUI;
import Screens.Screen;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Do Screen");
	
	public GUI() {
	    super("SS");
	    this.setBounds(200, 200, 200, 100);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(1,1));

	    button.addActionListener(new ButtonEventListener());
	    container.add(button);
	}
	
	class ButtonEventListener extends JWindow implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				doUnvisible();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Image img = Screen.grabScreen();
			SelectCoordGui g2 = new SelectCoordGui(img);
			g2.setVisible(true);
			
			System.out.println("it's work!");	
		}
	}
	

	private void doUnvisible() throws InterruptedException
	{
		this.setVisible(false);
		TimeUnit.SECONDS.sleep(1);
	}
	
	private void doVisible()
	{
		this.setVisible(true);
	}
}