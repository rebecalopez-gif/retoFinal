package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaPartidaNew extends JDialog implements ActionListener{

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private CriaturasControlador cont;
    private JLabel lblNewLabel;
    private JTextField nombreCriaturaField;
    private JButton btnNewButton;
    private JLabel lblNewLabel_1;
    private Toolkit tk; //es para usar toda la pantalla supuestamente

    public VentanaPartidaNew(VentanaNewUsuario ventanaNewUsuario, CriaturasControlador controlador, boolean b) {
    	super(ventanaNewUsuario,true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Monstruito adorable .png"));
        this.cont = controlador;

        // Pantalla completa para JDialog
        tk = Toolkit.getDefaultToolkit();
        int ancho = (int) tk.getScreenSize().getWidth();
        int alto = (int) tk.getScreenSize().getHeight();

        this.setSize(1349, 727);          
        this.setLocationRelativeTo(null);
        
        getContentPane().setLayout(null);
        contentPanel.setBounds(0, 0, 754, 494);
        contentPanel.setLayout(null);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Fondo horizontal cri.png"));
        lblNewLabel.setBounds(0, 0, 1539, 1048);
        contentPanel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Enter the creature’s name:");
        lblNewLabel_1.setForeground(new Color(33, 143, 197));
        lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblNewLabel_1.setBounds(459, 378, 397, 51);
        contentPanel.add(lblNewLabel_1);
        
        nombreCriaturaField = new JTextField();
        nombreCriaturaField.setForeground(new Color(33, 143, 197));
        nombreCriaturaField.setFont(new Font("Monospaced", Font.ITALIC, 14));
        nombreCriaturaField.setBounds(459, 427, 353, 57);
        contentPanel.add(nombreCriaturaField);
        nombreCriaturaField.setColumns(10);
        
        btnNewButton = new JButton("PLAY");
        btnNewButton.setBackground(new Color(70, 130, 180)); // azul bonito
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBounds(616, 605, 186, 63);
        getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(this);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {
			String nombre = nombreCriaturaField.getText();
			Creature criatura = new Creature(nombre);

			this.dispose();
			VentanaHabitacion hab = new VentanaHabitacion(cont,criatura); 
			hab.setVisible(true); 
		}
		
	}
}
