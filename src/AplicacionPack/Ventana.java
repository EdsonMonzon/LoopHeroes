package AplicacionPack;

import AplicacionPack.Pantallas.Pantalla;

import javax.swing.*;

//Clase que extiende de JFrame, se usa como contenedor principal del juego
public class Ventana extends JFrame {
    private Pantalla pantalla;

    public Ventana() {
        setTitle("Champ Run");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setPantalla(Pantalla pantalla) {
        this.pantalla = pantalla;
    }

    public void cambiarPantalla(Pantalla nuevaPantalla) {
        getContentPane().remove(pantalla); // Remueve el panel actual
        pantalla = nuevaPantalla; // Asigna el nuevo panel
        getContentPane().add(pantalla); // Agrega el nuevo panel
        revalidate(); // Revalida el contenido del contenedor
        repaint(); // Repinta el contenedor
    }

    public Pantalla getPantalla() {
        return pantalla;
    }
}
