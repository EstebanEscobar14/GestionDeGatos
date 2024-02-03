/**
 * SliderTransition es una clase abstracta que define métodos para renderizar imágenes durante
 * transiciones utilizadas en el componente PanelSlider para animar cambios entre componentes.
 * Las clases que implementen esta interfaz deben proporcionar implementaciones concretas para
 * los métodos abstractos definidos aquí.
 *
 * @author Esteban
 */
package raven.swing.slider;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Clase abstracta que define métodos para renderizar imágenes durante
 * transiciones en el componente PanelSlider.
 */
public abstract class SliderTransition {

    /**
     * Renderiza la imagen del componente anterior durante la transición.
     *
     * @param component Componente anterior.
     * @param g Gráficos para renderizar.
     * @param image Imagen del componente anterior.
     * @param width Ancho del componente.
     * @param height Altura del componente.
     * @param animate Valor de animación.
     */
    public abstract void renderImageOld(Component component, Graphics g, Image image, int width, int height, float animate);

    /**
     * Renderiza la imagen del componente nuevo durante la transición.
     *
     * @param component Componente nuevo.
     * @param g Gráficos para renderizar.
     * @param image Imagen del componente nuevo.
     * @param width Ancho del componente.
     * @param height Altura del componente.
     * @param animate Valor de animación.
     */
    public abstract void renderImageNew(Component component, Graphics g, Image image, int width, int height, float animate);

    /**
     * Indica si se debe cerrar el componente después de la animación.
     *
     * @return true si se debe cerrar, false de lo contrario.
     */
    public boolean closeAfterAnimation() {
        return true;
    }

    /**
     * Renderiza ambas imágenes (antigua y nueva) durante la transición.
     *
     * @param component Componente actual.
     * @param g Gráficos para renderizar.
     * @param imageOld Imagen del componente anterior.
     * @param imageNew Imagen del componente nuevo.
     * @param width Ancho del componente.
     * @param height Altura del componente.
     * @param animate Valor de animación.
     */
    public void render(Component component, Graphics g, Image imageOld, Image imageNew, int width, int height, float animate) {
        renderImageOld(component, g.create(), imageOld, width, height, animate);
        renderImageNew(component, g.create(), imageNew, width, height, animate);
    }
}
