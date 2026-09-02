package fr.abyss.service;

import fr.abyss.model.Capability;
import fr.abyss.model.Drone;
import fr.abyss.model.DroneStatus;
import fr.abyss.model.Mission;
import fr.abyss.model.MissionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Service de gestion de la flotte — Solution Essentiel complète.
 */
public class FleetManager {

    private final List<Drone> drones = new ArrayList<>();
    private final List<Mission> missions = new ArrayList<>();

    public FleetManager() {
        initializeDefaultData();
    }

    public void initializeDefaultData() {
        drones.clear();
        missions.clear();

        // Flotte de référence
        drones.add(new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0));
        drones.add(new Drone(2, "Argos", 65, 1200, Set.of(Capability.OBSERVATION, Capability.RECOVERY), 25.0));
        drones.add(new Drone(3, "Hephaistos", 90, 800, Set.of(Capability.REPAIR), 10.0));
        drones.add(new Drone(4, "Titan", 45, 2000, Set.of(Capability.RECOVERY), 50.0));
        drones.add(new Drone(5, "Proteus", 100, 1000, Set.of(Capability.OBSERVATION, Capability.REPAIR), 8.0));

        // Missions de référence
        missions.add(new Mission(1, "Photographier l'épave Aurora", 350, 20, Capability.OBSERVATION, 0.0));
        missions.add(new Mission(2, "Récupérer la balise B12", 700, 30, Capability.RECOVERY, 18.0));
        missions.add(new Mission(3, "Réparer le capteur Omega", 600, 35, Capability.REPAIR, 0.0));
        missions.add(new Mission(4, "Explorer la fosse Hécate", 1500, 35, Capability.OBSERVATION, 0.0));
        missions.add(new Mission(5, "Remonter une boîte noire", 900, 25, Capability.RECOVERY, 40.0));
        missions.add(new Mission(6, "Inspecter le pipeline Nord", 750, 20, Capability.OBSERVATION, 0.0));
    }

    public List<Drone> getDrones() {
        return Collections.unmodifiableList(drones);
    }

    public List<Mission> getMissions() {
        return Collections.unmodifiableList(missions);
    }

    public void displayDrones() {
        System.out.println("\n--- FLOTTE DE DRONES ---");
        for (Drone drone : drones) {
            System.out.println(drone);
        }
    }

    public void displayMissions() {
        System.out.println("\n--- LISTE DES MISSIONS ---");
        for (Mission mission : missions) {
            System.out.println(mission);
        }
    }

    /**
     * Vérifie la faisabilité d'une affectation (Règles R1 à R5) avec détails explicatifs.
     */
    public boolean testAssignment(int droneId, int missionId) {
        Drone drone = findDroneById(droneId);
        Mission mission = findMissionById(missionId);

        if (drone == null || mission == null) {
            System.out.println("[ERREUR] Drone ou Mission non trouvé(e).");
            return false;
        }

        System.out.println("\n=> Analyse d'affectation :");
        System.out.println("   Drone   : " + drone.getName() + " (" + drone.getBattery() + "% bat, " + drone.getMaxDepth() + "m max, caps=" + drone.getCapabilities() + ", charge=" + drone.getMaxPayloadKg() + "kg)");
        System.out.println("   Mission : " + mission.getName() + " (" + mission.getDepth() + "m, coût " + mission.getEnergyCost() + "%, req=" + mission.getRequiredCapability() + ", charge=" + mission.getPayloadKg() + "kg)");

        List<String> reasons = new ArrayList<>();

        // R1 - Disponibilité
        if (!drone.isAvailable()) {
            reasons.add("Le drone n'est pas disponible (statut=" + drone.getStatus() + ").");
        }
        if (!mission.isPending()) {
            reasons.add("La mission n'est pas en attente (statut=" + mission.getStatus() + ").");
        }

        // R2 - Profondeur
        if (!drone.canDiveTo(mission.getDepth())) {
            reasons.add("Profondeur excessive : mission requiert " + mission.getDepth() + "m > drone limité à " + drone.getMaxDepth() + "m.");
        }

        // R3 - Énergie (coût + 15%)
        if (!drone.hasEnoughBatteryFor(mission.getEnergyCost())) {
            int required = mission.getEnergyCost() + 15;
            reasons.add("Batterie insuffisante : drone à " + drone.getBattery() + "% < seuil minimal " + required + "% (" + mission.getEnergyCost() + "% coût + 15% réserve).");
        }

        // R4 - Capacité
        if (!drone.hasCapability(mission.getRequiredCapability())) {
            reasons.add("Capacité manquante : mission requiert " + mission.getRequiredCapability() + ".");
        }

        // R5 - Charge utile
        if (!drone.canCarry(mission.getPayloadKg())) {
            reasons.add("Charge trop lourde : mission requiert " + mission.getPayloadKg() + "kg > capacité drone " + drone.getMaxPayloadKg() + "kg.");
        }

        if (reasons.isEmpty()) {
            System.out.println("   [SUCCÈS] Affectation POSSIBLE (toutes les règles R1 à R5 sont respectées).");
            return true;
        } else {
            System.out.println("   [REFUS] Affectation IMPOSSIBLE. Raisons :");
            for (String r : reasons) {
                System.out.println("   - " + r);
            }
            return false;
        }
    }

    /**
     * Affecte formellement la mission au drone si validée.
     */
    public boolean assignMission(int droneId, int missionId) {
        if (!testAssignment(droneId, missionId)) {
            System.out.println("[ÉCHEC] Impossible d'affecter cette mission.");
            return false;
        }

        Drone drone = findDroneById(droneId);
        Mission mission = findMissionById(missionId);

        drone.setStatus(DroneStatus.ON_MISSION);
        mission.setStatus(MissionStatus.ASSIGNED);
        System.out.println("[CONFIRMATION] " + drone.getName() + " est maintenant affecté à \"" + mission.getName() + "\".");
        return true;
    }

    /**
     * R6 - Exécute une mission pour un drone actuellement en mer.
     */
    public boolean executeMission(int droneId, int missionId) {
        Drone drone = findDroneById(droneId);
        Mission mission = findMissionById(missionId);

        if (drone == null || mission == null) {
            System.out.println("[ERREUR] Identifiants invalides.");
            return false;
        }

        if (drone.getStatus() != DroneStatus.ON_MISSION || mission.getStatus() != MissionStatus.ASSIGNED) {
            System.out.println("[ERREUR R6] Le drone doit être ON_MISSION et la mission ASSIGNED pour être exécutés.");
            return false;
        }

        int initialBattery = drone.getBattery();
        drone.executeMission(mission.getEnergyCost());
        mission.setStatus(MissionStatus.COMPLETED);

        System.out.println("\n[SUCCÈS R6] Mission \"" + mission.getName() + "\" accomplie !");
        System.out.println("   Batterie : " + initialBattery + "% -> " + drone.getBattery() + "% (-" + mission.getEnergyCost() + "%)");
        System.out.println("   Statut final du drone : " + drone.getStatus());
        System.out.println("   Statut final de la mission : " + mission.getStatus());
        return true;
    }

    /**
     * R7 - Recharge un drone.
     */
    public void rechargeDrone(int droneId, int amount) {
        Drone drone = findDroneById(droneId);
        if (drone == null) {
            System.out.println("[ERREUR] Drone introuvable.");
            return;
        }
        int initial = drone.getBattery();
        drone.recharge(amount);
        System.out.println("[RECHARGE] " + drone.getName() + " : " + initial + "% -> " + drone.getBattery() + "% | Statut : " + drone.getStatus());
    }

    /**
     * Extension E5 - Liste les missions compatibles avec un drone donné.
     */
    public List<Mission> findCompatibleMissions(Drone drone) {
        List<Mission> compatible = new ArrayList<>();
        if (drone == null) return compatible;

        for (Mission m : missions) {
            if (m.isPending()
                    && drone.canDiveTo(m.getDepth())
                    && drone.hasEnoughBatteryFor(m.getEnergyCost())
                    && drone.hasCapability(m.getRequiredCapability())
                    && drone.canCarry(m.getPayloadKg())) {
                compatible.add(m);
            }
        }
        return compatible;
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
