/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package continuas;

import java.awt.Image;
import java.awt.Label;
import javax.swing.ImageIcon;

/**
 *
 * @author layo
 */
public class NewClass {

    ImageIcon ResizeImage(String imagePath, byte[] pic, Label lbl) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        Image img = myImage.getImage();

        Image img2 = img.getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
}
