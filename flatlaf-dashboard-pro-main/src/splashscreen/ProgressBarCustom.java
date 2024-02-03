package splashscreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

/**
 * Clase que representa una barra de progreso personalizada con colores específicos y un fondo del ProgressBar.
 * Permite establecer un color personalizado para el texto de la barra de progreso.
 * @author esteban
 */
public class ProgressBarCustom extends JProgressBar {

    /**
     * Obtiene el color personalizado para el texto de la barra de progreso.
     * @return El color personalizado para el texto de la barra de progreso.
     */
    public Color getColorString() {
        return colorString;
    }

    /**
     * Establece el color personalizado para el texto de la barra de progreso.
     * @param colorString El color personalizado para el texto de la barra de progreso.
     */
    public void setColorString(Color colorString) {
        this.colorString = colorString;
    }

    // Color por defecto para el texto de la barra de progreso (morado claro)
    private Color colorString = new Color(186, 168, 243);

    /**
     * Constructor que configura la barra de progreso con dimensiones, colores y un fondo específicos.
     */
    public ProgressBarCustom() {
        setPreferredSize(new Dimension(100, 5)); // Establece las dimensiones de la barra de progreso
        setBackground(new Color(77, 77, 77)); // Establece el color del fondo del ProgressBar
        setForeground(new Color(190, 190, 190)); // Establece el color del ProgressBar

        // Configura la interfaz de usuario de la barra de progreso para personalizar el color del texto
        setUI(new BasicProgressBarUI() {
            @Override
            protected void paintString(Graphics grphcs, int i, int i1, int i2, int i3, int i4, Insets insets) {
                grphcs.setColor(getColorString()); // Establece el color personalizado para el texto
                super.paintString(grphcs, i, i1, i2, i3, i4, insets);
            }
        });
    }
}

