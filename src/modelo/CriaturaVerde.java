package modelo;

import java.awt.Image;

import javax.swing.ImageIcon;

import interfaces.Estados;

public class CriaturaVerde extends Creature implements Estados{
	public CriaturaVerde(int cod, String user, String name, int exp, int en, int hun, int hap) {
        super(cod, user, name, exp, en, hun, hap);
    }
	
	@Override
    public Image getImagen(String emocion) {
        return new ImageIcon(getClass().getResource("/image/verde/" + emocion + ".png")).getImage();
    }
}
