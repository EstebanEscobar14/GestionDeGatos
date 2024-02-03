package swing.shadow.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 * Clase que proporciona un efecto de ondulación (ripple effect) para un
 * componente Swing. Cuando se presiona el botón izquierdo del ratón en el
 * componente, se agrega un efecto de ondulación en la ubicación del clic. Se
 * puede personalizar el color de la ondulación.
 *
 * @author esteban
 */
public class RippleEffect {

    /**
     * Componente al cual se aplicará el efecto de ondulación.
     */
    private final Component component;

    /**
     * Color de la ondulación.
     */
    private Color rippleColor = new Color(255, 255, 255);

    /**
     * Lista de efectos de ondulación.
     */
    private List<Effect> effects;

    /**
     * Constructor que inicializa la clase con el componente proporcionado.
     *
     * @param component El componente al cual se aplicará el efecto de
     * ondulación.
     */
    public RippleEffect(Component component) {
        this.component = component;
        init();
    }

    /**
     * Método de inicialización que configura el manejo de eventos del ratón.
     */
    private void init() {
        effects = new ArrayList<>();
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    addEffect(e.getPoint());
                }
            }
        });
    }

    /**
     * Agrega un efecto de ondulación en la ubicación especificada.
     *
     * @param location La ubicación donde se agregará el efecto de ondulación.
     */
    public void addEffect(Point location) {
        effects.add(new Effect(component, location));
    }

    /**
     * Método para renderizar los efectos de ondulación.
     *
     * @param g El contexto de gráficos en el que se renderizarán los efectos.
     * @param contain La forma que contiene los efectos.
     */
    public void reder(Graphics g, Shape contain) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < effects.size(); i++) {
            Effect effect = effects.get(i);
            if (effect != null) {
                effect.render(g2, contain);
            }
        }
    }

    /**
     * Clase interna que representa un efecto de ondulación.
     */
    private class Effect {

        /**
         * Componente al cual se aplica el efecto.
         */
        private final Component component;

        /**
         * Ubicación del efecto.
         */
        private final Point location;

        /**
         * Animador para el efecto de ondulación.
         */
        private Animator animator;

        /**
         * Valor de animación para controlar la apariencia del efecto.
         */
        private float animate;

        /**
         * Constructor que inicializa el efecto con el componente y la ubicación
         * especificados.
         *
         * @param component El componente al cual se aplica el efecto.
         * @param location La ubicación del efecto.
         */
        public Effect(Component component, Point location) {
            this.component = component;
            this.location = location;
            init();
        }

        /**
         * Método de inicialización que configura el animador para el efecto.
         */
        private void init() {
            animator = new Animator(500, new TimingTargetAdapter() {
                @Override
                public void timingEvent(float fraction) {
                    animate = fraction;
                    component.repaint();
                }

                @Override
                public void end() {
                    effects.remove(Effect.this);
                }
            });
            animator.setResolution(5);
            animator.setDeceleration(.5f);
            animator.start();
        }

        /**
         * Renderiza el efecto de ondulación en el contexto de gráficos
         * especificado y la forma que lo contiene.
         *
         * @param g2 El contexto de gráficos donde se renderizará el efecto.
         * @param contain La forma que contiene el efecto.
         */
        public void render(Graphics2D g2, Shape contain) {
            Area area = new Area(contain);
            area.intersect(new Area(getShape(getSize(contain.getBounds2D()))));
            g2.setColor(rippleColor);
            float alpha = 0.3f;
            if (animate >= 0.7f) {
                double t = animate - 0.7f;
                alpha = (float) (alpha - (alpha * (t / 0.3f)));
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.fill(area);
        }

        /**
         * Obtiene la forma del efecto en función del tamaño actual y el valor
         * de animación.
         *
         * @param size El tamaño de la forma del efecto.
         * @return La forma del efecto.
         */
        private Shape getShape(double size) {
            double s = size * animate;
            double x = location.getX();
            double y = location.getY();
            Shape shape = new Ellipse2D.Double(x - s, y - s, s * 2, s * 2);
            return shape;
        }

        /**
         * Obtiene el tamaño del efecto basado en las dimensiones de la forma
         * que lo contiene.
         *
         * @param rec La forma que contiene el efecto.
         * @return El tamaño del efecto.
         */
        private double getSize(Rectangle2D rec) {
            double size;
            if (rec.getWidth() > rec.getHeight()) {
                if (location.getX() < rec.getWidth() / 2) {
                    size = rec.getWidth() - location.getX();
                } else {
                    size = location.getX();
                }
            } else {
                if (location.getY() < rec.getHeight() / 2) {
                    size = rec.getHeight() - location.getY();
                } else {
                    size = location.getY();
                }
            }
            return size + (size * 0.1f);
        }
    }

    /**
     * Establece el color de la ondulación.
     *
     * @param rippleColor El color de la ondulación.
     */
    public void setRippleColor(Color rippleColor) {
        this.rippleColor = rippleColor;
    }

    /**
     * Obtiene el color de la ondulación.
     *
     * @return El color de la ondulación.
     */
    public Color getRippleColor() {
        return rippleColor;
    }
}
