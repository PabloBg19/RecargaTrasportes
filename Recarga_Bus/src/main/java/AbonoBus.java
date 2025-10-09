import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbonoBus
{
    private JPanel AbonoBus;
    private JPanel MoldeBanner;
    private JLabel Banner;
    private JTextField textFieldNombre;
    private JPasswordField passwordFieldApellidos;
    private JLabel Nombre;
    private JLabel Apellidos;
    private JCheckBox checkBoxTyC;
    private JButton buttonAcceder;
    private JPanel InicioSesion;
    private JPanel UyC;
    private JPanel TyC;
    private JPanel Boton;
    private JPanel AnuncioIzda;
    private JPanel AnuncioDer;
    private JLabel Anuncio1;
    private JLabel Anuncio2;
    private JLabel DNI;
    private JTextField textFieldDNI;
    private JTextField textFieldApellidos;

    public AbonoBus()
    {
        // Márgenes laterales
        int margen = 60;
        AnuncioIzda.setBorder(new EmptyBorder(margen, margen, margen, margen));
        AnuncioDer.setBorder(new EmptyBorder(margen, margen, margen, margen));

        // Escalar imágenes dinámicamente
        ComponentAdapter resizeListener = new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                ajustarImagen(Anuncio1);
                ajustarImagen(Anuncio2);
            }
        };
        Anuncio1.addComponentListener(resizeListener);
        Anuncio2.addComponentListener(resizeListener);

        // 🟢 Acción del botón "Acceder"
        buttonAcceder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                validarCampos();
            }
        });
    }

    // Método que valida los campos del formulario
    private void validarCampos()
    {
        String nombre = textFieldNombre.getText().trim();
        String apellidos = textFieldApellidos.getText().trim();
        String dni = textFieldDNI.getText().trim().toUpperCase();
        boolean aceptaTyC = checkBoxTyC.isSelected();

        StringBuilder mensaje = new StringBuilder();

        if (nombre.isEmpty()) mensaje.append(" - Nombre\n");
        if (apellidos.isEmpty()) mensaje.append(" - Apellidos\n");

        if (dni.isEmpty())
        {
            mensaje.append(" - DNI\n");
        }
        else if (!esDNIValido(dni))
        {
            mensaje.append(" - DNI con formato incorrecto\n");
        }

        if (!aceptaTyC) mensaje.append(" - Aceptar los Términos y Condiciones\n");

        if (mensaje.length() > 0)
        {
            JOptionPane.showMessageDialog(null,
                    "Falta información por rellenar o incorrecta:\n" + mensaje,
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Inicio de sesión correcto ✅",
                    "Acceso permitido",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 🧩 Validar formato y letra del DNI
    private boolean esDNIValido(String dni)
    {
        // Formato general: 8 números seguidos de una letra
        if (!dni.matches("^[0-9]{8}[A-Z]$"))
        {
            return false;
        }

        // Validar la letra del DNI
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = letras.charAt(numero % 23);
        char letraIntroducida = dni.charAt(8);

        return letraCorrecta == letraIntroducida;
    }

    private void ajustarImagen(JLabel label)
    {
        if (label.getIcon() != null && label.getIcon() instanceof ImageIcon)
        {
            int w = label.getWidth();
            int h = label.getHeight();

            if (w <= 0 || h <= 0) return;

            ImageIcon icon = (ImageIcon) label.getIcon();
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(newImg));
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("AbonoBus");
        AbonoBus abonoBus = new AbonoBus();
        frame.setContentPane(abonoBus.AbonoBus);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}