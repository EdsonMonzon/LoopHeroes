package PartidaPack;

import AplicacionPack.Layout.*;
import AplicacionPack.Pantallas.PantallaJuego;

import java.util.Random;

import static AplicacionPack.Pantallas.Pantalla.descansar;

public class Jugador {

    // Constantes y atributos estáticos
    public static final Random random = new Random();

    // Atributos del jugador
    public Heroe heroe;

    private int numeroJugador;
    private int heroeID;
    private int efectoCasilla = 0;

    // Posición y movimiento en el mapa
    private final int ALTO_MAPA = 9;
    private final int ANCHO_MAPA = 12;
    private int lado;
    private int alto;

    // Referencias a otras clases y objetos de la partida
    private Juego juego;
    private PantallaJuego pantallaJuego;
    private Casilla casillaJugador;
    private Foto foto;

    boolean dadosTirados = false;
    boolean accionUsada = false;

    // Constructor
    public Jugador(Juego juego, int numeroJugador, Heroe heroe, Casilla casilla) {
        this.juego = juego;
        this.pantallaJuego = juego.getPantallaJuego();

        this.numeroJugador = numeroJugador;
        this.heroe = heroe;

        if (casilla == null) {
            throw new IllegalArgumentException("La casilla proporcionada es null.");
        }
        System.out.println("Casilla jugador: " + casillaJugador);


        this.casillaJugador = casilla;
        juego.casillasConJugadores.add(casillaJugador);

        calcularPosicionEnCasilla();
        this.foto = new Fichas(juego.getPantallaJuego(), numeroJugador, 47, 50, obtenerPosX(), obtenerPosY(), 3);

    }

    // Métodos de acceso
    public int getVida() { return heroe.vida; }
    public int getRango() { return heroe.rango; }
    public int getDaño() { return heroe.daño; }
    public int getMonedas() { return heroe.monedas; }
    public int getPuntos() { return heroe.puntos; }
    public int getEfectoCasilla() { return efectoCasilla; }
    public void setEfectoCasilla(int efectoCasilla) { this.efectoCasilla = efectoCasilla; }
    public Foto getFoto() { return foto; }
    public int getNumeroJugador() { return numeroJugador; }
    public int getHeroeID() { return heroeID; }
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
    }
    private void atacar() {
        int rango = heroe.rango; // Rango del héroe
        int filaAtacante = casillaJugador.getFila();
        int columnaAtacante = casillaJugador.getColumna();

        for (Casilla casilla : juego.casillasConJugadores) {
            int fila = casilla.getFila();
            int columna = casilla.getColumna();

            // Cálculo de la distancia de Manhattan
            int distancia = Math.abs(fila - filaAtacante) + Math.abs(columna - columnaAtacante);

            // Verificar si está dentro del rango y en línea recta (ataque en cruz)
            if (distancia <= rango && (fila == filaAtacante || columna == columnaAtacante)) {
                // La casilla está en rango, aplicamos daño a los jugadores en ella
                for (Jugador jugador : casilla.getJugadores()) {
                    jugador.hacerDaño(heroe.daño);
                }
            }
        }

        for(Jugador jugador: juego.listaJugadores){
            if (jugador.heroe.vida <= 0) {
                jugador.revivirJugador();
            }
        }
    }

    private void curar(){
        heroe.curar(3);
    }

    private void moneda(){
        heroe.ganarMonedas(1);
    }

    private void hacerDaño(int daño) {
        heroe.vida -= daño;
    }

    private void revivirJugador(){
        moverA(1);
        heroe.reiniciarVida();
        heroe.puntos--;
        heroe.monedas=0;
    }

    private boolean ejecutarTirada() {
        pantallaJuego.instruccion.setText("Elije uno de los dados");
        descansar(50);

        int dado1 = lanzarDados(1,7);
        int dado2 = (efectoCasilla != 6) ? lanzarDados(1,7) : 0;
        int dadoAccion=lanzarDados(7,10);

        pantallaJuego.dado1.setId(dado1);
        pantallaJuego.dado1.unblock();
        if (efectoCasilla != 6) {
            pantallaJuego.dado2.setId(dado2);
            pantallaJuego.dado2.unblock();
        }
        pantallaJuego.dadoAccion.setId(dadoAccion);
        pantallaJuego.dadoAccion.unblock();

        return seleccionarDado(dado1, dado2,dadoAccion);
    }

    private boolean seleccionarDado(int dado1, int dado2, int dadoAccion) {
        dadosTirados = false;
        accionUsada = false;

        while (!dadosTirados || !accionUsada) {
            if (pantallaJuego.dado1.isBotonPress()) {
                procesarMovimiento(dado1);
                dadosTirados = true;

                pantallaJuego.dado1.block();
                pantallaJuego.dado2.block();

            } else if (pantallaJuego.dado2.isBotonPress()) {
                procesarMovimiento(dado2);
                dadosTirados = true;

                pantallaJuego.dado1.block();
                pantallaJuego.dado2.block();
            } else if(pantallaJuego.dadoAccion.isBotonPress()){
                procesarAccion(dadoAccion);
                accionUsada = true;

                pantallaJuego.dadoAccion.block();
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

    public void procesarAccion(int accion) {
        switch(accion){
            case 7->{
                atacar();
            }
            case 8->{
                curar();
            }
            case 9->{
                moneda();
            }
        }
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
        int nuevoNumeroCasilla = (casillaJugador.getNumero() + movimiento) % juego.getTamañoCamino();
        if (nuevoNumeroCasilla < 0) {
            nuevoNumeroCasilla += juego.getTamañoCamino(); // Ajuste para negativos
        }

        actualizarCasilla(nuevoNumeroCasilla);
        actualizarCoordenadas();
        actualizarEfectos();

        System.out.println("Casilla jugador: " + casillaJugador);
    }

    private void actualizarCasilla(int nuevoNumeroCasilla) {
        for (Casilla[] filaCasillas : juego.getTablero()) {
            for (Casilla casilla : filaCasillas) {
                if (casilla.getNumero() == nuevoNumeroCasilla) {
                    casillaJugador.deleteJugador(this);
                    juego.casillasConJugadores.remove(casillaJugador);
                    casilla.addJugador(this);
                    juego.casillasConJugadores.add(casilla);
                    casillaJugador = casilla;
                    if (efectoCasilla != 9) {
                        casillaJugador.revelar();
                    }
                    return;
                }
            }
        }
    }
    public void moverA(int numCasilla) {
        actualizarCasilla(numCasilla);
        actualizarCoordenadas();
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
            case 4 -> { limpiarEfectos(); nuevoTurnoJugador(); } // Vuelve a tirar
            case 5 -> efectoCasilla = 5; // Pierde un turno
            case 6 -> efectoCasilla = 6; // Solo un dado
            case 7 -> efectoCasilla = 7; // Multiplica dado
            case 8 -> efectoCasilla = 8; // Tiro inverso
            case 9 -> efectoCasilla = 9; // No revela casilla
        }
    }

    public void nuevoTurnoJugador(){
        dadosTirados=true;
        accionUsada=true;

        turnoJugador();
    }

    public void limpiarEfectos() {
        efectoCasilla = 0;
    }

    // Métodos auxiliares
    private int lanzarDados(int origin , int bound) {
        int valor = random.nextInt(origin, bound);
        return valor;
    }

    private void actualizarCoordenadas() {
        //ArrayList<Foto> fotos = pantallaJuego.getFotos();
        foto.setX(obtenerPosX());
        foto.setY(obtenerPosY());
        pantallaJuego.getDrawable().get(foto.getCapa()).set(foto.getIndex(),foto);
        //fotos.set(indiceFoto, foto);
    }

    private int obtenerPosX() {
        return (casillaJugador.getColumna() * 95) + 80 + lado;
    }

    private int obtenerPosY() {
        return (casillaJugador.getFila() * 95) + 72 + alto;
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
        pantallaJuego.dado1.block();
        pantallaJuego.dado2.block();
        pantallaJuego.dadoAccion.block();
    }
}
