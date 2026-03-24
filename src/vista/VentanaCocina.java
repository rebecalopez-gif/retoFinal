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

		// PANEL PRINCIPAL
		contentPanel.setLayout(null);
		contentPanel.setBounds(0, 0, 1308, 825);
		getContentPane().add(contentPanel);

		// BOTÓN HABITACIÓN
		botonroom = new JButton("BEDROOM");
		botonroom.setFont(new Font("Monospaced", Font.BOLD, 20));
		botonroom.setForeground(Color.WHITE);
		botonroom.setBackground(new Color(123, 31, 162));
		botonroom.setBounds(1100, 700, 150, 60);
		contentPanel.add(botonroom);
		botonroom.addActionListener(this);

		// BOTÓN GYM
		BOTONGIM = new JButton("GYM");
		BOTONGIM.setFont(new Font("Monospaced", Font.BOLD, 20));
		BOTONGIM.setForeground(Color.WHITE);
		BOTONGIM.setBackground(new Color(33, 150, 243));
		BOTONGIM.setBounds(31, 700, 150, 60);
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
		lblNewLabel.setBounds(0, 0, 1294, 808);
		contentPanel.add(lblNewLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();

		if(o==btnNevera) {
			JOptionPane.showMessageDialog(this, comboBoxComida, "Elegir comida", JOptionPane.QUESTION_MESSAGE);
			Food ob=(Food) comboBoxComida.getSelectedItem();


			if(controlador.darComida(creatureName, ob)) {

			}


		}else if(e.getSource()==BOTONGIM) {//ir al gym
			this.dispose();
			VentanaGym gym = new VentanaGym(this, controlador,creatureName);
			gym.setVisible(true);

		}else if(e.getSource()==botonroom) { //ir a la HABITACION
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, controlador,creatureName);
			habitacion.setVisible(true);

		}

	}
}
