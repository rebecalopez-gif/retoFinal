package vista;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Image;

/**
 * VentanaGym representa la zona de gimnasio del juego, donde la criatura del usuario
 * puede realizar actividades como entrenar o salir a pasear.
 *
 * Funcionalidades principales:
 * <ul>
 *   <li>Mostrar la criatura y sus estadísticas actuales (energía, hambre, felicidad, experiencia).</li>
 *   <li>Permitir al usuario enviar a la criatura de paseo si cumple los requisitos.</li>
 *   <li>Desbloquear accesorios según la experiencia obtenida.</li>
 *   <li>Navegar hacia la cocina o la habitación.</li>
 * </ul>
 *
 * Esta ventana se muestra como un JDialog modal y utiliza un layout absoluto
 * para posicionar todos los elementos gráficos.
 *
 * Interactúa con {@link CriaturasControlador} para actualizar el estado de la criatura
 * y gestionar los desbloqueos de accesorios.
 *
 * @author Rebeca
 * @version 1.0
 */
public class VentanaGym extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	/** Panel principal que contiene todos los elementos gráficos. */
	private final JPanel contentPanel = new JPanel();

	/** Herramienta para ajustar el tamaño de la ventana */
	private Toolkit tk;
	/** Controlador que gestiona la lógica del juego y las criaturas. */
	private CriaturasControlador cont;
	/** Criatura asociada a esta ventana. */
	private Creature criatura;

	/** Botón invisible que representa la puerta para salir a pasear. */
	private JButton btn_Puerta;
	/** Botón para ir a la cocina. */
	private JButton btn_Cocina;
	/** Botón para ir a la habitación. */
	private JButton btn_Habitacion;
	/** Imagen principal de la criatura. */
	private JLabel lbl_Criatura;
	/** Etiqueta donde se muestra el accesorio equipado. */
	private JLabel lbl_Accesorio;
	/** Etiqueta que miestra el gimnasio */
	private JLabel lbl_Gym;
	/** Etiquetas que muestran las estadísticas de la criatura. */
	private JLabel lbl_EXP;
	private JLabel lbl_EMOTI;
	private JLabel lbl_NumExp;
	private JLabel lbl_FondoLista;
	private JLabel lbl_NumHunger;
	private JLabel lbl_NumHappy;
	private JLabel lbl_NumEnergy;


	public VentanaGym(JDialog ventanas, CriaturasControlador controlador, Creature criatura) {
		super(ventanas,true);
		this.cont = controlador;
		this.criatura = criatura;

		//PANTALLA COMPLETA PARA JDIALOG
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/image/Monstruito adorable .png")));
		tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		//BOTÓN COCINA
		btn_Cocina = new JButton("KITCHEN");
		btn_Cocina.setOpaque(true);
		btn_Cocina.setForeground(Color.WHITE);
		btn_Cocina.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Cocina.setFocusPainted(false);
		btn_Cocina.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		btn_Cocina.setBackground(new Color(33, 150, 243));
		btn_Cocina.setBounds(1349, 805, 150, 60);
		contentPanel.add(btn_Cocina);
		btn_Cocina.addActionListener(this);

		//BOTÓN HABITACIÓN
		btn_Habitacion = new JButton("BEDROOM");
		btn_Habitacion.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Habitacion.setForeground(Color.WHITE);
		btn_Habitacion.setBackground(new Color(123, 31, 162));
		btn_Habitacion.setFocusPainted(false);
		btn_Habitacion.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		btn_Habitacion.setBounds(31, 805, 150, 60);
		btn_Habitacion.setOpaque(true);
		contentPanel.add(btn_Habitacion);
		btn_Habitacion.addActionListener(this);
		
		//ACCESORIO (CREAR SIEMPRE EL JLABEL ANTES DE USARLO)
		lbl_Accesorio = new JLabel();
		lbl_Accesorio.setBounds(746, 410, 200, 200);
		contentPanel.add(lbl_Accesorio);
		
		//SE EQUIPA EL ACCESORIO QUE TENGA LA CRIATURA
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(lbl_Accesorio.getWidth(), lbl_Accesorio.getHeight(), Image.SCALE_SMOOTH);
		    lbl_Accesorio.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {
		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    lbl_Accesorio.setBounds(722, 373, 250, iconoSG.getIconHeight());
		    lbl_Accesorio.setIcon(new ImageIcon(imgSG));

		} else {
		    lbl_Accesorio.setIcon(null);
		}

		//CRIATURA
		lbl_Criatura = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura)));
		lbl_Criatura.setIcon(icon);
		lbl_Criatura.setBounds(621, 479, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(lbl_Criatura);

		//BOTON PUERTA
		btn_Puerta = new JButton();
		btn_Puerta.setBounds(1179, 311, 240, 380);
		btn_Puerta.setOpaque(false);
		btn_Puerta.setContentAreaFilled(false);
		btn_Puerta.setBorderPainted(false);
		btn_Puerta.addActionListener(this);
		contentPanel.add(btn_Puerta);

		//TABLA DE ESTADISTICAS DE LA CRIATURA
		lbl_NumExp = new JLabel("");
		lbl_NumExp.setBounds(1374, 31, 54, 25);
		lbl_NumExp.setForeground(new Color(128, 0, 64));
		lbl_NumExp.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumExp.setText(String.valueOf(criatura.getExperience()));
		contentPanel.add(lbl_NumExp);

		lbl_EXP = new JLabel("EXPERIENCE:");
		lbl_EXP.setBackground(new Color(240, 240, 240));
		lbl_EXP.setForeground(new Color(248, 52, 140));
		lbl_EXP.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_EXP.setBounds(1247, 26, 117, 34);
		contentPanel.add(lbl_EXP);

		lbl_EMOTI = new JLabel("<html>ENERGY:<br>HUNGER:<br>HAPPINESS:</html>");
		lbl_EMOTI.setBackground(new Color(255, 217, 236));
		lbl_EMOTI.setForeground(new Color(255, 89, 172));
		lbl_EMOTI.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_EMOTI.setBounds(1247, 57, 117, 79);
		contentPanel.add(lbl_EMOTI);

		lbl_NumHappy = new JLabel("");
		lbl_NumHappy.setForeground(new Color(128, 0, 64));
		lbl_NumHappy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumHappy.setBounds(1374, 111, 54, 25);
		lbl_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		contentPanel.add(lbl_NumHappy);

		lbl_NumHunger = new JLabel("");
		lbl_NumHunger.setForeground(new Color(128, 0, 64));
		lbl_NumHunger.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumHunger.setBounds(1374, 85, 54, 25);
		lbl_NumHunger.setText(String.valueOf(criatura.getHunger()));
		contentPanel.add(lbl_NumHunger);

		lbl_NumEnergy = new JLabel("");
		lbl_NumEnergy.setForeground(new Color(128, 0, 64));
		lbl_NumEnergy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumEnergy.setBounds(1374, 60, 54, 25);
		lbl_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		contentPanel.add(lbl_NumEnergy);

		lbl_FondoLista = new JLabel("");
		lbl_FondoLista.setBounds(1241, 31, 271, 112);
		lbl_FondoLista.setOpaque(true);
		lbl_FondoLista.setBackground(new Color(255, 217, 236));
		contentPanel.add(lbl_FondoLista);

		//GYM (AL FINAL PARA QUE APAREZCA DE FONDO)
		lbl_Gym = new JLabel("");
		lbl_Gym.setIcon(new ImageIcon(VentanaGym.class.getResource("/image/Gym.png")));
		lbl_Gym.setBounds(0, 0, ancho, alto);
		contentPanel.add(lbl_Gym);


	}
	
	/**
	 * Actualiza en pantalla los valores actuales de energía, hambre, felicidad
	 * y experiencia de la criatura. Este método se llama después de realizar
	 * acciones que modifican sus estadísticas.
	 */
  
	private void actualizarEmociones() { //METODO PARA ACTUALIZAR EL NÚMERO DE ACTUALIZACIONES 
	    lbl_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
	    lbl_NumHunger.setText(String.valueOf(criatura.getHunger()));
	    lbl_NumHappy.setText(String.valueOf(criatura.getHappiness()));
	    lbl_NumExp.setText(String.valueOf(criatura.getExperience()));
	}

	/**
	 * Gestiona las acciones de los botones del gimnasio:
	 * <ul>
	 *   <li><b>Puerta:</b> intenta enviar a la criatura de paseo. Si no cumple los
	 *       requisitos mínimos de energía o hambre, se muestra un aviso.</li>
	 *   <li><b>KITCHEN:</b> abre la ventana de la cocina.</li>
	 *   <li><b>BEDROOM:</b> vuelve a la habitación principal.</li>
	 * </ul>
	 *
	 * También gestiona el desbloqueo de accesorios según la experiencia obtenida
	 * durante el paseo.
	 *
	 * @param e evento generado por la interacción del usuario.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btn_Puerta) { //SALIR DE PASEO
			boolean desbloqueo = false;
			
			//QUE NO ENTRE SI NO CUMPLE LOS REQUISITOS
			if (criatura.getEnergy()<20){
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE IS TOO TIRED TO GO OUT.","It hasn’t gone for its walk.",JOptionPane.INFORMATION_MESSAGE);
			
			}else if(criatura.getHunger()<20) {
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE IS TOO HUNGRY TO GO OUT.","It hasn’t gone for its walk.",JOptionPane.INFORMATION_MESSAGE);
			
			}else if(cont.irDePaseo(criatura)) {
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE HAS GAINED NEW EXPERIENCE\n BUT LOOK AT ITS NEEDS!" ,"Walk results",JOptionPane.INFORMATION_MESSAGE);
				lbl_Criatura.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));
				actualizarEmociones();//llamo al metodo pa actualizarlo
			} 
			
			//DESBLOQUEO DE ACCESORIOS
			if (criatura.getExperience()>0 && !cont.comprobarBH(criatura)) {
				cont.desbloqueoBH(criatura);
				desbloqueo = true;
			} else if (criatura.getExperience()>99 && !cont.comprobarSG(criatura)) {
				cont.desbloqueoSG(criatura);
				desbloqueo = true;
			}
			
			//MENSAJE DE DESBLOQUE DE ACCESORIO
			if (desbloqueo) {
				JOptionPane.showMessageDialog(this, (String)"CONGRATULATIONS!\n YOU HAVE UNLOCKED A NEW ACCESORY FOR YOUR CREATURE" ,"EVENT!",JOptionPane.INFORMATION_MESSAGE);
				desbloqueo = false;
			}
		}
		
		if (e.getSource() == btn_Cocina) {//IR A LA COCINA
			this.dispose();
			VentanaCocina cocina = new VentanaCocina(this, cont, criatura);
			cocina.setVisible(true);
		}

		if (e.getSource() == btn_Habitacion) {//IR A LA HABITACIÓN
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont, criatura);
			habitacion.setVisible(true);
		}
	}
}

