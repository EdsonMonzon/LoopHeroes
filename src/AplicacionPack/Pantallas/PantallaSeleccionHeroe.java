package AplicacionPack.Pantallas;

import AplicacionPack.Layout.BotonHeroe;
import AplicacionPack.Layout.Layout;
import AplicacionPack.Sprite;
import AplicacionPack.Layout.Boton;
import AplicacionPack.Layout.Labels;
import AplicacionPack.Ventana;
import PartidaPack.Jugador;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PantallaSeleccionHeroe extends Pantalla {

    final int NUMERO_HEROES = 4;
    int numeroJugadores;
    int[] listaHeroesJugadores;

    private Ventana ventana;

    private Labels label;
    private Boton botonAtras;
    private Boton heroePaladin;
    private Boton heroeArquero;
    private Boton heroeBanquero;
    private Boton heroeCorredor;

    /**
     * Al ser iniciado se le coloca el nombre de la pantalla que se pasa por parametro
     *
     * @param ventana
     */
    public PantallaSeleccionHeroe(Ventana ventana, int numeroJugadores) {
        super("PantallaSeleccionHeroe");
        this.ventana = ventana;
        this.numeroJugadores = numeroJugadores;
        this.listaHeroesJugadores =new int[numeroJugadores];

        this.botonAtras = new Boton(this, 1, 150, 150, 75, 75,0);

        this.label = new Labels(this, "Turno del jugador 1", 800, 150, 100,50,1);
        this.heroeArquero = new BotonHeroe(this, 1, 330, 450, 660, 450,0);
        this.heroeBanquero = new BotonHeroe(this, 2, 330, 450, 990, 450,0);
        this.heroeCorredor = new BotonHeroe(this, 3, 330, 450, 1320, 450,0);
        this.heroePaladin = new BotonHeroe(this, 4, 330, 450, 330, 450,0);

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(6))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Boton getBotonAtras() {
        return botonAtras;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    public Boton getHeroePaladin() {
        return heroePaladin;
    }

    public Boton getHeroeArquero() {
        return heroeArquero;
    }

    public Boton getHeroeBanquero() {
        return heroeBanquero;
    }

    public Boton getHeroeCorredor() {
        return heroeCorredor;
    }

    public Labels getLabel() {
        return label;
    }

    public void manejarEventos() {
        seleccionHeroes();

        if (getBotonAtras().isBotonPress()) {
            descansar(50);
            ventana.cambiarPantalla(new PantallaPartidaCompetitiva(ventana));
        } else {
            ventana.cambiarPantalla(new PantallaJuego(ventana, listaHeroesJugadores));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        repintar(g);
    }

    public void seleccionHeroes() {
        int heroesSeleccionados = 0; // Contador de héroes seleccionados

        while (heroesSeleccionados < numeroJugadores) {
            if (getBotonAtras().isBotonPress()) {
                break; // Si se presiona el botón Atrás, salir del bucle
            }

            for(Integer[] i:botones){
                Boton b=(Boton)drawable.get(i[0]).get(i[1]);

                if(b.isBotonPress()){
                    listaHeroesJugadores[heroesSeleccionados]=b.getId();
                    heroesSeleccionados++;
                    b.block();
                }
            }

            descansar(10); // Descansar por 10 milisegundos para evitar uso excesivo de CPU
        }
        // Aquí puedes hacer algo con los héroes seleccionados por cada jugador, por ejemplo, almacenarlos en una estructura adecuada.
    }
}

