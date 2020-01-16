package GUIs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import Canvas.MyCanvas;
import Saves.SaveToServ;
import Screens.Screen;

public class SaveServGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton SaveButton = new JButton("Сохранить");
	
	private MyCanvas c;
	private String login, password;
	private SaveServGui ssgui = this;

	
	
	public SaveServGui(MyCanvas c) {
		super("Сохранение на сервер");
		this.c = c;
		this.setBounds(Screen.d.width/6, Screen.d.height/6, 800, 600);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		SaveButton.addActionListener(new SaveButtonEventListener());
		this.add(SaveButton);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

class SaveButtonEventListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			SaveToServ s = new SaveToServ();
			setLogin("sahsa@mail.com");
			setPassword("12345");
			s.TrySave(c.getBufferedImage(c.getGraphics()), ssgui);
			System.out.println("It's Saved!");
		}
	}
	
}
