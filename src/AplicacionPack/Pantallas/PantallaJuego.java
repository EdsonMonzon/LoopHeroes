package AplicacionPack.Pantallas;

import AplicacionPack.Layout.*;
import AplicacionPack.Sprite;
import AplicacionPack.Ventana;
import PartidaPack.Juego;
import PartidaPack.Jugador;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Pantalla principal que pinta los componentes del juego
 */
public class PantallaJuego extends Pantalla{

    public Ventana ventana;
    Juego juego;

    public boolean partidaTerminada=false;

    //Numero y lista de jugadores
    public int numJugadores;
    public int [] listaIdHeroes;

    final int ALTO_MAPA =9;
    final int ANCHO_MAPA =12;

    Boton botonAtras;
    public Boton tirar;
    public Boton abrirMenu;
    public Boton cerrarMenu;
    public Boton guardar;
    public Boton terminarPartida;
    public Boton saltarTurno;

    public Boton dadoAccion=new BotonDados(this,7,150,150,1500,520,1);
    public Boton dado1=new BotonDados(this,1,150, 150, 1300, 700,1);
    public Boton dado2 =new BotonDados(this,1,150, 150, 1500, 700,1);;

    Foto fondoTablero;
    Foto barraLateral;
    Foto menuStats;
    public Foto menu;

    public Labels turno;
    public Labels instruccion;
    public Labels informacion;
    public Labels vida;
    public Labels daño;
    public Labels monedas;
    public Labels rango;

    public Boton getTirar() {
        return tirar;
    }

    public PantallaJuego(Ventana ventana, int[]listaHeroesJugadores) {
        super("PantallaJuego");
        this.ventana = ventana;
        //Recibimos la lista de los jugadores
        this.listaIdHeroes = listaHeroesJugadores;
        this.numJugadores = this.listaIdHeroes.length;


        //Cargamos las imagenes y botones de la pantalla
        configBotones();
        configFotos();
        configLabels();

        desactivarMenu();

        //Inicializa la clase juego
        juego =new Juego(this);

        //Cargamos las casillas y las fichas en el mapa
        //configCasillas();
        //configJugadores();

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(7))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public PantallaJuego(Ventana ventana, String ruta) {
        super("PantallaJuego");
        this.ventana = ventana;

        //Cargamos las imagenes y botones de la pantalla
        configBotones();
        configFotos();
        configLabels();

        desactivarMenu();

        //Inicializa la clase juego
        juego =new Juego(this,ruta);

        //Cargamos las casillas y las fichas en el mapa
        //configCasillas();
        //configJugadores();

        try {
            setImagen(ImageIO.read(new File(Sprite.getPantallasImg(7))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void configLabels(){
        turno=new Labels(this,"Turno del jugador 1",1400,50,200,30,1);
        instruccion=new Labels(this,"Presiona \"Tirar\" para tirar los dados" ,1400,150,200,30,1);
        informacion=new Labels(this,"Estas en la casilla inicial" ,1400,250,200,30,1);
        vida=new Labels(this,"0",1295,460,10,10,2);
        daño=new Labels(this,"0",1295,500,10,10,2);
        monedas=new Labels(this,"0",1295,540,10,10,2);
        rango=new Labels(this,"0",1295,580,10,10,2);
    }

    public void configBotones(){
        //this.botonAtras = crearBoton(this, 1, 150, 150, 75, 75,"Boton");
        this.tirar = new Boton(this, 13, 150, 38, 1400, 850,1);
        this.abrirMenu = new Boton(this, 14, 40, 40, 1550, 50,1);
        this.cerrarMenu=new Boton(this,15,20,20,635,360,5);
        this.terminarPartida=new Boton(this,16,100,50,800,500,5);
        this.guardar=new Boton(this,17,100,50,800,420,5);
        this.saltarTurno=new Boton(this,18,38,38,1570,850,1);
    }

    public void configFotos(){
        this.fondoTablero = new Estaticos(this, 1, 1200, 900, 600, 450,0);
        this.barraLateral = new Estaticos(this, 2, 400, 900, 1400, 450,0);
        this.menu= new Estaticos(this,3, 350,200,800,450,4);
        this.menuStats= new Estaticos(this,4,50,150,1252,520,1);
    }

    /**
    public void configCasillas(){
        //Recorremos el mapa y por casa casilla con valor mayor a 0 crea una foto con la imagen de una casilla
        for(int i = 0; i < ALTO_MAPA; i++){
            for(int j = 0; j < ANCHO_MAPA; j++){
                if(juego.tablero[i][j].getEfecto()!=0){
                    if(juego.tablero[i][j].isRevelada()){
                        getFotos().add(juego.tablero[i][j].getFoto());
                    }else{
                        getFotos().add(juego.tablero[i][j].getFotoOculta());
                    }

                }
            }
        }
    }*/

    /**
    public void configJugadores(){
        for(Jugador j:juego.getListaJugadores()){
            getFotos().add(j.getFoto());
            j.setIndiceFoto(getFotos().size()-1);
        }
    }*/

    //Cada jugador juega su turno hasta el final del juego
    public void manejarEventos(){
        while(!partidaTerminada){
            for (Jugador juga : juego.getListaJugadores()) {
                turno.setText("Turno del jugador "+(juga.getNumeroJugador()+1));
                if(juga.getEfectoCasilla()==5){
                    juga.limpiarEfectos();
                }else{
                    juga.turnoJugador();
                }
                if(partidaTerminada){
                    break;
                }
            }
        }
        if(terminarPartida.isBotonPress()){
            juego=null;
            ventana.cambiarPantalla(new PantallaInicio(ventana));
        }
    }
    public void activarMenu(){
        tirar.block();
        cerrarMenu.setVisible(true);
        cerrarMenu.unblock();
        terminarPartida.setVisible(true);
        terminarPartida.unblock();
        menu.setVisible(true);
        guardar.setVisible(true);
        guardar.unblock();
    }
    public void desactivarMenu(){
        tirar.unblock();
        cerrarMenu.setVisible(false);
        cerrarMenu.block();
        terminarPartida.setVisible(false);
        terminarPartida.block();
        menu.setVisible(false);
        guardar.setVisible(false);
        guardar.block();
        abrirMenu.setBotonPress(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        repintar(g);
    }
}


