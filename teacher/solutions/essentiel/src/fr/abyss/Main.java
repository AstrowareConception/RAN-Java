package fr.abyss;

import fr.abyss.model.Drone;
import fr.abyss.model.Mission;
import fr.abyss.service.FleetManager;

import java.util.List;
import java.util.Scanner;

/**
 * Point d'entrée de la solution complète — Parcours Essentiel.
 */
public class Main {

    public static void main(String[] args) {
        FleetManager manager = new FleetManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("======================================================");
        System.out.println("     CENTRE DE CONTRÔLE ABYSS EXPLORER (SOLUTION ESSENTIEL)");
        System.out.println("======================================================");

        while (running) {
            System.out.println("\n--- MENU DE SUPERVISION ---");
            System.out.println("1. Afficher la flotte de drones");
            System.out.println("2. Afficher les missions");
            System.out.println("3. Tester une affectation (R1 à R5)");
            System.out.println("4. Affecter une mission");
            System.out.println("5. Exécuter une mission (R6)");
            System.out.println("6. Recharger un drone (R7)");
            System.out.println("7. Rechercher les missions compatibles pour un drone (E5)");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un entier valide.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manager.displayDrones();
                case 2 -> manager.displayMissions();
                case 3 -> {
                    System.out.print("Numéro du drone (1-5) : ");
                    int dId = scanner.nextInt();
                    System.out.print("Numéro de la mission (1-6) : ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    manager.testAssignment(dId, mId);
                }
                case 4 -> {
                    System.out.print("Numéro du drone (1-5) : ");
                    int dId = scanner.nextInt();
                    System.out.print("Numéro de la mission (1-6) : ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    manager.assignMission(dId, mId);
                }
                case 5 -> {
                    System.out.print("Numéro du drone en mission : ");
                    int dId = scanner.nextInt();
                    System.out.print("Numéro de la mission assignée : ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    manager.executeMission(dId, mId);
                }
                case 6 -> {
                    System.out.print("Numéro du drone à recharger : ");
                    int dId = scanner.nextInt();
                    System.out.print("Quantité d'énergie (% à ajouter) : ");
                    int amount = scanner.nextInt();
                    scanner.nextLine();
                    manager.rechargeDrone(dId, amount);
                }
                case 7 -> {
                    System.out.print("Numéro du drone pour recherche : ");
                    int dId = scanner.nextInt();
                    scanner.nextLine();
                    Drone d = manager.findDroneById(dId);
                    if (d == null) {
                        System.out.println("Drone introuvable.");
                    } else {
                        List<Mission> compatible = manager.findCompatibleMissions(d);
                        System.out.println("\n--- Missions compatibles pour " + d.getName() + " (" + compatible.size() + " trouvée(s)) ---");
                        for (Mission m : compatible) {
                            System.out.println(" * " + m);
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Fermeture du centre Abyss Explorer. Session terminée.");
                    running = false;
                }
                default -> System.out.println("Choix invalide. Veuillez saisir un numéro entre 0 et 7.");
            }
        }

        scanner.close();
    }
}
