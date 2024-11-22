package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import AplicacionPack.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Casillas extends Foto{

    public Casillas(Pantalla pantalla, int id, int fotoAncho, int fotoAlto, int fotoX, int fotoY, int capa) {
        super(pantalla, id, fotoAncho, fotoAlto, fotoX, fotoY,1,capa);

        try {

            super.imagen= ImageIO.read(new File(Sprite.getCasillas(id)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void setImagen(int id) {
        try {
            super.imagen=ImageIO.read(new File(Sprite.getCasillas(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
