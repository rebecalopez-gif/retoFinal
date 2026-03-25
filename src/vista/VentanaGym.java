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
	private JLabel lblNewLabel;
	private Toolkit tk;
	private JButton btnPuerta, bOTONCOCINA, btnHabitacion;
	private Creature criatura;

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
		bOTONCOCINA.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		bOTONCOCINA.setBackground(new Color(123, 31, 162));
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

		JLabel lblCriatura = new JLabel("");
		lblCriatura.setIcon(new ImageIcon(VentanaGym.class.getResource("/image/Feliz.png")));
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

		lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon(VentanaGym.class.getResource("/image/Gym.png")));

		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPuerta) {
			if(cont.irDePaseo(criatura)) {
				JOptionPane.showMessageDialog(this, (String)"YOUR PET HAS GAINED","WALK OUT RESULT",JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, (String)"YOUR PET IS TO HUNGRY OR TO TIRED TO GO OUTSIDE!","WALKING OUT CANCELLED",JOptionPane.INFORMATION_MESSAGE);
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