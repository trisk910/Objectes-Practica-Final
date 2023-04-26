package Presentation;

import Business.Team;
import Business.TeamsManager;
import Persistence.TeamsDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {

    //Atributos
    private final MainMenu mainMenu;
    private final TeamsManager teamsManager;
    private final ManagerMenu managerMenu;
    private final SpectatorMenu spectatorMenu;

    //Métodos
    /**
     * Constructor de la clase UIController
     * @param mainMenu
     * @param teamsManager
     * @param managerMenu
     * @param spectatorMenu
     */
    public UIController(MainMenu mainMenu, ManagerMenu managerMenu, SpectatorMenu spectatorMenu, TeamsManager teamsManager) {
        this.mainMenu = mainMenu;
        this.managerMenu = managerMenu;
        this.spectatorMenu = spectatorMenu;
        this.teamsManager = teamsManager;
    }

    /**
     * Método que muestra el menú principal y llama a la función determinada de la opción escogida
     */
    public void start () {
        mainMenu.futureball();

        String option = " ";
        boolean exitMenu = false;

        option = mainMenu.show();

        while (!exitMenu) {

            switch(option){
                case "a":
                    //Mostramos las opciones del menu del manager
                    managerMenu.managerSwitchOptions();
                    break;
                case "b":
                    //Mostramos las opciones del menu del espectador
                    spectatorMenu.spectatorSwitchOptions();
                    break;
                default:  System.out.println("\nIncorrect option!\n");
            }
        }
    }

}
