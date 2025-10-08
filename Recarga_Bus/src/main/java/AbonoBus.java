import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbonoBus {
    private JPanel AbonoBus;
    private JPanel MoldeBanner;
    private JLabel Banner;
    private JTextField textFieldUsuario;
    private JPasswordField passwordFieldContrasena;
    private JLabel Usuario;
    private JLabel Contrasena;
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

    public AbonoBus() {
        // Márgenes laterales
        int margen = 60;
        AnuncioIzda.setBorder(new EmptyBorder(margen, margen, margen, margen));
        AnuncioDer.setBorder(new EmptyBorder(margen, margen, margen, margen));

        // Escalar imágenes dinámicamente
        ComponentAdapter resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarImagen(Anuncio1);
                ajustarImagen(Anuncio2);
            }
        };
        Anuncio1.addComponentListener(resizeListener);
        Anuncio2.addComponentListener(resizeListener);

        // 🟢 Acción del botón "Acceder"
        buttonAcceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCampos();
            }
        });
    }

    // Método que valida los campos del formulario
    private void validarCampos() {
        String usuario = textFieldUsuario.getText().trim();
        String contrasena = new String(passwordFieldContrasena.getPassword()).trim();
        boolean aceptaTyC = checkBoxTyC.isSelected();

        if (usuario.isEmpty() || contrasena.isEmpty() || !aceptaTyC) {
            StringBuilder mensaje = new StringBuilder("Falta información por rellenar:\n");

            if (usuario.isEmpty()) mensaje.append(" - Usuario\n");
            if (contrasena.isEmpty()) mensaje.append(" - Contraseña\n");
            if (!aceptaTyC) mensaje.append(" - Aceptar los Términos y Condiciones\n");

            JOptionPane.showMessageDialog(null, mensaje.toString(), "Campos incompletos", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Inicio de sesión correcto ✅", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void ajustarImagen(JLabel label) {
        if (label.getIcon() != null && label.getIcon() instanceof ImageIcon) {
            int w = label.getWidth();
            int h = label.getHeight();

            if (w <= 0 || h <= 0) return;

            ImageIcon icon = (ImageIcon) label.getIcon();
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(newImg));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AbonoBus");
        AbonoBus abonoBus = new AbonoBus();
        frame.setContentPane(abonoBus.AbonoBus);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}

