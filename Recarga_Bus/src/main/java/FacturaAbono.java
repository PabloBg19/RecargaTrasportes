import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FacturaAbono
{
    private JPanel Factura;
    private JLabel Titulo;
    private JLabel Inicio;
    private JLabel Fin;
    private JLabel Dias;
    private JLabel PrecioDia;
    private JLabel Total;
    private JButton buttonCerrar;
    private JLabel NumeroTarjeta;

    //Declaramos el precio por día (0,15€)
    private static final double PRECIO_POR_DIA = 0.15;

    //Creamos un constructor para recibir las fechas seleccionadas
    public FacturaAbono(java.util.Date fechaInicioUtil, java.util.Date fechaFinUtil, String numeroTargeta)
    {
        //Creamos márgenes para mejorar la estética
        Titulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        NumeroTarjeta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Inicio.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Fin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Dias.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        PrecioDia.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Total.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Convertimos a LocalDate para cálculo fiable
        LocalDate inicio = Instant.ofEpochMilli(fechaInicioUtil.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = Instant.ofEpochMilli(fechaFinUtil.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();

        //Calculamos la cantidad de días entre ambas fechas
        long dias = ChronoUnit.DAYS.between(inicio, fin);
        double total = dias * PRECIO_POR_DIA;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Cambiamos el contenido de los labels a uno más apropiado
        if (NumeroTarjeta != null) NumeroTarjeta.setText("Número de tarjeta: " + numeroTargeta);
        if (Titulo != null) Titulo.setText("Factura - TUSSAM");
        if (Inicio != null) Inicio.setText("Fecha de inicio: " + inicio.format(fmt));
        if (Fin != null) Fin.setText("Fecha de finalización: " + fin.format(fmt));
        if (Dias != null) Dias.setText("Número de días: " + dias);
        if (PrecioDia != null) PrecioDia.setText(String.format("Precio por día: %.2f €", PRECIO_POR_DIA));
        if (Total != null) Total.setText(String.format("Total a pagar: %.2f €", total));

        //Botón cerrar
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
}