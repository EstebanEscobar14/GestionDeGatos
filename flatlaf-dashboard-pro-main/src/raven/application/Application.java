package raven.application;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;
import raven.components.Background;
import raven.forms.DashboardForm;
import raven.menu.FormManager;

/**
 * Clase principal que representa la aplicación. Extiende JFrame y configura la
 * apariencia y comportamiento iniciales de la aplicación. Inicializa la
 * interfaz de usuario y muestra el formulario de inicio (DashboardForm).
 *
 * @author Esteban
 */
public class Application extends JFrame {

    /**
     * Constructor de la clase. Inicializa la apariencia y el comportamiento
     * iniciales de la aplicación.
     */
    public Application() {
        init();
    }

    /**
     * Inicializa la apariencia y el comportamiento iniciales de la aplicación.
     */
    private void init() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1366, 768)));
        setLocationRelativeTo(null);
        setContentPane(new Background());
        FormManager.install(this);
        FormManager.showForm(new DashboardForm());
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    }

    /**
     * Método principal que inicia la aplicación. Configura la apariencia,
     * instala la fuente por defecto y muestra la interfaz de usuario.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        EventQueue.invokeLater(() -> new Application().setVisible(true));
    }
}
