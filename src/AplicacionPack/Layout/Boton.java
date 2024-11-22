package AplicacionPack.Layout;

import AplicacionPack.Sprite;
import AplicacionPack.Pantallas.Pantalla;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Clase que representa un boton en la pantalla
public class Boton extends Layout{

    int id;
    boolean block=false;

    BufferedImage imgBoton;
    BufferedImage imgBotonNormal;
    BufferedImage imgBotonHover;
    BufferedImage imgBotonPress;

    private boolean botonPress = false;
    private boolean botonHover = false;

    private int capa;


    //Al crearlo es necesario pasarle la pantalla a la que pertenece
    // ,el id del que sacara las imagenes y las proporciones que tendra respecto a la ventana
    public Boton(Pantalla pantalla,int id, int botonAncho, int botonAlto, int botonX, int botonY, int capa) {
        super(pantalla,botonAncho,botonAlto,botonX,botonY,capa,"boton");
        this.id=id;
        this.clase="boton";

        //Carga las imagenes
        try {
            // Cargar las imágenes desde archivos
            this.imgBotonNormal = ImageIO.read(new File(Sprite.getBotonesNormales(id)));
            this.imgBotonHover = ImageIO.read(new File(Sprite.getBotonesHover(id)));
            this.imgBotonPress = ImageIO.read(new File(Sprite.getBotonesPress(id)));// Nueva imagen

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImgBoton() {
        return imgBoton;
    }


    public void setId(int id) {
        // Cargar las imágenes desde archivos
        try {
            this.imgBotonNormal = ImageIO.read(new File(Sprite.getBotonesNormales(id)));
            this.imgBotonHover = ImageIO.read(new File(Sprite.getBotonesHover(id)));
            this.imgBotonPress = ImageIO.read(new File(Sprite.getBotonesPress(id)));// Nueva imagen
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean getBlock(){
        return block;
    }

    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setBotonHover(boolean botonHover) {
        this.botonHover = botonHover;
    }

    public void setBotonPress(boolean botonPress) {
        this.botonPress = botonPress;
    }

    public void setAnchoReal(int anchoReal) {
        this.anchoReal = anchoReal;
    }

    public void setAltoReal(int altoReal) {
        this.altoReal = altoReal;
    }

    public void setxReal(int xReal) {
        this.xReal = xReal;
    }

    public void setyReal(int yReal) {
        this.yReal = yReal;
    }

    public boolean isBotonPress() {
        if(block){
            return false;
        }
        return botonPress;
    }

    public boolean isBotonHover() {
        return botonHover;
    }

    public BufferedImage getImgBotonNormal() {
        return imgBotonNormal;
    }

    public BufferedImage getImgBotonHover() {
        return imgBotonHover;
    }

    public BufferedImage getImgBotonPress() {
        return imgBotonPress;
    }

    public JPanel getPantalla() {
        return pantalla;
    }

    public int getAnchoReal() {
        return (int) this.anchoReal;
    }

    public int getAltoReal() {
        return (int) this.altoReal;
    }

    public int getxReal() {
        return (int) this.xReal;
    }

    public int getyReal() {
        return (int) this.yReal;
    }

    public int getId() {
        return id;
    }
    public void block(){
        this.block=true;
        this.botonPress=false;
    }
    public void unblock(){
        this.botonPress=false;
        this.block=false;
    }

    //Comprueba si las coordenadas estan dentro del boton
    public void verificaSobreBoton(int x, int y) {
        botonHover = x >= xReal && x <= xReal + anchoReal &&
                y >= yReal && y <= yReal + altoReal;
    }

    //Comprueba si las coordenadas estan dentro del boton
    public void verificaPressBoton(int x, int y) {
        if(x >= xReal && x <= xReal + anchoReal &&
                y >= yReal && y <= yReal + altoReal) botonPress = true;
    }

    //Actualiza la imagen del boton de acuerdo a su estado
    public void cambiaImgBoton() {
        if (botonPress) {
            imgBoton =imgBotonPress;
        } else if (botonHover) {
            imgBoton =imgBotonHover;
        }else{
            imgBoton =imgBotonNormal;
        }
        if(block){
            imgBoton=imgBotonPress;
        }
    }

    //Repinta el boton con la imagen, las coordenadas y las medidas anteriormente calculadas
    @Override
    public void dibujar(Graphics2D g2d){
        cambiaImgBoton();

        g2d.drawImage(imgBoton, (int) xReal, (int) yReal, (int) anchoReal, (int) altoReal,pantalla);
    }

    public void setBounds(double x, double y, double width, double height) {
        this.xReal = x;
        this.yReal = y;
        this.anchoReal = width;
        this.altoReal = height;
    }
}
