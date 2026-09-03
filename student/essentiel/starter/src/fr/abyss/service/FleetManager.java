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
     * Consultez docs/REGLES_METIER.md pour retrouver les valeurs du tableau de référence.
     */
    public void initializeData() {
        drones.clear();
        missions.clear();

        // TODO 3.1 : Créer et ajouter les 5 drones de la flotte dans la liste `drones`.
        // Exemple pour le 1er drone :
        // drones.add(new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0));

        // TODO 3.2 : Créer et ajouter les 6 missions dans la liste `missions`.
        // Exemple pour la 1ère mission :
        // missions.add(new Mission(1, "Photographier l'épave Aurora", 350, 20, Capability.OBSERVATION, 0.0));
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    /**
     * TODO 4.1 : Afficher l'ensemble des drones de la flotte.
     */
    public void displayDrones() {
        System.out.println("\n--- FLOTTE DE DRONES ---");
        // TODO 4.1 : Parcourir la liste `drones` avec une boucle for-each et afficher chaque drone.
    }

    /**
     * TODO 4.2 : Afficher l'ensemble des missions.
     */
    public void displayMissions() {
        System.out.println("\n--- LISTE DES MISSIONS ---");
        // TODO 4.2 : Parcourir la liste `missions` avec une boucle for-each et afficher chaque mission.
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
