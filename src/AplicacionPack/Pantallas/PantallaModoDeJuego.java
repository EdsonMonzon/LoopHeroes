package AplicacionPack.Pantallas;

import AplicacionPack.Sprite;
import AplicacionPack.Layout.*;
import AplicacionPack.Ventana;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Pantalla para elegir un modo de juego
//Posee 3 botones, Compe, Personalizada y cargar  partida ademas del boton atras
public class PantallaModoDeJuego extends Pantalla {

    private Ventana ventana;

    private Boton botonAtras;
    private Boton botonPartidaCompetitiva;
    private Boton botonCargarPartida;


    public Boton getBotonAtras() {
        return botonAtras;
    }

    public Boton getBotonPartidaCompetitiva() {
        return botonPartidaCompetitiva;
    }

    public Boton getBotonCargarPartida() {
        return botonCargarPartida;
    }

    public PantallaModoDeJuego(Ventana ventana) {

        super("PantallaModoDeJuego");
        this.ventana = ventana;
        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75,0);
        this.botonPartidaCompetitiva = new Boton(this, 4, 150, 150, 800, 350,0);
        this.botonCargarPartida = new Boton(this, 6, 150, 150, 800, 500,0);

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(2))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void manejarEventos(){
        if (getBotonAtras().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaInicio(ventana));
        } else if (getBotonPartidaCompetitiva().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaPartidaCompetitiva(ventana));
        } else if (getBotonCargarPartida().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaCargarPartida(ventana));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }
}
