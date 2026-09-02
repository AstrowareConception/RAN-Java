package fr.abyss.domain.model.equipments;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Equipment;

public record Sonar(String name, double weightKg, int extraEnergyConsumption) implements Equipment {

    public Sonar() {
        this("Sonar Multifaisceau", 4.0, 5);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Capability getProvidedCapability() {
        return Capability.OBSERVATION;
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
