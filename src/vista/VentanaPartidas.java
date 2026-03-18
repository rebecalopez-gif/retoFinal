package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.UserGame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class VentanaPartidas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblNewLabel;
	private JComboBox<Creature> comboBox;
	private UserGame user;
	private JButton btnPlay, btnCreate, btnDelete;

	public VentanaPartidas(JFrame parent, CriaturasControlador controlador, UserGame user) {
		super(parent,true);
		this.cont=controlador;
		this.user=user;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("GAMES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(187, 28, 82, 24);
		contentPanel.add(lblNewLabel);
		
		comboBox = new JComboBox<Creature>();
		comboBox.setBounds(132, 62, 176, 20);
		contentPanel.add(comboBox);
		for (Creature c : cont.obtenerPartidas(user)) {
			comboBox.addItem(c);
		}
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(185, 214, 84, 20);
		contentPanel.add(btnPlay);
		btnPlay.addActionListener(this);
		
		btnCreate = new JButton("Create new game");
		btnCreate.setBounds(32, 214, 115, 20);
		contentPanel.add(btnCreate);
		btnCreate.addActionListener(this);

		btnDelete = new JButton("Delete game");
		btnDelete.setBounds(305, 214, 100, 20);
		contentPanel.add(btnDelete);
		btnDelete.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcion;
		
		if (e.getSource()==btnPlay) {
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont, (Creature) comboBox.getSelectedItem());
			habitacion.setVisible(true);
		}
		
		if (e.getSource()==btnDelete) {
			opcion=JOptionPane.showConfirmDialog(this,(String)"Delete Game","Are you sure you want to delete this game?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				cont.eliminarPartida((Creature) comboBox.getSelectedItem());
				comboBox.removeAllItems();
				for (Creature c : cont.obtenerPartidas(user)) {
					comboBox.addItem(c);
				}
			}
		}
		
		if (e.getSource()==btnCreate) {
			this.dispose();
			VentanaPartidaNew partidaNew = new VentanaPartidaNew(this, cont);
			partidaNew.setVisible(true);
		}
		
	}
}
