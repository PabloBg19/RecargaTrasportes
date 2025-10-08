package Interfaz;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Random;

public class comprabilletes {
    // Datos del usuario
    private String nombreUsuario;
    private String apellidoUsuario;
    private String dniUsuario;
    private int edadUsuario;



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

    public comprabilletes(String nombre, String apellido, String dni, int edad) {
        this.nombreUsuario = nombre;
        this.apellidoUsuario = apellido;
        this.dniUsuario = dni;
        this.edadUsuario = edad;
        panelIda.setLayout(new BorderLayout());
        JDateChooser dateChooser = new JDateChooser();
        panelIda.add(dateChooser, BorderLayout.CENTER);

        panelVuelta.setLayout(new BorderLayout());
        JDateChooser dateChooser2 = new JDateChooser();
        panelVuelta.add(dateChooser2, BorderLayout.CENTER);

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarBillete(dateChooser, dateChooser2);
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    private void mostrarBillete(JDateChooser ida, JDateChooser vuelta) {
        String estacionSalida = comboBox1.getSelectedItem().toString();
        String estacionLlegada = comboBox2.getSelectedItem().toString();
        int pasajeros = (Integer) spinner1.getValue();
        String descuentoTexto = comboBox3.getSelectedItem().toString();
        boolean descuentoH = plazaHDisponibleCheckBox.isSelected();
        boolean perro = llevoPerroCheckBox.isSelected();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaIda = ida.getDate() != null ? sdf.format(ida.getDate()) : "No seleccionada";
        String fechaVuelta = vuelta.getDate() != null ? sdf.format(vuelta.getDate()) : "No seleccionada";

        // Crear el diálogo personalizado
        JDialog dialog = new JDialog((Frame)null, "Billete RENFE", true);
        dialog.setSize(700, 450);
        dialog.setLocationRelativeTo(null);

        // Panel principal del billete
        JPanel billetePanel = new JPanel();
        billetePanel.setLayout(new BorderLayout(10, 10));
        billetePanel.setBackground(Color.WHITE);
        billetePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                new EmptyBorder(20, 20, 20, 20)
        ));

        // Panel superior con logo y datos principales
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        // Logo RENFE (simulado con texto estilizado)
        JLabel logoLabel = new JLabel("renfe", SwingConstants.RIGHT);
        logoLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 36));
        logoLabel.setForeground(new Color(102, 45, 145));
        topPanel.add(logoLabel, BorderLayout.EAST);

        // Información del billete
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        // Número de billete
        String numBillete = generarNumeroBillete();
        JLabel lblNumBillete = new JLabel("Num. Billete: " + numBillete);
        lblNumBillete.setFont(new Font("Arial", Font.PLAIN, 11));

        // Localizador
        JLabel lblLocalizador = new JLabel("Localizador: " + generarLocalizador());
        lblLocalizador.setFont(new Font("Arial", Font.PLAIN, 11));

        // Tarifa
        JLabel lblTarifa = new JLabel("Tarifa: " + descuentoTexto);
        lblTarifa.setFont(new Font("Arial", Font.PLAIN, 11));

        infoPanel.add(lblNumBillete);
        infoPanel.add(lblLocalizador);
        infoPanel.add(lblTarifa);

        topPanel.add(infoPanel, BorderLayout.WEST);

        // Panel central con detalles del viaje
        JPanel centerPanel = new JPanel(new GridLayout(0, 4, 15, 8));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Añadir campos del billete
        addBilleteField(centerPanel, "Salida", estacionSalida.toUpperCase());
        addBilleteField(centerPanel, "Llegada", estacionLlegada.toUpperCase());
        addBilleteField(centerPanel, "Fecha", fechaIda);
        addBilleteField(centerPanel, "Hora", "08:30");

        addBilleteField(centerPanel, "AVE", generarNumeroTren());
        addBilleteField(centerPanel, "Tipo", "Turista");
        addBilleteField(centerPanel, "Coche", String.valueOf(new Random().nextInt(10) + 1));
        addBilleteField(centerPanel, "Plaza", generarAsiento(descuentoH));

        // Datos del pasajero
        String nombreCompleto = nombreUsuario + " " + apellidoUsuario;
        addBilleteField(centerPanel, "Pasajero", nombreCompleto.toUpperCase());
        addBilleteField(centerPanel, "DNI", dniUsuario);
        addBilleteField(centerPanel, "Edad", String.valueOf(edadUsuario) + " años");
        addBilleteField(centerPanel, "", ""); // Espacio vacío para balance visual

        // Panel inferior con precio y extras
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Precio
        double precio = calcularPrecio(pasajeros, descuentoTexto, perro);
        JLabel lblTotal = new JLabel("Total: " + String.format("%.2f", precio) + " €");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblGastos = new JLabel("Gastos de gestión: 0.00 €");
        lblGastos.setFont(new Font("Arial", Font.PLAIN, 12));
        lblGastos.setAlignmentX(Component.LEFT_ALIGNMENT);

        bottomPanel.add(lblTotal);
        bottomPanel.add(Box.createVerticalStrut(5));
        bottomPanel.add(lblGastos);

        // Extras
        if (perro || pasajeros > 1) {
            bottomPanel.add(Box.createVerticalStrut(10));
            JLabel lblExtras = new JLabel("Información adicional:");
            lblExtras.setFont(new Font("Arial", Font.BOLD, 11));
            lblExtras.setAlignmentX(Component.LEFT_ALIGNMENT);
            bottomPanel.add(lblExtras);

            if (pasajeros > 1) {
                JLabel lblPasajeros = new JLabel("• Pasajeros: " + pasajeros);
                lblPasajeros.setFont(new Font("Arial", Font.PLAIN, 10));
                lblPasajeros.setAlignmentX(Component.LEFT_ALIGNMENT);
                bottomPanel.add(lblPasajeros);
            }
            if (perro) {
                JLabel lblPerro = new JLabel("• Viaja con mascota");
                lblPerro.setFont(new Font("Arial", Font.PLAIN, 10));
                lblPerro.setAlignmentX(Component.LEFT_ALIGNMENT);
                bottomPanel.add(lblPerro);
            }
        }

        // Nota al pie
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));

        JLabel lblNota = new JLabel("Mantenga la integridad de toda la hoja, sin cortar ninguna de las zonas impresas.");
        lblNota.setFont(new Font("Arial", Font.ITALIC, 9));
        lblNota.setHorizontalAlignment(SwingConstants.CENTER);
        lblNota.setForeground(new Color(80, 80, 80));
        footerPanel.add(lblNota, BorderLayout.CENTER);

        // Ensamblar todo
        billetePanel.add(topPanel, BorderLayout.NORTH);
        billetePanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBackground(Color.WHITE);
        bottomContainer.add(bottomPanel, BorderLayout.NORTH);
        bottomContainer.add(footerPanel, BorderLayout.SOUTH);
        billetePanel.add(bottomContainer, BorderLayout.SOUTH);

        dialog.add(billetePanel);
        dialog.setVisible(true);
    }

    private void addBilleteField(JPanel panel, String label, String value) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBackground(Color.WHITE);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Arial", Font.BOLD, 11));
        lblLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.PLAIN, 13));
        lblValue.setAlignmentX(Component.LEFT_ALIGNMENT);

        fieldPanel.add(lblLabel);
        fieldPanel.add(lblValue);

        panel.add(fieldPanel);
    }

    private String generarNumeroBillete() {
        Random rand = new Random();
        return String.format("%013d", rand.nextLong() % 10000000000000L);
    }

    private String generarLocalizador() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private String generarNumeroTren() {
        return String.format("%05d", new Random().nextInt(10000));
    }

    private String generarAsiento(boolean descuentoH) {
        Random rand = new Random();
        int numero = rand.nextInt(80) + 1;
        String letra = descuentoH ? "H" : String.valueOf((char)('A' + rand.nextInt(4)));
        return numero + letra;
    }

    private double calcularPrecio(int pasajeros, String descuento, boolean perro) {
        double precioBase = 38.20;
        double total = precioBase * pasajeros;

        if (descuento.contains("JOVEN")) {
            total *= 0.75;
        } else if (descuento.contains("SENIOR")) {
            total *= 0.80;
        }

        if (perro) {
            total += 10.0;
        }

        return total;
    }
}