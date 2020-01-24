package GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import GUIs.SaveServGui.SaveButtonEventListener;
import MouseDraw.DrawObjectText;
import Screens.Screen;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("OK");
	private JTextField TextField = new JTextField();
	private GUI g = this;
	
	
	public GUI() {
	    super("Ввод текста");
	    this.setBounds((Screen.d.width-255)/2, (Screen.d.height-110)/2, 255, 110);
		this.setLayout(null);
		button.addActionListener(new ButtonEventListener());
		TextField.setBounds(10, 10, 220, 20);
		button.setBounds(70, 40, 100, 20);
		this.add(TextField);
		this.add(button);
	}
	
	class ButtonEventListener extends JWindow implements ActionListener {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
				DrawObjectText.setTxt(TextField.getText().trim());
				g.setVisible(false);
		}
	}
	
	
}