package Interfaz;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class RenfeInterfaz {

    public static void main(String[] args) {
        JFrame frame = new JFrame("RenfeInterfaz");
        frame.setContentPane(new RenfeInterfaz().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(790,630);
        frame.setVisible(true);

    }
    private JPanel panel1;
    private JPanel panelnav;
    private JTextField textfieldnombre;
    private JTextField textfieldapellido;
    private JSpinner spinner1;
    private JTextField textField3;
    private JButton accederButton;
    private JPanel panellogin;
    private JLabel rellendatoslabel;
    private JLabel namelabel;
    private JLabel apellidoslabel;
    private JLabel edadlabel;
    private JLabel añoslabel;
    private JLabel dnilabel;
    private JComboBox comboBox1;

    public RenfeInterfaz() {
        accederButton.addActionListener(e -> {
            int edad = (Integer) spinner1.getValue();
            if (edad<18){
                JOptionPane.showMessageDialog(null, "Debes ser mayor de edad");
            }else if (edad<200){
                JOptionPane.showMessageDialog(null, "Bienvenido a Renfe");
            }else{
                JOptionPane.showMessageDialog(null, "Edad no valida");
            }
        });
    }

}
