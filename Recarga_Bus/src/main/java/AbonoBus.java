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
        // Creamos márgenes en los laterales para separar las imágenes de los bordes
        int margen = 60;

        AnuncioIzda.setBorder(new EmptyBorder(margen, margen, margen, margen));
        AnuncioDer.setBorder(new EmptyBorder(margen, margen, margen, margen));

        // Escalamos las imágenes dinamicamente
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

        // Le damos acción al botón acceder
        buttonAcceder.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                validarCampos();
            }
        });
    }

    // Creamos validarCampos para comprobar que antes de acceder los estén campos de forma correcta
    private void validarCampos()
    {
        //Creamos Strings para guardar los dátos introducidos
        String nombre = textFieldNombre.getText().trim();
        String apellidos = textFieldApellidos.getText().trim();
        String dni = textFieldDNI.getText().trim().toUpperCase();
        boolean aceptaTyC = checkBoxTyC.isSelected();

        //Creamos un StringBuilder para crear un mensaje en caso de haber campos incompletos
        StringBuilder mensaje = new StringBuilder();

        //Comprobamos que los campos nombre y apellidos no estén vacios,
        //en caso contrario, añade Nombre o Apellidos al mensaje
        if (nombre.isEmpty()) mensaje.append(" - Nombre\n");
        if (apellidos.isEmpty()) mensaje.append(" - Apellidos\n");

        //Sentencia if para comprobar el DNI
        if (dni.isEmpty())
        {
            mensaje.append(" - DNI\n");
        }
        //Llama a DNIValido para comprobar el formato
        else if (!esDNIValido(dni))
        {
            mensaje.append(" - DNI con formato incorrecto\n");
        }

        //Comprueba los Términos y Condiciones
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
            //Cerramos la ventana
            SwingUtilities.getWindowAncestor(AbonoBus).dispose();

            //Abrimos la ventana correspondiente a ElegirAbono
            ElegirAbono elegirAbono = new ElegirAbono();
            JFrame frameElegir = new JFrame("Elegir Abono");
            frameElegir.setContentPane(elegirAbono.getPanel());
            frameElegir.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frameElegir.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frameElegir.setVisible(true);
        }
    }

    //Validamos el formato del DNI
    private boolean esDNIValido(String dni)
    {
        //Comprueba que el formato son 8 números y una letra
        if (!dni.matches("^[0-9]{8}[A-Z]$"))
        {
            return false;
        }

        //Comprueba que la letra es la correcta
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = letras.charAt(numero % 23);
        char letraIntroducida = dni.charAt(8);

        return letraCorrecta == letraIntroducida;
    }

    //Ajustamos las imágenes
    private void ajustarImagen(JLabel label)
    {
        //Sentencia para comprobar que el formato de la imágen es el correcto
        if (label.getIcon() != null && label.getIcon() instanceof ImageIcon)
        {
            //Obtenemos el ancho y el alto
            int w = label.getWidth();
            int h = label.getHeight();

            //Si el alto o el ancho son 0 o negativos, termina la sentencia y no intenta escalar la imagen
            if (w <= 0 || h <= 0) return;

            //Obtenemos la imagen
            ImageIcon icon = (ImageIcon) label.getIcon();
            Image img = icon.getImage();

            //Creamos una versión redimensionada de la imagen
            Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

            //Reemplazamos la imagen
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