package Presentation;

import Business.Team;
import Business.TeamsManager;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {

    //Atributos
    private final TeamsManager teamsManager;

    /**
     * Constructor de la clase MainMenu
     * @param teamsManager
     */
    public MainMenu(TeamsManager teamsManager) {
        this.teamsManager = teamsManager;
    }

    /**
     * Método que muestra el menú principal y muestra un mensage en el caso de encontrar el archivo JSON vacío
     */
    public void futureball() {
        System.out.println("\t______         _                       _             _  _ ");
        System.out.println("\t|  ___|       | |                     | |           | || |");
        System.out.println("\t| |_    _   _ | |_  _   _  _ __   ___ | |__    __ _ | || |");
        System.out.println("\t|  _|  | | | || __|| | | || '__| / _ \\| '_ \\  / _` || || |");
        System.out.println("\t| |    | |_| || |_ | |_| || |   |  __/| |_) || (_| || || |");
        System.out.println("\t\\_|     \\__,_| \\__| \\__,_||_|    \\___||_.__/  \\__,_||_||_|\n");


        if (teamsManager.getTeamsFindJson()){
            System.out.println("\t(Teams not found. Generated 8 new teams)");
        }
    }

    /**
     * Método que muestra el menú principal del programa y devuelve la opción elegida
     * @return option
     */
    public String show() {

        String option = " ";
        boolean isCorrectOption = false;

        while (!isCorrectOption) {
            System.out.println("\tWelcome to Futureball. Who are you?\n");
            System.out.println("\tA) Manager");
            System.out.println("\tB) Spectator\n");
            System.out.print("Enter a role: ");

            Scanner scn = new Scanner(System.in);
            try {
                option = scn.next().toLowerCase();
                if (option.equals("a") || option.equals("b")) {
                    isCorrectOption = true;
                    return option;
                } else {
                    System.out.println("\nIncorrect option!\n");
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
            }
        }
        return option;
    }

    /**
     * Método que comprueba el equipo escogido y devuelve el índice del equipo
     * @param chosenTeam
     * @return chosenTeam
     */
    public int chooseTeam(int chosenTeam) {

        boolean chooseTeamFlag = false;

        while (!chooseTeamFlag) {
            try {
                Scanner scn = new Scanner(System.in);
                chosenTeam = Integer.parseInt(scn.next());

                if (chosenTeam < 1 || chosenTeam > 8) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Choose again: ");
                } else {
                    chooseTeamFlag = true;
                }

            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Choose again: ");
            }
        }
        return chosenTeam;
    }

    /**
     * Método que pide al usuario si desea cambiar el nombre del equipo y devuelve la respuesta
     * @return answer
     */
    public String changeNameAnswer(String answer) {

        boolean answerName = false;

        while (!answerName) {
            try {
                Scanner scn = new Scanner(System.in);
                answer = scn.next().toLowerCase();
                if (!answer.equals("y") && !answer.equals("n")) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Do you wanna change the team's name (y/n)? ");
                } else {
                    answerName = true;
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Do you wanna change the team's name (y/n)? ");
            }
        }
        return answer;
    }

    /**
     * Método que pide al usuario si desea ver el detalle de un partido, comprueba la respuesta del usuario y la devuelve
     * @return answer
     */
    public String detailMatchAnswer(String answer) {

        boolean answerDetail = false;

        while (!answerDetail) {
            try {
                Scanner scn = new Scanner(System.in);
                answer = scn.next().toLowerCase();
                if (!answer.equals("y") && !answer.equals("n")) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Wanna see detail for one of the matches (y/n)? ");
                } else {
                    answerDetail = true;
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Wanna see detail for one of the matches (y/n)? ");
            }
        }
        return answer;
    }

    /**
     * Método que pide al usuario si desea ver el detalle de un jugador, comprueba la respuesta del usuario y la devuelve
     * @return answer
     */
    public String detailPlayerAnswer(String answer) {

        boolean answerDetail = false;

        while (!answerDetail) {
            try {
                Scanner scn = new Scanner(System.in);
                answer = scn.next().toLowerCase();
                if (!answer.equals("y") && !answer.equals("n")) {
                    System.out.println("\nIncorrect option!\n");
                    System.out.print("Wanna see the detail for one of the players (y/n)? ");
                } else {
                    answerDetail = true;
                }
            } catch (Exception e) {
                System.out.println("\nIncorrect option!\n");
                System.out.print("Wanna see the detail for one of the players (y/n)? ");
            }
        }
        return answer;
    }

}
