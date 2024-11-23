package PartidaPack;

import AplicacionPack.Layout.Labels;
import AplicacionPack.Pantallas.PantallaJuego;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Juego {

    private final Random random = new Random();
    PantallaJuego pantallaJuego;

    // Constantes para definir el tamaño del mapa
    final int ALTO_MAPA = 9;
    final int ANCHO_MAPA = 12;

    private int tamañoCamino;

    // Atributos del tablero y jugadores
    public Casilla[][] tablero;
    public Jugador[] listaJugadores;
    public int[] listaHeroesJugadores;
    public int[] listaNumerosJugadores;
    public int[] listaEfectosJugadores;
    public int[] listaVidaJugadores;
    private int numJugadores;

    // Matrices para definir el camino y valores del mapa
    public int[][] mapaCamino = new int[ALTO_MAPA][ANCHO_MAPA];
    public int[][] mapaValores = new int[ALTO_MAPA][ANCHO_MAPA];

    // Lista de casillas reveladas y archivos de mapas
    ArrayList<Integer> casillasReveladas = new ArrayList<>();
    ArrayList<Casilla> casillasConJugadores = new ArrayList<>();
    String[] mapas = new String[]{"mapa1.txt", "mapa2.txt", "mapa3.txt", "mapa4.txt", "mapa5.txt"};

    // Constructor para iniciar una nueva partida
    public Juego(PantallaJuego pantallaJuego) {
        this.pantallaJuego = pantallaJuego;
        this.numJugadores = pantallaJuego.numJugadores;
        this.listaHeroesJugadores = pantallaJuego.listaHeroesJugadores;

        inicializarJuego();  // Inicializa los atributos del juego
        inicializarJugadores();  // Configura a los jugadores con valores iniciales
        leerPartidaNueva(mapas[random.nextInt(mapas.length)]);  // Lee un archivo de mapa aleatorio
        construirTablero();  // Construye el tablero visualmente
    }

    // Constructor para cargar una partida guardada
    public Juego(PantallaJuego pantallaJuego, String archivoLeer) {
        this.pantallaJuego = pantallaJuego;

        leerNumeroJugadores(archivoLeer);  // Lee el número de jugadores del archivo
        pantallaJuego.numJugadores = numJugadores;
        this.listaHeroesJugadores = new int[numJugadores];

        inicializarJuego();  // Inicializa los atributos del juego
        leerPartidaGuardada(archivoLeer);  // Carga datos de una partida guardada
        construirTablero();  // Construye el tablero visualmente
    }

    // Inicializa los atributos de jugadores y tablero
    private void inicializarJuego() {
        listaJugadores = new Jugador[numJugadores];
        listaNumerosJugadores = new int[numJugadores];
        listaEfectosJugadores = new int[numJugadores];
        listaVidaJugadores = new int[numJugadores];
        tablero = new Casilla[ALTO_MAPA][ANCHO_MAPA];
    }

    // Configura los jugadores con posición inicial en 1
    private void inicializarJugadores() {
        for(int i = 0; i < numJugadores; i++) {
            listaNumerosJugadores[i] = 1;
            listaVidaJugadores[i] = new Heroe(listaHeroesJugadores[i]).vida;
        }
    }

    // Métodos getter para obtener varios atributos
    public PantallaJuego getPantallaJuego() { return pantallaJuego; }
    public Casilla[][] getTablero() { return tablero; }
    public Jugador[] getListaJugadores() { return listaJugadores; }
    public int getTamañoCamino() { return tamañoCamino; }
    public int getNumJugadores() { return numJugadores; }

    // Lee el número de jugadores de un archivo de partida guardada
    private void leerNumeroJugadores(String archivoLeer) {
        try (Scanner sc = new Scanner(new File(archivoLeer))) {
            this.numJugadores = sc.nextInt();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Guarda el estado actual del juego en un archivo
    public void guardarPartida() {
        try (FileWriter writer = new FileWriter("partida1.txt")) {
            writer.write(numJugadores + "\n");

            // Guarda datos de cada jugador
            for (Jugador juga : listaJugadores) {
                writer.write(juga.getCasilla().getNumero() + " ");
                writer.write(juga.getHeroeID() + " ");
                writer.write(juga.getEfectoCasilla() + " ");
                writer.write(juga.getVida() + " ");
                writer.write("\n");
            }

            // Guarda las casillas reveladas
            for (int i = 0; i < ALTO_MAPA; i++) {
                for (int j = 0; j < ANCHO_MAPA; j++) {
                    if (tablero[i][j].isRevelada()) {
                        writer.write(tablero[i][j].getNumero() + " ");
                    }
                }
            }
            writer.write("\n");

            // Guarda el mapa de camino
            for (int i = 0; i < ALTO_MAPA; i++) {
                for (int j = 0; j < ANCHO_MAPA; j++) {
                    writer.write(tablero[i][j].getNumero() + " ");
                }
                writer.write("\n");
            }

            // Guarda el mapa de efectos
            for (int i = 0; i < ALTO_MAPA; i++) {
                for (int j = 0; j < ANCHO_MAPA; j++) {
                    writer.write(tablero[i][j].getEfecto() + " ");
                }
                writer.write("\n");
            }
            System.out.println("Archivo de partida creado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de partida: " + e.getMessage());
        }
    }

    // Método para leer una partida nueva desde un archivo de mapa
    private void leerPartidaNueva(String archivo) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            leerMapaDeCamino(sc);  // Carga el camino del mapa
            asignarValoresAleatorios();  // Asigna valores a las casillas de manera aleatoria
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para leer una partida guardada desde un archivo
    private void leerPartidaGuardada(String archivo) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            sc.nextLine(); // Salta el número de jugadores

            leerDatosJugadores(sc);  // Carga los datos de los jugadores
            leerCasillasReveladas(sc);  // Carga las casillas reveladas
            leerMapaDeCamino(sc);  // Carga el camino del mapa
            leerMapaDeValores(sc);  // Carga los valores de las casillas
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Lee los datos de los jugadores del archivo
    private void leerDatosJugadores(Scanner sc) {
        for (int i = 0; i < numJugadores; i++) {
            try (Scanner lineaScanner = new Scanner(sc.nextLine())) {
                listaNumerosJugadores[i] = lineaScanner.nextInt();
                listaHeroesJugadores[i] = lineaScanner.nextInt();
                listaEfectosJugadores[i] = lineaScanner.nextInt();
                listaVidaJugadores[i] = lineaScanner.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Error al leer la posición de los jugadores.");
            }
        }
    }

    // Lee las casillas reveladas del archivo
    private void leerCasillasReveladas(Scanner sc) {
        try (Scanner lineaScanner = new Scanner(sc.nextLine())) {
            while (lineaScanner.hasNextInt()) {
                casillasReveladas.add(lineaScanner.nextInt());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error al leer las casillas reveladas.");
        }
    }

    // Lee el camino del mapa desde el archivo y establece el tamaño
    private void leerMapaDeCamino(Scanner sc) {
        for (int i = 0; i < ALTO_MAPA; i++) {
            for (int j = 0; j < ANCHO_MAPA; j++) {
                mapaCamino[i][j] = sc.nextInt();
                tamañoCamino = Math.max(tamañoCamino, mapaCamino[i][j]);
            }
            sc.nextLine();
        }
    }

    // Lee el mapa de valores de las casillas del archivo
    private void leerMapaDeValores(Scanner sc) {
        for (int i = 0; i < ALTO_MAPA; i++) {
            for (int j = 0; j < ANCHO_MAPA; j++) {
                mapaValores[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }
    }

    // Asigna valores aleatorios a las casillas que son parte del camino
    private void asignarValoresAleatorios() {
        for (int i = 0; i < ALTO_MAPA; i++) {
            for (int j = 0; j < ANCHO_MAPA; j++) {
                if (mapaCamino[i][j] == 0) {
                    mapaValores[i][j] = 0;
                } else if (mapaCamino[i][j] == 1) {
                    mapaValores[i][j] = 1;
                } else {
                    mapaValores[i][j] = random.nextInt(1, 10);
                }
            }
        }
    }

    // Construye el tablero visual y configura las posiciones iniciales de los jugadores
    private void construirTablero() {
        for (int i = 0; i < ALTO_MAPA; i++) {
            for (int j = 0; j < ANCHO_MAPA; j++) {
                // Crea cada casilla en el tablero con sus propiedades
                tablero[i][j] = new Casilla(i, j, mapaValores[i][j], mapaCamino[i][j], this);

                // Si la casilla es parte del camino, crea una etiqueta visual
                if (mapaCamino[i][j] != 0) {
                    int labelX = j * 95 + 120;
                    int labelY = i * 95 + 110;
                    new Labels(pantallaJuego, String.valueOf(mapaCamino[i][j]), labelX, labelY, 12, 12,3);
                }

                // Coloca a cada jugador en su casilla inicial
                for (int juga = 0; juga < numJugadores; juga++) {
                    if (listaNumerosJugadores[juga] == mapaCamino[i][j]) {
                        listaJugadores[juga] = new Jugador(this, juga, listaHeroesJugadores[juga], tablero[i][j]);
                        listaJugadores[juga].setEfectoCasilla(listaEfectosJugadores[juga]);
                        listaJugadores[juga].heroe.vida=listaVidaJugadores[juga];
                    }
                }

                // Revela las casillas previamente reveladas
                if (casillasReveladas.contains(tablero[i][j].getNumero())) {
                    tablero[i][j].revelar();
                }
            }
        }
    }
}
