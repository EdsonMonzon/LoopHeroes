package AplicacionPack.Pantallas;

import AplicacionPack.Layout.*;
import AplicacionPack.Ventana;
import PartidaPack.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//Clase que extiende de JPanel, se inserta en la ventana
public abstract class Pantalla extends JPanel {

    private String tipoDePantalla;
    private BufferedImage imagen;
    protected ArrayList<Integer[]>botones=new ArrayList<>();
    protected ArrayList<Integer[]>textBoxes=new ArrayList<>();
    protected ArrayList<Integer[]>labels=new ArrayList<>();
    protected ArrayList<Integer[]> fotos =new ArrayList<>();

    public ArrayList<ArrayList<Layout>>drawable=new ArrayList<>();

    /**
     * Al ser iniciado se le coloca el nombre de la pantalla que se pasa por parametro
     * @param tipoDePantalla, nombre que tendra la pantalla
     */
    public Pantalla(String tipoDePantalla) {
        this.tipoDePantalla = tipoDePantalla;


        //Verifica si un boton fue presionado y repinta constantemente
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for(Integer[] i:botones){
                    Boton b=(Boton)drawable.get(i[0]).get(i[1]);
                    b.verificaPressBoton(x, y);
                }

                repaint();
            }
        });

        //Verifica la posicion del mouse y repinta constantemente
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                for(Integer[] i:botones){
                    Boton b=(Boton)drawable.get(i[0]).get(i[1]);
                    b.verificaSobreBoton(x, y);
                }
                repaint();
            }
        });
    }

    public ArrayList<ArrayList<Layout>> getDrawable() {
        return drawable;
    }
    public ArrayList<Layout> getDrawable(int capa) {
        if (drawable.size() <= capa) {
            for (int i = drawable.size(); i <= capa; i++) {
                drawable.add(new ArrayList<>());
            }
        }
        return drawable.get(capa);
    }

    public void setTipoDePantalla(String tipoDePantalla) {
        this.tipoDePantalla = tipoDePantalla;
    }

    public String getTipoDePantalla() {
        return tipoDePantalla;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public BufferedImage getImagen() {
        return imagen;
    }


    //Al repintar
    public void repintar(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        // Escalar la imagen de fondo al tamaño de la pantalla
        g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        for(ArrayList<Layout> l : drawable) {
            for(Layout l1 : l) {
                if(l1.isVisible()) {
                    l1.escalar();
                    l1.dibujar(g2d);
                }
            }
        }

        //Escala todos los botones, les cambia el sprite de ser nesesario y repinta
        /**
        for(Foto f : fotos){
            if(f.isVisible()){
                f.escalaFoto();
                f.dibujar(g2d);
            }
        }

        //Igual con las text boxes
        for(TextBox t : textBoxes){
            if(t.isVisible()){
                t.escalaTextBox();
                t.dibujar(g2d);
            }
        }

        for(Boton b : botones){
            if(b.isVisible()){
                b.escalaBoton();
                b.cambiaImgBoton();
                b.dibujar(g2d);
            }
        }

        for(Labels l : labels){
            if(l.isVisible()){
                l.escalaLabel();
                l.dibujar(g2d);
            }
        }*/
    }

    public static void descansar(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer[]> getBotones() {
        return botones;
    }

    public ArrayList<Integer[]> getTextBoxes() {
        return textBoxes;
    }

    public ArrayList<Integer[]> getLabels() {
        return labels;
    }

    public ArrayList<Integer[]> getFotos() {
        return fotos;
    }

    abstract public void manejarEventos();
}
