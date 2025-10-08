package Interfaz;

import javax.swing.*;

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
            String nombre = textfieldnombre.getText().trim();
            String apellido = textfieldapellido.getText().trim();
            String dni = textField3.getText().trim();

            // Validaciones
            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellena todos los campos");
                return;
            }

            if (edad < 18) {
                JOptionPane.showMessageDialog(null, "Debes ser mayor de edad");
            } else if (edad < 200) {
                // Cerrar ventana actual
                SwingUtilities.getWindowAncestor(accederButton).dispose();

                // Abrir comprabilletes con los datos del usuario
                JFrame frame = new JFrame("Compra de Billetes - " + nombre + " " + apellido);
                frame.setContentPane(new comprabilletes(nombre, apellido, dni, edad).getPanel1());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(810, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Edad no válida");
            }
        });
    }
}