import AplicacionPack.Layout.TextBox;
import AplicacionPack.Pantallas.*;
import AplicacionPack.Ventana;
import PartidaPack.Juego;


//Ejecuta el juego
public class Main {

    public static void main(String[] args) {
        mostrarMenu();
    }

    //Inicia el juego
    public static void mostrarMenu() {

        //Crea un nuevo objeto de clase Ventana
        Ventana ventana = new Ventana();

        //Crea una pantalla y la agrega a ventana
        Pantalla pantalla = new PantallaInicio(ventana);

        //Muestra la pantalla
        ventana.setPantalla(pantalla);
        ventana.getContentPane().add(pantalla);
        ventana.setVisible(true);

        //Constantemente revisamos la pantalla que se encuentre en la ventana
        while (true) {
                pantalla = ventana.getPantalla();
                pantalla.manejarEventos();

            descansar(10);
        }
    }

    //Metodo que descansa X segundo el hilo
    public static void descansar(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Toda la logica de las pantallas se basa en revisar si se presiono un boton especifico en una
     * pantalla especifica y en base a eso cambiar la pantalla que se muestra en la ventana
*/
     }
