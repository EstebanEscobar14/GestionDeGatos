package swing.shadow;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Clase de utilidades para trabajar con gráficos y manipulación de imágenes.
 * Proporciona métodos para crear imágenes compatibles, cargar imágenes, 
 * convertir imágenes a un formato compatible y crear miniaturas.
 * Además, incluye métodos para acceder y establecer píxeles de una imagen.
 * Esta clase está diseñada para simplificar tareas comunes relacionadas con gráficos e imágenes.
 * @author esteban
 */
public class GraphicsUtilities {

    /**
     * Constructor privado para evitar instanciación directa de la clase de
     * utilidades.
     */
    private GraphicsUtilities() {
    }

    /**
     * Obtiene la configuración de gráficos para la pantalla primaria.
     *
     * @return Configuración de gráficos para la pantalla primaria.
     */
    private static GraphicsConfiguration getGraphicsConfiguration() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().
                getDefaultScreenDevice().getDefaultConfiguration();
    }

    public static BufferedImage createColorModelCompatibleImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        return new BufferedImage(cm,
                cm.createCompatibleWritableRaster(image.getWidth(),
                        image.getHeight()),
                cm.isAlphaPremultiplied(), null);
    }

    public static BufferedImage createCompatibleImage(BufferedImage image) {
        return createCompatibleImage(image, image.getWidth(), image.getHeight());
    }

    public static BufferedImage createCompatibleImage(BufferedImage image,
            int width, int height) {
        return getGraphicsConfiguration().createCompatibleImage(width, height,
                image.getTransparency());
    }

    public static BufferedImage createCompatibleImage(int width, int height) {
        return getGraphicsConfiguration().createCompatibleImage(width, height);
    }

    public static BufferedImage createCompatibleTranslucentImage(int width,
            int height) {
        return getGraphicsConfiguration().createCompatibleImage(width, height,
                Transparency.TRANSLUCENT);
    }

    public static BufferedImage loadCompatibleImage(URL resource)
            throws IOException {
        BufferedImage image = ImageIO.read(resource);
        return toCompatibleImage(image);
    }

    public static BufferedImage toCompatibleImage(BufferedImage image) {
        if (image.getColorModel().equals(
                getGraphicsConfiguration().getColorModel())) {
            return image;
        }
        BufferedImage compatibleImage
                = getGraphicsConfiguration().createCompatibleImage(
                        image.getWidth(), image.getHeight(),
                        image.getTransparency());
        Graphics g = compatibleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return compatibleImage;
    }

    public static BufferedImage createThumbnailFast(BufferedImage image,
            int newSize) {
        float ratio;
        int width = image.getWidth();
        int height = image.getHeight();
        if (width > height) {
            if (newSize >= width) {
                throw new IllegalArgumentException("newSize must be lower than"
                        + " the image width");
            } else if (newSize <= 0) {
                throw new IllegalArgumentException("newSize must"
                        + " be greater than 0");
            }
            ratio = (float) width / (float) height;
            width = newSize;
            height = (int) (newSize / ratio);
        } else {
            if (newSize >= height) {
                throw new IllegalArgumentException("newSize must be lower than"
                        + " the image height");
            } else if (newSize <= 0) {
                throw new IllegalArgumentException("newSize must"
                        + " be greater than 0");
            }
            ratio = (float) height / (float) width;
            height = newSize;
            width = (int) (newSize / ratio);
        }
        BufferedImage temp = createCompatibleImage(image, width, height);
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();
        return temp;
    }

    /**
     * Crea una miniatura rápida de una imagen dada con un nuevo tamaño
     * proporcionado.Utiliza interpolación bilineal para una calidad de imagen
     * mejorada.
     *
     * @param image La imagen original.
     * @param newWidth
     * @param newHeight
     * @return La miniatura creada.
     */
    public static BufferedImage createThumbnailFast(BufferedImage image,
            int newWidth, int newHeight) {
        if (newWidth >= image.getWidth()
                || newHeight >= image.getHeight()) {
            throw new IllegalArgumentException("newWidth and newHeight cannot"
                    + " be greater than the image"
                    + " dimensions");
        } else if (newWidth <= 0 || newHeight <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must"
                    + " be greater than 0");
        }
        BufferedImage temp = createCompatibleImage(image, newWidth, newHeight);
        Graphics2D g2 = temp.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(image, 0, 0, temp.getWidth(), temp.getHeight(), null);
        g2.dispose();
        return temp;
    }

    public static BufferedImage createThumbnail(BufferedImage image,
            int newSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        boolean isWidthGreater = width > height;
        if (isWidthGreater) {
            if (newSize >= width) {
                throw new IllegalArgumentException("newSize must be lower than"
                        + " the image width");
            }
        } else if (newSize >= height) {
            throw new IllegalArgumentException("newSize must be lower than"
                    + " the image height");
        }
        if (newSize <= 0) {
            throw new IllegalArgumentException("newSize must"
                    + " be greater than 0");
        }
        float ratioWH = (float) width / (float) height;
        float ratioHW = (float) height / (float) width;
        BufferedImage thumb = image;
        do {
            if (isWidthGreater) {
                width /= 2;
                if (width < newSize) {
                    width = newSize;
                }
                height = (int) (width / ratioWH);
            } else {
                height /= 2;
                if (height < newSize) {
                    height = newSize;
                }
                width = (int) (height / ratioHW);
            }
            BufferedImage temp = createCompatibleImage(image, width, height);
            Graphics2D g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
            g2.dispose();
            thumb = temp;
        } while (newSize != (isWidthGreater ? width : height));
        return thumb;
    }

    public static BufferedImage createThumbnail(BufferedImage image,
            int newWidth, int newHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (newWidth >= width || newHeight >= height) {
            throw new IllegalArgumentException("newWidth and newHeight cannot"
                    + " be greater than the image"
                    + " dimensions");
        } else if (newWidth <= 0 || newHeight <= 0) {
            throw new IllegalArgumentException("newWidth and newHeight must"
                    + " be greater than 0");
        }
        BufferedImage thumb = image;
        do {
            if (width > newWidth) {
                width /= 2;
                if (width < newWidth) {
                    width = newWidth;
                }
            }
            if (height > newHeight) {
                height /= 2;
                if (height < newHeight) {
                    height = newHeight;
                }
            }
            BufferedImage temp = createCompatibleImage(image, width, height);
            Graphics2D g2 = temp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
            g2.dispose();
            thumb = temp;
        } while (width != newWidth || height != newHeight);
        return thumb;
    }

    /**
     * Obtiene los píxeles de una región rectangular de una imagen y los
     * almacena en un array.
     *
     * @param img La imagen de la cual se obtendrán los píxeles.
     * @param x Coordenada x de la esquina superior izquierda de la región.
     * @param y Coordenada y de la esquina superior izquierda de la región.
     * @param w Ancho de la región.
     * @param h Altura de la región.
     * @param pixels El array que contendrá los píxeles de la región.
     * @return Un array que contiene los píxeles de la región.
     */
    public static int[] getPixels(BufferedImage img,
            int x, int y, int w, int h, int[] pixels) {
        if (w == 0 || h == 0) {
            return new int[0];
        }
        if (pixels == null) {
            pixels = new int[w * h];
        } else if (pixels.length < w * h) {
            throw new IllegalArgumentException("pixels array must have a length"
                    + " >= w*h");
        }
        int imageType = img.getType();
        if (imageType == BufferedImage.TYPE_INT_ARGB
                || imageType == BufferedImage.TYPE_INT_RGB) {
            Raster raster = img.getRaster();
            return (int[]) raster.getDataElements(x, y, w, h, pixels);
        }
        // Unmanages the image
        return img.getRGB(x, y, w, h, pixels, 0, w);
    }

    /**
     * Establece los píxeles de una región rectangular en una imagen utilizando
     * un array de píxeles dado.
     *
     * @param img La imagen en la que se establecerán los píxeles.
     * @param x Coordenada x de la esquina superior izquierda de la región.
     * @param y Coordenada y de la esquina superior izquierda de la región.
     * @param w Ancho de la región.
     * @param h Altura de la región.
     * @param pixels El array que contiene los píxeles que se establecerán en la
     * región.
     */
    public static void setPixels(BufferedImage img,
            int x, int y, int w, int h, int[] pixels) {
        if (pixels == null || w == 0 || h == 0) {
            return;
        } else if (pixels.length < w * h) {
            throw new IllegalArgumentException("pixels array must have a length"
                    + " >= w*h");
        }

        int imageType = img.getType();
        if (imageType == BufferedImage.TYPE_INT_ARGB
                || imageType == BufferedImage.TYPE_INT_RGB) {
            WritableRaster raster = img.getRaster();
            raster.setDataElements(x, y, w, h, pixels);
        } else {
            // Unmanages the image
            img.setRGB(x, y, w, h, pixels, 0, w);
        }
    }
}
