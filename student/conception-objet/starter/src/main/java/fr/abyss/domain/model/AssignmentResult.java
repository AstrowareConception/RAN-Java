package fr.abyss.domain.model;

import java.util.List;

/**
 * Résultat métier qualifié d'une tentative d'affectation de mission.
 */
public record AssignmentResult(boolean isAllowed, List<AssignmentFailureReason> reasons) {

    public static AssignmentResult success() {
        return new AssignmentResult(true, List.of());
    }

    public static AssignmentResult failure(List<AssignmentFailureReason> reasons) {
        if (reasons == null || reasons.isEmpty()) {
            throw new IllegalArgumentException("Un échec d'affectation doit comporter au moins une raison.");
        }
        return new AssignmentResult(false, List.copyOf(reasons));
    }

    public static AssignmentResult failure(AssignmentFailureReason singleReason) {
        return new AssignmentResult(false, List.of(singleReason));
    }

    public boolean hasReason(AssignmentFailureReason reason) {
        return reasons.contains(reason);
    }
}
