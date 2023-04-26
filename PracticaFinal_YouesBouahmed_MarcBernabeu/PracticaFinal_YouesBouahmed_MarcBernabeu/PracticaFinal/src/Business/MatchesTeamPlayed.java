package Business;

import java.util.ArrayList;

public class MatchesTeamPlayed {
    //Atributos
    private final String rival;
    private final int scoredGoals;
    private final int concededGoals;
    private final String result;
    private final ArrayList<String> detail;

    //Métodos
    /**
     * Constructor de la clase MatchesTeamPlayed
     * @param rival
     * @param scoredGoals
     * @param concededGoals
     * @param result
     * @param detail
     */
    public MatchesTeamPlayed(String rival, int scoredGoals, int concededGoals, String result, ArrayList<String> detail) {
        this.rival = rival;
        this.scoredGoals = scoredGoals;
        this.concededGoals = concededGoals;
        this.result = result;
        this.detail = detail;
    }

    /**
     * Método que devuelve el rival
     * @return rival
     */
    public String getRival(){
        return this.rival;
    }
    /**
     * Método que devuelve los goles marcados
     * @return scoredGoals
     */
    public int getScoredGoals(){
        return this.scoredGoals;
    }
    /**
     * Método que devuelve los goles encajados
     * @return concededGoals
     */
    public int concededGoals(){
        return this.concededGoals;
    }
    /**
     * Método que devuelve el resultado
     * @return result
     */
    public String getResult(){
        return result;
    }
    /**
     * Método que muetra el detalle de cada partido
     */
    public void printDetail(){
        for (int i = 0; i < detail.size(); i++) {
            System.out.println(detail.get(i));
        }
    }
}
