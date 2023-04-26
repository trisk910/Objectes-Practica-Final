package Business;

import java.util.ArrayList;
import java.util.Scanner;

import playerDataGenerator.PlayerDataGenerator;

public class Team {

    //Atributos
    private String name;
    private double funds;
    private int wonMatches;
    private int lostMatches;
    private int scoredPoints;
    private int concededPoints;
    private final ArrayList<Player> playerList;
    private final ArrayList<MatchesTeamPlayed> matchesTeamPlayedArrayList;

    //Métodos
    /**
     * Constructor de la clase Team
     * @param name
     * @param funds
     * @param scoredPoints
     * @param concededPoints
     * @param playerList
     * @param matchesTeamPlayedArrayList
     */
    public Team(String name, double funds, int scoredPoints, int concededPoints, ArrayList<MatchesTeamPlayed> matchesTeamPlayedArrayList, ArrayList<Player> playerList) {
        this.name = name;
        this.funds = funds;
        this.scoredPoints = scoredPoints;
        this.concededPoints = concededPoints;
        this.matchesTeamPlayedArrayList = matchesTeamPlayedArrayList;
        this.playerList = playerList;
    }
    /**
     * Método que devuelve el nombre del equipo
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Método que devuelve los fondos del equipo
     * @return funds
     */
    public double getFunds(){return funds;}
    /**
     * Método que devuelve la array de partidos jugados
     * @return matchesTeamPlayedArrayList
     */
    public ArrayList<MatchesTeamPlayed> getMatchesPlayed(){return matchesTeamPlayedArrayList;}

    /**
     * Método que estrablece el nombre del equipo
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Método que establece los fondos del equipo
     * @param funds
     */
    public void setFunds(double funds) {
        this.funds = funds;
    }

    /**
     * Método que muestra los datos del equipo
     */
    public void showTeamInfo() {
        System.out.println("\n\t--- "+name+" ---\n");
        System.out.println("Founds: "+funds+" fc\n");
        System.out.println("Tournaments played: "+null+"\n");
        System.out.println("Matches played: "+ matchesTeamPlayedArrayList.size());
        wonMatches = 0;
        lostMatches = 0;
        resultGames();
        System.out.println("Won: "+wonMatches+"     Lost: "+lostMatches+"\n");
        resultGoals();
        System.out.println("Points scored: "+scoredPoints);
        System.out.println("Points conceded: "+concededPoints+"\n");
    }

    /**
     * Método que muestra los datos de los jugadores del equipo
     */
    public void showPlayers() {
        System.out.println("--- "+name+" ---"+"--- PLAYERS ---");

        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i) instanceof PlayerAttacker) {
                System.out.println(i+1+") "+playerList.get(i).getName()+ " - "+playerList.get(i).getSpeed()+"/"+playerList.get(i).getEndurance()+"/"+playerList.get(i).getTrickery() +" - "+((((PlayerAttacker) playerList.get(i)).getAccuracy()+"%")));
            } else {
                System.out.println(i+1+") "+playerList.get(i).getName()+ " - "+playerList.get(i).getSpeed()+"/"+playerList.get(i).getEndurance()+"/"+playerList.get(i).getTrickery());
            }
        }
    }

    /**
     * Método que actualiza los resultados de los partidos jugados por el equipo
     */
    public void resultGames() {
        wonMatches = 0;
        lostMatches = 0;
        for (int i = 0; i < matchesTeamPlayedArrayList.size(); i++) {
            if (matchesTeamPlayedArrayList.get(i).getResult().equals("win")) {
                wonMatches++;
            } else if (matchesTeamPlayedArrayList.get(i).getResult().equals("lost")){
                lostMatches++;
            }
        }
    }

    /**
     * Método que actualiza los goles anotados y encajados por el equipo
     */
    public void resultGoals() {
        scoredPoints = 0;
        concededPoints = 0;
        for (int i = 0; i < matchesTeamPlayedArrayList.size(); i++) {
            scoredPoints = scoredPoints + matchesTeamPlayedArrayList.get(i).getScoredGoals();
            concededPoints = concededPoints + matchesTeamPlayedArrayList.get(i).concededGoals();
        }
    }
    /**
     * Método que muestra el historial de los partidos jugados por el equipo
     */
    public void showMatchHistory() {
        System.out.println("\t\n--- "+ name + " --- MATCH HISTORY\n");
        for (int i = 0; i < matchesTeamPlayedArrayList.size(); i++) {

            int matchCount = 0;
            int scoredGoals = matchesTeamPlayedArrayList.get(i).getScoredGoals();
            int concededGoals = matchesTeamPlayedArrayList.get(i).concededGoals();

            if (scoredGoals > concededGoals) {
                System.out.println((i + 1) + ") " + name + " vs. " + matchesTeamPlayedArrayList.get(i).getRival() + " - " + scoredGoals + "/" + concededGoals);
            } else {
                System.out.println((i + 1) + ") " + matchesTeamPlayedArrayList.get(i).getRival() + " vs. " + name + " - " + concededGoals + "/" + scoredGoals);
            }
            matchCount++;
            if (matchCount > 10) {
                i = matchesTeamPlayedArrayList.size();
            }
        }
        System.out.println("\n----------");
    }
    /**
     * Método que pide al usuario si quiere ver el detalle de un partido mostrado anteriormente
     */
    public void showDetailMatch() {
        boolean answerTeam = false;

        while (!answerTeam) {
            try {
                Scanner scn = new Scanner(System.in);
                int answer = scn.nextInt() -1;
                if (answer < 0 || answer > matchesTeamPlayedArrayList.size()) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Which one? ");
                } else {
                    System.out.println("\n\t--- "+name+" vs. "+ matchesTeamPlayedArrayList.get(answer).getRival()+" ---\n");
                    matchesTeamPlayedArrayList.get(answer).printDetail();
                    System.out.println("\n-----------------------");

                    answerTeam = true;
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Which one? ");
            }
        }
    }
    /**
     * Método que modifica el nombre del equipo
     */
    public void changeName() {
        boolean correctName = false;
        while (!correctName) {
            System.out.print("New name: ");
            Scanner scn = new Scanner(System.in);
            String newName = scn.nextLine();

            if (newName.length() > 20) {
                System.out.print("\nThe name is too long. Must be less than 20 characters\n");
            }  else {
                correctName = true;
                name = newName;
                System.out.print("\n¡Name changed!\n");
            }
        }
    }

    /**
     * Método que subsiture a un jugador por otro dentro de un equipo seleccionado
     */
    public void contractNewPlayer(){
        System.out.print("\nWhich Player do you wanna replace? ");
        Scanner scn = new Scanner(System.in);
        int playerNum = scn.nextInt();

        System.out.println(name + " sold " + playerList.get(playerNum - 1).getName() + " for " + playerList.get(playerNum - 1).getValue() + " fc and hired a new Player for 100 fc");
        funds -= 65;

        if (funds < 100)
            System.out.println("Error: operation could not be done, the team doesn't have enough fc.");
        else {
            if (playerList.get(playerNum -1) instanceof PlayerAttacker ) {
                System.out.println(name + " has a new attacking player:");

                Player newAttacckerPlayer;
                newAttacckerPlayer = generatePlayer(1);
                String pName = newAttacckerPlayer.getName();
                int pSpeed = newAttacckerPlayer.getSpeed();
                int pEndurance = newAttacckerPlayer.getEndurance();
                int pTrickery = newAttacckerPlayer.getTrickery();
                PlayerAttacker pa = (PlayerAttacker) newAttacckerPlayer;
                int pAccuracy = pa.getAccuracy();
                double pValue = newAttacckerPlayer.getValue();

                System.out.println("\n--- "+ pName+ " ---");
                System.out.println("\nTeam: "+ name);
                System.out.println("Position: Attacker");
                System.out.println("\nSpeed: "+ pSpeed);
                System.out.println("Endurance: "+ pEndurance);
                System.out.println("Trickery: "+ pTrickery);

                System.out.println("Accuracy: "+ pAccuracy);
                System.out.println("\nValue: " + pValue +" fc");
                System.out.println("\n-----------");

                playerList.set(playerNum - 1,new PlayerAttacker(pName,pSpeed,pEndurance,pTrickery,pValue,pAccuracy,0,0,0));

            } else {
                System.out.println(name + " has a new defending player:");

                Player newDefenderPlayer;
                newDefenderPlayer = generatePlayer(1);
                String pName = newDefenderPlayer.getName();
                int pSpeed = newDefenderPlayer.getSpeed();
                int pEndurance = newDefenderPlayer.getEndurance();
                int pTrickery = newDefenderPlayer.getTrickery();

                double pValue = newDefenderPlayer.getValue();

                System.out.println("\n--- "+ pName+ " ---");
                System.out.println("\nTeam: "+ name);
                System.out.println("Position: Attacker");
                System.out.println("\nSpeed: "+ pSpeed);
                System.out.println("Endurance: "+ pEndurance);
                System.out.println("Trickery: "+ pTrickery);
                System.out.println("\nValue: " + pValue +" fc");
                System.out.println("\n-----------");

                playerList.set(playerNum - 1,new Player(pName,pSpeed,pEndurance,pTrickery,pValue,0,0,0));
            }
        }
    }
    /**
     * Método que muestra la información de un jugador
     */
    public void showPlayerDetail() {

        boolean answerPlayer = false;

        while (!answerPlayer) {
            try {
                Scanner scn = new Scanner(System.in);
                int answer = scn.nextInt() - 1;
                if (answer < 0 || answer > playerList.size()) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Which one? ");
                } else {
                    System.out.println("\n--- " + playerList.get(answer).getName() + " ---\n");
                    System.out.println("Team: " + name);

                    if (playerList.get(answer) instanceof PlayerAttacker) {
                        System.out.println("Position: Attacker\n");
                    } else {
                        System.out.println("Position: Defender\n");
                    }

                    playerList.get(answer).showStats();

                    System.out.println("\nValue: " + playerList.get(answer).getValue() + " fc\n");
                    System.out.println("Matches played: " + playerList.get(answer).getParticipatedMatches());
                    //resultGames();
                    System.out.println("Won: " + playerList.get(answer).getpWonGames() + "\tLost: "+playerList.get(answer).getpLostGames() + "\n");

                    System.out.println("Points scored: "+playerList.get(answer).getScoredPoints());

                    answerPlayer = true;
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Which one? ");
            }
        }
    }
    /**
     * Método que genera un jugador aleatorio
     * @param playerType
     * @return Player
     */
    public Player generatePlayer(int playerType) {
        PlayerDataGenerator generatePlayer =  new PlayerDataGenerator();
        String completeName = generatePlayer.GeneratePlayerName();
        completeName = completeName.replaceAll("(\\r|\\n)", "");

        Player player;
        int speed = generatePlayer.GenerateAttackingPlayerStats().getSpeed();
        int endurance = generatePlayer.GenerateAttackingPlayerStats().getEndurance();
        int trickery = generatePlayer.GenerateAttackingPlayerStats().getTrickery();
        int scPoints = 0;
        int pWonGames = 0;
        int pLostGames = 0;

        if (playerType == 1) {
            int accuracy = generatePlayer.GenerateAttackingPlayerStats().getAccuracy();
            double value = (((speed + endurance + trickery) / 3) * accuracy) / 100;
            player = new PlayerAttacker(completeName, speed, endurance, trickery, value, accuracy,scPoints,pWonGames,pLostGames);
        } else {
            double value = ((speed + endurance + trickery) / 3) * 0.75;
            player = new Player(completeName, speed, endurance, trickery, value, scPoints,pWonGames,pLostGames);
        }
        return player;
    }
    /**
     * Método que devuelve un jugador de tipo atacante
     * @param position
     * @return attacker
     */
    public Player getAttacker(int position){
        Player attacker = playerList.get(position);
        return attacker;
    }
    /**
     * Método que devuelve un jugador de tipo defensor
     * @param position
     * @return defende
     */
    public Player getDefense(int position){
        Player defense = playerList.get(position);
        return defense;
    }
    /**
     * Método que añade al equipo un partido jugado
     */
    public void addMatchPlayed(MatchesTeamPlayed match){
        matchesTeamPlayedArrayList.add(match);
    }


}
