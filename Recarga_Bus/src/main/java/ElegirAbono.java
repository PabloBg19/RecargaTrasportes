import javax.swing.*;

public class ElegirAbono
{
    private JPanel ElegirAbono;
    private JPanel Targeta;
    private JPanel TaragetayOpciones;
    private JPanel Bono;
    private JLabel Logo;
    private JLabel Autobus;
    private JLabel NumeroTargeta;
    private JTextField textFieldNumeroTargeta;
    private JButton buttonRecargar;
    private JPanel DatosTargeta;
    private JPanel BotonRecargar;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ElegirAbono");
        frame.setContentPane(new ElegirAbono().ElegirAbono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
