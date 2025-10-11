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
        BannerInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        LogoyBus.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        Recargar.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        Numero.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        Targeta.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Configurar calendarios
        CalendarioInicio.setLayout(new BorderLayout());
        JDateChooser dateChooserInicio = new JDateChooser();
        CalendarioInicio.add(dateChooserInicio, BorderLayout.CENTER);
        CalendarioInicio.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 40));

        CalendarioFin.setLayout(new BorderLayout());
        JDateChooser dateChooserFin = new JDateChooser();
        CalendarioFin.add(dateChooserFin, BorderLayout.CENTER);
        CalendarioFin.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 40));

        // 🟢 Validar al pulsar "Recargar"
        recargarButton.addActionListener(e -> {
            String numeroTarjeta = textField1.getText().trim();
            java.util.Date fechaInicio = dateChooserInicio.getDate();
            java.util.Date fechaFin = dateChooserFin.getDate();

            StringBuilder mensaje = new StringBuilder();

            // ✅ Validar número de tarjeta (10 dígitos)
            if (!numeroTarjeta.matches("\\d{10}"))
            {
                mensaje.append(" - El número de tarjeta debe tener exactamente 10 dígitos.\n");
            }

            // ✅ Validar que las fechas estén seleccionadas
            if (fechaInicio == null)
            {
                mensaje.append(" - Debes seleccionar una fecha de inicio.\n");
            }
            if (fechaFin == null)
            {
                mensaje.append(" - Debes seleccionar una fecha de finalización.\n");
            }

            // ✅ Validar que la fecha de fin sea posterior a la de inicio
            if (fechaInicio != null && fechaFin != null && !fechaFin.after(fechaInicio))
            {
                mensaje.append(" - La fecha de finalización debe ser posterior a la de inicio.\n");
            }

            // ✅ Mostrar mensaje según el resultado
            if (mensaje.length() > 0)
            {
                JOptionPane.showMessageDialog(null,
                        "Faltan valores por rellenar o incorrectos:\n" + mensaje,
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
            }

            else
            {
                FacturaAbono factura = new FacturaAbono(fechaInicio, fechaFin);
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
