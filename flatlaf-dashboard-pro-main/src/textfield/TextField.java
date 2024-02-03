/**
 * Clase que extiende JTextField y proporciona una apariencia personalizada con sombra.
 * Permite ajustar el redondeo de las esquinas, el color de la sombra, y presenta un diseño transparente.
 * @author esteban
 */
package textfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;
import swing.shadow.ShadowRenderer;

public class TextField extends JTextField {

    /**
     * Obtiene el radio de redondeo de las esquinas.
     * @return El radio de redondeo de las esquinas.
     */
    public int getRound() {
        return round;
    }

    /**
     * Establece el radio de redondeo de las esquinas.
     * @param round El nuevo valor para el radio de redondeo de las esquinas.
     */
    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        repaint();
    }

    /**
     * Obtiene el color de la sombra.
     * @return El color de la sombra.
     */
    public Color getShadowColor() {
        return shadowColor;
    }

    /**
     * Establece el color de la sombra.
     * @param shadowColor El nuevo color de la sombra.
     */
    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }

    private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);

    /**
     * Constructor que inicializa la clase y configura la apariencia predeterminada.
     */
    public TextField() {
        setUI(new TextUI());
        setOpaque(false);
        setForeground(new Color(80, 80, 80));
        setSelectedTextColor(new Color(255, 255, 255));
        setSelectionColor(new Color(133, 209, 255));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
    }

    /**
     * Método que se llama para pintar el componente.
     * Dibuja la sombra y el fondo del campo de texto.
     * @param grphcs El contexto gráfico.
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        //  Crear imagen de sombra
        g2.drawImage(imageShadow, 0, 0, null);
        //  Crear color de fondo
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    /**
     * Establece los límites del componente y actualiza la imagen de la sombra.
     * @param x La coordenada x.
     * @param y La coordenada y.
     * @param width El ancho del componente.
     * @param height La altura del componente.
     */
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    /**
     * Crea la imagen de la sombra para el componente.
     */
    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    /**
     * Crea una imagen con sombra para el componente.
     * @return La imagen con sombra.
     */
    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
        } else {
            return null;
        }
    }

    /**
     * Clase interna que extiende BasicTextFieldUI para personalizar la apariencia del texto.
     */
    private class TextUI extends BasicTextFieldUI {
        /**
         * Override este método para eliminar el fondo o no pintar el fondo.
         */
        @Override
        protected void paintBackground(Graphics grphcs) {
            // No hace nada para evitar pintar el fondo
        }
    }
}
