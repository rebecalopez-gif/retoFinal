package vista;

import java.awt.BorderLayout;
import java.awt.Color;
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
import controlador.CriaturasControlador;
import modelo.Accesory;
import modelo.Creature;
import modelo.Objetos;
import javax.swing.JList;

/**
 * VentanaHabitacion representa la habitación principal donde el jugador puede
 * interactuar con su criatura y acceder a distintas áreas del juego.
 *
 * Funcionalidades principales:
 * <ul>
 *   <li>Visualizar la criatura y sus estadísticas (energía, hambre, felicidad, experiencia).</li>
 *   <li>Acceder al armario para ver y equipar accesorios.</li>
 *   <li>Ir al gimnasio para entrenar a la criatura.</li>
 *   <li>Ir a la cocina para alimentarla.</li>
 *   <li>Usar la cama para salir del juego.</li>
 * </ul>
 *
 * La ventana se muestra como un JDialog modal y utiliza un layout absoluto
 * para posicionar todos los elementos gráficos.
 *
 * Esta clase interactúa con {@link CriaturasControlador} para obtener y modificar
 * el estado de la criatura.
 *
 * @author Rebeca
 * @version 1.0, 16/04/2026
 */
public class VentanaHabitacion extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	/** Panel principal que contiene todos los elementos gráficos. */
	private final JPanel contentPanel = new JPanel();

	/** Herramienta para ajustar el tamaño de la ventana*/
	private Toolkit tk;
	/** Controlador que gestiona la lógica del juego y las criaturas. */
	private CriaturasControlador cont;
	/** Nombre del accesorio actualmente equipado. */
	private String nombreAccesorio = "";
	/** Criatura asociada a esta habitación. */
	private Creature criatura;

	/** Lista de objetos disponibles en el armario. */
	private JList list;
	/** Scroll asociado a la lista de objetos. */
	private JScrollPane scroll;
	/** Botón para abrir el armario. */
	private JButton btn_Armario;
	/** Botón para salir del juego (cama). */
	private JButton btn_Cama;
	/** Botón para ir a la cocina. */
	private JButton btn_Cocina;
	/** Botón para ir al gimnasio. */
	private JButton btn_Gym;
	/** Imagen principal de la criatura. */
	private JLabel lbl_Criatura;
	/** Etiqueta donde se muestra el accesorio equipado. */
	private JLabel lbl_Accesorio;
	/** Imagen de la habitación */
	private JLabel lbl_Habitacion;
	/** Etiquetas que muestran las estadísticas de la criatura. */
	private JLabel lbl_EXP;
	private JLabel lbl_EMOTI;
	private JLabel lbl_NumExp;
	private JLabel lbl_FondoLista;
	private JLabel lbl_NumHunger;
	private JLabel lbl_NumHappy;
	private JLabel lbl_NumEnergy;

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
		this.criatura = criatura;

		//PANTALLA COMPLETA PARA JDIALOG
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHabitacion.class.getResource("/image/Monstruito adorable .png")));
		tk = Toolkit.getDefaultToolkit(); //PARA HACER LA PANTALLA COMPLETA EN DIALOG
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		//LISTA DE ACCESORIOS
		list = new JList();
		list.setFont(new Font("Monospaced", Font.BOLD, 14));
		list.setBackground(new Color(173, 216, 230));
		list.setForeground(new Color(0, 64, 128));
		list.setSelectionBackground(new Color(0, 128, 192));
		list.setSelectionForeground(Color.WHITE);
		list.setBounds(73, 47, 342, 208);
		list.setVisible(false); //QUE LA LISTA NO SEA VISIBLE DE PRIMERAS
		contentPanel.add(list);
		list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 128, 192), 2),"OBJECTS"));

		//SELECCIONAR OBJETOS CLICKANDO CON EL RATON
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { //SI SE CLICKA DOS VECES
					Accesory seleccionado = (Accesory) list.getSelectedValue();

					if(seleccionado != null) {
						String nombre = seleccionado.getObjectName();
						switch(nombre) {
							case "birthdayHat":
								ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
								//POSICIÓN DEL GORRO (NO LA CAMBIAMOS)
								lbl_Accesorio.setBounds(524, 438, 200, 200);
								//PARA ESCALAR LA IMAGEN
								Image imgBH = icono.getImage().getScaledInstance(lbl_Accesorio.getWidth(),lbl_Accesorio.getHeight(),Image.SCALE_SMOOTH);
								if (!nombreAccesorio.equals(nombre)) { //SI SE CLICKA EN EL OBJETO Y NO ESTA EQUIPADO, SE EQUIPA
									lbl_Accesorio.setIcon(new ImageIcon(imgBH));
									nombreAccesorio = nombre;
									cont.quitarCualquierObjeto(criatura);
									cont.equiparObjeto(criatura, seleccionado);
									cont.efectoAccesorio(criatura, seleccionado);
								} else { //SI SE CLICKA EN EL OBJETO Y ESTÁ EQUIPADO, SE QUITA
									lbl_Accesorio.setIcon(null);
									nombreAccesorio = "";
									cont.quitarObjeto(criatura, seleccionado);
								}
								break;
							case "Sunglasses":
								ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
								Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
								// POSICIÓN MÁS ABAJO PARA LAS GAFAS
								lbl_Accesorio.setBounds(500, 400, 250, iconoSG.getIconHeight());
								if (!nombreAccesorio.equals(nombre)) { //SI SE CLICKA EN EL OBJETO Y NO ESTA EQUIPADO, SE EQUIPA
									lbl_Accesorio.setIcon(new ImageIcon(imgSG));
									nombreAccesorio = nombre;
									cont.quitarCualquierObjeto(criatura);
									cont.equiparObjeto(criatura, seleccionado);
									cont.efectoAccesorio(criatura, seleccionado);
								} else { //SI SE CLICKA EN EL OBJETO Y ESTÁ EQUIPADO, SE QUITA
									lbl_Accesorio.setIcon(null);
									nombreAccesorio = "";
									cont.quitarObjeto(criatura, seleccionado);
								}
								break;
						}
					}
				}
			}
		});

		//BOTÓN GYM
		btn_Gym = new JButton("GYM");
		btn_Gym.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Gym.setForeground(Color.WHITE);
		btn_Gym.setBackground(new Color(33, 150, 243)); //
		btn_Gym.setFocusPainted(false);
		btn_Gym.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		btn_Gym.setBounds(1349, 805, 150, 60);
		btn_Gym.setOpaque(true);
		contentPanel.add(btn_Gym);
		btn_Gym.addActionListener(this);

		//BOTÓN COCINA
		btn_Cocina = new JButton("KITCHEN");
		btn_Cocina.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Cocina.setForeground(Color.WHITE);
		btn_Cocina.setBackground(new Color(123, 31, 162));
		btn_Cocina.setFocusPainted(false);
		btn_Cocina.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		btn_Cocina.setBounds(31, 805, 150, 60);
		btn_Cocina.setOpaque(true);
		contentPanel.add(btn_Cocina);
		btn_Cocina.addActionListener(this);

		//ACCESORIO (CREAR SIEMPRE EL JLABEL ANTES DE USARLO)
		lbl_Accesorio = new JLabel();
		lbl_Accesorio.setBounds(524, 438, 200, 200);
		contentPanel.add(lbl_Accesorio);
		
		//SE EQUIPA EL ACCESORIO QUE TENGA LA CRIATURA
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(lbl_Accesorio.getWidth(), lbl_Accesorio.getHeight(), Image.SCALE_SMOOTH);
		    lbl_Accesorio.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {
		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    lbl_Accesorio.setBounds(500, 400, 250, iconoSG.getIconHeight());
		    lbl_Accesorio.setIcon(new ImageIcon(imgSG));

		} else {
		    lbl_Accesorio.setIcon(null);
		}

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
		
		//SCROLL
		scroll = new JScrollPane(list);
		scroll.setBounds(73, 47, 342, 208);
		scroll.setVisible(false);
		contentPanel.add(scroll);

		//CRIATURA
		lbl_Criatura = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource(this.criatura.setImage(this.criatura)));
		lbl_Criatura.setIcon(icon);
		lbl_Criatura.setBounds(399, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(lbl_Criatura);

		//BOTÓN ARMARIO
		btn_Armario = new JButton();
		btn_Armario.setBounds(84, 285, 342, 427); //POSICION Y TAMAÑO DEL ARMARIO
		btn_Armario.setOpaque(false);
		btn_Armario.setContentAreaFilled(false);
		btn_Armario.setBorderPainted(false);
		btn_Armario.addActionListener(this);
		contentPanel.add(btn_Armario);

		//BOTÓN CAMA
		btn_Cama = new JButton();
		btn_Cama.setBounds(1058, 481, 400, 306); //POSICION Y TAMAÑO DE LA CAMA
		btn_Cama.setOpaque(false);
		btn_Cama.setContentAreaFilled(false);
		btn_Cama.setBorderPainted(false);
		btn_Cama.addActionListener(this);
		contentPanel.add(btn_Cama);

		//HABITACIÓN (AL FINAL PARA QUE APAREZCA DE FONDO)
		lbl_Habitacion = new JLabel("");
		lbl_Habitacion.setBackground(new Color(240, 240, 240));
		lbl_Habitacion.setIcon(new ImageIcon(VentanaHabitacion.class.getResource("/image/Habitación infantil .png")));
		lbl_Habitacion.setBounds(0, 0, ancho, alto);
		contentPanel.add(lbl_Habitacion);
	}
	
	private void actualizarEmociones() { //METODO PARA ACTUALIZAR EL NÚMERO DE ACTUALIZACIONES 
		lbl_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		lbl_NumHunger.setText(String.valueOf(criatura.getHunger()));
		lbl_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		lbl_NumExp.setText(String.valueOf(criatura.getExperience()));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btn_Armario) {//VER OBJETOS
			if (!list.isVisible()) {
				List<Objetos> objetos = cont.verObjectos(criatura);
				list.setListData(objetos.toArray()); //RELLENAR LA LISTA
				list.setVisible(true); //HACER VISIBLE LA LISTA
				scroll.setVisible(true); //HACER VISIBLE EL SCROLL
				actualizarEmociones();
			} else {
				list.setVisible(false);
				scroll.setVisible(false);
			}

		}else if(e.getSource() == btn_Cama) { //DORMIR, ES DECIR SALIR DEL JUEGO
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Are you sure you want to leave the game?","Log out...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				cont.descansar(criatura);
				this.dispose(); //PARA CERRAR LA VENTANA ACTUAL
				System.exit(0); //TERMINA LA EJECUCIÓN DEL PROGRAMA
			}
		} else if(e.getSource()==btn_Gym) {//IR AL GYM
			this.dispose();
			VentanaGym gym = new VentanaGym(this,cont,criatura);
			gym.setVisible(true);

		}else if(e.getSource()==btn_Cocina) {//IR A LA COCINA
			this.dispose();
			VentanaCocina cocina = new VentanaCocina(this,cont,criatura);
			cocina.setVisible(true);

		}

	}
}
