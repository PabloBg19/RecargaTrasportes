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
    private JPanel CalendarioInicio;
    private JPanel CalendarioFin;
    private JPanel Contenidos;
    private JPanel Numero;
    private JPanel Recargar;
    private JTextField textField1;
    private JLabel NumeroTargeta;
    private JButton recargarButton;
    private JPanel TargetaIcono;
    private JLabel TargetaIconoImagen;
    private JPanel LogoyBus;
    private JPanel Fecha;
    private JLabel SeleccionarFecha;
    private JLabel Calendario;
    private JPanel FechaInicio;
    private JLabel Inicio;
    private JPanel FechaFin;
    private JLabel Fin;
    private JPanel BonoContenido;
    private JPanel BannerInferior;
    private JLabel IconoBanner;

    public ElegirAbono()
    {
        //Ajustamos márgenes para que cuadre
        BannerInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        LogoyBus.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        Recargar.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        Numero.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        Targeta.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        //Configuramos los calendarios del inicio y el fin
        //Calendario inicio
        CalendarioInicio.setLayout(new BorderLayout());
        JDateChooser dateChooserInicio = new JDateChooser();
        CalendarioInicio.add(dateChooserInicio, BorderLayout.CENTER);
        CalendarioInicio.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 40));

        //Calendario fin
        CalendarioFin.setLayout(new BorderLayout());
        JDateChooser dateChooserFin = new JDateChooser();
        CalendarioFin.add(dateChooserFin, BorderLayout.CENTER);
        CalendarioFin.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 40));

        //Hacemos comprobaciones al pulsar el boton de recarga
        recargarButton.addActionListener(e -> {
            String numeroTarjeta = textField1.getText().trim();
            java.util.Date fechaInicio = dateChooserInicio.getDate();
            java.util.Date fechaFin = dateChooserFin.getDate();

            StringBuilder mensaje = new StringBuilder();

            //Validamos que la targeta tenga 10 números
            if (!numeroTarjeta.matches("\\d{10}"))
            {
                mensaje.append(" - El número de tarjeta debe tener exactamente 10 dígitos.\n");
            }

            //Comprobamos que las fechas están seleccionadas
            if (fechaInicio == null)
            {
                mensaje.append(" - Debes seleccionar una fecha de inicio.\n");
            }
            if (fechaFin == null)
            {
                mensaje.append(" - Debes seleccionar una fecha de finalización.\n");
            }

            //Verificamos que la fecha de fin no sea anterior a la de inicio
            if (fechaInicio != null && fechaFin != null && !fechaFin.after(fechaInicio))
            {
                mensaje.append(" - La fecha de finalización debe ser posterior a la de inicio.\n");
            }

            //Mostramos un mensaje en caso de fallar alguna validación
            if (mensaje.length() > 0)
            {
                JOptionPane.showMessageDialog(null,
                        "Faltan valores por rellenar o incorrectos:\n" + mensaje,
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
            }

            //En caso de validar, se abre la pestaña correspondiente a FacturaAbono
            else
            {
                FacturaAbono factura = new FacturaAbono(fechaInicio, fechaFin, numeroTarjeta);
                JFrame frameFactura = new JFrame("Factura de Recarga");
                frameFactura.setContentPane(factura.getPanel());
                frameFactura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameFactura.pack();
                frameFactura.setLocationRelativeTo(null);
                frameFactura.setVisible(true);
            }
        });
    }

    public JPanel getPanel()
    {
        return ElegirAbono;
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("ElegirAbono");
        frame.setContentPane(new ElegirAbono().ElegirAbono);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
