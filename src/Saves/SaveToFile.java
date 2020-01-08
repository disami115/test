package Saves;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveToFile extends JFrame
{
    private  JButton  btnSaveFile   = null;
    private  JButton  btnOpenDir    = null;
    private  JButton  btnFileFilter = null;
    private static  Graphics img = null;
    private  JFileChooser fileChooser = null;

    private final String[][] FILTERS = {{"docx", "����� Word (*.docx)"},
                                        {"pdf" , "Adobe Reader(*.pdf)"}};
    public SaveToFile() {
        super("������ SaveToFile");
        btnOpenDir = new JButton("������� ����������");
        btnSaveFile = new JButton("��������� ����");

        fileChooser = new JFileChooser();
        addFileChooserListeners();

        JPanel contents = new JPanel();
        contents.add(btnOpenDir   );
        contents.add(btnSaveFile  );
        setContentPane(contents);
    	}
    
    private void addFileChooserListeners() {
    	
    	btnOpenDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("����� ����������");
                // ����������� ������ - ������ �������
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(SaveToFile.this);
                // ���� ���������� �������, ������� �� � ���������
                if (result == JFileChooser.APPROVE_OPTION )
                          JOptionPane.showMessageDialog(SaveToFile.this, fileChooser.getSelectedFile());
            }
        });
        
        btnSaveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                   
            }
        });
    	
    }
        
    public static void openDialog(Graphics g)
    {
    	img = g;
        // ����������� ����������� ���� JFileChooser
        UIManager.put(
                 "FileChooser.saveButtonText", "���������");
        UIManager.put(
                 "FileChooser.cancelButtonText", "������");
        UIManager.put(
                 "FileChooser.fileNameLabelText", "������������ �����");
        UIManager.put(
                 "FileChooser.filesOfTypeLabelText", "���� ������");
        UIManager.put(
                 "FileChooser.lookInLabelText", "����������");
        UIManager.put(
                 "FileChooser.saveInLabelText", "��������� � ����������");
        UIManager.put(
                 "FileChooser.folderNameLabelText", "���� ����������");

        new SaveToFile();
    }
    
    public void TrySave(BufferedImage bf) {
    	fileChooser.setDialogTitle("���������� �����");
        // ����������� ������ - ������ ����
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int result = fileChooser.showSaveDialog(SaveToFile.this);
        // ���� ���� ������, �� ���������� ��� � ���������
        
         
        if (result == JFileChooser.APPROVE_OPTION ) {
        	File file = null;
        	file = fileChooser.getSelectedFile();
        	try {  
                System.out.println(file.getAbsolutePath());
                ImageIO.write(bf, "jpg", file);
            }  
            catch (Exception e1) {
                System.out.println("���-�� ����� �� ���...");
            }
        }
    }
}
