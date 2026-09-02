package fr.abyss.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Modélise un drone sous-marin autonome dans la solution Essentiel.
 */
public class Drone {

    private final int id;
    private final String name;
    private int battery;
    private final int maxDepth;
    private DroneStatus status;
    private final Set<Capability> capabilities = new HashSet<>();
    private final double maxPayloadKg;

    public Drone(int id, String name, int battery, int maxDepth, Set<Capability> capabilities, double maxPayloadKg) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom du drone ne peut pas être vide.");
        }
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("La batterie doit être comprise entre 0 et 100%.");
        }
        if (maxDepth <= 0) {
            throw new IllegalArgumentException("La profondeur maximale doit être positive.");
        }
        if (maxPayloadKg < 0) {
            throw new IllegalArgumentException("La charge maximale doit être positive ou nulle.");
        }

        this.id = id;
        this.name = name;
        this.battery = battery;
        this.maxDepth = maxDepth;
        if (capabilities != null) {
            this.capabilities.addAll(capabilities);
        }
        this.maxPayloadKg = maxPayloadKg;
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

    public Set<Capability> getCapabilities() {
        return Collections.unmodifiableSet(capabilities);
    }

    public double getMaxPayloadKg() {
        return maxPayloadKg;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    // --- Méthodes Métier ---

    /**
     * R1 - Vérifie si le drone est disponible.
     */
    public boolean isAvailable() {
        return this.status == DroneStatus.AVAILABLE;
    }

    /**
     * R2 - Vérifie si le drone peut supporter la profondeur ciblée.
     */
    public boolean canDiveTo(int targetDepth) {
        return this.maxDepth >= targetDepth;
    }

    /**
     * R3 - Vérifie si la batterie est suffisante en incluant la réserve de 15%.
     */
    public boolean hasEnoughBatteryFor(int energyCost) {
        return this.battery >= (energyCost + 15);
    }

    /**
     * R4 - Vérifie si le drone possède la capacité requise.
     */
    public boolean hasCapability(Capability capability) {
        return this.capabilities.contains(capability);
    }

    /**
     * R5 - Vérifie si le drone peut emporter la charge utile.
     */
    public boolean canCarry(double payloadKg) {
        return this.maxPayloadKg >= payloadKg;
    }

    /**
     * R6 - Exécute la mission : décrémente la batterie et actualise le statut.
     */
    public void executeMission(int energyCost) {
        this.battery = Math.max(0, this.battery - energyCost);
        if (this.battery < 15) {
            this.status = DroneStatus.MAINTENANCE;
        } else {
            this.status = DroneStatus.AVAILABLE;
        }
    }

    /**
     * R7 - Recharge le drone et met à jour son statut s'il était en maintenance.
     */
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
        return String.format("[%d] %-12s | Bat: %3d%% | Prof. max: %4dm | Charge max: %4.1fkg | Statut: %-11s | Caps: %s",
                id, name, battery, maxDepth, maxPayloadKg, status, capabilities);
    }
}
