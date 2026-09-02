package fr.abyss.service;

import fr.abyss.model.Capability;
import fr.abyss.model.Drone;
import fr.abyss.model.DroneStatus;
import fr.abyss.model.Mission;
import fr.abyss.model.MissionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service de gestion de la flotte et des missions.
 */
public class FleetManager {

    private final List<Drone> drones = new ArrayList<>();
    private final List<Mission> missions = new ArrayList<>();

    public FleetManager() {
        initializeData();
    }

    /**
     * TODO 3 : Initialiser les 5 drones et les 6 missions de référence.
     */
    public void initializeData() {
        drones.clear();
        missions.clear();

        // 1. Drones
        drones.add(new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0));
        drones.add(new Drone(2, "Argos", 65, 1200, Set.of(Capability.OBSERVATION, Capability.RECOVERY), 25.0));
        drones.add(new Drone(3, "Hephaistos", 90, 800, Set.of(Capability.REPAIR), 10.0));
        drones.add(new Drone(4, "Titan", 45, 2000, Set.of(Capability.RECOVERY), 50.0));
        drones.add(new Drone(5, "Proteus", 100, 1000, Set.of(Capability.OBSERVATION, Capability.REPAIR), 8.0));

        // 2. Missions
        missions.add(new Mission(1, "Photographier l'épave Aurora", 350, 20, Capability.OBSERVATION, 0.0));
        missions.add(new Mission(2, "Récupérer la balise B12", 700, 30, Capability.RECOVERY, 18.0));
        missions.add(new Mission(3, "Réparer le capteur Omega", 600, 35, Capability.REPAIR, 0.0));
        missions.add(new Mission(4, "Explorer la fosse Hécate", 1500, 35, Capability.OBSERVATION, 0.0));
        missions.add(new Mission(5, "Remonter une boîte noire", 900, 25, Capability.RECOVERY, 40.0));
        missions.add(new Mission(6, "Inspecter le pipeline Nord", 750, 20, Capability.OBSERVATION, 0.0));
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    /**
     * TODO 4 : Afficher l'ensemble des drones de la flotte.
     */
    public void displayDrones() {
        System.out.println("\n--- FLOTTE DE DRONES ---");
        for (Drone drone : drones) {
            System.out.println(drone);
        }
    }

    /**
     * TODO 4 : Afficher l'ensemble des missions.
     */
    public void displayMissions() {
        System.out.println("\n--- LISTE DES MISSIONS ---");
        for (Mission mission : missions) {
            System.out.println(mission);
        }
    }

    /**
     * TODO 5 : Tester si l'affectation d'une mission à un drone est possible.
     * Vérifier les règles R1 (Disponibilité), R2 (Profondeur) et R3 (Batterie + réserve 15%).
     *
     * @return true si l'affectation est valide, false sinon.
     */
    public boolean testAssignment(int droneId, int missionId) {
        Drone drone = findDroneById(droneId);
        Mission mission = findMissionById(missionId);

        if (drone == null || mission == null) {
            System.out.println("[ERREUR] Identifiant de drone ou de mission introuvable.");
            return false;
        }

        // TODO 5 : afficher les informations utiles, puis vérifier R1, R2 et R3.
        // Commencez par une variable `boolean valid = true`, puis passez-la à false
        // lorsqu'une règle n'est pas respectée. Consultez HINTS.md seulement si nécessaire.
        return false;
    }

    /**
     * TODO 6 : Affecter réellement la mission au drone si le test est validé.
     */
    public boolean assignMission(int droneId, int missionId) {
        // TODO 6 : appeler testAssignment. En cas de succès, retrouver les objets puis
        // faire passer le drone à ON_MISSION et la mission à ASSIGNED.
        return false;
        /*
        if (!testAssignment(droneId, missionId)) {
            System.out.println("Affectation abandonnée en raison des règles métier non respectées.");
            return false;
        }

        Drone drone = findDroneById(droneId);
        Mission mission = findMissionById(missionId);

        drone.setStatus(DroneStatus.ON_MISSION);
        mission.setStatus(MissionStatus.ASSIGNED);

        System.out.println("[SUCCÈS] Mission \"" + mission.getName() + "\" assignée à " + drone.getName() + ".");
        return true;
         */
    }

    public Drone findDroneById(int id) {
        for (Drone d : drones) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public Mission findMissionById(int id) {
        for (Mission m : missions) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
}
