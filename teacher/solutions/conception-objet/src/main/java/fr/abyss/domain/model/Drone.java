package fr.abyss.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entité de domaine représentant un drone sous-marin avec support d'équipements modulaires.
 */
public class Drone {

    private final int id;
    private final String name;
    private int battery;
    private final int maxDepth;
    private DroneStatus status;
    private Integer assignedMissionId;
    private final Set<Capability> intrinsicCapabilities = new HashSet<>();
    private final List<Equipment> equipments = new ArrayList<>();
    private final double basePayloadCapacityKg;

    public Drone(int id, String name, int battery, int maxDepth, Set<Capability> intrinsicCapabilities, double basePayloadCapacityKg) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom du drone ne peut pas être vide.");
        }
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Le niveau de batterie initial doit être compris entre 0 et 100%.");
        }
        if (maxDepth <= 0) {
            throw new IllegalArgumentException("La profondeur maximale doit être strictement positive.");
        }
        if (basePayloadCapacityKg < 0) {
            throw new IllegalArgumentException("La capacité d'emport doit être positive ou nulle.");
        }

        this.id = id;
        this.name = name;
        this.battery = battery;
        this.maxDepth = maxDepth;
        if (intrinsicCapabilities != null) {
            this.intrinsicCapabilities.addAll(intrinsicCapabilities);
        }
        this.basePayloadCapacityKg = basePayloadCapacityKg;
        this.status = DroneStatus.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBattery() {
        return battery;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public double getBasePayloadCapacityKg() {
        return basePayloadCapacityKg;
    }

    public Set<Capability> getIntrinsicCapabilities() {
        return Collections.unmodifiableSet(intrinsicCapabilities);
    }

    public List<Equipment> getEquipments() {
        return Collections.unmodifiableList(equipments);
    }

    public void installEquipment(Equipment equipment) {
        if (equipment != null) {
            this.equipments.add(equipment);
        }
    }

    public void removeEquipment(Equipment equipment) {
        this.equipments.remove(equipment);
    }

    /**
     * Retourne toutes les capacités actives (intrinsèques + conférées par les équipements).
     */
    public Set<Capability> getActiveCapabilities() {
        Set<Capability> all = new HashSet<>(intrinsicCapabilities);
        for (Equipment eq : equipments) {
            all.add(eq.getProvidedCapability());
        }
        return Collections.unmodifiableSet(all);
    }

    /**
     * Calcule la charge maximale admissible (base + bonus équipements).
     */
    public double getTotalPayloadCapacityKg() {
        return basePayloadCapacityKg + equipments.stream()
                .mapToDouble(Equipment::getAdditionalPayloadCapacityKg)
                .sum();
    }

    // --- Invariants & Méthodes Métier ---

    public boolean isAvailable() {
        return this.status == DroneStatus.AVAILABLE;
    }

    public boolean canDiveTo(int targetDepth) {
        return this.maxDepth >= targetDepth;
    }

    public boolean hasEnoughBatteryFor(int energyCost) {
        int extraCost = equipments.stream().mapToInt(Equipment::getExtraEnergyConsumption).sum();
        int totalCost = energyCost + extraCost;
        return this.battery >= (totalCost + 15);
    }

    public boolean hasCapability(Capability capability) {
        return getActiveCapabilities().contains(capability);
    }

    public boolean canCarry(double payloadKg) {
        return getTotalPayloadCapacityKg() >= payloadKg;
    }

    public void startMission(int missionId) {
        if (!isAvailable()) {
            throw new IllegalStateException("Un drone indisponible ne peut pas commencer une mission.");
        }
        this.assignedMissionId = missionId;
        this.status = DroneStatus.ON_MISSION;
    }

    public void executeMission(int missionId, int energyCost) {
        if (status != DroneStatus.ON_MISSION || !Integer.valueOf(missionId).equals(assignedMissionId)) {
            throw new IllegalStateException("Cette mission n'est pas affectée à ce drone.");
        }
        int extraCost = equipments.stream().mapToInt(Equipment::getExtraEnergyConsumption).sum();
        int totalCost = energyCost + extraCost;
        this.battery = Math.max(0, this.battery - totalCost);
        this.assignedMissionId = null;
        if (this.battery < 15) {
            this.status = DroneStatus.MAINTENANCE;
        } else {
            this.status = DroneStatus.AVAILABLE;
        }
    }

    public void recharge(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("La quantité de recharge doit être positive.");
        }
        this.battery = Math.min(100, this.battery + amount);
        if (this.status == DroneStatus.MAINTENANCE && this.battery >= 15) {
            this.status = DroneStatus.AVAILABLE;
        }
    }

    @Override
    public String toString() {
        return String.format("[%d] %-12s | Bat: %3d%% | Prof. max: %4dm | Charge: %4.1fkg | Statut: %-11s | Caps: %s",
                id, name, battery, maxDepth, getTotalPayloadCapacityKg(), status, getActiveCapabilities());
    }
}
