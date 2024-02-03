package splashscreen;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JPanel;

/**
 * Clase que representa un panel con un fondo de gradiente. Se utiliza para proporcionar un fondo con dos colores en un degradado.
 * El panel divide su altura en dos secciones y aplica un gradiente diferente a cada sección, proporcionando un efecto visual agradable.
 * @author esteban
 */
public class GradientPanel extends JPanel {

    private final Color gradientStart;
    private final Color gradientEnd;

    /**
     * Constructor que configura el panel con colores de inicio y fin para el gradiente.
     * @param gradientStart Color de inicio del gradiente.
     * @param gradientEnd Color de fin del gradiente.
     */
    public GradientPanel(Color gradientStart, Color gradientEnd) {
        this.gradientStart = gradientStart;
        this.gradientEnd = gradientEnd;
    }

    /**
     * Método de pintado del panel que aplica el gradiente superior e inferior.
     * @param g Objeto Graphics utilizado para pintar en el panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;

        // Color morado claro
        Color lightPurple = new Color(88, 86, 214, 200);

        // Gradiente superior
        GradientPaint painter = new GradientPaint(0, 0, lightPurple, 0, height / 2, gradientStart);
        Paint oldPainter = g2.getPaint();
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        // Gradiente inferior
        painter = new GradientPaint(0, height / 2, gradientStart, 0, height, gradientEnd);
        g2.setPaint(painter);
        g2.fill(g2.getClip());

        g2.setPaint(oldPainter);
    }
}

