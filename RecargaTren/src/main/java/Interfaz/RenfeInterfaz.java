package Interfaz;

import javax.swing.*;

public class RenfeInterfaz {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RenfeInterfaz");
        frame.setContentPane(new RenfeInterfaz().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(770,590);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private JPanel panel1;
    private JPanel panelnav;
    private JTextField textField1;
    private JTextField textField2;
    private JSpinner spinner1;
    private JTextField textField3;
    private JButton accederButton;
}
