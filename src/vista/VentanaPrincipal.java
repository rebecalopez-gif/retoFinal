package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import controlador.XMLGenerator;
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

/**
 * VentanaPrincipal representa la pantalla inicial del juego, donde el usuario
 * puede iniciar sesión, crear una nueva cuenta o generar el archivo XML de datos.
 * 
 * Esta ventana funciona como punto de entrada al sistema y gestiona:
 * - Validación de usuario y contraseña.
 * - Navegación hacia la ventana de creación de usuario.
 * - Navegación hacia la ventana de partidas si el inicio de sesión es correcto.
 * - Generación automática del archivo XML mediante XMLGenerator.
 * 
 * La interfaz está construida con Swing y utiliza un controlador de tipo
 * {@link CriaturasControlador} para gestionar la lógica del juego.
 * 
 * @author Irene
 * @version 1.0, 16/04/2026
 */
public class VentanaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    /** Panel principal del JFrame. */
    private JPanel contentPane;
    /** Controlador principal que gestiona la lógica del juego. */
    private CriaturasControlador cont;
    /** Campo de texto para introducir el nombre de usuario. */
    private JTextField textField;
    /** Campo de contraseña para introducir la clave del usuario. */
    private JPasswordField passwordField;
    /** Botón para acceder a la ventana de registro. */
    private JButton btnNoTienesCuenta;
    /** Botón para iniciar sesión. */
    private JButton btnInicioSesion;
    /** Etiqueta para el texto "PASSWORD". */
    private JLabel lblContrasea;
    /** Etiqueta para el texto "USER". */
    private JLabel lblUser;
    /** Etiqueta que contiene la imagen de fondo. */
    private JLabel lblIMAGEN;
    /** Etiqueta para mostrar mensajes de error o información. */
    private JLabel lblMensaje;
    /** Botón para generar el archivo XML automáticamente. */
    private JButton botonxml;

    /**
     * Crea la ventana principal del juego, inicializando todos los componentes
     * gráficos y configurando los listeners de los botones.
     *
     * @param controlador instancia de {@link CriaturasControlador} que gestiona
     *                    la lógica del juego y la autenticación de usuarios.
     */
    public VentanaPrincipal(CriaturasControlador controlador) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/image/Monstruito adorable .png")));
        this.cont = controlador;
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);//PA QUE SEA PANTALLA COMPLETA 
        setBounds(100, 100, 1097, 759);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        botonxml = new JButton("Generate XML");
        botonxml.setBackground(new Color(255, 128, 192));
        botonxml.setForeground(new Color(128, 0, 255));
        botonxml.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 14));
        botonxml.setBounds(422, 519, 152, 39);
        contentPane.add(botonxml);
        botonxml.addActionListener(this);
        
        lblMensaje = new JLabel("");
        lblMensaje.setForeground(new Color(0, 128, 192));
        lblMensaje.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
        lblMensaje.setBounds(595, 513, 515, 45);
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
        btnInicioSesion.setBounds(595, 629, 179, 90);
        contentPane.add(btnInicioSesion);

        lblIMAGEN = new JLabel("");
        lblIMAGEN.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/image/Fondo horizontal cri.png")));
        lblIMAGEN.setBounds(-9, -40, 1539, 1048);
        contentPane.add(lblIMAGEN);
        
        btnInicioSesion.addActionListener(this);
        btnNoTienesCuenta.addActionListener(this);
        
    }

    /**
     * Maneja los eventos de los botones de la interfaz:
     * 
     * - LOG IN: valida los campos, intenta iniciar sesión y abre la ventana de partidas.
     * - NO ACCOUNT?: abre la ventana de creación de nuevo usuario.
     * - Generate XML: genera automáticamente el archivo XML del juego.
     *
     * @param e el evento de acción generado por el usuario.
     */
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
            
        }else if(e.getSource() == botonxml) { //es el boton de generar el xml automatico
            XMLGenerator.generarXML(); // llama a la clase y al metodo
            JOptionPane.showMessageDialog(this, "XML generated successfully!");
        }
    }
}