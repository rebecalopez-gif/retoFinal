package modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

import interfaces.Estados;

public class CriaturaMorada extends Creature implements Estados{

	@Override
    public Image getImagen(String emocion) {
        return new ImageIcon(getClass().getResource("/image/morada/" + emocion + ".png")).getImage();
    }
}
