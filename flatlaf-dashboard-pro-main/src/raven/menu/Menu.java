package raven.menu;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import raven.drawer.component.DrawerBuilder;
import raven.drawer.component.DrawerPanel;

/**
 * Menu es una clase que representa el menú de la aplicación. Se encarga de la
 * presentación y comportamiento del menú. Extiende JPanel y utiliza un
 * DrawerBuilder para construir y personalizar el contenido del menú.
 *
 * @author Esteban
 */
public class Menu extends JPanel {

    private final DrawerBuilder drawerBuilder;

    public DrawerBuilder getDrawerBuilder() {
        return drawerBuilder;
    }

    /**
     * Constructor de la clase Menu.
     *
     * @param drawerBuilder El DrawerBuilder utilizado para construir y
     * personalizar el contenido del menú.
     */
    public Menu(DrawerBuilder drawerBuilder) {
        this.drawerBuilder = drawerBuilder;
        init();
    }

    /**
     * Inicializa la configuración y contenido del menú.
     */
    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");

        // Configura el diseño del menú utilizando MigLayout
        setLayout(new MigLayout("wrap,fill", "[fill," + drawerBuilder.getDrawerWidth() + "!]", "[fill]"));

        // Agrega un MouseListener para ocultar el menú al hacer clic con el botón izquierdo
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    FormManager.hideMenu();
                }
            }
        });

        // Crea un DrawerPanel con el DrawerBuilder y agrega un MouseListener vacío
        DrawerPanel drawerPanel = new DrawerPanel(drawerBuilder);
        drawerPanel.addMouseListener(new MouseAdapter() {
            // Métodos de MouseAdapter no implementados
        });

        // Construye y personaliza el contenido del menú utilizando el DrawerBuilder
        drawerBuilder.build(drawerPanel);

        // Agrega el DrawerPanel al menú
        add(drawerPanel);
    }
}
