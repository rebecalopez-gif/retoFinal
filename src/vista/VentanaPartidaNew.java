package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.UserGame;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * VentanaPartidaNew permite al usuario crear una nueva partida asignando un nombre
 * a la criatura inicial. Esta ventana se muestra como un JDialog modal para evitar
 * que el usuario interactúe con otras pantallas hasta completar el proceso.
 *
 * Funcionalidades principales:
 * - Introducir el nombre de la nueva criatura.
 * - Validar que el nombre no esté vacío.
 * - Crear la criatura y guardarla mediante el controlador.
 * - Abrir la ventana de habitación asociada a la nueva partida.
 *
 * Utiliza un {@link CriaturasControlador} para gestionar la creación y registro
 * de la criatura en la base de datos.
 * 
 * @author Rebeca
 * @version 1.0, 16/04/2026
 */
public class VentanaPartidaNew extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	/** Panel principal del contenido del diálogo. */
	private final JPanel contentPanel = new JPanel();
	/** Controlador encargado de gestionar la lógica del juego y las criaturas. */
	private CriaturasControlador cont;
	/** Etiqueta que contiene la imagen de fondo. */
	private JLabel lblIMAGEN;
	/** Campo de texto donde el usuario introduce el nombre de la criatura. */
	private JTextField nombreCriaturaField;
	/** Botón para confirmar la creación de la partida y comenzar a jugar. */
	private JButton btnNewButton;
	/** Etiqueta que indica al usuario que debe introducir un nombre. */
	private JLabel lblNewLabel_1;
	/** Herramienta para obtener el tamaño de pantalla y ajustar la ventana. */
	private Toolkit tk;
	/** Instancia de la criatura creada. */
	private Creature criatura;
	/** Usuario actualmente autenticado. */
	private UserGame usuarioActual;
	/** Referencia a ventanas relacionadas (si se requiere). */
	private JDialog ventanas;
	/** Referencia a la ventana de creación de usuario (si se requiere). */
	private VentanaNewUsuario ventanaNewUsuario;
	private UserGame user;

	/**
	 * Crea la ventana para iniciar una nueva partida. Permite al usuario introducir
	 * el nombre de la criatura y valida que el campo no esté vacío antes de crearla.
	 *
	 * @param parent ventana padre desde la cual se abre este diálogo.
	 * @param cont controlador que gestiona la creación de criaturas y la lógica del juego.
	 * @param b parámetro booleano no utilizado directamente, pero mantenido por compatibilidad.
	 */
	public VentanaPartidaNew(JDialog parent, CriaturasControlador cont,boolean b, UserGame user) {
		super(parent,true);
		this.cont = cont;
		this.user=user;

		tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;

		// Ajustar el JDialog al tamaño completo
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPartidaNew.class.getResource("/image/Monstruito adorable .png")));

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnNewButton = new JButton("PLAY");
		btnNewButton.setBackground(new Color(70, 130, 180)); // azul bonito
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 22));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(657, 612, 186, 63);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(this);


		nombreCriaturaField = new JTextField();
		nombreCriaturaField.setForeground(new Color(33, 143, 197));
		nombreCriaturaField.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 21));
		nombreCriaturaField.setBounds(554, 434, 353, 57);
		nombreCriaturaField.setColumns(10);
		contentPanel.add(nombreCriaturaField);

		lblNewLabel_1 = new JLabel("Enter the creature’s name:");
		lblNewLabel_1.setForeground(new Color(33, 143, 197));
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 16));
		lblNewLabel_1.setBounds(554, 375, 397, 51);
		contentPanel.add(lblNewLabel_1);

		lblIMAGEN = new JLabel();
		lblIMAGEN.setBounds(0, 0, this.getWidth(), this.getHeight());
		lblIMAGEN.setIcon(new ImageIcon(VentanaPartidaNew.class.getResource("/image/Fondo horizontal cri.png")));

		contentPanel.add(lblIMAGEN); 

	}

	/**
	 * Gestiona las acciones del botón "PLAY". Valida que el nombre introducido no esté vacío
	 * y, si es válido, crea una nueva criatura asociada al usuario actual, la registra en la
	 * base de datos y abre la ventana de habitación correspondiente.
	 *
	 * @param e evento generado por la interacción del usuario.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean valido=false;
		String nombre;
		if(e.getSource() == btnNewButton) {


			valido = true;
			nombre = nombreCriaturaField.getText();

			if(nombre.trim().isEmpty()) { //limpiar espacios
				JOptionPane.showMessageDialog(this, "Enter a name");
				valido = false;
			}
			if(cont.nombreCriatura(nombre).equalsIgnoreCase(nombre)) {
				JOptionPane.showMessageDialog(this,(String)"This name is already in use.","ERROR",JOptionPane.ERROR_MESSAGE,null);
				valido=false;
				
			}else  if(valido) {
				Creature criatura = new Creature(user.getUserName(), nombre); //que sea este user que ha iniciado sesion
				cont.insertarCriatura(criatura); //para guardarlo en la BBDD
				this.dispose();
				criatura= cont.verCriatura(nombre);
				VentanaHabitacion hab = new VentanaHabitacion(this, cont, criatura); 
				hab.setVisible(true);
			}
		}
	}
}
