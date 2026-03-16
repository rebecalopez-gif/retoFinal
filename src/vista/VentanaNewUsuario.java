package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
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


	public VentanaNewUsuario(VentanaPrincipal ventanaPrincipal, CriaturasControlador cont, boolean b) {
		super(ventanaPrincipal, true);
		this.cont = cont;

		Toolkit tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;

		// Ajustar el JDialog al tamaño completo
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);


		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\ire22\\OneDrive\\Imágenes\\Monstruito adorable .png"));

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnNewButton = new JButton("CREATE USER");
		btnNewButton.setBackground(new Color(215, 255, 255));
		btnNewButton.setForeground(new Color(0, 128, 192));
		btnNewButton.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setBounds(637, 653, 309, 76);
		contentPanel.add(btnNewButton);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBackground(new Color(193, 224, 255));
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_1.setEchoChar('*');
		passwordField_1.setBounds(641, 508, 362, 45);
		contentPanel.add(passwordField_1);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(193, 224, 255));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setEchoChar('*');
		passwordField.setBounds(641, 406, 362, 45);
		contentPanel.add(passwordField);

		textField_User = new JTextField();
		textField_User.setBackground(new Color(193, 224, 255));
		textField_User.setBounds(616, 307, 387, 41);
		contentPanel.add(textField_User);
		textField_User.setColumns(10);

		lblRepeatPassword = new JLabel("REPEAT PASSWORD:");
		lblRepeatPassword.setForeground(new Color(0, 128, 192));
		lblRepeatPassword.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblRepeatPassword.setBounds(446, 508, 256, 45);
		contentPanel.add(lblRepeatPassword);


		lblContrasea = new JLabel("PASSWORD:");
		lblContrasea.setForeground(new Color(0, 128, 192));
		lblContrasea.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblContrasea.setBounds(449, 419, 164, 45);
		contentPanel.add(lblContrasea);

		lblUser = new JLabel("USER:");
		lblUser.setForeground(new Color(0, 128, 192));
		lblUser.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblUser.setBounds(455, 314, 96, 45);
		contentPanel.add(lblUser);


		JLabel lblIMAGEN = new JLabel();
		lblIMAGEN.setIcon(new ImageIcon("C:\\Users\\ire22\\OneDrive\\Imágenes\\Fondo horizontal cri.png"));
		lblIMAGEN.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblIMAGEN);
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if(e.getSource()==btnNewButton) {
			if (!new String(passwordField.getPassword()).equals(new String(passwordField_1.getPassword()))) {
				JOptionPane.showMessageDialog(this, "The passwords do not match.",  "ERROR", JOptionPane.INFORMATION_MESSAGE);
			}else {
				this.dispose();
				VentanaPartidaNew venta= new VentanaPartidaNew(this,cont,true);
				venta.setVisible(true);
			}

		}
	}
}