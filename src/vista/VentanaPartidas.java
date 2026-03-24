package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
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

import java.awt.Toolkit;


import javax.swing.JComboBox;

public class VentanaPartidas extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblGames, lblIMAGEN;
	private JComboBox<Creature> comboBox;
	private UserGame user;
	private JButton btnPlay, btnCreate, btnDelete;

	public VentanaPartidas(JFrame parent, CriaturasControlador controlador, UserGame user) {
		super(parent,true);
		this.cont=controlador;
		this.user=user;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;

		// Ajustar el JDialog al tamaño completo
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPartidas.class.getResource("/image/Monstruito adorable .png")));
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblGames = new JLabel("GAMES");
		lblGames.setForeground(new Color(0, 128, 192));
		lblGames.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		lblGames.setBounds(729, 278, 82, 24);
		contentPanel.add(lblGames);
		
		comboBox = new JComboBox<Creature>();
		comboBox.setForeground(new Color(0, 128, 192));
		comboBox.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 20));
		comboBox.setBounds(627, 324, 270, 35);
		contentPanel.add(comboBox);
		for (Creature c : cont.obtenerPartidas(user)) {
			comboBox.addItem(c);
		}
		
		btnPlay = new JButton("Play");
		
		btnPlay.setBounds(669, 559, 187, 55);
		btnPlay.setBackground(new Color(196, 236, 255));
		btnPlay.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
		btnPlay.setForeground(new Color(0, 128, 192));
		contentPanel.add(btnPlay);
		btnPlay.addActionListener(this);
		
		btnCreate = new JButton("Create new game");
		btnCreate.setBackground(new Color(196, 236, 255));
		btnCreate.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
		btnCreate.setForeground(new Color(0, 128, 192));
		btnCreate.setBounds(426, 559, 187, 55);
		contentPanel.add(btnCreate);
		btnCreate.addActionListener(this);

		btnDelete = new JButton("Delete game");
		btnDelete.setBackground(new Color(196, 236, 255));
		btnDelete.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setForeground(new Color(0, 128, 192));
		btnDelete.setBounds(920, 559, 187, 55);
		contentPanel.add(btnDelete);
		btnDelete.addActionListener(this);
		
		lblIMAGEN = new JLabel();
		lblIMAGEN.setIcon(new ImageIcon(VentanaNewUsuario.class.getResource("/image/Fondo horizontal cri.png")));
		lblIMAGEN.setBounds(0, 0, ancho, alto);
		contentPanel.add(lblIMAGEN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int opcion;
		
		if (e.getSource()==btnPlay) {
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont, (Creature) comboBox.getSelectedItem());
			habitacion.setVisible(true);
			this.dispose();
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
			VentanaPartidaNew partidaNew = new VentanaPartidaNew(this, cont,true);
			partidaNew.setVisible(true);
			
		}
		
	}
}
