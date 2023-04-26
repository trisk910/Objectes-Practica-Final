package Presentation;

import Business.Team;
import Business.TeamsManager;
import Persistence.TeamsDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class SpectatorMenu {

    //Atributos
    private ArrayList<Team> teamsList;
    private final MainMenu menu;
    private final TeamsManager teamsManager;

    //Métodos
    /**
     * Constructor de la clase SpectatorMenu
     * @param menu
     * @param teamsManager
     */
    public SpectatorMenu(MainMenu menu, TeamsManager teamsManager) {
        this.menu = menu;
        this.teamsManager = teamsManager;
    }

    /**
     * Método que muestra el menú de espectador y devuelve la opción escogida
     * @return int
     */
    public int spectatorMenu() {
        System.out.println("\n\tEntering spectator mode...");
        System.out.println("\tSpectator menu:\n");
        System.out.println("\t1) Show team info");
        System.out.println("\t2) Start a match\n");
        System.out.println("\t3) Exit\n");
        System.out.print("Enter an option: ");

        Scanner scn = new Scanner(System.in);

        try {
            int option = scn.nextInt();
            if (option == 1 || option == 2 || option == 3) {
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
     * Método que obtiene la elección del usuario y llama al método correspondiente en el menú Spectator
     */
    public void spectatorSwitchOptions() {

        teamsList = teamsManager.getTeamsList();

        int spectatorOption = spectatorMenu();

        switch (spectatorOption) {
            //Consultar equipo
            case 1:
                System.out.println();

                for (int i = 0; i < teamsList.size(); i++) {
                    System.out.println("\t" +(i+1) + ") " + teamsList.get(i).getName());
                }

                System.out.print("\nWhat team do you want to see? ");

                //Proceso para escoger un equipo correcto entre 8
                int chosenTeam = menu.chooseTeam(0);

                //Mostramos información del equipo
                if(chosenTeam > 0 && chosenTeam <= 8) {
                    teamsList.get(chosenTeam -1).showTeamInfo();
                    teamsList.get(chosenTeam-1).showPlayers();
                    System.out.println("\n-----------------------");
                }
                break;
            //Iniciar un partido
            case 2:
                boolean findLocalTeam = false;
                int teamPos1 = -1;
                Scanner scn = new Scanner(System.in);
                while (!findLocalTeam) {
                    System.out.print("Choose local team: ");
                    String teamName = scn.nextLine().toLowerCase();
                    teamPos1 = teamsManager.findTeam(teamName);

                    if (teamPos1 == -1) {
                        System.out.println("Team not found!");
                    } else {
                        findLocalTeam = true;
                    }
                }

                boolean findVisitingTeam = false;
                int teamPos2 = -1;
                while (!findVisitingTeam) {
                    System.out.print("Choose visiting team: ");
                    String teamName = scn.nextLine().toLowerCase();
                    teamPos2 = teamsManager.findTeam(teamName);

                    if (teamPos2 == -1) {
                        System.out.println("Team not found!");
                    } else {
                        findVisitingTeam = true;
                    }
                }

                teamsManager.startMatch(teamPos1, teamPos2);
                System.out.println("\n-----------------------");
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

}


