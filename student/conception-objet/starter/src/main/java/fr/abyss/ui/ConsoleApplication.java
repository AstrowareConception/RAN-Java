package fr.abyss.ui;

import fr.abyss.domain.model.AssignmentFailureReason;
import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;
import fr.abyss.service.FleetService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Interface utilisateur textuelle (Console) pour le parcours Conception Objet.
 */
public class ConsoleApplication {

    private final FleetService fleetService;
    private final Scanner scanner;

    public ConsoleApplication(FleetService fleetService) {
        this.fleetService = fleetService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        System.out.println("======================================================");
        System.out.println("  ABYSS EXPLORER — CONSOLE (PARCOURS CONCEPTION OBJET)");
        System.out.println("======================================================");

        while (running) {
            printMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un chiffre.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayFleet();
                case 2 -> displayMissions();
                case 3 -> handleTestAssignment();
                case 4 -> handleAssignMission();
                case 5 -> handleExecuteMission();
                case 6 -> handleRechargeDrone();
                case 7 -> handleFindBestDrone();
                case 0 -> {
                    System.out.println("Arrêt du centre de supervision.");
                    running = false;
                }
                default -> System.out.println("Option inconnue.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- MENU DE CONTRÔLE ---");
        System.out.println("1. Afficher les drones");
        System.out.println("2. Afficher les missions");
        System.out.println("3. Tester une affectation (Résultat qualifié)");
        System.out.println("4. Affecter une mission");
        System.out.println("5. Exécuter une mission (R6)");
        System.out.println("6. Recharger un drone (R7)");
        System.out.println("7. Rechercher le meilleur drone pour une mission");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private void displayFleet() {
        System.out.println("\n--- FLOTTE ---");
        fleetService.getDrones().forEach(System.out::println);
    }

    private void displayMissions() {
        System.out.println("\n--- MISSIONS ---");
        fleetService.getMissions().forEach(System.out::println);
    }

    private void handleTestAssignment() {
        System.out.print("ID Drone (1-5) : ");
        int dId = scanner.nextInt();
        System.out.print("ID Mission (1-6) : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        AssignmentResult result = fleetService.evaluateAssignment(dId, mId);
        if (result.isAllowed()) {
            System.out.println("[SUCCÈS] Affectation autorisée par le modèle métier.");
        } else {
            System.out.println("[REFUS] Affectation rejetée pour les motifs suivants :");
            for (AssignmentFailureReason reason : result.reasons()) {
                System.out.println(" - " + reason.name() + " : " + reason.getDescription());
            }
        }
    }

    private void handleAssignMission() {
        System.out.print("ID Drone (1-5) : ");
        int dId = scanner.nextInt();
        System.out.print("ID Mission (1-6) : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        boolean ok = fleetService.assignMission(dId, mId);
        if (ok) {
            System.out.println("[SUCCÈS] Mission assignée.");
        } else {
            System.out.println("[ÉCHEC] Affectation rejetée.");
        }
    }

    private void handleExecuteMission() {
        System.out.print("ID Drone : ");
        int dId = scanner.nextInt();
        System.out.print("ID Mission : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        boolean ok = fleetService.executeMission(dId, mId);
        if (ok) {
            System.out.println("[SUCCÈS] Mission exécutée.");
        } else {
            System.out.println("[ÉCHEC] Impossible d'exécuter la mission.");
        }
    }

    private void handleRechargeDrone() {
        System.out.print("ID Drone : ");
        int dId = scanner.nextInt();
        System.out.print("Quantité d'énergie (%) : ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        fleetService.rechargeDrone(dId, amount);
        System.out.println("[OK] Opération de recharge exécutée.");
    }

    private void handleFindBestDrone() {
        System.out.print("ID Mission : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        Optional<Mission> missionOpt = fleetService.findMissionById(mId);
        if (missionOpt.isEmpty()) {
            System.out.println("Mission introuvable.");
            return;
        }

        Mission mission = missionOpt.get();
        List<Drone> compatible = fleetService.findCompatibleDrones(mission);
        System.out.println("Drones compatibles trouvés : " + compatible.size());
        compatible.forEach(d -> System.out.println(" * " + d.getName() + " (bat=" + d.getBattery() + "%)"));

        Optional<Drone> best = fleetService.findBestDroneFor(mission);
        if (best.isPresent()) {
            System.out.println(">> Meilleur drone recommandé : " + best.get().getName() + " (Batterie max résiduelle)");
        } else {
            System.out.println("Aucun drone compatible disponible.");
        }
    }
}
