package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Foto extends Layout {

    BufferedImage imagen;
    private int id;
    private int tipo;

    boolean visible=true;

    public Foto(Pantalla pantalla, int id, int fotoAncho, int fotoAlto, int fotoX, int fotoY,int tipo, int capa) {
        super(pantalla,fotoAncho,fotoAlto,fotoX,fotoY,capa,"foto");
        this.id=id;
        this.tipo = tipo;
        this.clase="foto";

        //Guarda las proporciones
        this.ancho = fotoAncho;
        this.alto = fotoAlto;
        this.x = fotoX;
        this.y = fotoY;

    }

    public int getTipo(){
        return tipo;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    @Override
    public void dibujar(Graphics2D g2d){
        g2d.drawImage(imagen, (int) xReal, (int) yReal, (int) anchoReal, (int) altoReal,pantalla);
    }

    abstract public void setImagen(int id);

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}

