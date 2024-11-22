package PartidaPack;

import AplicacionPack.Layout.*;
import AplicacionPack.Pantallas.PantallaJuego;

import java.util.Random;

import static AplicacionPack.Pantallas.Pantalla.descansar;

public class Jugador {

    // Constantes y atributos estáticos
    public static final Random random = new Random();

    // Atributos del jugador
    private Heroe heroe;
    private int vida;
    private int numeroJugador;
    private int heroeID;
    private int efectoCasilla = 0;
    private int numero;

    // Posición y movimiento en el mapa
    private final int ALTO_MAPA = 9;
    private final int ANCHO_MAPA = 12;
    private int lado;
    private int alto;
    private int fila;
    private int columna;

    // Referencias a otras clases y objetos de la partida
    private Juego juego;
    private PantallaJuego pantallaJuego;
    private Casilla casillaJugador;
    private Foto foto;

    // Constructor
    public Jugador(Juego juego, int numeroJugador, int heroeID, Casilla casilla) {
        this.juego = juego;
        this.pantallaJuego = juego.getPantallaJuego();

        this.numeroJugador = numeroJugador;
        this.heroeID = heroeID;
        this.heroe = new Heroe(heroeID);
        this.vida = heroe.getVidaHeroe();

        this.casillaJugador = casilla;
        this.columna = casillaJugador.getColumna();
        this.fila = casillaJugador.getFila();
        this.numero = casillaJugador.getNumero();

        calcularPosicionEnCasilla();
        this.foto = new Fichas(juego.getPantallaJuego(), numeroJugador, 47, 50, obtenerPosX(), obtenerPosY(), 3);
    }

    // Métodos de acceso
    public int getVida() { return vida; }
    public int getEfectoCasilla() { return efectoCasilla; }
    public void setEfectoCasilla(int efectoCasilla) { this.efectoCasilla = efectoCasilla; }
    public void setVida(int vida) { this.vida = vida; }
    public int getNumero() { return numero; }
    public Foto getFoto() { return foto; }
    public int getNumeroJugador() { return numeroJugador; }
    public int getHeroeID() { return heroeID; }
    public void setColumna(int columna) { this.columna = columna; }
    public void setFila(int fila) { this.fila = fila; }
    public Casilla getCasilla() { return casillaJugador; }

    // Métodos del turno del jugador
    public void turnoJugador() {
        boolean finTurno = false;

        bloquearBotones();
        imprimeInformacion();

        while (!finTurno) {
            pantallaJuego.instruccion.setText("Presiona el boton de tirar");

            if (pantallaJuego.tirar.isBotonPress()) {
                finTurno = ejecutarTirada();
            } else if (pantallaJuego.abrirMenu.isBotonPress()) {
                finTurno=gestionarMenu();
            }
            descansar(10);
        }

        actualizarEfectos();
    }

    private boolean ejecutarTirada() {
        pantallaJuego.instruccion.setText("Elije uno de los dados");
        descansar(50);

        int dado1 = lanzarDados();
        int dado2 = (efectoCasilla != 6) ? lanzarDados() : 0;

        pantallaJuego.dado1B.setId(dado1);
        pantallaJuego.dado1B.unblock();
        if (efectoCasilla != 6) {
            pantallaJuego.dado2B.setId(dado2);
            pantallaJuego.dado2B.unblock();
        }

        return seleccionarDado(dado1, dado2);
    }

    private boolean seleccionarDado(int dado1, int dado2) {
        boolean dadosTirados = false;

        while (!dadosTirados) {
            if (pantallaJuego.dado1B.isBotonPress()) {
                procesarMovimiento(dado1);
                dadosTirados = true;
            } else if (pantallaJuego.dado2B.isBotonPress()) {
                procesarMovimiento(dado2);
                dadosTirados = true;
            }
            descansar(10);
        }
        return true;
    }

    private boolean gestionarMenu() {
        pantallaJuego.activarMenu();
        boolean terminarMenu = false;

        while (!terminarMenu) {
            if (pantallaJuego.terminarPartida.isBotonPress()) {
                pantallaJuego.partidaTerminada = true;
                return true;
            } else if (pantallaJuego.cerrarMenu.isBotonPress()) {
                pantallaJuego.desactivarMenu();
                terminarMenu = true;
            } else if (pantallaJuego.guardar.isBotonPress()) {
                juego.guardarPartida();
                pantallaJuego.guardar.setBotonPress(false);
            }
            descansar(10);
        }
        return false;
    }

    // Movimiento y actualización de posición
    public void procesarMovimiento(int movimiento) {
        int movimientoFinal = switch (efectoCasilla) {
            case 7 -> movimiento * 2; // Multiplicación de dado
            case 8 -> movimiento * -1; // Movimiento inverso
            default -> movimiento;
        };
        mover(movimientoFinal);
    }

    public void mover(int movimiento) {
        int nuevoNumeroCasilla = (numero + movimiento) % juego.getTamañoCamino();
        if (nuevoNumeroCasilla < 0) {
            nuevoNumeroCasilla += juego.getTamañoCamino(); // Ajuste para negativos
        }

        actualizarCasilla(nuevoNumeroCasilla);
        actualizarCoordenadas();
    }

    private void actualizarCasilla(int nuevoNumeroCasilla) {
        for (Casilla[] filaCasillas : juego.getTablero()) {
            for (Casilla casilla : filaCasillas) {
                if (casilla.getNumero() == nuevoNumeroCasilla) {
                    casillaJugador.deleteJugador(this);
                    casilla.addJugador(this);
                    casillaJugador = casilla;
                    if (efectoCasilla != 9) {
                        casillaJugador.revelar();
                    }
                    return;
                }
            }
        }
    }

    // Gestión de efectos de casilla
    private void actualizarEfectos() {
        if (efectoCasilla != 9) {
            limpiarEfectos();
            revisarEfecto();
        } else {
            limpiarEfectos();
        }
    }

    void revisarEfecto() {
        switch (casillaJugador.getEfecto()) {
            case 2 -> mover(-1); // Retroceso
            case 3 -> mover(1); // Avance
            case 4 -> { limpiarEfectos(); turnoJugador(); } // Vuelve a tirar
            case 5 -> efectoCasilla = 5; // Pierde un turno
            case 6 -> efectoCasilla = 6; // Solo un dado
            case 7 -> efectoCasilla = 7; // Multiplica dado
            case 8 -> efectoCasilla = 8; // Tiro inverso
            case 9 -> efectoCasilla = 9; // No revela casilla
        }
    }

    public void limpiarEfectos() {
        efectoCasilla = 0;
    }

    // Métodos auxiliares
    private int lanzarDados() {
        int valor = random.nextInt(1, 7);
        return valor;
    }

    private void actualizarCoordenadas() {
        //ArrayList<Foto> fotos = pantallaJuego.getFotos();
        this.columna = casillaJugador.getColumna();
        this.fila = casillaJugador.getFila();
        this.numero = casillaJugador.getNumero();

        foto.setX(obtenerPosX());
        foto.setY(obtenerPosY());
        pantallaJuego.getDrawable().get(foto.getCapa()).set(foto.getIndex(),foto);
        //fotos.set(indiceFoto, foto);
    }

    private int obtenerPosX() {
        return (columna * 95) + 80 + lado;
    }

    private int obtenerPosY() {
        return (fila * 95) + 72 + alto;
    }

    private void calcularPosicionEnCasilla() {
        lado = (numeroJugador + 1) % 2 == 0 ? 23 : -23;
        alto = numeroJugador < 2 ? 23 : -23;
    }

    void imprimeInformacion() {
        switch (casillaJugador.getEfecto()) {
            case 1 -> pantallaJuego.informacion.setText("Estas en la casilla inicial");
            case 2 -> pantallaJuego.informacion.setText("Haz retrocedido 1 casilla");
            case 3 -> pantallaJuego.informacion.setText("Haz avanzado 2 casillas");
            case 4 -> pantallaJuego.informacion.setText("Vuelve a tirar");
            case 5 -> pantallaJuego.informacion.setText("Haz perdido 1 turno");
            case 6 -> pantallaJuego.informacion.setText("El siguiente turno solo se tirara 1 dado");
            case 7 -> pantallaJuego.informacion.setText("El siguiente turno tu dado se multiplicara");
            case 8 -> pantallaJuego.informacion.setText("Tu siguiente tiro te retrocede");
            case 9 -> pantallaJuego.informacion.setText("No revelas la siguiente casilla");
        }
    }

    private void bloquearBotones() {
        pantallaJuego.tirar.unblock();
        pantallaJuego.dado1B.block();
        pantallaJuego.dado2B.block();
    }
}
