package fr.abyss.domain.model;

import java.util.List;

/**
 * Objet résultat métier immuable qualifiant l'autorisation ou le rejet d'une affectation.
 */
public record AssignmentResult(boolean isAllowed, List<AssignmentFailureReason> reasons) {

    public static AssignmentResult success() {
        return new AssignmentResult(true, List.of());
    }

    public static AssignmentResult failure(List<AssignmentFailureReason> reasons) {
        if (reasons == null || reasons.isEmpty()) {
            throw new IllegalArgumentException("Un résultat de rejet doit comporter au moins un motif.");
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
