package AplicacionPack.Pantallas;

import AplicacionPack.Sprite;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Pantalla que se lanza al iniciar la aplicacion
//Posee 2 botones, Start y Salir
public class PantallaInicio extends Pantalla {

    private Ventana ventana;

    private Boton botonStart;
    private Boton botonSalir;


    public Boton getBotonStart() {
        return botonStart;
    }
    public Boton getBotonSalir() {
        return botonSalir;
    }

    public PantallaInicio(Ventana ventana) {

        super("PantallaInicio");
        this.ventana = ventana;
        this.botonStart = new Boton
                (this, 2,
                        150, 150,
                        800,  350,0);
        this.botonSalir = new Boton
                (this, 3,
                        150, 150,
                        800,  500,0);

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(1))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manejarEventos(){
        repaint();
        if (getBotonStart().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
        } else if (getBotonSalir().isBotonPress()) {
            descansar(50);
            System.exit(0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
