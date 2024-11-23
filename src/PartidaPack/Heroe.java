package PartidaPack;

public class Heroe {
    public int id;
    public int vida=20;
    public int rango=5;
    public int daño=5;
    public int puntos=0;
    public int monedas=0;

    public Heroe(int id) {
        this.id = id;
        switch (id){
            case 1->{
                arquero();
            }
            case 2->{
                banquero();
            }
            case 3->{
                corredor();
            }
            case 4->{
                paladin();
            }
        }
    }
    private void arquero(){
        this.vida=20;
        this.rango=12;
        this.daño=5;
    }
    private void banquero(){
        this.vida=25;
        this.rango=3;
        this.daño=5;
    }
    private void corredor(){
        this.vida=18;
        this.rango=3;
        this.daño=4;
    }
    private void paladin(){
        this.vida=30;
        this.rango=1;
        this.daño=5;
    }

    public void reiniciarVida(){
        switch (id){
            case 1->{
                this.vida=20;
            }
            case 2->{
                this.vida=25;
            }
            case 3-> {
                this.vida = 18;
            }
            case 4->{
                this.vida=30;
            }
        }
    }
}
