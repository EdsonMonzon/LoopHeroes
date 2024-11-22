package AplicacionPack.Pantallas;

import AplicacionPack.Sprite;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

//Tipo de pantalla que se lanza cuando se quiere cargar una partida guardada
//Posee  3 botones y el boton atras
public class PantallaCargarPartida extends Pantalla{


    private Ventana ventana;

    private Boton botonAtras;
    private Boton botonCargar1;
    private Boton botonCargar2;
    private Boton botonCargar3;


    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBotonCargar1() {
        return botonCargar1;
    }

    public Boton getBotonCargar2() {
        return botonCargar2;
    }

    public Boton getBotonCargar3() {
        return botonCargar3;
    }

    public PantallaCargarPartida(Ventana ventana) {

        super("PantallaCargarPartida");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75,0);
        this.botonCargar1 = new Boton(this, 7, 150, 150, 800, 350,0);
        this.botonCargar2 = new Boton(this, 8, 150, 150, 800, 500,0);
        this.botonCargar3 = new Boton(this, 9, 150, 150, 800, 650,0);
        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(5))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manejarEventos(){
        if (getBotonAtras().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
        } else if (getBotonCargar1().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaJuego(ventana,"partida1.txt"));
        } else if (getBotonCargar2().isBotonPress()) {
            descansar(50);
        } else if (getBotonCargar3().isBotonPress()) {
            descansar(50);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
