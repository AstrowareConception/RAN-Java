package fr.abyss.domain.model.equipments;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Equipment;

public record RepairKit(String name, double weightKg, int extraEnergyConsumption) implements Equipment {

    public RepairKit() {
        this("Kit de Soudure Sous-Marine", 6.0, 3);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Capability getProvidedCapability() {
        return Capability.REPAIR;
    }

    @Override
    public double getWeightKg() {
        return weightKg;
    }

    @Override
    public int getExtraEnergyConsumption() {
        return extraEnergyConsumption;
    }
}
