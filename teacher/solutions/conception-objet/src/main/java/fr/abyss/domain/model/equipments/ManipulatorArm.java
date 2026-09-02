package fr.abyss.domain.model.equipments;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Equipment;

public record ManipulatorArm(String name, double weightKg, int extraEnergyConsumption) implements Equipment {

    public ManipulatorArm() {
        this("Bras Robotique Articulé", 8.0, 5);
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
