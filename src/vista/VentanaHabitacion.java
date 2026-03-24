package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import modelo.Creature;
import modelo.Objectos;

import javax.swing.JList;

/**
 * VentanaHabitacion representa la interfaz de la habitación del juego.
 * Permite al usuario interactuar con:
 * <ul>
 *   <li>El armario: ver los objetos disponibles</li>
 *   <li>La cama: salir del juego</li>
 *   <li>El bichito: representación de la criatura del usuario</li>
 * </ul>
 * 
 * Esta clase extiende JDialog y utiliza un layout nulo para posicionar los elementos.
 * 
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
	private Creature criatura;


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
		this.criatura = criatura;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHabitacion.class.getResource("/image/Monstruito adorable .png")));

		this.cont = controlador;
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
				if (e.getClickCount() == 2) {
					Objectos seleccionado = (Objectos) list.getSelectedValue();

					if(seleccionado != null) {
						JOptionPane.showMessageDialog(null, "You selected: " + seleccionado.toString());
						//crear acciones
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

		// Scroll
		scroll = new JScrollPane(list);
		scroll.setBounds(73, 47, 342, 208);
		scroll.setVisible(false);

		bichito = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource("/image/Feliz.png"));
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArmario) { //ver objetos
			List<Objectos> objetos = cont.verObjectos();
			list.setListData(objetos.toArray()); //rellenar la lista
			list.setVisible(true); //hacer visible la lista

			scroll.setVisible(true); //hacer visible el scroll

		}else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Are you sure you want to leave the game?","Log out...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				this.dispose(); //para cerrar la ventana actual
			}
		} else if(e.getSource()==bOTONGYM) {//ir al gym
			VentanaGym gym = new VentanaGym(this, cont,criatura);
			gym.setVisible(true);
			this.dispose();
		}else if(e.getSource()==BOTONCOCINA) { //ir a la cocina
			VentanaCocina cocina = new VentanaCocina(this, cont,criatura);
			cocina.setVisible(true);
			this.dispose();
		}

	}

}

