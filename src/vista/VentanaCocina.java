package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Food;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;

/**
 * VentanaCocina representa la zona de cocina del juego, donde el usuario puede
 * alimentar a su criatura utilizando los distintos alimentos disponibles.
 *
 * Funcionalidades principales:
 * <ul>
 *   <li>Mostrar la criatura y su accesorio equipado.</li>
 *   <li>Permitir seleccionar un alimento desde un JComboBox.</li>
 *   <li>Aplicar los efectos del alimento sobre la criatura.</li>
 *   <li>Actualizar las estadísticas (energía, hambre, felicidad, experiencia).</li>
 *   <li>Navegar hacia el gimnasio o la habitación.</li>
 * </ul>
 *
 * Esta ventana se muestra como un JDialog modal y utiliza un layout absoluto
 * para posicionar todos los elementos gráficos.
 *
 * Interactúa con {@link CriaturasControlador} para obtener la lista de alimentos
 * y aplicar sus efectos sobre la criatura.
 *
 * @author Galder
 * @version 1.0, 16/04/2026
 */
public class VentanaCocina extends JDialog implements ActionListener{

	/** Botón invisible que representa la nevera donde se elige la comida. */
	private JButton btnNevera;
	/** Lista de alimentos disponibles para alimentar a la criatura. */
	private ArrayList<Food> listaComida = new ArrayList<>();
	/** ComboBox que muestra los alimentos disponibles. */
	private JComboBox<Food> comboBoxComida;
	/** Controlador que gestiona la lógica del juego. */
	private CriaturasControlador controlador;
	/** Criatura asociada a esta ventana. */
	private Creature criatura;
	/** Botón para ir al gimnasio. */
	private JButton BOTONGYM;
	/** Botón para volver a la habitación. */
	private JButton BOTONROOM;
	/** Panel principal que contiene todos los elementos gráficos. */
	private final JPanel contentPanel = new JPanel();
	/** Imagen principal de la criatura. */
	private JLabel bichito;
	/** Etiqueta donde se muestra el accesorio equipado. */
	private JLabel accesoriolabel;
	/** Etiquetas que muestran las estadísticas de la criatura. */
	private JLabel lblNewLabel_NumEnergy, lblNewLabel_NumHunger,
	               lblNewLabel_NumHappy, lblNewLabel_NumExp;
	
	private JLabel lblNewLabel_Back,lblNewLabel,lblNewLabel_EMOTI,lblNewLabel_EXP;

	/**
	 * Crea e inicializa la ventana de cocina. Carga la lista de alimentos disponibles,
	 * muestra la criatura, su accesorio y las estadísticas actuales.
	 *
	 * @param ventanas ventana padre desde la cual se abre este diálogo.
	 * @param cont controlador que gestiona la lógica del juego.
	 * @param criatura criatura del usuario que se mostrará e interactuará en esta zona.
	 */
	public VentanaCocina(JDialog ventanas,CriaturasControlador cont, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCocina.class.getResource("/image/Monstruito adorable .png")));
		this.controlador=cont;
		this.criatura = criatura;
		listaComida=cont.listaComida();
		

		// PANEL PRINCIPAL
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
		
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		accesoriolabel = new JLabel();
		accesoriolabel.setBounds(823, 436, 200, 200);
		contentPanel.add(accesoriolabel);
		
		//ACCESORIO
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(accesoriolabel.getWidth(), accesoriolabel.getHeight(), Image.SCALE_SMOOTH);
		    accesoriolabel.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {

		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    accesoriolabel.setBounds(801, 400, 250, iconoSG.getIconHeight());
		    accesoriolabel.setIcon(new ImageIcon(imgSG));

		} else {
		    
		    accesoriolabel.setIcon(null);
		}
		
		//CRIATURA
		bichito = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource(this.criatura.setImage(this.criatura)));
		bichito.setIcon(icon);
		bichito.setBounds(700, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(bichito);

		// BOTÓN HABITACIÓN
		BOTONROOM = new JButton("BEDROOM");
		BOTONROOM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONROOM.setForeground(Color.WHITE);
		BOTONROOM.setBackground(new Color(123, 31, 162));
		BOTONROOM.setBounds(1349, 805, 150, 60);
		contentPanel.add(BOTONROOM);
		BOTONROOM.addActionListener(this);

		// BOTÓN GYM
		BOTONGYM = new JButton("GYM");
		BOTONGYM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONGYM.setForeground(Color.WHITE);
		BOTONGYM.setBackground(new Color(33, 150, 243));
		BOTONGYM.setBounds(31, 805, 150, 60);
		contentPanel.add(BOTONGYM);
		BOTONGYM.addActionListener(this);

		// NEVERA
		btnNevera = new JButton();
		btnNevera.setBounds(47, 145, 396, 515);
		btnNevera.setOpaque(false);
		btnNevera.setContentAreaFilled(false);
		btnNevera.setBorderPainted(false);
		contentPanel.add(btnNevera);
		btnNevera.addActionListener(this);

		// COMBOBOX COMIDA
		comboBoxComida = new JComboBox<Food>();
		for (Food f : listaComida) comboBoxComida.addItem(f);

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
		//comboBoxComida.setSelectedIndex(-1);

		// FONDO (AL FINAL, PARA QUE QUEDE DETRÁS)
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaCocina.class.getResource("/image/Cocina.png")));
		lblNewLabel.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblNewLabel);
		
	}
	
	/**
	 * Actualiza en pantalla los valores actuales de energía, hambre, felicidad
	 * y experiencia de la criatura. Este método se llama después de alimentarla
	 * o realizar acciones que modifiquen sus estadísticas.
	 */
	private void actualizarEmociones() {
	    lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
	    lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
	    lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
	    lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
	}

	/**
	 * Gestiona las acciones de los botones de la cocina:
	 * <ul>
	 *   <li><b>Nevera:</b> abre un cuadro de diálogo con un JComboBox para elegir comida.
	 *       Si la criatura puede comer el alimento seleccionado, se actualizan sus estadísticas.</li>
	 *   <li><b>GYM:</b> abre la ventana del gimnasio.</li>
	 *   <li><b>BEDROOM:</b> vuelve a la habitación principal.</li>
	 * </ul>
	 *
	 * También actualiza la imagen de la criatura después de comer.
	 *
	 * @param e evento generado por la interacción del usuario.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();

		if(o==btnNevera) {
			JOptionPane.showMessageDialog(this, comboBoxComida, "Elegir comida", JOptionPane.QUESTION_MESSAGE);
			Food ob=(Food) comboBoxComida.getSelectedItem();
			
		
			if(controlador.darComida(criatura, ob)) {
				actualizarEmociones();
			}
			
			bichito.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));

		}else if(e.getSource()==BOTONGYM) {//ir al gym
			this.dispose();
			VentanaGym gym = new VentanaGym(this, controlador,criatura);
			gym.setVisible(true);

		}else if(e.getSource()==BOTONROOM) { //ir a la HABITACION
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, controlador,criatura);
			habitacion.setVisible(true);

		}

	}
}

