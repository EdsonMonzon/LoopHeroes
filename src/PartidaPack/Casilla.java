package PartidaPack;

import AplicacionPack.Layout.Casillas;
import AplicacionPack.Layout.Foto;
import AplicacionPack.Pantallas.PantallaJuego;

import java.util.ArrayList;

public class Casilla {
    private int numero;
    private int fila;
    private int columna;
    private int efecto;
    private boolean revelada = false;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private Juego juego;
    private PantallaJuego pantallaJuego;
    private Foto foto;

    // Constructor de la clase Casilla
    public Casilla(int fila, int columna, int efecto, int numero, Juego juego) {
        this.fila = fila;
        this.columna = columna;
        this.efecto = efecto;
        this.numero = numero;
        this.juego = juego;
        this.pantallaJuego=juego.getPantallaJuego();
        if(efecto!=0){
            System.out.println("No soy 0, me dibujo");
            foto= new Casillas(juego.getPantallaJuego(), 0,95,95,(columna*95)+80,(fila*95)+72,2);
        }
    }

    public ArrayList<Jugador> getJugadores() {return jugadores;}

    public int getNumero() {return numero;}

    public boolean isRevelada() {return revelada;}

    public int getEfecto() {return efecto;}

    public void revelar() {
        if(numero!=1){
            this.revelada = true;

            ((Casillas)pantallaJuego.getDrawable(foto.getCapa()).get(foto.getIndex())).setImagen(efecto);

            /**
            ArrayList<Foto>f=pantallaJuego.getFotos();

            for(int i=0;i<f.size();i++){
                if(f.get(i)==fotoOculta) {
                    f.set(i,foto);
                }
            }*/
        }
    }
    public Foto getFoto() {return foto;}

    public int getColumna() {return columna;}

    public int getFila() {return fila;}

    // Método para establecer el número de la casilla
    public void setNumero(int numero) {this.numero = numero;}

    // Método para verificar si hay jugadores en la casilla
    public boolean hayJugador() {return !jugadores.isEmpty();}

    // Método para agregar un jugador a la casilla
    public void addJugador(Jugador jugador) {
        // Verificar si el jugador ya está en la casilla antes de agregarlo
        if (!jugadores.contains(jugador)) {
            jugadores.add(jugador);
        }
    }

    // Método para eliminar un jugador de la casilla
    public void deleteJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }
}
