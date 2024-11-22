package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import AplicacionPack.Ventana;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class TextBox extends JTextField {
    private Pantalla pantalla;
    private Ventana ventana;

    private double textBoxX;
    private double textBoxY;
    private double textBoxAncho;
    private double textBoxAlto;

    private double textBoxAnchoProporcion;
    private double textBoxAltoProporcion;
    private double textBoxXProporcion;
    private double textBoxYProporcion;

    public TextBox(Pantalla pantalla, Ventana ventana, double textBoxAnchoProporcion, double textBoxAltoProporcion, double textBoxXProporcion, double textBoxYProporcion) {
        this.pantalla = pantalla;
        this.ventana = ventana;
        this.textBoxAnchoProporcion = textBoxAnchoProporcion;
        this.textBoxAltoProporcion = textBoxAltoProporcion;
        this.textBoxXProporcion = textBoxXProporcion;
        this.textBoxYProporcion = textBoxYProporcion;

    }
    public void escalaTextBox() {

        textBoxAncho = pantalla.getWidth() / textBoxAnchoProporcion;
        textBoxAlto = pantalla.getHeight() / textBoxAltoProporcion;
        textBoxX = (pantalla.getWidth() / textBoxXProporcion) - textBoxAncho / 2;
        textBoxY = (pantalla.getHeight()/ textBoxYProporcion) - textBoxAlto / 2;

    }

    public void dibujar(Graphics2D g2d){

        ((PlainDocument) this.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                try {
                    int value = Integer.parseInt(newText);
                    if (value >= 1 && value <= 10) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                } catch (NumberFormatException e) {
                    // No hacer nada si no se puede convertir a número
                }
            }
        });

        setLayout(null);

        JLabel label = new JLabel("Introduce el numero de jugadores");
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setBounds((int) textBoxX, (int) textBoxY - (int)textBoxAlto, (int) textBoxAncho, (int) textBoxAlto);

        setBackground(Color.WHITE); // Cambiar color de fondo
        setForeground(Color.BLACK);  // Cambiar color de primer plano
        setFont(new Font("Arial", Font.BOLD, 14));
        setBounds((int) textBoxX, (int) textBoxY, (int) textBoxAncho, (int) textBoxAlto);

        pantalla.add(label);
        pantalla.add(this);

        g2d.drawRect((int) textBoxX, (int) textBoxY, (int) textBoxAncho, (int) textBoxAlto);
    }
}
