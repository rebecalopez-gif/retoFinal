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
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;

public class VentanaPartidas extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CriaturasControlador cont;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private UserGame user;

	public VentanaPartidas(JFrame parent, CriaturasControlador controlador, UserGame user) {
		super(parent,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPartidas.class.getResource("/image/Monstruito adorable .png")));
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
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(132, 62, 176, 20);
		contentPanel.add(comboBox);
		for (Creature c : cont.obtenerPartidas(user)) {
			comboBox.addItem(c.getCreatureName());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
