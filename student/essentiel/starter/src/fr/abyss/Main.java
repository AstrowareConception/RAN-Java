package fr.abyss;

import fr.abyss.service.FleetManager;

import java.util.Scanner;

/**
 * Point d'entrée de l'application console Abyss Explorer — Parcours Essentiel.
 */
public class Main {

    public static void main(String[] args) {
        FleetManager manager = new FleetManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("======================================================");
        System.out.println("     BIENVENUE SUR ABYSS EXPLORER (PARCOURS ESSENTIEL)");
        System.out.println("======================================================");

        while (running) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Afficher les drones");
            System.out.println("2. Afficher les missions");
            System.out.println("3. Tester une affectation (R1, R2, R3)");
            System.out.println("4. Affecter une mission");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            if (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un numéro valide.");
                scanner.nextLine();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Nettoie le saut de ligne

            switch (choice) {
                case 1 -> manager.displayDrones();
                case 2 -> manager.displayMissions();
                case 3 -> {
                    System.out.print("Identifiant du drone (1-5) : ");
                    int dId = scanner.nextInt();
                    System.out.print("Identifiant de la mission (1-6) : ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    manager.testAssignment(dId, mId);
                }
                case 4 -> {
                    System.out.print("Identifiant du drone (1-5) : ");
                    int dId = scanner.nextInt();
                    System.out.print("Identifiant de la mission (1-6) : ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();
                    manager.assignMission(dId, mId);
                }
                case 0 -> {
                    System.out.println("Fermeture du centre de contrôle Abyss Explorer. À bientôt !");
                    running = false;
                }
                default -> System.out.println("Option inconnue. Veuillez choisir parmi les options du menu.");
            }
        }

        scanner.close();
    }
}
