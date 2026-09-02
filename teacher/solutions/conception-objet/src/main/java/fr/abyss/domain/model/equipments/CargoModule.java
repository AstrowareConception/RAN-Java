package fr.abyss.domain.model.equipments;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Equipment;

public record CargoModule(String name, double weightKg, int extraEnergyConsumption, double additionalPayloadCapacity) implements Equipment {

    public CargoModule() {
        this("Panier d'Emport Externe", 5.0, 2, 20.0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Capability getProvidedCapability() {
        return Capability.RECOVERY;
    }

    @Override
    public double getWeightKg() {
        return weightKg;
    }

    @Override
    public int getExtraEnergyConsumption() {
        return extraEnergyConsumption;
    }

    @Override
    public double getAdditionalPayloadCapacityKg() {
        return additionalPayloadCapacity;
    }
}
