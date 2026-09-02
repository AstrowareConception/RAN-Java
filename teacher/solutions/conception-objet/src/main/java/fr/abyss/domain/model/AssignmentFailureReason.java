package fr.abyss.domain.model;

/**
 * Motifs qualifiés de refus d'affectation d'une mission à un drone.
 */
public enum AssignmentFailureReason {
    DRONE_UNAVAILABLE("Le drone n'est pas disponible (statut != AVAILABLE)"),
    MISSION_UNAVAILABLE("La mission n'est pas en attente (statut != PENDING)"),
    DEPTH_EXCEEDED("La profondeur de la mission dépasse la profondeur maximale tolérée par le drone"),
    INSUFFICIENT_BATTERY("La batterie est insuffisante pour couvrir le coût et la réserve de sécurité (15%)"),
    MISSING_CAPABILITY("Le drone ne possède pas la capacité requise pour cette mission"),
    PAYLOAD_TOO_HEAVY("La charge utile demandée dépasse la charge maximale admissible du drone"),
    REQUIRED_EQUIPMENT_MISSING("Un module d'équipement indispensable est absent du drone");

    private final String description;

    AssignmentFailureReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
