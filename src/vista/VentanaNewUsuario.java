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

public class VentanaNewUsuario extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;

	private JLabel lblContrasea;
	private JLabel lblUser;
	private JLabel lblRepeatPassword;
	private JTextField textField_User;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnNewButton;
	private JLabel lblYearOfBirth;
	private JTextField textField_Year;


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

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		boolean insertado;
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