package fr.abyss.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité de domaine représentant un drone sous-marin autonome.
 */
public class Drone {

    private final int id;
    private final String name;
    private int battery;
    private final int maxDepth;
    private DroneStatus status;
    private final Set<Capability> capabilities = new HashSet<>();
    private final double maxPayloadKg;

    // TODO 1 : Consolider les invariants de construction
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
            throw new IllegalArgumentException("La charge maximale doit être positive.");
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

    // --- TODO 4 : Implémenter les méthodes métier du drone ---

    public boolean isAvailable() {
        // TODO 4 : exprimer la condition de disponibilité à partir du statut.
        return false;
    }

    public boolean canDiveTo(int targetDepth) {
        // TODO 4 : comparer la profondeur demandée à maxDepth.
        return false;
    }

    public boolean hasEnoughBatteryFor(int energyCost) {
        // TODO 4 : ne pas oublier la réserve obligatoire de 15 %.
        return false;
    }

    public boolean hasCapability(Capability capability) {
        // TODO 4 : interroger les capacités du drone.
        return false;
    }

    public boolean canCarry(double payload) {
        // TODO 4 : comparer la charge demandée à la charge maximale.
        return false;
    }

    public void executeMission(int energyCost) {
        // TODO 4 : diminuer la batterie sans sortir de [0, 100], puis mettre à jour le statut.
    }

    public void recharge(int amount) {
        // TODO 4 : refuser une quantité négative, plafonner à 100 et rétablir la disponibilité si pertinent.
    }

    @Override
    public String toString() {
        return String.format("[%d] %-12s | Bat: %3d%% | Prof. max: %4dm | Charge max: %4.1fkg | Statut: %-11s | Caps: %s",
                id, name, battery, maxDepth, maxPayloadKg, status, capabilities);
    }
}
