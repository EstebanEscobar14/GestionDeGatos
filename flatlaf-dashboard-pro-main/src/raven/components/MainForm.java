package raven.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import raven.menu.FormManager;
import raven.swing.slider.PanelSlider;
import raven.swing.slider.SimpleTransition;

/**
 * Clase que representa el formulario principal de la aplicación.
 * Contiene un encabezado con botones de menú y acciones, y un área de desplazamiento
 * para mostrar los formularios o componentes asociados.
 * Se encarga de gestionar la transición entre formularios y actualizar los botones de acción.
 *
 * Utiliza el estilo de diseño FlatLaf y contiene componentes como botones y un panel deslizante.
 *
 * @author Esteban
 */
public class MainForm extends JPanel {

    /**
     * Constructor que inicializa el formulario principal.
     * Configura el estilo, el diseño y los componentes del formulario.
     */
    public MainForm() {
        init();
    }

    /**
     * Inicializa la configuración del formulario, incluyendo estilo, diseño y componentes.
     */
    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "arc:30");
        setLayout(new MigLayout("wrap,fillx", "[fill]", ""));
        header = createHeader();
        panelSlider = new PanelSlider();
        JScrollPane scroll = new JScrollPane(panelSlider);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "width:10");
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        add(header);
        add(scroll);
    }

    /**
     * Crea el panel del encabezado que contiene botones de menú y acciones.
     * @return El panel del encabezado configurado con los botones.
     */
    private JPanel createHeader() {
        JPanel panel = new JPanel(new MigLayout("insets 3"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");

        cmdMenu = createButton(new FlatSVGIcon("raven/resources/icon/menu.svg"));
        cmdUndo = createButton(new FlatSVGIcon("raven/resources/icon/undo.svg"));
        cmdRedo = createButton(new FlatSVGIcon("raven/resources/icon/redo.svg"));
        cmdRefresh = createButton(new FlatSVGIcon("raven/resources/icon/refresh.svg"));
        cmdMenu.addActionListener(e -> {
            FormManager.showMenu();
        });
        cmdUndo.addActionListener(e -> {
            FormManager.undo();
        });
        cmdRedo.addActionListener(e -> {
            FormManager.redo();
        });
        cmdRefresh.addActionListener(e -> {
            FormManager.refresh();
        });

        panel.add(cmdMenu);
        panel.add(cmdUndo);
        panel.add(cmdRedo);
        panel.add(cmdRefresh);
        return panel;
    }

    /**
     * Crea un botón con el estilo y la apariencia configurados.
     * @param icon El ícono a mostrar en el botón.
     * @return El botón configurado.
     */
    private JButton createButton(Icon icon) {
        JButton button = new JButton(icon);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Button.toolbar.background;"
                + "arc:10;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        return button;
    }

    /**
     * Muestra un formulario o componente en el área deslizante del formulario principal.
     * @param component El componente a mostrar.
     */
    public void showForm(Component component) {
        checkButton();
        panelSlider.addSlide(component, SimpleTransition.getDefaultTransition(false));
        revalidate();
    }

    /**
     * Establece un formulario o componente en el área deslizante del formulario principal.
     * @param component El componente a establecer.
     */
    public void setForm(Component component) {
        checkButton();
        panelSlider.addSlide(component, null);
    }

    /**
     * Verifica y actualiza el estado de los botones de acción basándose en la capacidad de
     * deshacer/rehacer y la existencia del formulario actual.
     */
    private void checkButton() {
        cmdUndo.setEnabled(FormManager.getForms().isUndoAble());
        cmdRedo.setEnabled(FormManager.getForms().isRedoAble());
        cmdRefresh.setEnabled(FormManager.getForms().getCurrent() != null);
    }

    private JPanel header;
    private JButton cmdMenu;
    private JButton cmdUndo;
    private JButton cmdRedo;
    private JButton cmdRefresh;
    private PanelSlider panelSlider;
}
