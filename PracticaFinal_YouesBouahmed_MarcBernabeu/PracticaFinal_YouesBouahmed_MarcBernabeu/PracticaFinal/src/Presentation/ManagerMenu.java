package Presentation;

import Business.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ManagerMenu {

    //Atributos
    private ArrayList<Team> teamsList;
    private ArrayList<MatchesTeamPlayed> matchesTeamPlayeds;
    private final MainMenu menu;
    private final TeamsManager teamsManager;

    //Métodos
    /**
     * Constructor de la clase ManagerMenu
     * @param menu
     * @param teamsManager
     */
    public ManagerMenu(MainMenu menu, TeamsManager teamsManager) {
        this.menu = menu;
        this.teamsManager = teamsManager;
    }

    /**
     * Método que muestra el menú de gestión de equipos y devuelve la opción escogida
     * @return int
     */
    public int managerMenu(){
        System.out.println("\n\tEntering management mode...");
        System.out.println("\tManager menu:\n");
        System.out.println("\t1) Choose a Team\n");
        System.out.println("\t2) Exit\n");
        System.out.print("Enter an option: ");

        Scanner scn = new Scanner(System.in);

        try {
            int option = scn.nextInt();
            if (option == 1 || option == 2) {
                return option;
            } else {
                System.out.println("\nIncorrect option!");
            }
            return 0;
        } catch (Exception e) {
            System.out.println("\nIncorrect option!");
            return 0;
        }
    }

    /**
     * Método que obtiene la elección del usuario y llama al método correspondiente en el menú Manager
     */
    public void managerSwitchOptions() {

        teamsList = teamsManager.getTeamsList();

        int managerOption = managerMenu();

        switch (managerOption) {
            case 1:
                System.out.println();
                for (int i = 0; i < teamsList.size(); i++) {
                    System.out.println("\t" +(i+1) + ") " + teamsList.get(i).getName());
                }

                System.out.print("\nWhat team do you want to manage? ");
                int chosenTeam = menu.chooseTeam(0);

                int teamOption = 0;

                matchesTeamPlayeds = teamsList.get(chosenTeam-1).getMatchesPlayed();

                teamOption = -1;
                while(teamOption != 5){
                    teamOption = teamManagementOptions(teamsList.get(chosenTeam-1).getName());
                    switch (teamOption) {
                        //Team info
                        case 1:
                            teamsList.get(chosenTeam-1).showTeamInfo();
                            System.out.println("-----------------------");
                            System.out.print("Do you wanna change the team's name (y/n)? ");
                            String answer1 = menu.changeNameAnswer(" ");

                            if (answer1.equals("y")) {
                                teamsList.get(chosenTeam-1).changeName();
                                teamsManager.updateJson();
                            } else {
                                System.out.println("\nNothing changed");
                            }
                            System.out.println("\n-----------------------");
                            break;
                       //Match History
                       case 2:
                           boolean stopShowing = false;
                           while (!stopShowing) {
                               if (teamsList.get(chosenTeam-1).getMatchesPlayed().size() == 0) {
                                   System.out.println("\nSorry, no match history to show");
                                   break;
                               }
                               teamsList.get(chosenTeam-1).showMatchHistory();
                               System.out.print("Wanna see detail for one of the matches (y/n)? ");
                               String answer2 = menu.detailMatchAnswer(" ");

                               if (answer2.equals("y")) {
                                   System.out.print("Which one? ");
                                   teamsList.get(chosenTeam-1).showDetailMatch();
                               } else if(answer2.equals("n")) {
                                   System.out.println("\nNothing to see");
                                   stopShowing = true;
                                   break;
                               }

                               Scanner scn = new Scanner(System.in);
                               String enter = scn.nextLine();

                               if (enter.equals("\n")) {
                                   stopShowing = false;
                               }
                           }
                           System.out.println("\n-----------------------");
                           break;
                       //Players
                       case 3:
                           System.out.println();
                           teamsList.get(chosenTeam-1).showPlayers();

                           System.out.println("\n-----------------------");
                           System.out.print("Wanna see the detail para one of the players (y/n)? ");
                           String answer3 = menu.detailPlayerAnswer(" ");

                           if (answer3.equals("y")) {
                               System.out.print("Which one? ");
                               teamsList.get(chosenTeam - 1).showPlayerDetail();
                           } else if (answer3.equals("n")) {
                               System.out.println("\nNothing to see");
                           }
                           System.out.println("\n-----------------------");
                           break;
                       //Hire new player
                       case 4:
                           System.out.println();
                           teamsList.get(chosenTeam-1).showPlayers();
                           teamsList.get(chosenTeam-1).contractNewPlayer();
                           teamsManager.updateJson();
                           break;
                       case 5:
                            break;
                       default:  System.out.println("\nIncorrect option!");
                    }
                }
            case 3:
                System.exit(0);
                break;
        }

    }

    /**
     * Método que muestra el menú de gestión de equipos y devuelve la opción escogida
     * @param team
     * @return int
     */
    public int teamManagementOptions(String team){
        System.out.println("\n\tManaging "+team+"\n");
        System.out.println("\t1) Team info");
        System.out.println("\t2) Match history");
        System.out.println("\t3) Players");
        System.out.println("\t4) Hire a new player\n");
        System.out.println("\t5) Exit\n");
        System.out.print("¿Option? ");

        Scanner scn = new Scanner(System.in);
        try {
            int option = scn.nextInt();
            return option;
        } catch (Exception e) {
            return 0;
        }
    }

}
