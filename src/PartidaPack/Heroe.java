package PartidaPack;

public class Heroe {
    int vida;
    int id;

    public Heroe(int id) {
        this.id = id;
        switch (id){
            case 1->{
                vida = 5;
            }
            case 2->{
                vida = 10;
            }
            case 3->{
                vida = 20;
            }
            case 4->{
                vida = 10;
            }
        }
    }
    public int getVidaHeroe() {
        return vida;
    }
}
