package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import exception.UserExisteException;
import modelo.UserGame;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * VentanaNewUsuario permite registrar un nuevo usuario en el sistema.
 * 
 * Esta ventana se muestra como un JDialog modal y solicita:
 * - Nombre de usuario.
 * - Año de nacimiento.
 * - Contraseña.
 * - Repetición de contraseña.
 *
 * La ventana valida todos los campos antes de crear el usuario:
 * - El año debe tener 4 dígitos y ser numérico.
 * - El usuario no puede estar vacío.
 * - La contraseña no puede estar vacía.
 * - Ambas contraseñas deben coincidir.
 * - El usuario debe tener al menos 6 años.
 *
 * Si la creación es exitosa, se abre la ventana para crear una nueva partida.
 * Si el usuario ya existe, se lanza una excepción {@link UserExisteException}.
 *
 * Utiliza un {@link CriaturasControlador} para registrar el usuario en la base de datos.
 * 
 * @author Irene
 * @version 1.0, 16/04/2026
 */
public class VentanaNewUsuario extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	/** Panel principal del contenido del diálogo. */
	private final JPanel contentPanel = new JPanel();
	/** Controlador encargado de gestionar usuarios y lógica del juego. */
	private CriaturasControlador cont;
	/** Etiqueta para el campo de contraseña. */
	private JLabel lblContrasea;
	/** Etiqueta para el campo de usuario. */
	private JLabel lblUser;
	/** Etiqueta para repetir la contraseña. */
	private JLabel lblRepeatPassword;
	/** Campo de texto para introducir el nombre de usuario. */
	private JTextField textField_User;
	/** Campo de contraseña principal. */
	private JPasswordField passwordField;
	/** Campo para repetir la contraseña. */
	private JPasswordField passwordField_1;
	/** Botón para crear el usuario. */
	private JButton btnNewButton;
	/** Etiqueta para el año de nacimiento. */
	private JLabel lblYearOfBirth;
	/** Campo de texto para introducir el año de nacimiento. */
	private JTextField textField_Year;
	
	private JButton botonback;

	/**
	 * Crea la ventana de registro de un nuevo usuario. Configura todos los campos
	 * necesarios para introducir los datos del usuario y valida la información
	 * antes de permitir su creación.
	 *
	 * @param ventanaPrincipal ventana padre desde la cual se abre este diálogo.
	 * @param cont controlador que gestiona la creación de usuarios.
	 * @param b parámetro booleano no utilizado directamente, mantenido por compatibilidad.
	 */
	public VentanaNewUsuario(VentanaPrincipal ventanaPrincipal, CriaturasControlador cont, boolean b) {
		super(ventanaPrincipal, true);
		this.cont = cont;

		Toolkit tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;

		// Ajustar el JDialog al tamaño completo
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);

		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaNewUsuario.class.getResource("/image/Monstruito adorable .png")));

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		botonback = new JButton("Back");
		botonback.setForeground(new Color(0, 128, 192));
		botonback.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
		botonback.setBackground(new Color(196, 236, 255));
		botonback.setBounds(697, 758, 187, 55);
		contentPanel.add(botonback);
		botonback.addActionListener(this);
		
		textField_Year = new JTextField();
		textField_Year.setForeground(new Color(128, 0, 128));
		textField_Year.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
		textField_Year.setColumns(10);
		textField_Year.setBackground(new Color(193, 224, 255));
		textField_Year.setBounds(663, 325, 348, 39);
		contentPanel.add(textField_Year);
		
		lblYearOfBirth = new JLabel("YEAR OF BIRTH:");
		lblYearOfBirth.setForeground(new Color(0, 128, 192));
		lblYearOfBirth.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblYearOfBirth.setBounds(450, 326, 189, 45);
		contentPanel.add(lblYearOfBirth);

		btnNewButton = new JButton("CREATE USER");
		btnNewButton.setBackground(new Color(215, 255, 255));
		btnNewButton.setForeground(new Color(0, 128, 192));
		btnNewButton.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(637, 653, 309, 76);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(this);

		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBackground(new Color(193, 224, 255));
		passwordField_1.setEchoChar('*');
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passwordField_1.setBounds(664, 490, 348, 40);
		contentPanel.add(passwordField_1);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(193, 224, 255));
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		passwordField.setBounds(665, 407, 347, 41);
		contentPanel.add(passwordField);

		textField_User = new JTextField();
		textField_User.setBackground(new Color(193, 224, 255));
		textField_User.setForeground(new Color(128, 0, 128));
		textField_User.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
		textField_User.setBounds(664, 255, 348, 39);
		contentPanel.add(textField_User);
		textField_User.setColumns(10);

		lblRepeatPassword = new JLabel("REPEAT PASSWORD:");
		lblRepeatPassword.setForeground(new Color(0, 128, 192));
		lblRepeatPassword.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblRepeatPassword.setBounds(448, 489, 256, 45);
		contentPanel.add(lblRepeatPassword);


		lblContrasea = new JLabel("PASSWORD:");
		lblContrasea.setForeground(new Color(0, 128, 192));
		lblContrasea.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblContrasea.setBounds(448, 407, 164, 45);
		contentPanel.add(lblContrasea);

		lblUser = new JLabel("USER:");
		lblUser.setForeground(new Color(0, 128, 192));
		lblUser.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblUser.setBounds(452, 251, 96, 45);
		contentPanel.add(lblUser);


		JLabel lblIMAGEN = new JLabel();
		lblIMAGEN.setIcon(new ImageIcon(VentanaNewUsuario.class.getResource("/image/Fondo horizontal cri.png")));
		lblIMAGEN.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblIMAGEN);
	}

	/**
	 * Gestiona la acción del botón "CREATE USER". Realiza las siguientes validaciones:
	 * 
	 * - El año no puede estar vacío, debe ser numérico y tener 4 dígitos.
	 * - El nombre de usuario no puede estar vacío.
	 * - La contraseña no puede estar vacía.
	 * - Ambas contraseñas deben coincidir.
	 * 
	 * Si los datos son válidos, intenta registrar el usuario mediante el controlador.
	 * Si el usuario ya existe, se captura la excepción {@link UserExisteException}.
	 * 
	 * Si el registro es exitoso, se abre la ventana para crear una nueva partida.
	 *
	 * @param e evento generado por la interacción del usuario.
	 */
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		boolean insertado;
		
		if (e.getSource()==botonback) {
			this.dispose();
			VentanaPrincipal principal = new VentanaPrincipal(cont);
			principal.setVisible(true);
		}
		
        if(e.getSource()==btnNewButton) {
        	
        String year=textField_Year.getText(); 
        
        if (year.isEmpty()) { //compruebo que no este vacio el año
            JOptionPane.showMessageDialog(this, "The year cannot be empty.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (!year.matches("\\d+")) { //  "\\d" mira que sea de 0-9 y "+" que sea uno o mas numeros  
            JOptionPane.showMessageDialog(this, "The year must contain only numbers.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }else if (year.length() != 4) { //que tenga 4 cifras 
            JOptionPane.showMessageDialog(this, "The year must have exactly 4 digits.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //lo compruebo antes de pasarlo a int para evitar que explote con una excepcion
        int  birthYear = Integer.parseInt(year);
        String nom = textField_User.getText();
        String pass = new String(passwordField.getPassword());
        UserGame user = new UserGame(nom, pass,birthYear);
			
			if(nom.isEmpty()) {
				JOptionPane.showMessageDialog(this, "The user cannot be empty.",  "ERROR", JOptionPane.WARNING_MESSAGE);
				
			}else if(pass.isEmpty()) {
				JOptionPane.showMessageDialog(this, "The password cannot be empty.",  "ERROR", JOptionPane.WARNING_MESSAGE);
				
			}else if (!new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
				JOptionPane.showMessageDialog(this, "The passwords do not match.",  "ERROR", JOptionPane.ERROR_MESSAGE);
			
			} else {
				try {
					insertado = cont.introducirUser(user);

				    if (insertado) {
				        this.dispose();
				        VentanaPartidaNew venta = new VentanaPartidaNew(this, cont, true);
				        venta.setVisible(true);
	
				    }else {
				        JOptionPane.showMessageDialog(this,
				                "The user could not be created. They must be at least 6 years old.",
				                "Error",
				                JOptionPane.ERROR_MESSAGE);
				    }
				} catch(UserExisteException ex) {
					System.err.println(ex.getMessage()); //El mensaje en la consola (ROJO)
					JOptionPane.showMessageDialog(this,ex.getMessage(),"ERROR", JOptionPane.INFORMATION_MESSAGE);//COMPROBAR SI ESTO HACE FALTA 
				}
			}
		}
	}
}