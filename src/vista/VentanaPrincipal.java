package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.*;

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
    private JLabel lblIMAGEN;
    private boolean nickVacio; 
    private JLabel lblMensaje;

    public VentanaPrincipal(CriaturasControlador controlador) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/image/Monstruito adorable .png")));
        this.cont = controlador;

        setExtendedState(JFrame.MAXIMIZED_BOTH);//PA QUE SEA PANTALLA COMPLETA 
        setBounds(100, 100, 1097, 759);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lblMensaje = new JLabel("");
        lblMensaje.setForeground(new Color(0, 128, 192));
        lblMensaje.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
        lblMensaje.setBounds(502, 519, 515, 45);
        contentPane.add(lblMensaje);

        btnNoTienesCuenta = new JButton("NO ACCOUNT?");
        btnNoTienesCuenta.setBackground(new Color(196, 236, 255));
        btnNoTienesCuenta.setForeground(new Color(0, 128, 192));
        btnNoTienesCuenta.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
        btnNoTienesCuenta.setBounds(835, 628, 206, 92);
        contentPane.add(btnNoTienesCuenta);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(193, 224, 255));
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 25));
        passwordField.setBounds(667, 417, 371, 45);
        contentPane.add(passwordField);

        textField = new JTextField();
        textField.setBackground(new Color(193, 224, 255));
        textField.setForeground(new Color(128, 0, 128));
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

        lblIMAGEN = new JLabel("");
        lblIMAGEN.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/image/Fondo horizontal cri.png")));
        lblIMAGEN.setBounds(-9, -40, 1539, 1048);
        contentPane.add(lblIMAGEN);
        
        btnInicioSesion.addActionListener(this);
        btnNoTienesCuenta.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nick = textField.getText();
        String pass = new String(passwordField.getPassword());
        UserGame user = new UserGame(nick, pass);

        if(e.getSource() == btnInicioSesion) {

            if(nick.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "¡YOU MUST FILL IN ALL FIELDS!",  
                    "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            if(cont.iniciarSesion(user)) {
                this.dispose();
                VentanaPartidas partida = new VentanaPartidas(this,cont,user);
                partida.setVisible(true);
            } else {
            	lblMensaje.setText("USER NOT FOUND");
            }

        } else if(e.getSource() == btnNoTienesCuenta) {
            this.dispose();
            VentanaNewUsuario venta = new VentanaNewUsuario(this, cont, true);
            venta.setVisible(true);
        }
    }
}