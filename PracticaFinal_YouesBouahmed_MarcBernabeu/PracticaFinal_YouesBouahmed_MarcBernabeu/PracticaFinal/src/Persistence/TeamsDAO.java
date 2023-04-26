package Persistence;

import Business.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.google.gson.JsonParser;
import playerDataGenerator.PlayerDataGenerator;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TeamsDAO {

    private boolean foundTeamsJson;

    /**
     * Método que genera un total de 4 jugadores de forma aletoria
     * @return players
     */
    private ArrayList<Player> assignPlayers(){
        ArrayList<Player> playerArrayList = new ArrayList<>();

        //Generamos la información del jugador
        PlayerDataGenerator generatePlayer =  new PlayerDataGenerator();
        for (int i = 0; i < 4; i++) {

            String completeName = generatePlayer.GeneratePlayerName();
            completeName = completeName.replaceAll("(\\r|\\n)", "");

            int speed = generatePlayer.GenerateAttackingPlayerStats().getSpeed();
            int endurance = generatePlayer.GenerateAttackingPlayerStats().getEndurance();
            int trickery = generatePlayer.GenerateAttackingPlayerStats().getTrickery();
            int scPoints = 0;
            if (i > 1) {
                int accuracy = generatePlayer.GenerateAttackingPlayerStats().getAccuracy();
                double value = (((speed + endurance + trickery) / 3) * accuracy) / 100;
                playerArrayList.add(new PlayerAttacker(completeName,speed,endurance,trickery,value,accuracy,scPoints,0,0));
            } else {
                double value = ((speed + endurance + trickery) / 3) * 0.75;
                playerArrayList.add(new Player(completeName,speed,endurance,trickery, value,scPoints,0,0));
            }
        }
        return playerArrayList;
    }

    /**
     * Método que genera un total de 8 equipos de forma aleatoria. En el caso de existir estos equipos en el fichero JSON, se cargan estos equipos
     * @return teams
     */
    public ArrayList<Team> getTeams(){
        ArrayList<Team> teamArrayList = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());

        Gson gson = new Gson();

         String jsonString = null;
         boolean createTeam = false;

         try {
             jsonString = Files.readString(Paths.get("./teams.json"));
         } catch (Exception e) {
             e.printStackTrace();
         }

         if (jsonString.isEmpty()) {
             createTeam = true;
             foundTeamsJson = true;
         }
         System.out.println();

         if (createTeam) {

             for (int i = 0; i < 8 ; i++) {
                 String teamName = generateTeamName();
                 compareTeamName(teamArrayList,teamName);

                 double funds = r.nextInt((400+1)-100) + 100;

                 ArrayList<MatchesTeamPlayed> generateMatchesArrayList = new ArrayList<>();

                 ArrayList<Player> playerArrayList = assignPlayers();

                 int scoredPoints = 0;
                 int concededPoints = 0;

                 teamArrayList.add(new Team(teamName,funds,scoredPoints,concededPoints,generateMatchesArrayList,playerArrayList));
             }
             generateNewTeamJSON(teamArrayList);
         } else {
             JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();

             for (int i = 0; i < jsonArray.size(); i++) {
                String teamName = jsonArray.get(i).getAsJsonObject().get("name").getAsString();
                ArrayList<Player> playerArrayList = new ArrayList();
                ArrayList<MatchesTeamPlayed> matchesReadJSON = new ArrayList<>();

                double funds = jsonArray.get(i).getAsJsonObject().get("funds").getAsDouble();
                int scoredPoints = jsonArray.get(i).getAsJsonObject().get("scoredPoints").getAsInt();
                int concededPoints = jsonArray.get(i).getAsJsonObject().get("concededPoints").getAsInt();
                JsonArray pArray = jsonArray.get(i).getAsJsonObject().get("playerList").getAsJsonArray();

                for (int a = 0; a < pArray.size(); a++) {
                    int accuracy;
                    try {
                        accuracy = pArray.get(a).getAsJsonObject().get("accuracy").getAsInt();
                    }  catch (NullPointerException e) {
                        accuracy  = 0;
                    }

                    String name =  pArray.get(a).getAsJsonObject().get("name").getAsString();
                    int speed = pArray.get(a).getAsJsonObject().get("speed").getAsInt();
                    int endurance = pArray.get(a).getAsJsonObject().get("endurance").getAsInt();
                    int trickery = pArray.get(a).getAsJsonObject().get("trickery").getAsInt();
                    double value = pArray.get(a).getAsJsonObject().get("value").getAsDouble();

                    int scPoints = pArray.get(a).getAsJsonObject().get("scoredPoints").getAsInt();

                    int pWonGames = pArray.get(a).getAsJsonObject().get("pWonGames").getAsInt();
                    int pLostGames = pArray.get(a).getAsJsonObject().get("pLostGames").getAsInt();

                    if (accuracy == 0) {
                        playerArrayList.add(new Player(name, speed, endurance, trickery,value,scPoints,pWonGames,pLostGames));
                    }
                    else {
                        playerArrayList.add(new PlayerAttacker(name,speed,endurance,trickery,value,accuracy,scPoints,pWonGames,pLostGames));
                    }
                }

                JsonArray mArray = jsonArray.get(i).getAsJsonObject().get("matchesTeamPlayedArrayList").getAsJsonArray();

                for (int z = 0; z <mArray.size(); z++) {
                    String rival = mArray.get(z).getAsJsonObject().get("rival").getAsString();
                    int scoredGoals = mArray.get(z).getAsJsonObject().get("scoredGoals").getAsInt();
                    int concededGoals = mArray.get(z).getAsJsonObject().get("concededGoals").getAsInt();
                    String result = mArray.get(z).getAsJsonObject().get("result").getAsString();

                    String[] detail;
                    ArrayList<String> detailList = new ArrayList<>();
                    try {
                        JsonArray detailArray = mArray.get(z).getAsJsonObject().get("detail").getAsJsonArray();
                        detail = gson.fromJson(detailArray, String[].class);
                        detailList = new ArrayList<>(Arrays.asList(detail));

                    } catch (NullPointerException e) {
                        detail = null;
                    }
                    matchesReadJSON.add(new MatchesTeamPlayed(rival,scoredGoals,concededGoals,result,detailList));
                }

                 teamArrayList.add(new Team(teamName,funds,scoredPoints,concededPoints,matchesReadJSON,playerArrayList));
            }
         }
         return teamArrayList;
     }

     /**
      * Método que lee un fichero y lo devuelve en forma de cadena de texto
      * @param filename
      * @return String
      */
    public String readFile(String filename){
        File file = new File(filename);
        Scanner scn;

        try {
            scn = new Scanner(file);
        } catch (Exception e) {
            System.out.println("File not found");
            return "";
        }
        return scn.nextLine();
    }

    /**
     * Método que genera un archivo JSON con los equipos generados a partir de un ArrayList de equipos
     * @param teamArrayList
     */
    public void generateNewTeamJSON(ArrayList<Team> teamArrayList) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(teamArrayList);

        try {
            Files.writeString(Paths.get("./teams.json"), jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que genera un nombre de equipo aleatorio a partir de una lista de nombres de equipos dentro de un fichero .txt
     * @return String
     */
    public String generateTeamName(){
        String teamsFile = "";

        try {
            teamsFile = readFile("./teamsName.txt");
        } catch (Exception a){
            a.printStackTrace();
        }

        Random r = new Random(System.currentTimeMillis());
        String[] teamNames = teamsFile.split("-");
        String chosenTeamName = teamNames[r.nextInt(teamNames.length)];
        return chosenTeamName;
    }

    /**
     * Método que comprueba que el nombre del equipo generado no se repita dentro de la lista de los equipos
     */
    public void compareTeamName(ArrayList<Team> teamArrayList, String teamName) {
        for (int z = 0; z < teamArrayList.size(); z++){
            if(teamArrayList.get(z).getName().equals(teamName)) {
                teamName = generateTeamName();
                teamArrayList.get(z).setName(teamName);
            }
        }
    }

    /**
     * Método que devuelve una boleana para comprobar si el archivo JSON está vacío
     * @return foundTeamsJson
     */
    public boolean getFoundTeamsJson() {
        return foundTeamsJson;
    }
}
