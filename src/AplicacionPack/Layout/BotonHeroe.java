package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import AplicacionPack.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BotonHeroe extends Boton{
    public BotonHeroe(Pantalla pantalla, int id, int botonAncho, int botonAlto, int botonX, int botonY, int capa) {
        super(pantalla, id, botonAncho, botonAlto, botonX, botonY, capa);

        try {
            super.imgBotonNormal = ImageIO.read(new File(Sprite.getBotonesHeroeNormales(id)));
            super.imgBotonHover = ImageIO.read(new File(Sprite.getBotonesHeroeHover(id)));
            super.imgBotonPress = ImageIO.read(new File(Sprite.getBotonesHeroePress(id)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
