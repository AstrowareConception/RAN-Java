package fr.abyss.service;

import fr.abyss.domain.model.AssignmentFailureReason;
import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;

/**
 * Service métier validant l'affectation d'une mission à un drone.
 */
public class MissionAssignmentService {

    /**
     * TODO 3 : Évaluer l'ensemble des règles métier (R1 à R5) et retourner un résultat qualifié.
     */
    public AssignmentResult validateAssignment(Drone drone, Mission mission) {
        if (drone == null || mission == null) {
            throw new IllegalArgumentException("Le drone et la mission ne peuvent pas être null.");
        }

        // TODO 3 : créer une liste des motifs de refus, puis y ajouter les raisons liées
        // à R1, R2, R3, R4 et R5. Retournez AssignmentResult.success() si la liste est vide.
        // Les enum et le record existants constituent un contrat : ne les modifiez pas au départ.
        return AssignmentResult.failure(AssignmentFailureReason.DRONE_UNAVAILABLE);
    }
}
