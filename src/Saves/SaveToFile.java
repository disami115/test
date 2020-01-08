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

    private final String[][] FILTERS = {{"docx", "Файлы Word (*.docx)"},
                                        {"pdf" , "Adobe Reader(*.pdf)"}};
    public SaveToFile() {
        super("Пример SaveToFile");
        btnOpenDir = new JButton("Открыть директорию");
        btnSaveFile = new JButton("Сохранить файл");

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
                fileChooser.setDialogTitle("Выбор директории");
                // Определение режима - только каталог
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(SaveToFile.this);
                // Если директория выбрана, покажем ее в сообщении
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
        // Локализация компонентов окна JFileChooser
        UIManager.put(
                 "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                 "FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                 "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                 "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                 "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                 "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                 "FileChooser.folderNameLabelText", "Путь директории");

        new SaveToFile();
    }
    
    public void TrySave(BufferedImage bf) {
    	fileChooser.setDialogTitle("Сохранение файла");
        // Определение режима - только файл
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int result = fileChooser.showSaveDialog(SaveToFile.this);
        // Если файл выбран, то представим его в сообщении
        
         
        if (result == JFileChooser.APPROVE_OPTION ) {
        	File file = null;
        	file = fileChooser.getSelectedFile();
        	try {  
                System.out.println(file.getAbsolutePath());
                ImageIO.write(bf, "jpg", file);
            }  
            catch (Exception e1) {
                System.out.println("Что-то пошло не так...");
            }
        }
    }
}
