package scrollbar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 * Clase que representa una barra de desplazamiento personalizada con un diseño moderno y colores personalizados.
 * Esta clase extiende JScrollBar y configura una instancia de ModernScrollBarUI como la interfaz de usuario (UI) para la barra de desplazamiento.
 * También establece el tamaño preferido, el color de primer plano y el color de fondo de la barra de desplazamiento.
 * @author esteban
 */
public class ScrollBarCustom extends JScrollBar {

    /**
     * Constructor que configura la barra de desplazamiento con una interfaz de usuario (UI) personalizada y ajusta sus propiedades.
     */
    public ScrollBarCustom() {
        // Configurar la interfaz de usuario (UI) personalizada para la barra de desplazamiento.
        setUI(new ModernScrollBarUI());

        // Establecer el tamaño preferido de la barra de desplazamiento.
        setPreferredSize(new Dimension(8, 8));

        // Establecer el color de primer plano de la barra de desplazamiento.
        setForeground(new Color(48, 144, 216));

        // Establecer el color de fondo de la barra de desplazamiento.
        setBackground(Color.WHITE);
    }
}
