package fr.abyss.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Représente un drone sous-marin autonome de la flotte Abyss Explorer.
 */
public class Drone {

    // TODO 1.1 : Déclarer les attributs privés suivants :
    // - int id
    // - String name
    // - int battery (pourcentage entre 0 et 100)
    // - int maxDepth (en mètres)
    // - DroneStatus status
    // - Set<Capability> capabilities
    // - double maxPayloadKg (charge utile maximale)

    private int id;
    private String name;
    private int battery;
    private int maxDepth;
    private DroneStatus status;
    private Set<Capability> capabilities = new HashSet<>();
    private double maxPayloadKg;

    // TODO 1.2 : Compléter le constructeur pour initialiser l'ensemble des champs
    public Drone(int id, String name, int battery, int maxDepth, Set<Capability> capabilities, double maxPayloadKg) {
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

    // TODO 1.3 : Vérifier et compléter les getters
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

    public void setBattery(int battery) {
        this.battery = Math.max(0, Math.min(100, battery));
    }

    @Override
    public String toString() {
        return String.format("[%d] %-12s | Bat: %3d%% | Prof. max: %4dm | Charge max: %4.1fkg | Statut: %-11s | Caps: %s",
                id, name, battery, maxDepth, maxPayloadKg, status, capabilities);
    }
}
