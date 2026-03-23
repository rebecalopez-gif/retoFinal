package vista;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import modelo.Creature;

public class Emocion {

public String Emocion (Creature c) {  //Plantearla como interface 
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
  * String emocion = emocionador.getEmocion(creature);
	Image img = load(emocion + ".png");
	label.setIcon(new ImageIcon(img));
*/


}
