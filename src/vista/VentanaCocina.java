package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private Creature creatureName;
	private JButton BOTONROOM;
	private JButton BOTONGYM;
	private final JPanel contentPanel = new JPanel();
	private JButton botonroom;
	private JButton BOTONGIM;
	private JLabel lblNewLabel;


	public VentanaCocina( JDialog ventanas,CriaturasControlador cont, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCocina.class.getResource("/image/Monstruito adorable .png")));
		this.controlador=cont;
		this.creatureName = criatura;
		listaComida=cont.listaComida();
		setBounds(100, 100, 1308, 825);
		getContentPane().setLayout(null);
		
		// BOTÓN HABIATCION
		BOTONROOM = new JButton("ROOM");
		BOTONROOM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONROOM.setForeground(Color.WHITE);
		BOTONROOM.setBackground(new Color(33, 150, 243)); // azul bonito
		BOTONROOM.setFocusPainted(false);
		BOTONROOM.setBorder(BorderFactory.createLineBorder(new Color(25, 118, 210), 3));
		BOTONROOM.setBounds(1349, 805, 150, 60); // más grande
		BOTONROOM.setOpaque(true);
		contentPanel.add(BOTONROOM);
		BOTONROOM.addActionListener(this);

		// BOTÓN gym
		BOTONGYM = new JButton("GYM");
		BOTONGYM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONGYM.setForeground(Color.WHITE);
		BOTONGYM.setBackground(new Color(123, 31, 162)); // morado bonito
		BOTONGYM.setFocusPainted(false);
		BOTONGYM.setBorder(BorderFactory.createLineBorder(new Color(81, 45, 168), 3));
		BOTONGYM.setBounds(31, 805, 150, 60); // más grande
		BOTONGYM.setOpaque(true);
		contentPanel.add(BOTONGYM);
		BOTONGYM.addActionListener(this);
		
		botonroom = new JButton("ROOM");
		botonroom.setBounds(1170, 723, 84, 20);
		getContentPane().add(botonroom);
		
		BOTONGIM = new JButton("GYM");
		BOTONGIM.setBounds(37, 700, 84, 20);
		getContentPane().add(BOTONGIM);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaCocina.class.getResource("/image/Cocina.png")));
		lblNewLabel.setBounds(0, 0, 1294, 808);
		getContentPane().add(lblNewLabel);

		btnNevera = new JButton("New button");
		btnNevera.setBounds(47, 145, 396, 515);
		getContentPane().add(btnNevera);
		btnNevera.setOpaque(false);
		btnNevera.setContentAreaFilled(false);
		btnNevera.setBorderPainted(false);

		comboBoxComida = new JComboBox<Food>();

		btnNevera.addActionListener(this);

		for(int i=0;i<listaComida.size();i++) {
			comboBoxComida.addItem(listaComida.get(i));
		}

		comboBoxComida.setSelectedIndex(-1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();

		if(o==btnNevera) {
			JOptionPane.showMessageDialog(this, comboBoxComida, "Elegir comida", JOptionPane.QUESTION_MESSAGE);
			Food ob=(Food) comboBoxComida.getSelectedItem();

			Creature c= new Creature(1,"Razer","Alissa",0,50,50,50);
			if(controlador.darComida(c, ob)) {

			}


		}else if(e.getSource()==BOTONGYM) {//ir al gym
			VentanaGym gym = new VentanaGym(this, controlador,creatureName);
			gym.setVisible(true);
			this.dispose();
		}else if(e.getSource()==BOTONROOM) { //ir a la HABITACION
			VentanaHabitacion habitacion = new VentanaHabitacion(this, controlador,creatureName);
			habitacion.setVisible(true);
			this.dispose();
		}

	}
}
