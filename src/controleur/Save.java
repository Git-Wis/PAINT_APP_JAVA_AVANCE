/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;

//import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author MILIA Juvaldo Wisman
 */
public class Save {
    JPanel canvas;

    
    public Save(JPanel canvas){
        this.canvas = canvas;

    }
    
    public void WhereSave(){
        JFileChooser ChoixChemain = new JFileChooser();
        ChoixChemain.setDialogTitle("Enregistrer l'image");
        int userSelection = ChoixChemain.showSaveDialog(this.canvas);
        FileNameExtensionFilter extensions = new FileNameExtensionFilter("Fichiers PNG", "png");
        ChoixChemain.setFileFilter(extensions);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            
            File chemain = ChoixChemain.getSelectedFile();
            BufferedImage image = new BufferedImage(this.canvas.getWidth(), this.canvas.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = image.createGraphics();
            this.canvas.paint(g);
            g.dispose();
            
            SaveImage(image,chemain);
                
        }
    }
    
    BufferedImage SaveImage(BufferedImage img,File FileName)
	{
		
		try {
		ImageIO.write(img,"PNG",FileName);
		}
		catch(IOException e){
                    e.printStackTrace();
		}
		return img;
	}
}
