package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;

public class VentanaHabitacion extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Toolkit tk;
	private JLabel lblNewLabel;
	private CriaturasControlador cont;
	private JButton btnArmario;
	private JButton btnCama;

	public VentanaHabitacion(CriaturasControlador controlador, Creature criatura) {

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Monstruito adorable .png"));
		this.cont = controlador;

		// Pantalla completa para JDialog
		Toolkit tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;

		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnArmario = new JButton();
		btnArmario.setBounds(72, 282, 368, 434); // posición y tamaño del armario
		btnArmario.setOpaque(false);
		btnArmario.setContentAreaFilled(false);
		btnArmario.setBorderPainted(false);
		btnArmario.addActionListener(this);
		contentPanel.add(btnArmario);

		btnCama = new JButton();
		btnCama.setBounds(1067, 475, 390, 320); // AJUSTA ESTO A TU CAMA
		btnCama.setOpaque(false);
		btnCama.setContentAreaFilled(false);
		btnCama.setBorderPainted(false);
		btnCama.addActionListener(this);
		contentPanel.add(btnCama);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Habitación infantil .png"));
		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);


		List<Mueble> muebles= cont.verMuebles(); //PARA QUE SE VEAN LOS MUEBLES
		for(Mueble mueble:muebles) {
			datosMueble.addItem(mueble);
		}


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArmario) { //ver objetos
			Creature criatura=new Creature();

		}else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Log out...","Are you sure you want to leave the game?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				this.dispose(); //para cerrar la ventana actual
			}
		}

	}
}
