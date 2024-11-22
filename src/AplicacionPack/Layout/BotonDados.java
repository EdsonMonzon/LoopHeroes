package AplicacionPack.Layout;

import AplicacionPack.Pantallas.Pantalla;
import AplicacionPack.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BotonDados extends Boton{
    public BotonDados(Pantalla pantalla, int id, int botonAncho, int botonAlto, int botonX, int botonY, int capa) {
        super(pantalla, id, botonAncho, botonAlto, botonX, botonY, capa);

        try {
            super.imgBotonNormal = ImageIO.read(new File(Sprite.getBotonesDadoNormal(id)));
            super.imgBotonHover = ImageIO.read(new File(Sprite.getBotonesDadoHover(id)));
            super.imgBotonPress = ImageIO.read(new File(Sprite.getBotonesDadoPress(id)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setId(int id) {
        try {
            super.imgBotonNormal = ImageIO.read(new File(Sprite.getBotonesDadoNormal(id)));
            super.imgBotonHover = ImageIO.read(new File(Sprite.getBotonesDadoHover(id)));
            super.imgBotonPress = ImageIO.read(new File(Sprite.getBotonesDadoPress(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
