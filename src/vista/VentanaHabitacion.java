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

    public VentanaHabitacion(VentanaPartidas ventanaPartidas,CriaturasControlador controlador, Creature criatura) {
    	super(ventanaPartidas,true);
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
        list.setBounds(73, 47, 342, 208);
        list.setVisible(false); //que no sea visible de primeras
        contentPanel.add(list);

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

		}else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
			int opcion=JOptionPane.showConfirmDialog(this,(String)"Log out...","Are you sure you want to leave the game?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
			if(opcion==JOptionPane.YES_OPTION) {
				this.dispose(); //para cerrar la ventana actual
			}
		} 
    
}
	}