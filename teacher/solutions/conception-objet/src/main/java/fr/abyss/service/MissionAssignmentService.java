package fr.abyss.service;

import fr.abyss.domain.model.AssignmentFailureReason;
import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;

import java.util.ArrayList;
import java.util.List;

/**
 * Service pur évaluant les règles d'affectation métier (R1 à R5, R8).
 */
public class MissionAssignmentService {

    public AssignmentResult validateAssignment(Drone drone, Mission mission) {
        if (drone == null || mission == null) {
            throw new IllegalArgumentException("Le drone et la mission doivent être non-null.");
        }

        List<AssignmentFailureReason> reasons = new ArrayList<>();

        // R1 - Disponibilité
        if (!drone.isAvailable()) {
            reasons.add(AssignmentFailureReason.DRONE_UNAVAILABLE);
        }
        if (!mission.isPending()) {
            reasons.add(AssignmentFailureReason.MISSION_UNAVAILABLE);
        }

        // R2 - Profondeur
        if (!drone.canDiveTo(mission.getDepth())) {
            reasons.add(AssignmentFailureReason.DEPTH_EXCEEDED);
        }

        // R3 - Énergie
        if (!drone.hasEnoughBatteryFor(mission.getEnergyCost())) {
            reasons.add(AssignmentFailureReason.INSUFFICIENT_BATTERY);
        }

        // R4 - Capacité requise (intrinsèque ou via équipements)
        if (!drone.hasCapability(mission.getRequiredCapability())) {
            reasons.add(AssignmentFailureReason.MISSING_CAPABILITY);
        }

        // R5 - Charge utile
        if (!drone.canCarry(mission.getPayloadKg())) {
            reasons.add(AssignmentFailureReason.PAYLOAD_TOO_HEAVY);
        }

        if (reasons.isEmpty()) {
            return AssignmentResult.success();
        } else {
            return AssignmentResult.failure(reasons);
        }
    }
}
