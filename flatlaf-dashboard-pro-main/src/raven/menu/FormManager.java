package raven.menu;

import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import raven.components.MainForm;
import raven.components.SimpleForm;
import raven.swing.slider.PanelSlider;
import raven.swing.slider.SimpleTransition;
import raven.utils.UndoRedo;

/**
 * FormManager es una clase que gestiona la navegación entre formularios en una
 * aplicación. Proporciona funciones para mostrar, ocultar, deshacer y rehacer
 * formularios, así como para actualizar la interfaz de usuario temporal de los
 * formularios.
 *
 * @author Esteban
 */
public class FormManager {

    // Instancia única de FormManager
    private static FormManager instance;

    // Marco principal de la aplicación
    private final JFrame frame;

    // Gestor de historial de formularios con funciones de deshacer y rehacer
    private final UndoRedo<SimpleForm> forms = new UndoRedo<>();

    // Estado que indica si el menú está visible
    private boolean menuShowing = true;

    // Panel deslizante utilizado para la transición entre el menú y los formularios principales
    private final PanelSlider panelSlider;

    // Formulario principal de la aplicación
    private final MainForm mainForm;

    // Menú de la aplicación
    private final Menu menu;

    /**
     * Instala el FormManager en el JFrame proporcionado.
     *
     * @param frame El JFrame principal de la aplicación.
     */
    public static void install(JFrame frame) {
        instance = new FormManager(frame);
    }

    /**
     * Constructor privado de la clase FormManager. Inicializa componentes
     * esenciales, como el panelSlider, mainForm y el menú.
     *
     * @param frame El JFrame principal de la aplicación.
     */
    private FormManager(JFrame frame) {
        this.frame = frame;
        panelSlider = new PanelSlider();
        mainForm = new MainForm();
        menu = new Menu(new MyDrawerBuilder());
        this.frame.getContentPane().add(panelSlider);
    }

    /**
     * Muestra el menú en la interfaz de usuario.
     */
    public static void showMenu() {
        instance.menuShowing = true;
        instance.panelSlider.addSlide(instance.menu, SimpleTransition.getShowMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    /**
     * Muestra un formulario en la interfaz de usuario y actualiza el historial
     * de formularios.
     *
     * @param component El formulario a mostrar.
     */
    public static void showForm(SimpleForm component) {
        if (isNewFormAble()) {
            instance.forms.add(component);
            if (instance.menuShowing == true) {
                instance.menuShowing = false;
                Image oldImage = instance.panelSlider.createOldImage();
                instance.mainForm.setForm(component);
                instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getSwitchFormTransition(oldImage, instance.menu.getDrawerBuilder().getDrawerWidth()));
            } else {
                instance.mainForm.showForm(component);
            }
            instance.forms.getCurrent().formInitAndOpen();
        }
    }

    public static void hideMenu() {
        instance.menuShowing = false;
        instance.panelSlider.addSlide(instance.mainForm, SimpleTransition.getHideMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    public static void undo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isUndoAble()) {
                instance.mainForm.showForm(instance.forms.undo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void redo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isRedoAble()) {
                instance.mainForm.showForm(instance.forms.redo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void refresh() {
        if (!instance.menuShowing) {
            instance.forms.getCurrent().formRefresh();
        }
    }

    /**
     * Obtiene el historial de formularios gestionado por el FormManager.
     *
     * @return El historial de formularios.
     */
    public static UndoRedo<SimpleForm> getForms() {
        return instance.forms;
    }

    /**
     * Verifica si es posible abrir un nuevo formulario basado en el estado
     * actual.
     *
     * @return true si es posible abrir un nuevo formulario, false de lo
     * contrario.
     */
    public static boolean isNewFormAble() {
        return instance.forms.getCurrent() == null || instance.forms.getCurrent().formClose();
    }

    /**
     * Actualiza la interfaz de usuario temporal de los formularios.
     */
    public static void updateTempFormUI() {
        for (SimpleForm f : instance.forms) {
            SwingUtilities.updateComponentTreeUI(f);
        }
    }

}
