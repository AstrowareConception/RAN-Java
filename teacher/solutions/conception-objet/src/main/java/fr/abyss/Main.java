package fr.abyss;

import fr.abyss.service.FleetService;
import fr.abyss.service.MissionAssignmentService;
import fr.abyss.service.MissionPlannerService;
import fr.abyss.ui.ConsoleApplication;

/**
 * Point d'entrée de la solution Conception Objet complète.
 */
public class Main {

    public static void main(String[] args) {
        MissionAssignmentService assignmentService = new MissionAssignmentService();
        FleetService fleetService = new FleetService(assignmentService);
        MissionPlannerService plannerService = new MissionPlannerService(assignmentService);

        ConsoleApplication app = new ConsoleApplication(fleetService, plannerService);
        app.run();
    }
}
