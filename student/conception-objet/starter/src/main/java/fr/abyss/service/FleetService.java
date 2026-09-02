package fr.abyss.service;

import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.DroneStatus;
import fr.abyss.domain.model.Mission;
import fr.abyss.domain.model.MissionPriority;
import fr.abyss.domain.model.MissionStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service de gestion globale de la flotte et orchestration des missions.
 */
public class FleetService {

    private final List<Drone> drones = new ArrayList<>();
    private final List<Mission> missions = new ArrayList<>();
    private final MissionAssignmentService assignmentService;

    public FleetService(MissionAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
        initializeDefaultFleet();
    }

    public void initializeDefaultFleet() {
        drones.clear();
        missions.clear();

        // Flotte
        drones.add(new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0));
        drones.add(new Drone(2, "Argos", 65, 1200, Set.of(Capability.OBSERVATION, Capability.RECOVERY), 25.0));
        drones.add(new Drone(3, "Hephaistos", 90, 800, Set.of(Capability.REPAIR), 10.0));
        drones.add(new Drone(4, "Titan", 45, 2000, Set.of(Capability.RECOVERY), 50.0));
        drones.add(new Drone(5, "Proteus", 100, 1000, Set.of(Capability.OBSERVATION, Capability.REPAIR), 8.0));

        // Missions
        missions.add(new Mission(1, "Photographier l'épave Aurora", 350, 20, Capability.OBSERVATION, 0.0, MissionPriority.NORMAL));
        missions.add(new Mission(2, "Récupérer la balise B12", 700, 30, Capability.RECOVERY, 18.0, MissionPriority.HIGH));
        missions.add(new Mission(3, "Réparer le capteur Omega", 600, 35, Capability.REPAIR, 0.0, MissionPriority.CRITICAL));
        missions.add(new Mission(4, "Explorer la fosse Hécate", 1500, 35, Capability.OBSERVATION, 0.0, MissionPriority.NORMAL));
        missions.add(new Mission(5, "Remonter une boîte noire", 900, 25, Capability.RECOVERY, 40.0, MissionPriority.HIGH));
        missions.add(new Mission(6, "Inspecter le pipeline Nord", 750, 20, Capability.OBSERVATION, 0.0, MissionPriority.LOW));
    }

    public List<Drone> getDrones() {
        return Collections.unmodifiableList(drones);
    }

    public List<Mission> getMissions() {
        return Collections.unmodifiableList(missions);
    }

    public AssignmentResult evaluateAssignment(int droneId, int missionId) {
        Drone drone = findDroneById(droneId).orElse(null);
        Mission mission = findMissionById(missionId).orElse(null);
        if (drone == null || mission == null) {
            throw new IllegalArgumentException("Drone ou Mission introuvable pour les identifiants fournis.");
        }
        return assignmentService.validateAssignment(drone, mission);
    }

    public boolean assignMission(int droneId, int missionId) {
        Drone drone = findDroneById(droneId).orElse(null);
        Mission mission = findMissionById(missionId).orElse(null);
        if (drone == null || mission == null) return false;

        // TODO 4 : valider l'affectation avant de modifier les deux statuts.
        return false;
    }

    public boolean executeMission(int droneId, int missionId) {
        Drone drone = findDroneById(droneId).orElse(null);
        Mission mission = findMissionById(missionId).orElse(null);
        if (drone == null || mission == null) return false;

        // TODO 4 : ne permettre l'exécution que pour un couple drone/mission effectivement affecté,
        // puis déléguer la consommation au drone et terminer la mission.
        return false;
    }

    public void rechargeDrone(int droneId, int amount) {
        // TODO 4 : retrouver le drone et déléguer la recharge à l'entité.
    }

    /**
     * TODO 5 : Filtrer l'ensemble des drones compatibles avec une mission donnée.
     */
    public List<Drone> findCompatibleDrones(Mission mission) {
        // TODO 5 : conserver uniquement les drones dont AssignmentResult est autorisé.
        return List.of();
    }

    /**
     * TODO 5 : Identifier le meilleur drone selon votre critère d'optimisation (ex. énergie résiduelle maximale).
     */
    public Optional<Drone> findBestDroneFor(Mission mission) {
        // TODO 5 : partir de la liste compatible et appliquer le critère que vous justifiez.
        return Optional.empty();
    }

    public Optional<Drone> findDroneById(int id) {
        return drones.stream().filter(d -> d.getId() == id).findFirst();
    }

    public Optional<Mission> findMissionById(int id) {
        return missions.stream().filter(m -> m.getId() == id).findFirst();
    }
}
