package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Layout{
    protected Pantalla pantalla;

    int TAMANIO_MAXIMO_ANCHO=1600;
    int TAMANIO_MAXIMO_ALTO=900;

    protected int index;
    protected int capa;
    protected String clase;

    protected double ancho;
    protected double alto;
    protected double x;
    protected double y;

    protected double anchoReal;
    protected double altoReal;
    protected double xReal;
    protected double yReal;

    boolean visible=true;

    public Layout(Pantalla pantalla, int ancho, int alto, int x, int y, int capa, String clase){
        this.pantalla=pantalla;
        this.ancho=ancho;
        this.alto=alto;
        this.x=x;
        this.y=y;
        this.capa=capa;
        this.clase=clase;

        pantalla.getDrawable(capa).add(this);
        this.index=pantalla.getDrawable(capa).size()-1;
        guardarCoordenadas();
    }
    public void escalar(){
        anchoReal = pantalla.getWidth() * ancho / TAMANIO_MAXIMO_ANCHO;
        altoReal = pantalla.getHeight() * alto / TAMANIO_MAXIMO_ALTO;
        xReal = (pantalla.getWidth() * x / TAMANIO_MAXIMO_ANCHO) - anchoReal / 2;
        yReal = (pantalla.getHeight() * y / TAMANIO_MAXIMO_ALTO) - altoReal / 2;
    }
    public void dibujar(Graphics2D g2d){

    }
    public boolean isVisible(){
        return visible;
    }
    public String getClase(){
        return clase;
    }

    public void guardarCoordenadas(){
        switch(clase){
            case "boton"->{
                pantalla.getBotones().add(new Integer[]{capa, index});
            }
            case "foto"->{
                pantalla.getFotos().add(new Integer[]{capa, index});
            }
            case "labels"->{
                pantalla.getLabels().add(new Integer[]{capa, index});
            }
        }
    }

    public int getCapa() {
        return capa;
    }

    public int getIndex() {
        return index;
    }
}
