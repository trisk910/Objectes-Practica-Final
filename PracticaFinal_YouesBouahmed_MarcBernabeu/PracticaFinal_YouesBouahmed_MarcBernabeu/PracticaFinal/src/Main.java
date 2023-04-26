import Business.MatchPlaying;
import Business.TeamsManager;
import Persistence.TeamsDAO;
import Presentation.MainMenu;
import Presentation.ManagerMenu;
import Presentation.SpectatorMenu;
import Presentation.UIController;


/*******************************************************************************
 *
 * @Proposito: programa que simula la gestión de un torneo de fútbol. Existen dos tipos de usuarios: el administrador y el espectador. En el administrador se puede gestionar
 * y mostrar información de un equipo(partidos, historial de partidos, jugadores...)  y en el espectador se puede mostrar la infomación de un equipo y realizar partidos entre los equipos escogidos.
 * @Autor/es: Younes Bouahmed Alavedra, Marc Bernabeu Rodriguez.
 * Fecha creacion: 28/12/22
 * Fecha ultima modificacion: 14/01/23
 *
 * *****************************************************************************/

/**
 * Clase Principal del programa.
 */
public class Main {
    public static void main(String[] args) {

        TeamsDAO dao1 = new TeamsDAO();
        MatchPlaying matchPlaying = new MatchPlaying();
        TeamsManager teamsManager = new TeamsManager(dao1, matchPlaying);


        MainMenu menu = new MainMenu(teamsManager);
        ManagerMenu managerMenu = new ManagerMenu(menu, teamsManager);
        SpectatorMenu spectatorMenu = new SpectatorMenu(menu,teamsManager);

        UIController controller = new UIController(menu, managerMenu, spectatorMenu, teamsManager);

        controller.start();
    }
}