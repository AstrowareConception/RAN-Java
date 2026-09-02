package fr.abyss;

import fr.abyss.service.FleetService;
import fr.abyss.service.MissionAssignmentService;
import fr.abyss.ui.ConsoleApplication;

/**
 * Point d'entrée de l'application Abyss Explorer — Parcours Conception Objet.
 */
public class Main {

    public static void main(String[] args) {
        MissionAssignmentService assignmentService = new MissionAssignmentService();
        FleetService fleetService = new FleetService(assignmentService);
        ConsoleApplication consoleApp = new ConsoleApplication(fleetService);

        consoleApp.run();
    }
}
