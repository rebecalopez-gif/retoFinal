package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Objectos;

import javax.swing.JList;

public class VentanaHabitacion extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Toolkit tk;
	private JLabel lblNewLabel;
	private CriaturasControlador cont;
	private JButton btnArmario;
	private JButton btnCama;
	private JList list;
<<<<<<< HEAD
	private JScrollPane scroll;
	private JLabel bichito;

	public VentanaHabitacion(JDialog ventanas,CriaturasControlador controlador, Creature criatura) {
		super(ventanas,true);
=======

	public VentanaHabitacion(VentanaPartidas ventanaPartidas,CriaturasControlador controlador, Creature criatura) {
		super(ventanaPartidas,true);
>>>>>>> branch 'main' of https://github.com/rebecalopez-gif/retoFinal
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHabitacion.class.getResource("/image/Monstruito adorable .png")));

		this.cont = controlador;
		Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog

		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1737, 1285);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		list = new JList();
<<<<<<< HEAD
		list.setFont(new Font("Monospaced", Font.BOLD, 14));
		list.setBackground(new Color(173, 216, 230));
		list.setForeground(new Color(0, 64, 128));
		list.setSelectionBackground(new Color(0, 128, 192));
		list.setSelectionForeground(Color.WHITE);
=======
		list.setBounds(73, 47, 342, 208);
		list.setVisible(false); //que no sea visible de primeras
		contentPanel.add(list);
>>>>>>> branch 'main' of https://github.com/rebecalopez-gif/retoFinal

<<<<<<< HEAD
		list.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 128, 192), 2),"OBJECTS"));
		
		//seleccionar objetos clickando con el raton
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2) {
		            Objectos seleccionado = (Objectos) list.getSelectedValue();

		            if(seleccionado != null) {
		                JOptionPane.showMessageDialog(null, 
		                    "You selected: " + seleccionado.toString());
		                //crear acciones
		            }
		        }
		    }
		});

		// Scroll
		scroll = new JScrollPane(list);
		scroll.setBounds(73, 47, 342, 208);
		scroll.setVisible(false);
		
		bichito = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource("/image/Monstruito adorable2 .png"));
		bichito.setIcon(icon);
		bichito.setBounds(399, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(bichito);
		
		contentPanel.add(scroll);

=======
>>>>>>> branch 'main' of https://github.com/rebecalopez-gif/retoFinal
		btnArmario = new JButton();
		btnArmario.setBounds(84, 285, 342, 427); // posición y tamaño del armario
		btnArmario.setOpaque(false);
		btnArmario.setContentAreaFilled(false);
		btnArmario.setBorderPainted(false);
		btnArmario.addActionListener(this);
		contentPanel.add(btnArmario);

		btnCama = new JButton();
		btnCama.setBounds(1058, 481, 400, 306); // AJUSTA ESTO A TU CAMA
		btnCama.setOpaque(false);
		btnCama.setContentAreaFilled(false);
		btnCama.setBorderPainted(false);
		btnCama.addActionListener(this);
		contentPanel.add(btnCama);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaHabitacion.class.getResource("/image/Habitación infantil .png")));
		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArmario) { //ver objetos
			List<Objectos> objetos = cont.verObjectos();
			list.setListData(objetos.toArray()); //rellenar la lista
			list.setVisible(true); //hacer visible la lista
<<<<<<< HEAD
			scroll.setVisible(true); //hacer visible el scroll
=======
>>>>>>> branch 'main' of https://github.com/rebecalopez-gif/retoFinal

		}else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Are you sure you want to leave the game?","Log out...",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				this.dispose(); //para cerrar la ventana actual
			}
		} 

	}
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'main' of https://github.com/rebecalopez-gif/retoFinal
