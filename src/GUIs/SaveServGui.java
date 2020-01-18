package GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Canvas.MyCanvas;
import Saves.SaveToServ;
import Screens.Screen;

import static javax.swing.JOptionPane.showMessageDialog;

public class SaveServGui extends JFrame{

	private static final long serialVersionUID = 1L;
	private JButton SaveButton = new JButton("Сохранить");
	private JTextField LoginField = new JTextField();
	private JTextField PasswordField = new JTextField();
	private MyCanvas c;
	private String login, password;
	private SaveServGui ssgui = this;
	
	
	
	public SaveServGui(MyCanvas c) {
		super("На сервер");
		this.c = c;
		this.setBounds((Screen.d.width-255)/2, (Screen.d.height-110)/2, 255, 110);
		this.setLayout(null);
		this.setVisible(true);
		SaveButton.addActionListener(new SaveButtonEventListener());
		LoginField.setBounds(10, 10, 100, 20);
		PasswordField.setBounds(LoginField.getBounds().width + 30, 10, 100, 20);
		SaveButton.setBounds(70, 40, 100, 20);
		this.add(LoginField);
		this.add(PasswordField);
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
			String l = LoginField.getText().trim();
			String p = PasswordField.getText().trim();
			if(!l.isEmpty() && !p.isEmpty())
			{
				setLogin(l);
				setPassword(p);
				try {
					showMessageDialog(null, s.TrySave(c.getBufferedImage(c.getGraphics()), ssgui));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
			else {
				showMessageDialog(null, "Не корректный логин и/или пароль.");
			}
			
		}
	}
	
}
