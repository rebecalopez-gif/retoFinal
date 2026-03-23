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

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaPartidaNew extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblIMAGEN;
	private JTextField nombreCriaturaField;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private Toolkit tk; //es para usar toda la pantalla supuestamente
	private Creature criatura;

	private JDialog ventanas;
	private VentanaNewUsuario ventanaNewUsuario;


    public VentanaPartidaNew(JDialog parent, CriaturasControlador cont,boolean b) {
    	super(parent,true);
    	  this.cont = cont;

		Toolkit tk = Toolkit.getDefaultToolkit();
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
		nombreCriaturaField.setFont(new Font("Monospaced", Font.ITALIC, 21));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean valido=false;
		String nombre;
	    if(e.getSource() == btnNewButton) {

	        valido = true;
	        nombre = nombreCriaturaField.getText();
			this.dispose();
			VentanaHabitacion hab = new VentanaHabitacion(ventanaNewUsuario,cont,criatura); 
			hab.setVisible(true); 
		

	        if(nombre.trim().isEmpty()) { //limpiar espacios
	            JOptionPane.showMessageDialog(this, "Enter a name");
	            valido = false;
	        }

	        if(valido) {
	            Creature criatura = new Creature(nombre);
	            this.dispose();
	            VentanaHabitacion hab2 = new VentanaHabitacion(this, cont, criatura); 
	            hab.setVisible(true);
	        }
	    }
	}
}