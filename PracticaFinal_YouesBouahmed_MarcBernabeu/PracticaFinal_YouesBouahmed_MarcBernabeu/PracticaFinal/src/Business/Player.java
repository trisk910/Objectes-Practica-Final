package Business;

import playerDataGenerator.PlayerDataGenerator;

import java.util.ArrayList;

public class Player {
    //Atributos
    private final String name;
    private final int speed;
    private final int endurance;
    private final int trickery;
    private final double value;
    private int scoredPoints;
    private int pWonGames;
    private int pLostGames;
    /**
     * Constructor de la clase Player
     * @param name
     * @param speed
     * @param endurance
     * @param trickery
     * @param value
     * @param scoredPoints
     * @param pWonGames
     * @param pLostGames
     */
    //Métodos
    public Player (String name, int speed, int endurance, int trickery, double value, int scoredPoints, int pWonGames,int pLostGames) {
        this.name = name;
        this.speed = speed;
        this.endurance = endurance;
        this.trickery = trickery;
        this.value = value;
        this.scoredPoints = scoredPoints;
        this.pWonGames = pWonGames;
        this.pLostGames = pLostGames;
    }

    /**
     * Método que devuelve el nombre del jugador
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Método que devuelve la velocidad del jugador
     * @return speed
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * Método que devuelve la resistencia del jugador
     * @return endurance
     */
    public int getEndurance() {
        return endurance;
    }
    /**
     * Método que devuelve la habilidad del jugador
     * @return trickery
     */
    public int getTrickery() {
        return trickery;
    }
    /**
     * Método que devuelve el valor del jugador
     * @return value
     */
    public double getValue() {
        return value;
    }/**
     * Método que devuelve los puntos marcados por el jugador
     * @return scoredPoints
     */
    public int getScoredPoints() {
        return scoredPoints;
    }
    /**
     * Método que devuelve los partidos ganados por el jugador
     * @return pWonGames
     */
    public int getpWonGames() {
        return pWonGames;
    }
    /**
     * Método que devuelve los partidos perdidos por el jugador
     * @return pLostGames
     */
    public int getpLostGames() {
        return pLostGames;
    }
    /**
     * Método que añade los partidos ganados por el jugador
     */
    public void addpWonGames() {
        pWonGames++;
    }
    /**
     * Método que añade los partidos perdidos por el jugador
     */
    public void addpLostGames() {
        pLostGames++;
    }
    /**
     * Método que devuelve los partidos jugados por el jugador
     * @return pLostGames + pWonGames
     */
    public int getParticipatedMatches(){
        return pLostGames + pWonGames;
    }

    /**
     * Método que muestra los datos del jugador
     */
    public void showStats() {
        System.out.println("Speed: "+speed);
        System.out.println("Endurance: "+endurance);
        System.out.println("trickery: "+trickery);
    }
    /**
     * Método que añade los puntos marcados por el jugador
     */
    public void addScoredPoints(int scoredPoints){
        this.scoredPoints += scoredPoints;
    }
}
