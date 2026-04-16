package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import controlador.CriaturasControlador;
import modelo.Accesory;
import modelo.Creature;
import modelo.Objetos;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 * VentanaHabitacion representa la interfaz de la habitación del juego.
 * Permite al usuario interactuar con:
 * <ul>
 *   <li>El armario: ver los objetos disponibles</li>
 *   <li>La cama: salir del juego</li>
 *   <li>El bichito: representación de la criatura del usuario</li>
 * </ul>
 * Esta clase extiende JDialog y utiliza un layout nulo para posicionar los elementos.
 * @author TuNombre //poner nuestro nombre
 * @version 1.0
 */
public class VentanaHabitacion extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Toolkit tk;
	private JLabel lblNewLabel;
	private CriaturasControlador cont;
	private JButton btnArmario;
	private JButton btnCama;
	private JList list;
	private JScrollPane scroll;
	private JLabel bichito;
	private JButton BOTONCOCINA;
	private JButton bOTONGYM;
	private Creature criatura;/////////
	private JLabel accesoriolabel;
	private String nombreAccesorio = "";
	private JTabbedPane tabbedPane;
	private JLabel lblNewLabel_1;
	private JLabel lblainx;
	private JLabel lblNewLabel_EXP;
	private JLabel lblNewLabel_EMOTI;
	private JLabel lblNewLabel_NumExp;
	private JLabel lblNewLabel_Back;
	private JLabel lblNewLabel_NumHunger;
	private JLabel lblNewLabel_NumHappy;
	private JLabel lblNewLabel_NumEnergy;

	/**
	 * Crea e inicializa la ventana de la habitación del juego.
	 * Configura los botones, el fondo, el scroll de objetos y el bichito.
	 * 
	 * @param ventanas el diálogo padre de esta ventana
	 * @param controlador el controlador de criaturas para acceder a la lógica del juego
	 * @param criatura la criatura del usuario asociada a esta habitación
	 */
	public VentanaHabitacion(JDialog ventanas,CriaturasControlador controlador, Creature criatura) {
		super(ventanas,true);
		this.cont = controlador;
		this.criatura = criatura;////


		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHabitacion.class.getResource("/image/Monstruito adorable .png")));
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		list = new JList();

		list.setFont(new Font("Monospaced", Font.BOLD, 14));
		list.setBackground(new Color(173, 216, 230));
		list.setForeground(new Color(0, 64, 128));
		list.setSelectionBackground(new Color(0, 128, 192));
		list.setSelectionForeground(Color.WHITE);
		list.setBounds(73, 47, 342, 208);
		list.setVisible(false); //que no sea visible de primeras
		contentPanel.add(list);

		list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 128, 192), 2),"OBJECTS"));

		//seleccionar objetos clickando con el raton
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { //clickar dos veces
					Accesory seleccionado = (Accesory) list.getSelectedValue();

					if(seleccionado != null) {
						String nombre = seleccionado.getObjectName();
						switch(nombre) {
						case "birthdayHat":
							// POSICIÓN DEL GORRO (NO LA CAMBIAMOS)
							accesoriolabel.setBounds(524, 438, 200, 200);
							ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
							//para escalar la imagen
							Image imgBH = icono.getImage().getScaledInstance(accesoriolabel.getWidth(),accesoriolabel.getHeight(),Image.SCALE_SMOOTH);
							if (!nombreAccesorio.equals(nombre)) { // SI SE CLICKA EN EL OBJETO Y NO ESTA EQUIPADO, SE EQUIPA
								accesoriolabel.setIcon(new ImageIcon(imgBH));
								nombreAccesorio = nombre;
								cont.quitarCualquierObjeto(criatura);
								cont.equiparObjeto(criatura, seleccionado);
							} else { //SI SE CLICKA EN EL OBJETO Y ESTÁ EQUIPADO, SE QUITA
								accesoriolabel.setIcon(null);
								nombreAccesorio = "";
								cont.quitarObjeto(criatura, seleccionado);
							}
							break;
						case "Sunglasses":
							ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
							Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
							// POSICIÓN MÁS ABAJO PARA LAS GAFAS
							accesoriolabel.setBounds(500, 400, 250, iconoSG.getIconHeight());
							if (!nombreAccesorio.equals(nombre)) { // SI SE CLICKA EN EL OBJETO Y NO ESTA EQUIPADO, SE EQUIPA
								accesoriolabel.setIcon(new ImageIcon(imgSG));
								nombreAccesorio = nombre;
								cont.quitarCualquierObjeto(criatura);
								cont.equiparObjeto(criatura, seleccionado);
							} else { //SI SE CLICKA EN EL OBJETO Y ESTÁ EQUIPADO, SE QUITA
								accesoriolabel.setIcon(null);
								nombreAccesorio = "";
								cont.quitarObjeto(criatura, seleccionado);
							}
							break;
						}
					}
				}
			}
		});

		// BOTÓN GYM
		bOTONGYM = new JButton("GYM");
		bOTONGYM.setFont(new Font("Monospaced", Font.BOLD, 20));
		bOTONGYM.setForeground(Color.WHITE);
		bOTONGYM.setBackground(new Color(33, 150, 243)); // azul bonito
		bOTONGYM.setFocusPainted(false);
		bOTONGYM.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		bOTONGYM.setBounds(1349, 805, 150, 60); // más grande
		bOTONGYM.setOpaque(true);
		contentPanel.add(bOTONGYM);
		bOTONGYM.addActionListener(this);

		// BOTÓN COCINA
		BOTONCOCINA = new JButton("KITCHEN");
		BOTONCOCINA.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONCOCINA.setForeground(Color.WHITE);
		BOTONCOCINA.setBackground(new Color(123, 31, 162)); // morado bonito
		BOTONCOCINA.setFocusPainted(false);
		BOTONCOCINA.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		BOTONCOCINA.setBounds(31, 805, 150, 60); // más grande
		BOTONCOCINA.setOpaque(true);
		contentPanel.add(BOTONCOCINA);
		BOTONCOCINA.addActionListener(this);

		// Crear SIEMPRE el JLabel antes de usarlo
		accesoriolabel = new JLabel();
		accesoriolabel.setBounds(524, 438, 200, 200);
		contentPanel.add(accesoriolabel);

		//ACCESORIO
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(accesoriolabel.getWidth(), accesoriolabel.getHeight(), Image.SCALE_SMOOTH);
		    accesoriolabel.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {

		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    accesoriolabel.setBounds(500, 400, 250, iconoSG.getIconHeight());
		    accesoriolabel.setIcon(new ImageIcon(imgSG));

		} else {
		    // No accesorio → lo dejas vacío
		    accesoriolabel.setIcon(null);
		}

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



		// Scroll
		scroll = new JScrollPane(list);
		scroll.setBounds(73, 47, 342, 208);
		scroll.setVisible(false);

		bichito = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource(this.criatura.setImage(this.criatura)));

		lblNewLabel_Back = new JLabel("");
		lblNewLabel_Back.setBounds(1241, 31, 271, 112);
		lblNewLabel_Back.setOpaque(true);
		lblNewLabel_Back.setBackground(new Color(255, 217, 236));
		contentPanel.add(lblNewLabel_Back);

		bichito.setIcon(icon);
		bichito.setBounds(399, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(bichito);

		contentPanel.add(scroll);

		btnArmario = new JButton();
		btnArmario.setBounds(84, 285, 342, 427); // posición y tamaño del armario
		btnArmario.setOpaque(false);
		btnArmario.setContentAreaFilled(false);
		btnArmario.setBorderPainted(false);
		btnArmario.addActionListener(this);
		contentPanel.add(btnArmario);

		btnCama = new JButton();
		btnCama.setBounds(1058, 481, 400, 306); // AJUSTA ESTO A TU CAMA
		btnCama.setOpaque(false);
		btnCama.setContentAreaFilled(false);
		btnCama.setBorderPainted(false);
		btnCama.addActionListener(this);
		contentPanel.add(btnCama);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setIcon(new ImageIcon(VentanaHabitacion.class.getResource("/image/Habitación infantil .png")));
		lblNewLabel.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblNewLabel);
	}

	/**
	 * Gestiona los eventos de los botones de la habitación.
	 * <ul>
	 *   <li>btnArmario: muestra los objetos del armario.</li>
	 *   <li>btnCama: pregunta si se desea salir del juego y cierra la ventana.</li>
	 * </ul>
	 * 
	 * @param e el evento de acción generado por los botones
	 */
	private void actualizarEmociones() { //metodo para actualizar el numero de actualizaciones 
		lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
		lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArmario) { //ver objetos
			if (!list.isVisible()) {
				List<Objetos> objetos = cont.verObjectos(criatura);
				list.setListData(objetos.toArray()); //rellenar la lista
				list.setVisible(true); //hacer visible la lista
				scroll.setVisible(true); //hacer visible el scroll
				actualizarEmociones();
			} else {
				list.setVisible(false);
				scroll.setVisible(false);
			}

		}else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Are you sure you want to leave the game?","Log out...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				cont.descansar(criatura);
				this.dispose(); // para cerrar la ventana actual
				System.exit(0); // Termina la ejecucion del programa
			}
		} else if(e.getSource()==bOTONGYM) {//ir al gym
			this.dispose();
			VentanaGym gym = new VentanaGym(this,cont,criatura);
			gym.setVisible(true);

		}else if(e.getSource()==BOTONCOCINA) { //ir a la cocina
			this.dispose();
			VentanaCocina cocina = new VentanaCocina(this,cont,criatura);
			cocina.setVisible(true);

		}

	}
}

