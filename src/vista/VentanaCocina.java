package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Food;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class VentanaCocina extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private Toolkit tk;
	private ArrayList <Food> listaComida =new ArrayList<Food>();
	private CriaturasControlador cont;
	private Creature criatura;

	private JComboBox<Food> comboBox_Comida;
	private JButton btn_Nevera;
	private JButton btn_Gym;
	private JButton btn_Habitacion;
	private JLabel lbl_Criatura;
	private JLabel lbl_Accesorio;
	private JLabel lbl_Cocina;
	private JLabel lbl_EXP;
	private JLabel lbl_EMOTI;
	private JLabel lbl_NumExp;
	private JLabel lbl_FondoLista;
	private JLabel lbl_NumHunger;
	private JLabel lbl_NumHappy;
	private JLabel lbl_NumEnergy;

	public VentanaCocina(JDialog ventanas,CriaturasControlador cont, Creature criatura) {
		super(ventanas,true);
		this.cont=cont;
		this.criatura = criatura;
		listaComida=cont.listaComida();
		

		//PANTALLA COMPLETA PARA JDIALOG
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCocina.class.getResource("/image/Monstruito adorable .png")));
		tk = Toolkit.getDefaultToolkit(); //para hacer pantalla completa en jdialog
		int ancho = tk.getScreenSize().width;
		int alto = tk.getScreenSize().height;
		this.setSize(1536, 1024);
		this.setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//ACCESORIO (CREAR SIEMPRE EL JLABEL ANTES DE USARLO)
		lbl_Accesorio = new JLabel();
		lbl_Accesorio.setBounds(823, 436, 200, 200);
		contentPanel.add(lbl_Accesorio);
		
		//SE EQUIPA EL ACCESORIO QUE TENGA LA CRIATURA
		if (cont.comprobarObjeto(criatura)==1) {
		    ImageIcon icono = new ImageIcon(getClass().getResource("/image/Accesorios estilo ca.png"));
		    Image imgBH = icono.getImage().getScaledInstance(lbl_Accesorio.getWidth(), lbl_Accesorio.getHeight(), Image.SCALE_SMOOTH);
		    lbl_Accesorio.setIcon(new ImageIcon(imgBH));

		} else if (cont.comprobarObjeto(criatura)==2) {

		    ImageIcon iconoSG = new ImageIcon(getClass().getResource("/image/Accesorios estilo caw2.png"));
		    Image imgSG = iconoSG.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
		    lbl_Accesorio.setBounds(801, 400, 250, iconoSG.getIconHeight());
		    lbl_Accesorio.setIcon(new ImageIcon(imgSG));

		} else {
		    
		    lbl_Accesorio.setIcon(null);
		}
		
		//CRIATURA
		lbl_Criatura = new JLabel("");
		ImageIcon icon = new ImageIcon(VentanaHabitacion.class.getResource(this.criatura.setImage(this.criatura)));
		lbl_Criatura.setIcon(icon);
		lbl_Criatura.setBounds(700, 506, icon.getIconWidth(), icon.getIconHeight());
		contentPanel.add(lbl_Criatura);

		//BOTÓN HABITACIÓN
		btn_Habitacion = new JButton("BEDROOM");
		btn_Habitacion.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Habitacion.setForeground(Color.WHITE);
		btn_Habitacion.setBackground(new Color(123, 31, 162));
		btn_Habitacion.setBounds(1349, 805, 150, 60);
		contentPanel.add(btn_Habitacion);
		btn_Habitacion.addActionListener(this);

		//BOTÓN GYM
		btn_Gym = new JButton("GYM");
		btn_Gym.setFont(new Font("Monospaced", Font.BOLD, 20));
		btn_Gym.setForeground(Color.WHITE);
		btn_Gym.setBackground(new Color(33, 150, 243));
		btn_Gym.setBounds(31, 805, 150, 60);
		contentPanel.add(btn_Gym);
		btn_Gym.addActionListener(this);

		//NEVERA
		btn_Nevera = new JButton();
		btn_Nevera.setBounds(47, 145, 396, 515);
		btn_Nevera.setOpaque(false);
		btn_Nevera.setContentAreaFilled(false);
		btn_Nevera.setBorderPainted(false);
		contentPanel.add(btn_Nevera);
		btn_Nevera.addActionListener(this);

		//COMBOBOX COMIDA
		comboBox_Comida = new JComboBox<Food>();
		for (Food f : listaComida) {
			comboBox_Comida.addItem(f);
		}
		comboBox_Comida.setSelectedIndex(-1);

		//TABLA DE ESTADISTICAS DE LA CRIATURA
		lbl_NumExp = new JLabel("");
		lbl_NumExp.setBounds(1374, 31, 54, 25);
		lbl_NumExp.setForeground(new Color(128, 0, 64));
		lbl_NumExp.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumExp.setText(String.valueOf(criatura.getExperience()));
		contentPanel.add(lbl_NumExp);
		
		lbl_EXP = new JLabel("EXPERIENCE:");
		lbl_EXP.setBackground(new Color(240, 240, 240));
		lbl_EXP.setForeground(new Color(248, 52, 140));
		lbl_EXP.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_EXP.setBounds(1247, 26, 117, 34);
		contentPanel.add(lbl_EXP);
		
		lbl_EMOTI = new JLabel("<html>ENERGY:<br>HUNGER:<br>HAPPINESS:</html>");
		lbl_EMOTI.setBackground(new Color(255, 217, 236));
		lbl_EMOTI.setForeground(new Color(255, 89, 172));
		lbl_EMOTI.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_EMOTI.setBounds(1247, 57, 117, 79);
		contentPanel.add(lbl_EMOTI);
		
		lbl_NumHappy = new JLabel("");
		lbl_NumHappy.setForeground(new Color(128, 0, 64));
		lbl_NumHappy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumHappy.setBounds(1374, 111, 54, 25);
		lbl_NumHappy.setText(String.valueOf(criatura.getHappiness()));
		contentPanel.add(lbl_NumHappy);
		
		lbl_NumHunger = new JLabel("");
		lbl_NumHunger.setForeground(new Color(128, 0, 64));
		lbl_NumHunger.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumHunger.setBounds(1374, 85, 54, 25);
		lbl_NumHunger.setText(String.valueOf(criatura.getHunger()));
		contentPanel.add(lbl_NumHunger);
		
		lbl_NumEnergy = new JLabel("");
		lbl_NumEnergy.setForeground(new Color(128, 0, 64));
		lbl_NumEnergy.setFont(new Font("Monospaced", Font.BOLD, 17));
		lbl_NumEnergy.setBounds(1374, 60, 54, 25);
		lbl_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
		contentPanel.add(lbl_NumEnergy);
		
		lbl_FondoLista = new JLabel("");
		lbl_FondoLista.setBounds(1241, 31, 271, 112);
		lbl_FondoLista.setOpaque(true);
		lbl_FondoLista.setBackground(new Color(255, 217, 236));
		contentPanel.add(lbl_FondoLista);

		//COCINA (AL FINAL PARA QUE APAREZCA DE FONDO)
		lbl_Cocina = new JLabel("");
		lbl_Cocina.setIcon(new ImageIcon(VentanaCocina.class.getResource("/image/Cocina.png")));
		lbl_Cocina.setBounds(0, 0, ancho, alto);
		contentPanel.add(lbl_Cocina);
		
	}
	
	private void actualizarEmociones() {//METODO PARA ACTUALIZAR EL NÚMERO DE ACTUALIZACIONES 
	    lbl_NumEnergy.setText(String.valueOf(criatura.getEnergy()));
	    lbl_NumHunger.setText(String.valueOf(criatura.getHunger()));
	    lbl_NumHappy.setText(String.valueOf(criatura.getHappiness()));
	    lbl_NumExp.setText(String.valueOf(criatura.getExperience()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btn_Nevera) {//ABRIR LA NEVERA
			JOptionPane.showMessageDialog(this, comboBox_Comida, "Elegir comida", JOptionPane.QUESTION_MESSAGE);
			Food ob=(Food) comboBox_Comida.getSelectedItem();
			
			if(cont.darComida(criatura, ob)) {
				actualizarEmociones();
			}
			
			lbl_Criatura.setIcon(new ImageIcon(VentanaGym.class.getResource(this.criatura.setImage(this.criatura))));

		}else if(e.getSource()==btn_Gym) {//IR AL GYM
			this.dispose();
			VentanaGym gym = new VentanaGym(this, cont,criatura);
			gym.setVisible(true);

		}else if(e.getSource()==btn_Habitacion) {//IR A LA HABITACIÓN
			this.dispose();
			VentanaHabitacion habitacion = new VentanaHabitacion(this, cont,criatura);
			habitacion.setVisible(true);

		}

	}
}

