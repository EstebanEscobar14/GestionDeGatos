package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * ThemesChange es una clase que gestiona el cambio de temas (claro/oscuro) en
 * la aplicación. Esta clase permite al usuario cambiar entre los temas de la
 * interfaz de usuario mediante botones. Extiende JPanel y proporciona la
 * capacidad de cambiar dinámicamente entre temas de interfaz de usuario claro y
 * oscuro.
 *
 * @author Esteban
 */
public class ThemesChange extends JPanel {

    /**
     * Constructor de ThemesChange. Inicializa la interfaz de usuario del cambio
     * de temas.
     */
    public ThemesChange() {
        init();
    }

    /**
     * Crea y devuelve un icono SVG con un filtro de color personalizado.
     *
     * @param path La ruta del archivo SVG.
     * @return El icono SVG con filtro de color personalizado.
     */
    private Icon createIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 0.7f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    /**
     * Inicializa la interfaz de usuario del cambio de temas.
     */
    private void init() {
        // Configuración de propiedades del panel
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
        // Diseño del panel principal utilizando MigLayout
        setLayout(new MigLayout("al center", "[fill,200]", "fill"));
        // Panel interno con estilo personalizado
        JPanel panel = new JPanel(new MigLayout("fill", "[fill]10[fill]", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "background:darken($Drawer.background,5%)");
        // Botones para cambiar entre temas claro y oscuro
        JButton buttonLight = new JButton(createIcon("raven/resources/icon/light.svg"));
        JButton buttonDark = new JButton(createIcon("raven/resources/icon/dark.svg"));
        // Manejadores de eventos para los botones
        buttonLight.addActionListener(e -> changeMode(false));
        buttonDark.addActionListener(e -> changeMode(true));
        // Configuración de propiedades de estilo para los botones
        buttonLight.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:null;"
                + "[light]background:$Drawer.background;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");

        buttonDark.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:$Drawer.background;"
                + "[light]background:null;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");
        // Añadir botones al panel interno
        panel.add(buttonDark);
        panel.add(buttonLight);
        // Añadir panel interno al panel principal
        add(panel);
    }

    /**
     * Cambia dinámicamente entre temas claro y oscuro si es necesario.
     *
     * @param dark `true` para cambiar al tema oscuro, `false` para cambiar al
     * tema claro.
     */
    private void changeMode(boolean dark) {
         // Verificar si es necesario cambiar el tema
        if (dark != FlatLaf.isLafDark()) {
            // Cambiar dinámicamente al tema oscuro
            if (dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FormManager.updateTempFormUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                // Cambiar dinámicamente al tema claro
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FormManager.updateTempFormUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
    }
}
