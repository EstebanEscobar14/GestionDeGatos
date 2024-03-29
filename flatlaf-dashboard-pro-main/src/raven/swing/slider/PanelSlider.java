package raven.swing.slider;

import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.VolatileImage;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * PanelSlider es una extensión de JLayeredPane que permite realizar
 * transiciones suaves entre componentes. Se utiliza para mostrar y ocultar
 * componentes con animaciones, facilitando la creación de interfaces de usuario
 * dinámicas.
 *
 * @author Esteban
 */
public class PanelSlider extends JLayeredPane {

    private PanelSnapshot panelSnapshot;
    private Component component;
    private Component oldComponent;

    /**
     * Constructor de PanelSlider. Inicializa la interfaz de usuario del panel
     * deslizante.
     */
    public PanelSlider() {
        init();
    }

    /**
     * Inicializa la interfaz de usuario del panel deslizante.
     */
    private void init() {
        setOpaque(true);
        panelSnapshot = new PanelSnapshot();
        setLayout(new CardLayout());
        setLayer(panelSnapshot, JLayeredPane.DRAG_LAYER);
        add(panelSnapshot);
        panelSnapshot.setVisible(false);
    }

    /**
     * Agrega un componente al panel deslizante con una transición especificada.
     *
     * @param component El nuevo componente a mostrar.
     * @param transition La transición que define la animación entre el
     * componente actual y el nuevo componente.
     */
    public void addSlide(Component component, SliderTransition transition) {
        component.applyComponentOrientation(getComponentOrientation());
        if (this.component != null) {
            this.oldComponent = this.component;
        }
        this.component = component;
        if (oldComponent == null) {
            add(component);
            repaint();
            revalidate();
            component.setVisible(true);
        } else {
            add(component);
            if (transition != null) {
                doLayout();
                SwingUtilities.updateComponentTreeUI(component);
                SwingUtilities.invokeLater(() -> {
                    Image oldImage = createImage(oldComponent);
                    Image newImage = createImage(component);
                    remove(oldComponent);
                    panelSnapshot.animate(transition, oldImage, newImage);
                });
            } else {
                component.setVisible(true);
                remove(oldComponent);
                revalidate();
                repaint();
            }
        }
    }

    /**
     * Crea una imagen volátil del componente proporcionado.
     *
     * @param component El componente del cual se creará la imagen.
     * @return La imagen volátil creada a partir del componente.
     */
    public Image createImage(Component component) {
        boolean check = false;
        for (Component com : getComponents()) {
            if (com == component) {
                check = true;
                break;
            }
        }
        if (!check) {
            add(component);
        }
        VolatileImage snapshot = component.createVolatileImage(getWidth(), getHeight());
        if (snapshot == null) {
            return null;
        }
        component.paint(snapshot.getGraphics());
        if (!check) {
            remove(component);
        }
        return snapshot;
    }

    /**
     * Crea una imagen volátil del componente anterior (oldComponent).
     *
     * @return La imagen volátil creada a partir del componente anterior.
     */
    public Image createOldImage() {
        if (oldComponent != null) {
            return createImage(oldComponent);
        }
        return null;
    }

    /**
     * Clase interna PanelSnapshot utilizada para realizar animaciones de
     * transición.
     */
    private class PanelSnapshot extends JComponent {

        @Override
        public void updateUI() {
            super.updateUI();
            if (sliderTransition != null && !sliderTransition.closeAfterAnimation()) {
                if (oldComponent != null) {
                    SwingUtilities.updateComponentTreeUI(oldComponent);
                    oldImage = PanelSlider.this.createImage(oldComponent);
                }
            }
        }

        private final Animator animator;
        private float animate;

        private SliderTransition sliderTransition;
        private Image oldImage;
        private Image newImage;

        public PanelSnapshot() {
            animator = new Animator(400, new Animator.TimingTarget() {
                @Override
                public void timingEvent(float v) {
                    animate = v;
                    repaint();
                }

                @Override
                public void end() {
                    if (sliderTransition.closeAfterAnimation()) {
                        setVisible(false);
                        oldImage.flush();
                        newImage.flush();
                    }
                    component.setVisible(true);
                }
            });
            animator.setInterpolator(CubicBezierEasing.EASE);
        }

        protected void animate(SliderTransition sliderTransition, Image oldImage, Image newImage) {
            if (animator.isRunning()) {
                animator.stop();
            }
            this.oldImage = oldImage;
            this.newImage = newImage;
            this.sliderTransition = sliderTransition;
            this.animate = 0f;
            repaint();
            setVisible(true);
            animator.start();
        }

        @Override
        public void paint(Graphics g) {
            if (sliderTransition != null) {
                int width = getWidth();
                int height = getHeight();
                sliderTransition.render(this, g, oldImage, newImage, width, height, animate);
            }
        }
    }
}
