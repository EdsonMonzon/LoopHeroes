package PartidaPack;

public class EstadoJugador {
    private int numeroCasilla;
    private int heroeID;
    private int efectoCasilla;
    private int vida;
    private int rango;
    private int daño;
    private int monedas;
    private int puntos;

    public EstadoJugador(int numeroCasilla, int heroeID, int efectoCasilla, int vida, int rango, int daño, int monedas, int puntos) {
        this.numeroCasilla = numeroCasilla;
        this.heroeID = heroeID;
        this.efectoCasilla = efectoCasilla;
        this.vida = vida;
        this.rango = rango;
        this.daño = daño;
        this.monedas = monedas;
        this.puntos = puntos;
    }

    // Getters y setters para cada atributo
    public int getNumeroCasilla() { return numeroCasilla; }
    public void setNumeroCasilla(int numeroCasilla) { this.numeroCasilla = numeroCasilla; }
    public int getHeroeID() { return heroeID; }
    public void setHeroeID(int heroeID) { this.heroeID = heroeID; }
    public int getEfectoCasilla() { return efectoCasilla; }
    public void setEfectoCasilla(int efectoCasilla) { this.efectoCasilla = efectoCasilla; }
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    public int getRango() { return rango; }
    public void setRango(int rango) { this.rango = rango; }
    public int getDaño() { return daño; }
    public void setDaño(int daño) { this.daño = daño; }
    public int getMonedas() { return monedas; }
    public void setMonedas(int monedas) { this.monedas = monedas; }
    public int getPuntos() { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }
}
