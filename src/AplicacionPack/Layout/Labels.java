package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Labels extends Layout {

    String text;
    Font font;

    public Labels(Pantalla pantalla, String text, int labelX, int labelY, int labelAncho, int labelAlto, int capa) {
        super(pantalla,labelAncho,labelAlto,labelX,labelY,capa,"labels");
        this.text = text;
        this.clase="labels";

        // Guarda las proporciones
        this.x = labelX;
        this.y = labelY;
        this.ancho =labelAncho;
        this.alto =labelAlto;

        //this.tamano = tamano;

        // Configura la fuente con un tamaño inicial grande y en negrita
        font = new Font("Press Start 2P", Font.BOLD, 32);  // Intenta "Press Start 2P" o cambia a "Arial"
    }

    public String getText(){
        return text;
    }

    public void setVisible(boolean visible) {}

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void dibujar(Graphics2D g2d) {
        // Configura las propiedades de renderizado para mejor calidad de texto
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        // Calcula el tamaño de la fuente en función de la altura escalada del label
        float fontSize = (float) altoReal; // Usa el alto escalado para definir el tamaño de la fuente
        Font scaledFont = font.deriveFont(fontSize);
        g2d.setFont(scaledFont);

        // Obtiene FontMetrics para medir el texto y centrarlo en el label
        FontMetrics metrics = g2d.getFontMetrics(scaledFont);
        Rectangle2D textBounds = metrics.getStringBounds(text, g2d);

        // Calcula la escala para ajustar el texto dentro del área del label
        double xScale = anchoReal / textBounds.getWidth();
        double yScale = altoReal / textBounds.getHeight();

        // Aplica la transformación de escala y translación
        AffineTransform at = new AffineTransform();
        at.translate(xReal + (anchoReal - textBounds.getWidth() * xScale) / 2,
                yReal + (altoReal + metrics.getAscent() * yScale) / 2 - metrics.getDescent() * yScale);
        at.scale(xScale, yScale);
        g2d.setFont(scaledFont.deriveFont(at));

        // Dibuja el borde blanco como sombra
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, 0, 0);

        // Dibuja el texto en negro encima
        g2d.setColor(Color.BLACK);
        g2d.drawString(text, 0, 0);
    }

}
