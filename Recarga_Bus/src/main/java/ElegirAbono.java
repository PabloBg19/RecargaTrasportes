import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class ElegirAbono
{
    private JPanel ElegirAbono;
    private JPanel Targeta;
    private JPanel TaragetayOpciones;
    private JPanel Bono;
    private JLabel Logo;
    private JLabel Autobus;
    private JLabel SeleccionarFecha;
    private JPanel Fecha;
    private JLabel Calendario;
    private JPanel FechaInicio;
    private JLabel Inicio;
    private JPanel CalendarioInicio;
    private JPanel FechaFin;
    private JLabel Fin;
    private JPanel CalendarioFin;
    private JPanel Contenidos;
    private JPanel Numero;
    private JPanel Recargar;
    private JTextField textField1;
    private JLabel NumeroTargeta;
    private JButton recargarButton;

    public ElegirAbono()
    {
        Targeta.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        CalendarioInicio.setLayout(new BorderLayout());
        JDateChooser dateChooserInicio = new JDateChooser();
        CalendarioInicio.add(dateChooserInicio, BorderLayout.CENTER);
        CalendarioInicio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ad

        CalendarioFin.setLayout(new BorderLayout());
        JDateChooser dateChooserFin = new JDateChooser();
        CalendarioFin.add(dateChooserFin, BorderLayout.CENTER);
        CalendarioFin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ElegirAbono");
        frame.setContentPane(new ElegirAbono().ElegirAbono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
