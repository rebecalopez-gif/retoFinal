package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class VentanaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private CriaturasControlador cont;

    private JTextField textField;
    private JPasswordField passwordField;

    private JButton btnNoTienesCuenta;
    private JButton btnInicioSesion;

    private JLabel lblContrasea;
    private JLabel lblUser;
    private JLabel lblNewLabel;

    public VentanaPrincipal(CriaturasControlador controlador) {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ire22\\OneDrive\\Imágenes\\Monstruito adorable .png"));
        this.cont = controlador;

        setExtendedState(JFrame.MAXIMIZED_BOTH);//PA QUE SEA PANTALLA COMPLETA 
        setBounds(100, 100, 1097, 759);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnNoTienesCuenta = new JButton("NO ACCOUNT?");
        btnNoTienesCuenta.setBackground(new Color(196, 236, 255));
        btnNoTienesCuenta.setForeground(new Color(0, 128, 192));
        btnNoTienesCuenta.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
        btnNoTienesCuenta.setBounds(835, 628, 206, 92);
        contentPane.add(btnNoTienesCuenta);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        passwordField.setBounds(667, 417, 371, 45);
        contentPane.add(passwordField);

        textField = new JTextField();
        textField.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 18));
        textField.setBounds(667, 318, 371, 39);
        contentPane.add(textField);
        textField.setColumns(10);

        lblContrasea = new JLabel("PASSWORD:");
        lblContrasea.setForeground(new Color(0, 128, 192));
        lblContrasea.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
        lblContrasea.setBounds(449, 419, 164, 45);
        contentPane.add(lblContrasea);

        lblUser = new JLabel("USER:");
        lblUser.setForeground(new Color(0, 128, 192));
        lblUser.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
        lblUser.setBounds(455, 314, 96, 45);
        contentPane.add(lblUser);

        btnInicioSesion = new JButton("LOG IN ");
        btnInicioSesion.setBackground(new Color(196, 236, 255));
        btnInicioSesion.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
        btnInicioSesion.setForeground(new Color(0, 128, 192));
        btnInicioSesion.setBounds(422, 628, 179, 90);
        contentPane.add(btnInicioSesion);

        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ire22\\OneDrive\\Imágenes\\Fondo horizontal cri.png"));
        lblNewLabel.setBounds(-9, -40, 1539, 1048);
        contentPane.add(lblNewLabel);
        
        btnInicioSesion.addActionListener(this);
        btnNoTienesCuenta.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	boolean nickVacio=true;
    	String nick=lblUser.getText();
    	if(e.getSource()==btnInicioSesion) {
    	
    		if(lblUser.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "¡YOU MUST FILL IN ALL FIELDS!",  "WARNINIG", JOptionPane.WARNING_MESSAGE);
				nickVacio=false;
			}
    		if(nickVacio) {
    			//MIRAR LO DE LA BASES DE DATOS 
				this.dispose();
				VentanaLogin menu = new VentanaLogin(this,true); 
				menu.setVisible(true); 
			}
    	}else if(e.getSource()==btnNoTienesCuenta) {
    		
    	}
    }
}