package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

public class VentanaGym extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblNewLabel;
	private Toolkit tk;
	private JButton btnPuerta;
	
	public VentanaGym(CriaturasControlador controlador, Creature criatura) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Monstruito adorable .png"));
        this.cont = controlador;

        // Pantalla completa para JDialog
        tk = Toolkit.getDefaultToolkit();
        int ancho = (int) tk.getScreenSize().getWidth();
        int alto = (int) tk.getScreenSize().getHeight();

        this.setSize(1607,978);          
        this.setLocationRelativeTo(null);
        
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		// Botón invisible sobre la puerta
        btnPuerta = new JButton();
        btnPuerta.setBounds(1179, 311, 240, 380); //  AJUSTA esto A la PUERTA
        btnPuerta.setOpaque(false);
        btnPuerta.setContentAreaFilled(false);
        btnPuerta.setBorderPainted(false);
        
        contentPanel.add(btnPuerta);
		
		lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\1dami\\Desktop\\PROYECTO FINAL\\FOTOS\\Cuarto infantil con .png"));
        lblNewLabel.setBounds(10, 10, 1536, 1024);
        contentPanel.add(lblNewLabel);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPuerta) {
			//metodo de ir a pasear
		}
		
	}

}
