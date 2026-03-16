package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
        tk = Toolkit.getDefaultToolkit();
        int ancho = (int) tk.getScreenSize().getWidth();
        int alto = (int) tk.getScreenSize().getHeight();

        this.setSize(745, 688);          
        this.setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Habitación infantil .png"));
        lblNewLabel.setBounds(10, 10, 1536, 1024);
        contentPanel.add(lblNewLabel);
        
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


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArmario) { //ver objetos
	        Creature criatura=new Creature();
			VentanaArmario arm = new VentanaArmario(cont, criatura); //no se si borrar
	        arm.setVisible(true);
	    }else if(e.getSource() == btnCama) { //dormir, es decir salir del juego
	    	
	    }
		
	}

}
