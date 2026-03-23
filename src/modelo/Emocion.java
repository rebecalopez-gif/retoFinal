package modelo;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Emocion {

public String obtenerEmocion (Creature c) {  //Plantearla como interface 
	 int energy = c.getEnergy();
     int happiness = c.getHasppiness();
     int hunger = c.getHunger();

	if(happiness> 80) {
		return "MuyFeliz" ;
	}
	if (happiness>50) {
		return "Feliz";
	}
	if (happiness < 30 || hunger > 70) {
        return "Triste";
    }
    
    if (energy < 30) {
        return "Cansado";
    }
    
    return "Feliz";

}
 /*DESPUES EN LA VISTA HAY QUE PONER 
  * 
  * private Emocion emocionador = new Emocion();

  * String emocion = emocionador.Emocion(creature);
	Image img = load(emocion + ".png");
	label.setIcon(new ImageIcon(img));
	
	private Image load(String fileName) {
    return Toolkit.getDefaultToolkit().getImage(
        getClass().getResource("/image/" + fileName)
    );
}


*/


}
