package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Food;

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

	public VentanaCocina( JDialog ventanas,CriaturasControlador cont, Creature criatura) {
		super(ventanas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCocina.class.getResource("/image/Monstruito adorable .png")));
		this.controlador=cont;
		this.creatureName = criatura;
		listaComida=cont.listaComida();
		setBounds(100, 100, 1308, 825);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
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


		}

	}
}
