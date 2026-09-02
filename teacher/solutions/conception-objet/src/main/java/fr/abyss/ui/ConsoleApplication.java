package fr.abyss.ui;

import fr.abyss.domain.model.AssignmentFailureReason;
import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;
import fr.abyss.domain.model.PlanAssignment;
import fr.abyss.service.FleetService;
import fr.abyss.service.MissionPlannerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Interface utilisateur textuelle pour la solution Conception Objet.
 */
public class ConsoleApplication {

    private final FleetService fleetService;
    private final MissionPlannerService plannerService;
    private final Scanner scanner;

    public ConsoleApplication(FleetService fleetService, MissionPlannerService plannerService) {
        this.fleetService = fleetService;
        this.plannerService = plannerService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        System.out.println("======================================================");
        System.out.println("   ABYSS EXPLORER — CONSOLE SUPERVISION (CONCEPTION)");
        System.out.println("======================================================");

        while (running) {
            printMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un entier valide.");
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
                case 8 -> handleMultiMissionPlanning();
                case 0 -> {
                    System.out.println("Arrêt du système de supervision.");
                    running = false;
                }
                default -> System.out.println("Choix non reconnu.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- MENU DE SUPERVISION ---");
        System.out.println("1. Afficher les drones");
        System.out.println("2. Afficher les missions");
        System.out.println("3. Tester une affectation (R1 à R5, R8)");
        System.out.println("4. Affecter une mission");
        System.out.println("5. Exécuter une mission (R6)");
        System.out.println("6. Recharger un drone (R7)");
        System.out.println("7. Recommander le meilleur drone pour une mission");
        System.out.println("8. Planifier automatiquement l'ensemble des missions (Défi 2)");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private void displayFleet() {
        System.out.println("\n--- ÉTAT DE LA FLOTTE ---");
        fleetService.getDrones().forEach(System.out::println);
    }

    private void displayMissions() {
        System.out.println("\n--- LISTE DES MISSIONS ---");
        fleetService.getMissions().forEach(System.out::println);
    }

    private void handleTestAssignment() {
        System.out.print("Numéro du drone (1-5) : ");
        int dId = scanner.nextInt();
        System.out.print("Numéro de la mission (1-6) : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        AssignmentResult result = fleetService.evaluateAssignment(dId, mId);
        if (result.isAllowed()) {
            System.out.println("\n[SUCCÈS] Affectation AUTORISÉE par le domaine métier.");
        } else {
            System.out.println("\n[REFUS] Affectation INTERDITE. Motifs qualifiés :");
            for (AssignmentFailureReason reason : result.reasons()) {
                System.out.println(" - " + reason.name() + " : " + reason.getDescription());
            }
        }
    }

    private void handleAssignMission() {
        System.out.print("Numéro du drone : ");
        int dId = scanner.nextInt();
        System.out.print("Numéro de la mission : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        boolean ok = fleetService.assignMission(dId, mId);
        if (ok) {
            System.out.println("[SUCCÈS] Mission assignée au drone.");
        } else {
            System.out.println("[ÉCHEC] Affectation refusée.");
        }
    }

    private void handleExecuteMission() {
        System.out.print("Numéro du drone : ");
        int dId = scanner.nextInt();
        System.out.print("Numéro de la mission : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        boolean ok = fleetService.executeMission(dId, mId);
        if (ok) {
            System.out.println("[SUCCÈS R6] Mission achevée avec succès.");
        } else {
            System.out.println("[ÉCHEC] Opération d'exécution impossible.");
        }
    }

    private void handleRechargeDrone() {
        System.out.print("Numéro du drone : ");
        int dId = scanner.nextInt();
        System.out.print("Pourcentage à ajouter : ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        fleetService.rechargeDrone(dId, amount);
        System.out.println("[RECHARGE R7] Opération de charge effectuée.");
    }

    private void handleFindBestDrone() {
        System.out.print("Numéro de la mission : ");
        int mId = scanner.nextInt();
        scanner.nextLine();

        Optional<Mission> missionOpt = fleetService.findMissionById(mId);
        if (missionOpt.isEmpty()) {
            System.out.println("Mission introuvable.");
            return;
        }

        Mission mission = missionOpt.get();
        List<Drone> compatible = fleetService.findCompatibleDrones(mission);
        System.out.println("\nDrones compatibles : " + compatible.size());
        compatible.forEach(d -> System.out.println(" * " + d.getName() + " (" + d.getBattery() + "% bat)"));

        Optional<Drone> best = fleetService.findBestDroneFor(mission);
        if (best.isPresent()) {
            System.out.println(">> Recommandation : " + best.get().getName() + " (stratégie : réserve d'énergie max)");
        } else {
            System.out.println("Aucun drone compatible disponible.");
        }
    }

    private void handleMultiMissionPlanning() {
        System.out.println("\n--- PLANIFICATION HEURISTIQUE DES MISSIONS ---");
        List<PlanAssignment> plan = plannerService.planMissions(fleetService.getDrones(), fleetService.getMissions());

        for (PlanAssignment pa : plan) {
            if (pa.isSuccessful()) {
                System.out.println("[ASSIGNÉ] Mission: \"" + pa.mission().getName() + "\" (Prio: " + pa.mission().getPriority() + ") -> Drone: " + pa.assignedDrone().getName());
            } else {
                System.out.println("[EN ATTENTE] Mission: \"" + pa.mission().getName() + "\" (Prio: " + pa.mission().getPriority() + ") -> Aucun drone disponible.");
            }
        }
    }
}
