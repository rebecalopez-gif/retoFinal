package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import java.awt.Color;
import javax.swing.BorderFactory;
import java.awt.Font;

public class VentanaGym extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblNewLabel, lblCriatura;
	private Toolkit tk;
	private JButton btnPuerta, bOTONCOCINA, btnHabitacion;
	private Creature criatura;

	private JLabel lblNewLabel_EXP;
	private JLabel lblNewLabel_EMOTI;
	private JLabel lblNewLabel_NumExp;
	private JLabel lblNewLabel_Back;
	private JLabel lblNewLabel_NumHunger;
	private JLabel lblNewLabel_NumHappy;
	private JLabel lblNewLabel_NumEnergy;


	public VentanaGym(JDialog ventanas, CriaturasControlador controlador, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/image/Monstruito adorable .png")));
		this.cont = controlador;
		this.criatura = criatura;

		// Pantalla completa para JDialog
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog

		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		bOTONCOCINA = new JButton("KITCHEN");
		bOTONCOCINA.setOpaque(true);
		bOTONCOCINA.setForeground(Color.WHITE);
		bOTONCOCINA.setFont(new Font("Monospaced", Font.BOLD, 20));
		bOTONCOCINA.setFocusPainted(false);
		bOTONCOCINA.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		bOTONCOCINA.setBackground(new Color(33, 150, 243));
		bOTONCOCINA.setBounds(1349, 805, 150, 60);
		contentPanel.add(bOTONCOCINA);
		bOTONCOCINA.addActionListener(this);

		btnHabitacion = new JButton("BEDROOM");
		btnHabitacion.setFont(new Font("Monospaced", Font.BOLD, 20));
		btnHabitacion.setForeground(Color.WHITE);
		btnHabitacion.setBackground(new Color(123, 31, 162)); // morado bonito
		btnHabitacion.setFocusPainted(false);
		btnHabitacion.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		btnHabitacion.setBounds(31, 805, 150, 60); // más grande
		btnHabitacion.setOpaque(true);
		contentPanel.add(btnHabitacion);
		btnHabitacion.addActionListener(this);

		lblCriatura = new JLabel("");
		lblCriatura.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));
		lblCriatura.setBounds(621, 479, 664, 400);
		contentPanel.add(lblCriatura);

		// Botón invisible sobre la puerta
		btnPuerta = new JButton();
		btnPuerta.setBounds(1179, 311, 240, 380); //  AJUSTA esto A la PUERTA
		btnPuerta.setOpaque(false);
		btnPuerta.setContentAreaFilled(false);
		btnPuerta.setBorderPainted(false);
		btnPuerta.addActionListener(this);
		contentPanel.add(btnPuerta);

		lblNewLabel_NumExp = new JLabel("");
		lblNewLabel_NumExp.setBounds(1374, 31, 54, 25);
		lblNewLabel_NumExp.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumExp.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
		contentPanel.add(lblNewLabel_NumExp);

		lblNewLabel_EXP = new JLabel("EXPERIENCE:");
		lblNewLabel_EXP.setBackground(new Color(240, 240, 240));
		lblNewLabel_EXP.setForeground(new Color(248, 52, 140));
		lblNewLabel_EXP.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_EXP.setBounds(1247, 26, 117, 34);
		contentPanel.add(lblNewLabel_EXP);

		lblNewLabel_EMOTI = new JLabel("<html>ENERGY:<br>HUNGER:<br>HAPPINESS:</html>");
		lblNewLabel_EMOTI.setBackground(new Color(255, 217, 236));
		lblNewLabel_EMOTI.setForeground(new Color(255, 89, 172));
		lblNewLabel_EMOTI.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_EMOTI.setBounds(1247, 57, 117, 79);
		contentPanel.add(lblNewLabel_EMOTI);

		lblNewLabel_NumHappy = new JLabel("");
		lblNewLabel_NumHappy.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumHappy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumHappy.setBounds(1374, 111, 54, 25);
		lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		contentPanel.add(lblNewLabel_NumHappy);

		lblNewLabel_NumHunger = new JLabel("");
		lblNewLabel_NumHunger.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumHunger.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumHunger.setBounds(1374, 85, 54, 25);
		lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
		contentPanel.add(lblNewLabel_NumHunger);

		lblNewLabel_NumEnergy = new JLabel("");
		lblNewLabel_NumEnergy.setForeground(new Color(128, 0, 64));
		lblNewLabel_NumEnergy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lblNewLabel_NumEnergy.setBounds(1374, 60, 54, 25);
		lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		contentPanel.add(lblNewLabel_NumEnergy);

		lblNewLabel_Back = new JLabel("");
		lblNewLabel_Back.setBounds(1241, 31, 271, 112);
		lblNewLabel_Back.setOpaque(true);
		lblNewLabel_Back.setBackground(new Color(255, 217, 236));
		contentPanel.add(lblNewLabel_Back);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaGym.class.getResource("/image/Gym.png")));
		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);


	}
	
	private void actualizarEmociones() { //metodo para actualizar el numero de actualizaciones 
	    lblNewLabel_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
	    lblNewLabel_NumHunger.setText(String.valueOf(criatura.getHunger()));
	    lblNewLabel_NumHappy.setText(String.valueOf(criatura.getHappiness()));
	    lblNewLabel_NumExp.setText(String.valueOf(criatura.getExperience()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPuerta) {
			if(cont.irDePaseo(criatura)) {
				JOptionPane.showMessageDialog(this, (String)"TU MASCOTA HA GANADO" ,"RESULTADO DEL PASEO",JOptionPane.INFORMATION_MESSAGE);
				lblCriatura.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));
				actualizarEmociones();//llamo al metodo pa actualizarlo
			} else {
				JOptionPane.showMessageDialog(this, (String)"TU MASCOTA TIENE DEMASIADA HAMBRE O ESTA DEMASIADO CANSADA COMO PARA SALIR","NO SE HA DADO EL PASEO",JOptionPane.INFORMATION_MESSAGE);
			}

		}

		if (e.getSource() == bOTONCOCINA) {
			this.dispose();
			VentanaCocina cocina = new VentanaCocina(this, cont, criatura);
			cocina.setVisible(true);
			
		}

		if (e.getSource() == btnHabitacion) {
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont, criatura);
			habitacion.setVisible(true);
		}
	}
}