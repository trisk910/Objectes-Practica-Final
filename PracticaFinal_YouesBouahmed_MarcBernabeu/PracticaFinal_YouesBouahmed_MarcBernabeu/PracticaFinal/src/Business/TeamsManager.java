package Business;

import Persistence.TeamsDAO;

import java.util.ArrayList;
import java.util.Random;

public class TeamsManager {

    private final TeamsDAO teamsDAO;
    private boolean teamsFindJson;
    private final ArrayList<Team> teamsList;
    private final MatchPlaying matchPlaying;
    //private final ArrayList<String> matchDetail = new ArrayList<>();

    /**
     * Constructor de la clase TeamsManager
     * @param teamsDAO
     * @param matchPlaying
     */
    public TeamsManager(TeamsDAO teamsDAO, MatchPlaying matchPlaying) {
        this.teamsDAO = teamsDAO;
        this.teamsList = teamsDAO.getTeams();
        this.matchPlaying = matchPlaying;
    }

    /**
     * Método que devuelve un boleano que indica si se han encontrado los equipos en el fichero json
     * @return teamsFindJson
     */
    public boolean getTeamsFindJson() {
        teamsFindJson = teamsDAO.getFoundTeamsJson();
        return teamsFindJson;
    }

    /**
     * Método que devuelve la lista de equipos
     * @return teamsList
     */
    public ArrayList<Team> getTeamsList() {
        return teamsList;
    }
    /**
     * Método que actualiza la lista de equipos dentro del JSON
     */
    public void updateJson() {
        teamsDAO.generateNewTeamJSON(teamsList);
    }
    /**
     * Método que devuelve la posicion del equipo que se ha seleccionado
     * @param teamName
     * @return teamPos
     */
    public int findTeam(String teamName) {
        int teamPos = -1;
        for (int i = 0; i < teamsList.size(); i++) {
            if (teamsList.get(i).getName().toLowerCase().equals(teamName)) {
                teamPos = i;
            }
        }
        return teamPos;
    }
    /**
     * Método que realiza el partido entre dos equipos
     * @param localPos
     * @param visitingPos
     */
    public void startMatch(int localPos, int visitingPos) {
        ArrayList<String> matchDetail = new ArrayList<>();
        matchDetail.clear();

        System.out.println("\nStarting match!");
        System.out.println("\n\t--- " + teamsList.get(localPos).getName() + " vs. " + teamsList.get(visitingPos).getName() + " ---\n");

        int localTeamScore = 0;
        int visitingTeamScore = 0;

        //Parte 1 partido
        //Emparejamiento 1
        Player attacker1Local = teamsList.get(localPos).getAttacker(2); //Atacante1 equipo local
        Player defender1Visitor = teamsList.get(visitingPos).getDefense(0); //Defensa1 equipo visitante

        int initialAttacker1LocalGoals = attacker1Local.getScoredPoints();

        String teamAnnouncement1 = teamsList.get(localPos).getName() + " attack!";
        matchDetail.add(teamAnnouncement1+"\n");
        System.out.println(teamAnnouncement1 + "\n");

        //Jugando partido
        ArrayList<String> detail1 = matchPlaying.matchDuel(attacker1Local, defender1Visitor);
        matchDetail.addAll(detail1);

        if (initialAttacker1LocalGoals < attacker1Local.getScoredPoints()){
            localTeamScore++;
        }
        System.out.println();

        //Emparejamiento 2
        Player attacker2Local = teamsList.get(localPos).getAttacker(3); //Atacante2 equipo local
        Player defender2Visitor = teamsList.get(visitingPos).getDefense(1); //Defensa2 equipo visitante

        int initialAttacker2LocalGoals = attacker2Local.getScoredPoints();

        //Jugando partido
        ArrayList<String> detail2 = matchPlaying.matchDuel(attacker2Local, defender2Visitor);
        matchDetail.addAll(detail2);

        if (initialAttacker2LocalGoals < attacker2Local.getScoredPoints()){
            localTeamScore++;
        }
        System.out.println();

        //Parte 2 partido
        //Emparejamiento 3
        Player defender1Local = teamsList.get(localPos).getDefense(0); //Defensor1 equipo local
        Player attacker1Visitor = teamsList.get(visitingPos).getAttacker(2); //Atacante1 equipo visitante

        int initialAttacker1VisitorGoals = attacker1Visitor.getScoredPoints();

        String teamAnnouncement2 = teamsList.get(visitingPos).getName() + " attack!";
        matchDetail.add(teamAnnouncement2+"\n");
        System.out.println(teamAnnouncement2 + "\n");

        //Jugando partido
        ArrayList<String> detail3 = matchPlaying.matchDuel(attacker1Visitor, defender1Local);
        matchDetail.addAll(detail3);

         if (initialAttacker1VisitorGoals < attacker1Visitor.getScoredPoints()){
            visitingTeamScore++;
         }
        System.out.println();

        //Emparejamiento 4
        Player defender2Local = teamsList.get(localPos).getDefense(1); //Defensor2 equipo local
        Player attacker2Visitor = teamsList.get(visitingPos).getAttacker(3); //Atacante2 equipo visitante

        int initialAttacker2VisitorGoals = attacker2Visitor.getScoredPoints();

        //Jugando partido
        ArrayList<String> detail4 = matchPlaying.matchDuel(attacker2Visitor, defender2Local);
        matchDetail.addAll(detail4);

        if (initialAttacker2VisitorGoals < attacker2Visitor.getScoredPoints()){
            visitingTeamScore++;
        }

        System.out.println();

        //Resultado partido
        if (localTeamScore > visitingTeamScore || visitingTeamScore > localTeamScore) {
            if (localTeamScore > visitingTeamScore) {
                String winning = teamsList.get(localPos).getName() + " win the game " + localTeamScore + "/" + visitingTeamScore;
                System.out.println(winning);
                matchDetail.add(winning);

                teamsList.get(localPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(visitingPos).getName(), localTeamScore, visitingTeamScore, "win",matchDetail));
                teamsList.get(visitingPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(localPos).getName(), visitingTeamScore, localTeamScore, "lost",matchDetail));
                double currentFC = teamsList.get(localPos).getFunds();
                teamsList.get(localPos).setFunds(currentFC + 30);

                //Añadimos al jugador que participa el partido
                attacker1Local.addpWonGames();
                attacker2Local.addpWonGames();
                defender1Local.addpWonGames();
                defender2Local.addpWonGames();

                attacker1Visitor.addpLostGames();
                attacker2Visitor.addpLostGames();
                defender1Visitor.addpLostGames();
                defender2Visitor.addpLostGames();

            }
            if (visitingTeamScore > localTeamScore) {
                String winning = teamsList.get(visitingPos).getName() + " win the game " +localTeamScore  + "/" + visitingTeamScore;
                System.out.println(winning);
                matchDetail.add(winning);

                teamsList.get(visitingPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(localPos).getName(), visitingTeamScore, localTeamScore, "win",matchDetail));
                teamsList.get(localPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(visitingPos).getName(), localTeamScore, visitingTeamScore, "lost",matchDetail));
                double currentFC = teamsList.get(visitingPos).getFunds();
                teamsList.get(visitingPos).setFunds(currentFC + 30);

                //Añadimos al jugador que participa el partido
                attacker1Local.addpLostGames();
                attacker2Local.addpLostGames();
                defender1Local.addpLostGames();
                defender2Local.addpLostGames();

                attacker1Visitor.addpWonGames();
                attacker2Visitor.addpWonGames();
                defender1Visitor.addpWonGames();
                defender2Visitor.addpWonGames();

            }
        } else {
            //Caso empate
            String draw = "Match Draw!";
            System.out.println(draw);
            matchDetail.add(draw);
            String deathmatch = "Initializing deathmatch!";
            System.out.println("\n"+deathmatch+"\n");
            matchDetail.add(deathmatch);

            Random r = new Random();
            int playerDeathMatchChoiceDefender = r.nextInt(1) + 0;
            int playerDeathMatchChoiceAttacker = r.nextInt(2) + 2;

            Player defenderDeathLocal = teamsList.get(localPos).getDefense(playerDeathMatchChoiceDefender); //Defensa equipo local
            Player attackerDeathVisitor = teamsList.get(visitingPos).getAttacker(playerDeathMatchChoiceAttacker); //Atacante equipo visitante

            int initialAttacker1VisitorGoalsDeathMatch = attackerDeathVisitor.getScoredPoints();

            ArrayList<String> deathMatchDetail = matchPlaying.matchDuel(attackerDeathVisitor, defenderDeathLocal);
            matchDetail.addAll(deathMatchDetail);

            if (initialAttacker1VisitorGoalsDeathMatch < attackerDeathVisitor.getScoredPoints()){
                visitingTeamScore++;
            } else {
                localTeamScore++;
            }

            if (visitingTeamScore > localTeamScore) {
                String winning = "\n" +  teamsList.get(visitingPos).getName() + " win the game " +localTeamScore  + "/" + visitingTeamScore;
                System.out.println(winning);
                matchDetail.add(winning);
                teamsList.get(visitingPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(localPos).getName(), visitingTeamScore,localTeamScore , "win",matchDetail));
                teamsList.get(localPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(visitingPos).getName(), localTeamScore, visitingTeamScore, "lost",matchDetail));
                double currentFC = teamsList.get(visitingPos).getFunds();
                teamsList.get(visitingPos).setFunds(currentFC + 30);

                //Añadimos al jugador que participa el partido
                attacker1Local.addpLostGames();
                attacker2Local.addpLostGames();
                defender1Local.addpLostGames();
                defender2Local.addpLostGames();

                attacker1Visitor.addpWonGames();
                attacker2Visitor.addpWonGames();
                defender1Visitor.addpWonGames();
                defender2Visitor.addpWonGames();

            } else {
                String retake = "\n" + defenderDeathLocal.getName() + " retakes the ball and.. scores 1 point";
                System.out.println(retake);
                matchDetail.add(retake);
                defenderDeathLocal.addScoredPoints(1);

                String winning = "\n" + teamsList.get(localPos).getName() + " win the game " + localTeamScore + "/" + visitingTeamScore;
                System.out.println(winning);
                matchDetail.add(winning);
                teamsList.get(localPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(visitingPos).getName(), localTeamScore, visitingTeamScore, "win",matchDetail));
                teamsList.get(visitingPos).addMatchPlayed(new MatchesTeamPlayed(teamsList.get(localPos).getName(), visitingTeamScore , localTeamScore, "lost",matchDetail));
                double currentFC = teamsList.get(localPos).getFunds();
                teamsList.get(localPos).setFunds(currentFC + 30);

                //Añadimos al jugador que participa el partido
                attacker1Local.addpWonGames();
                attacker2Local.addpWonGames();
                defender1Local.addpWonGames();
                defender2Local.addpWonGames();

                attacker1Visitor.addpLostGames();
                attacker2Visitor.addpLostGames();
                defender1Visitor.addpLostGames();
                defender2Visitor.addpLostGames();
            }
        }
        teamsList.get(localPos).resultGames();
        teamsList.get(visitingPos).resultGames();
        teamsList.get(localPos).resultGoals();
        teamsList.get(visitingPos).resultGoals();

        updateJson();
    }
}