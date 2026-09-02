package fr.abyss.domain.model;

/**
 * Motifs qualifiés de refus d'affectation d'une mission à un drone.
 */
public enum AssignmentFailureReason {
    DRONE_UNAVAILABLE("Le drone n'est pas au statut AVAILABLE"),
    MISSION_UNAVAILABLE("La mission n'est pas au statut PENDING"),
    DEPTH_EXCEEDED("La profondeur de la mission dépasse la profondeur maximale du drone"),
    INSUFFICIENT_BATTERY("Le niveau de batterie du drone est insuffisant pour couvrir le coût et la réserve de sécurité (15%)"),
    MISSING_CAPABILITY("Le drone ne possède pas la capacité requise par la mission"),
    PAYLOAD_TOO_HEAVY("La charge de la mission dépasse la capacité maximale d'emport du drone"),
    REQUIRED_EQUIPMENT_MISSING("Un équipement requis pour cette mission est manquant sur le drone");

    private final String description;

    AssignmentFailureReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
