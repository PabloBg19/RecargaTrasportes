import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FacturaAbono
{
    private JPanel Factura;      // creado por el .form (Field name)
    private JLabel Titulo;       // Field name
    private JLabel Inicio;       // Field name
    private JLabel Fin;          // Field name
    private JLabel Dias;         // Field name
    private JLabel PrecioDia;    // Field name
    private JLabel Total;        // Field name
    private JButton buttonCerrar;     // Field name


    private static final double PRECIO_POR_DIA = 0.15;

    /**
     * Constructor que recibe las fechas seleccionadas (java.util.Date).
     */
    public FacturaAbono(java.util.Date fechaInicioUtil, java.util.Date fechaFinUtil)
    {
        // Convertir a LocalDate para cálculo fiable
        LocalDate inicio = Instant.ofEpochMilli(fechaInicioUtil.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = Instant.ofEpochMilli(fechaFinUtil.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();

        long dias = ChronoUnit.DAYS.between(inicio, fin); // validación previa garantiza fin > inicio
        double total = dias * PRECIO_POR_DIA;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Rellenar labels (estos componentes vienen del .form)
        if (Titulo != null) Titulo.setText("Factura de Recarga - TUSSAM");
        if (Inicio != null) Inicio.setText("Fecha de inicio: " + inicio.format(fmt));
        if (Fin != null) Fin.setText("Fecha de finalización: " + fin.format(fmt));
        if (Dias != null) Dias.setText("Número de días: " + dias);
        if (PrecioDia != null) PrecioDia.setText(String.format("Precio por día: %.2f €", PRECIO_POR_DIA));
        if (Total != null) Total.setText(String.format("Total a pagar: %.2f €", total));

        // Botón cerrar
        if (buttonCerrar != null)
        {
            buttonCerrar.addActionListener(e -> {
                Window w = SwingUtilities.getWindowAncestor(Factura);
                if (w != null) w.dispose();
            });
        }
    }

    public JPanel getPanel()
    {
        return Factura;
    }

    // método main opcional para probar el form de forma independiente
    public static void main(String[] args) {
        java.util.Date hoy = new java.util.Date();
        java.util.Date manana = new java.util.Date(System.currentTimeMillis() + 24L*60*60*1000);
        JFrame f = new JFrame("Factura prueba");
        FacturaAbono factura = new FacturaAbono(hoy, manana);
        f.setContentPane(factura.getPanel());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}