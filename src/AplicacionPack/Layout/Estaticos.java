package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import AplicacionPack.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Estaticos extends Foto{
    public Estaticos(Pantalla pantalla, int id, int fotoAncho, int fotoAlto, int fotoX, int fotoY, int capa) {
        super(pantalla, id, fotoAncho, fotoAlto, fotoX, fotoY,0,capa);

        try {

            super.imagen= ImageIO.read(new File(Sprite.getEstaticos(id)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setImagen(int id) {
        try {
            super.imagen=ImageIO.read(new File(Sprite.getEstaticos(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
