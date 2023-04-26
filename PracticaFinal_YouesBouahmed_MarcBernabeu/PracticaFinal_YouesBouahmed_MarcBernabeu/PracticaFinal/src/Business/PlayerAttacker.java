package Business;


public class PlayerAttacker extends Player {

    //Atributos
    private final int accuracy;

    //Métodos
    /**
     * Constructor de la clase PlayerAttacker, hereda de la clase Player
     * @param name
     * @param speed
     * @param endurance
     * @param trickery
     * @param value
     * @param scoredPoints
     * @param pWonGames
     * @param pLostGames
     * @param accuracy
     */
    public PlayerAttacker(String name, int speed, int endurance, int trickery, double value, int accuracy, int scoredPoints, int pWonGames,int pLostGames) {
        super(name,speed,endurance,trickery,value,scoredPoints,pWonGames,pLostGames);
        this.accuracy = accuracy;
    }

    /**
     * Método que devuelve la precisión del jugador
     * @return accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }
    /**
     * Método que hereda de la clase Playeer y muestra los datos del jugador
     */
    public void showStats() {
        super.showStats();
        System.out.println("Accuracy: "+accuracy);
    }
}
