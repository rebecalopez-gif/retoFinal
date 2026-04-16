package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
	/** Controlador que gestiona la lógica del juego y las criaturas. */
	private CriaturasControlador cont;
	/** Criatura asociada a esta ventana. */
	private Creature criatura;
	/** Imagen principal de la criatura. */
	private JLabel lblCriatura;
	/** Etiqueta donde se muestra el accesorio equipado. */
	private JLabel accesoriolabel;
	/** Botón para ir a la cocina. */
	private JButton bOTONCOCINA;
	/** Botón para volver a la habitación. */
	private JButton btnHabitacion;
	/** Botón invisible que representa la puerta para salir a pasear. */
	private JButton btnPuerta;
	/** Etiquetas que muestran las estadísticas de la criatura. */
	private JLabel lblNewLabel_NumEnergy, lblNewLabel_NumHunger,
	               lblNewLabel_NumHappy, lblNewLabel_NumExp;

	private JLabel lblNewLabel_Back,lblNewLabel,lblNewLabel_EMOTI,lblNewLabel_EXP;
	/**
	 * Crea e inicializa la ventana del gimnasio. Muestra la criatura, sus estadísticas,
	 * los botones de navegación y gestiona la visualización del accesorio equipado.
	 *
	 * @param ventanas ventana padre desde la cual se abre este diálogo.
	 * @param controlador controlador que gestiona la lógica del juego.
	 * @param criatura criatura del usuario que se mostrará e interactuará en esta zona.
	 */
	public VentanaGym(JDialog ventanas, CriaturasControlador controlador, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/image/Monstruito adorable .png")));
		this.cont = controlador;
		this.criatura = criatura;

		// Pantalla completa para JDialog
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog

		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		bOTONCOCINA = new JButton("KITCHEN");
		bOTONCOCINA.setOpaque(true);
		bOTONCOCINA.setForeground(Color.WHITE);
		bOTONCOCINA.setFont(new Font("Monospaced", Font.BOLD, 20));
		bOTONCOCINA.setFocusPainted(false);
		bOTONCOCINA.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		bOTONCOCINA.setBackground(new Color(33, 150, 243));
		bOTONCOCINA.setBounds(1349, 805, 150, 60);
		contentPanel.add(bOTONCOCINA);
		bOTONCOCINA.addActionListener(this);

		btnHabitacion = new JButton("BEDROOM");
		btnHabitacion.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnHabitacion.setForeground(Color.WHITE);
		btnHabitacion.setBackground(new Color(123, 31, 162)); // morado bonito
		btnHabitacion.setFocusPainted(false);
		btnHabitacion.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		btnHabitacion.setBounds(31, 805, 150, 60); // más grande
		btnHabitacion.setOpaque(true);
		contentPanel.add(btnHabitacion);
		btnHabitacion.addActionListener(this);
		
		accesoriolabel = new JLabel();
		accesoriolabel.setBounds(746, 410, 200, 200);
		contentPanel.add(accesoriolabel);
		
		//ACCESORIO
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(accesoriolabel.getWidth(), accesoriolabel.getHeight(), Image.SCALE_SMOOTH);
		    accesoriolabel.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {
		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    accesoriolabel.setBounds(722, 373, 250, iconoSG.getIconHeight());
		    accesoriolabel.setIcon(new ImageIcon(imgSG));

		} else {
		    // No accesorio → lo dejas vacío
		    accesoriolabel.setIcon(null);
		}

		lblCriatura = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura)));
		lblCriatura.setIcon(icon);
		lblCriatura.setBounds(621, 479, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(lblCriatura);

		// Botón invisible sobre la puerta
		btnPuerta = new JButton();
		btnPuerta.setBounds(1179, 311, 240, 380); //  AJUSTA esto A la PUERTA
		btnPuerta.setOpaque(false);
		btnPuerta.setContentAreaFilled(false);
		btnPuerta.setBorderPainted(false);
		btnPuerta.addActionListener(this);
		contentPanel.add(btnPuerta);

		lblNewLabel_NumExp = new JLabel("");
		lblNewLabel_NumExp.setBounds(1374, 31, 54, 25);
		lblNewLabel_NumExp.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumExp.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
		contentPanel.add(lblNewLabel_NumExp);

		lblNewLabel_EXP = new JLabel("EXPERIENCE:");
		lblNewLabel_EXP.setBackground(new Color(240, 240, 240));
		lblNewLabel_EXP.setForeground(new Color(248, 52, 140));
		lblNewLabel_EXP.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_EXP.setBounds(1247, 26, 117, 34);
		contentPanel.add(lblNewLabel_EXP);

		lblNewLabel_EMOTI = new JLabel("<html>ENERGY:<br>HUNGER:<br>HAPPINESS:</html>");
		lblNewLabel_EMOTI.setBackground(new Color(255, 217, 236));
		lblNewLabel_EMOTI.setForeground(new Color(255, 89, 172));
		lblNewLabel_EMOTI.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_EMOTI.setBounds(1247, 57, 117, 79);
		contentPanel.add(lblNewLabel_EMOTI);

		lblNewLabel_NumHappy = new JLabel("");
		lblNewLabel_NumHappy.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumHappy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumHappy.setBounds(1374, 111, 54, 25);
		lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		contentPanel.add(lblNewLabel_NumHappy);

		lblNewLabel_NumHunger = new JLabel("");
		lblNewLabel_NumHunger.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumHunger.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumHunger.setBounds(1374, 85, 54, 25);
		lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
		contentPanel.add(lblNewLabel_NumHunger);

		lblNewLabel_NumEnergy = new JLabel("");
		lblNewLabel_NumEnergy.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumEnergy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumEnergy.setBounds(1374, 60, 54, 25);
		lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		contentPanel.add(lblNewLabel_NumEnergy);

		lblNewLabel_Back = new JLabel("");
		lblNewLabel_Back.setBounds(1241, 31, 271, 112);
		lblNewLabel_Back.setOpaque(true);
		lblNewLabel_Back.setBackground(new Color(255, 217, 236));
		contentPanel.add(lblNewLabel_Back);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaGym.class.getResource("/image/Gym.png")));
		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);


	}
	
	/**
	 * Actualiza en pantalla los valores actuales de energía, hambre, felicidad
	 * y experiencia de la criatura. Este método se llama después de realizar
	 * acciones que modifican sus estadísticas.
	 */
	private void actualizarEmociones() { //metodo para actualizar el numero de actualizaciones 
	    lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
	    lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
	    lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
	    lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
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
		if (e.getSource() == btnPuerta) {
			boolean desbloqueo = false;
			
			//pa que no me entre si no tiene las necesidades 
			if (criatura.getEnergy()<20){
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE IS TOO TIRED TO GO OUT.","It hasn’t gone for its walk.",JOptionPane.INFORMATION_MESSAGE);
			
			}else if(criatura.getHunger()<20) {
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE IS TOO HUNGRY TO GO OUT.","It hasn’t gone for its walk.",JOptionPane.INFORMATION_MESSAGE);
			
			}else if(cont.irDePaseo(criatura)) {
				JOptionPane.showMessageDialog(this, (String)"YOUR CREATURE HAS GAINED NEW EXPERIENCE\n BUT LOOK AT ITS NEEDS!" ,"Walk results",JOptionPane.INFORMATION_MESSAGE);
				lblCriatura.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));
				actualizarEmociones();//llamo al metodo pa actualizarlo
			} 
			
			if (criatura.getExperience()>0 && !cont.comprobarBH(criatura)) {
				cont.desbloqueoBH(criatura);
				desbloqueo = true;
			} else if (criatura.getExperience()>99 && !cont.comprobarSG(criatura)) {
				cont.desbloqueoSG(criatura);
				desbloqueo = true;
			}
			
			if (desbloqueo) {
				JOptionPane.showMessageDialog(this, (String)"CONGRATULATIONS!\n YOU HAVE UNLOCKED A NEW ACCESORY FOR YOUR CREATURE" ,"EVENT!",JOptionPane.INFORMATION_MESSAGE);
				desbloqueo = false;
			}
		}
		
		if (e.getSource() == bOTONCOCINA) {
			this.dispose();
			VentanaCocina cocina = new VentanaCocina(this, cont, criatura);
			cocina.setVisible(true);
		}

		if (e.getSource() == btnHabitacion) {
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont, criatura);
			habitacion.setVisible(true);
		}
	}
}

