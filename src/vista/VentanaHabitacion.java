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
<<<<<<< HEAD
=======
import modelo.Objectos;
>>>>>>> refs/heads/main

import javax.swing.JList;

public class VentanaHabitacion extends JDialog implements ActionListener {

<<<<<<< HEAD
	public VentanaHabitacion(VentanaPartidas ventanaPartidas, CriaturasControlador controlador, Creature criatura) {
		super(ventanaPartidas,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Monstruito adorable .png"));
		this.cont = controlador;
=======
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Toolkit tk;
    private JLabel lblNewLabel;
    private CriaturasControlador cont;
    private JButton btnArmario;
    private JButton btnCama;
    private JList list;

    public VentanaHabitacion(CriaturasControlador controlador, Creature criatura) {

        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaHabitacion.class.getResource("/image/Monstruito adorable .png")));

        this.cont = controlador;
>>>>>>> refs/heads/main

<<<<<<< HEAD
        Toolkit tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
=======
<<<<<<< HEAD
		// Pantalla completa para JDialog
		Toolkit tk = Toolkit.getDefaultToolkit();
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
=======
        Toolkit tk = Toolkit.getDefaultToolkit();
>>>>>>> refs/heads/main
        int ancho = tk.getScreenSize().width;
        int alto = tk.getScreenSize().height;
>>>>>>> refs/heads/main

<<<<<<< HEAD
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);

		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
=======
        this.setSize(ancho, alto);
        this.setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        btnArmario = new JButton();
        btnArmario.setBounds(72, 282, 368, 434);
        btnArmario.setOpaque(false);
        btnArmario.setContentAreaFilled(false);
        btnArmario.setBorderPainted(false);
        btnArmario.addActionListener(this);

        list = new JList();
        list.setBounds(73, 47, 342, 208);
        list.setVisible(false); //que no sea visible de primeras
        contentPanel.add(list);

        btnCama = new JButton();
        btnCama.setBounds(1067, 475, 390, 320);
        btnCama.setOpaque(false);
        btnCama.setContentAreaFilled(false);
        btnCama.setBorderPainted(false);
        btnCama.addActionListener(this);
        contentPanel.add(btnCama);
>>>>>>> refs/heads/main

<<<<<<< HEAD
		btnArmario = new JButton();
		btnArmario.setBounds(300, 200, 150, 250); // posición y tamaño del armario
		btnArmario.setOpaque(false);
		btnArmario.setContentAreaFilled(false);
		btnArmario.setBorderPainted(false);
		btnArmario.addActionListener(this);
		contentPanel.add(btnArmario);

		btnCama = new JButton();
		btnCama.setBounds(600, 300, 200, 150); // AJUSTA ESTO A TU CAMA
		btnCama.setOpaque(false);
		btnCama.setContentAreaFilled(false);
		btnCama.setBorderPainted(false);
		btnCama.addActionListener(this);
		contentPanel.add(btnCama);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ire22\\OneDrive\\Imágenes\\Habitación infantil .png"));
		lblNewLabel.setBounds(10, 10, 1536, 1024);
		contentPanel.add(lblNewLabel);


		List<Mueble> muebles= cont.verMuebles(); //PARA QUE SE VEAN LOS MUEBLES
		for(Mueble mueble:muebles) {
			datosMueble.addItem(mueble);
		}
=======
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(VentanaHabitacion.class.getResource("/image/Habitación infantil .png")));
        lblNewLabel.setBounds(10, 10, 1536, 1024);
        contentPanel.add(lblNewLabel);
    }
>>>>>>> refs/heads/main

<<<<<<< HEAD

	}
=======
    @Override
    public void actionPerformed(ActionEvent e) {
>>>>>>> refs/heads/main

<<<<<<< HEAD
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
=======
        if (e.getSource() == btnArmario) {
            List<Objectos> objetos = cont.verObjectos();
            list.setListData(objetos.toArray()); //rellenar la lista
            list.setVisible(true); //hacer visible la lista

        } else if (e.getSource() == btnCama) {
            int opcion = JOptionPane.showConfirmDialog(this,"Log out...","Are you sure you want to leave the game?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);

            if (opcion == JOptionPane.YES_OPTION) {
                this.dispose(); //cerrar el juego
            }
        }
    }
>>>>>>> refs/heads/main
}