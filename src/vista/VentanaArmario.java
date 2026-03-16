package vista;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.CriaturasControlador;
import modelo.Creature;
import modelo.Objectos;

public class VentanaArmario extends JDialog {

    public VentanaArmario(CriaturasControlador cont, Creature criatura) {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setModal(true);
        setTitle("Armario");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Ejemplo: mostrar objetos de la criatura
        for (Equip obj : criatura.getObjectos()) {
            panel.add(new JLabel(obj.getObjectName()));
        }

        add(panel);
    }
}

