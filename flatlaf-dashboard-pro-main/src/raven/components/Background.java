package raven.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Clase que representa un panel con fondo personalizado.
 * Este panel tiene esquinas redondeadas y utiliza el color de fondo definido por el cajón (Drawer).
 * Se ha configurado con márgenes y un estilo de borde.
 */
public class Background extends JPanel {

    /**
     * Constructor que inicializa la configuración del panel.
     */
    public Background() {
        init();
    }

    /**
     * Inicializa la configuración del panel, estableciendo la opacidad, el diseño y el estilo del fondo.
     */
    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Drawer.background");
    }

    /**
     * Sobrescribe el método paintComponent para proporcionar un fondo con esquinas redondeadas.
     * @param g el objeto Graphics utilizado para pintar el componente.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        FlatUIUtils.setRenderingHints(g2);
        int arc = UIScale.scale(30);
        g2.setColor(getBackground());
        FlatUIUtils.paintComponentBackground(g2, 0, 0, getWidth(), getHeight(), 0, arc);
        g2.dispose();
        super.paintComponent(g);
    }
}

