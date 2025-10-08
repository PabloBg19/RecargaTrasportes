package Interfaz;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class comprabilletes {
    public static void main(String[] args) {
        JFrame frame = new JFrame("comprabilletes");
        frame.setContentPane(new comprabilletes().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(810,600);
        frame.setVisible(true);
    }

    public comprabilletes() {
        panelIda.setLayout(new BorderLayout());
        JDateChooser dateChooser = new JDateChooser();
        panelIda.add(dateChooser, BorderLayout.CENTER);

        panelVuelta.setLayout(new BorderLayout());
        JDateChooser dateChooser2 = new JDateChooser();
        panelVuelta.add(dateChooser2, BorderLayout.CENTER);
    }


    private JPanel panel1;
    private JPanel panelNav;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JSpinner spinner1;
    private JButton BUSCARButton;
    private JPanel panelcalendario;
    private JPanel panelIda;
    private JPanel panelVuelta;
    private JCheckBox llevoPerroCheckBox;
    private JCheckBox plazaHDisponibleCheckBox;
}
