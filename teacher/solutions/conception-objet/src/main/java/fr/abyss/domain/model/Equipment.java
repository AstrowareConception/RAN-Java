package fr.abyss.domain.model;

/**
 * Contrat pour un équipement amovible embarqué sur un drone sous-marin (Règle R8).
 */
public interface Equipment {

    String getName();

    Capability getProvidedCapability();

    double getWeightKg();

    int getExtraEnergyConsumption();

    /**
     * Bonus éventuel de capacité d'emport apporté par cet équipement.
     */
    default double getAdditionalPayloadCapacityKg() {
        return 0.0;
    }
}
