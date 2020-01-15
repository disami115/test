package GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Do Screen");
	
	public GUI() {
	    super("ScreenSaver");
	    this.setBounds(200, 200, 200, 100);
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(1,1));
	    button.addActionListener(new ButtonEventListener());
	    container.add(button);
	}
	
	class ButtonEventListener extends JWindow implements ActionListener {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
				
		}
	}
	
	
}