package fr.abyss.domain.model;

/**
 * Enregistrement d'un résultat d'affectation planifiée pour une mission donnée.
 */
public record PlanAssignment(Mission mission, Drone assignedDrone, AssignmentResult result) {

    public boolean isSuccessful() {
        return assignedDrone != null && result.isAllowed();
    }
}
