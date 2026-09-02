package fr.abyss.service;

import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;
import fr.abyss.domain.model.PlanAssignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service de planification heuristique multi-missions (Défi Avancé).
 * Ordonne les missions par priorité (CRITICAL > HIGH > NORMAL > LOW) puis par profondeur,
 * et alloue le meilleur drone compatible disponible sans conflit.
 */
public class MissionPlannerService {

    private final MissionAssignmentService assignmentService;

    public MissionPlannerService(MissionAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    public List<PlanAssignment> planMissions(List<Drone> availableFleet, List<Mission> pendingMissions) {
        if (availableFleet == null || pendingMissions == null) {
            return List.of();
        }

        // 1. Trier les missions par priorité (CRITICAL -> LOW) puis par profondeur décroissante
        List<Mission> sortedMissions = new ArrayList<>(pendingMissions);
        sortedMissions.sort(
                Comparator.comparing(Mission::getPriority, Comparator.reverseOrder())
                        .thenComparing(Comparator.comparingInt(Mission::getDepth).reversed())
        );

        List<PlanAssignment> plan = new ArrayList<>();
        Set<Integer> assignedDroneIds = new HashSet<>();

        for (Mission mission : sortedMissions) {
            // Filtrer les drones encore libres et compatibles
            List<Drone> candidates = availableFleet.stream()
                    .filter(d -> !assignedDroneIds.contains(d.getId()))
                    .filter(d -> assignmentService.validateAssignment(d, mission).isAllowed())
                    .toList();

            // Sélectionner le drone laissant le plus d'énergie résiduelle
            Optional<Drone> bestDrone = candidates.stream()
                    .max(Comparator.comparingInt(Drone::getBattery));

            if (bestDrone.isPresent()) {
                Drone chosen = bestDrone.get();
                assignedDroneIds.add(chosen.getId());
                plan.add(new PlanAssignment(mission, chosen, AssignmentResult.success()));
            } else {
                // Échec d'allocation pour cette mission
                plan.add(new PlanAssignment(mission, null, AssignmentResult.failure(fr.abyss.domain.model.AssignmentFailureReason.DRONE_UNAVAILABLE)));
            }
        }

        return plan;
    }
}
