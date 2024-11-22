package AplicacionPack.Pantallas;

import AplicacionPack.Sprite;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaPartidaCompetitiva extends Pantalla{

    private Ventana ventana;

    private Boton botonAtras;
    private Boton boton2Jugadores;
    private Boton boton3Jugadores;
    private Boton boton4Jugadores;



    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBoton2Jugadores() {
        return boton2Jugadores;
    }

    public Boton getBoton3Jugadores() {
        return boton3Jugadores;
    }

    public Boton getBoton4Jugadores() {
        return boton4Jugadores;
    }

    public PantallaPartidaCompetitiva(Ventana ventana) {

        super("PantallaPartidaCompetitiva");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75,0);
        this.boton2Jugadores = new Boton(this, 10, 150, 150, 800, 350,0);
        this.boton3Jugadores = new Boton(this, 11, 150, 150, 800, 500,0);
        this.boton4Jugadores = new Boton(this, 12, 150, 150, 800, 650,0);

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(3))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manejarEventos(){
        if (getBotonAtras().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaModoDeJuego(ventana));
        } else if (getBoton2Jugadores().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana,2));
        } else if (getBoton3Jugadores().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana,3));
        } else if (getBoton4Jugadores().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaSeleccionHeroe(ventana,4));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
