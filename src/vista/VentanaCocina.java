package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Food;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class VentanaCocina extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton btnNevera;
	private ArrayList <Food> listaComida =new ArrayList<Food>();
	private JComboBox<Food> comboBoxComida;
	private CriaturasControlador controlador;
	private Creature criatura;
	private JButton BOTONROOM;
	private JButton BOTONGYM;
	private final JPanel contentPanel = new JPanel();
	private JButton botonroom;
	private JButton BOTONGIM;
	private JLabel lblNewLabel, bichito, accesoriolabel;


	public VentanaCocina( JDialog ventanas,CriaturasControlador cont, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCocina.class.getResource("/image/Monstruito adorable .png")));
		this.controlador=cont;
		this.criatura = criatura;
		listaComida=cont.listaComida();

		// PANEL PRINCIPAL
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
		
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//ACCESORIO
		accesoriolabel = new JLabel("");
		accesoriolabel.setBounds(825, 438, 200, 200);
		ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		//para escalar la imagen
		Image img = icono.getImage().getScaledInstance(accesoriolabel.getWidth(),accesoriolabel.getHeight(),Image.SCALE_SMOOTH);
		accesoriolabel.setIcon(new ImageIcon(img));
		contentPanel.add(accesoriolabel);
		
		//CRIATURA
		bichito = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource(this.criatura.setImage(this.criatura)));
		bichito.setIcon(icon);
		bichito.setBounds(700, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(bichito);

		// BOTÓN HABITACIÓN
		botonroom = new JButton("BEDROOM");
		botonroom.setFont(new Font("Monospaced", Font.BOLD, 20));
		botonroom.setForeground(Color.WHITE);
		botonroom.setBackground(new Color(123, 31, 162));
		botonroom.setBounds(1349, 805, 150, 60);
		contentPanel.add(botonroom);
		botonroom.addActionListener(this);

		// BOTÓN GYM
		BOTONGIM = new JButton("GYM");
		BOTONGIM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONGIM.setForeground(Color.WHITE);
		BOTONGIM.setBackground(new Color(33, 150, 243));
		BOTONGIM.setBounds(31, 805, 150, 60);
		contentPanel.add(BOTONGIM);
		BOTONGIM.addActionListener(this);

		// NEVERA
		btnNevera = new JButton();
		btnNevera.setBounds(47, 145, 396, 515);
		btnNevera.setOpaque(false);
		btnNevera.setContentAreaFilled(false);
		btnNevera.setBorderPainted(false);
		contentPanel.add(btnNevera);
		btnNevera.addActionListener(this);

		// COMBOBOX COMIDA
		comboBoxComida = new JComboBox<Food>();
		for (Food f : listaComida) comboBoxComida.addItem(f);
		//comboBoxComida.setSelectedIndex(-1);

		// FONDO (AL FINAL, PARA QUE QUEDE DETRÁS)
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaCocina.class.getResource("/image/Cocina.png")));
		lblNewLabel.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblNewLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();

		if(o==btnNevera) {
			JOptionPane.showMessageDialog(this, comboBoxComida, "Elegir comida", JOptionPane.QUESTION_MESSAGE);
			Food ob=(Food) comboBoxComida.getSelectedItem();


			if(controlador.darComida(criatura, ob)) {
				
			}
			
			bichito.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));

		}else if(e.getSource()==BOTONGIM) {//ir al gym
			this.dispose();
			VentanaGym gym = new VentanaGym(this, controlador,criatura);
			gym.setVisible(true);

		}else if(e.getSource()==botonroom) { //ir a la HABITACION
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, controlador,criatura);
			habitacion.setVisible(true);

		}

	}
}
