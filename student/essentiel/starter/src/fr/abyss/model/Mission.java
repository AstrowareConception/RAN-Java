package fr.abyss.model;

/**
 * Représente une mission sous-marine à assigner à un drone.
 */
public class Mission {

    // TODO 2.1 : Déclarer les attributs privés :
    // - int id
    // - String name
    // - int depth (en mètres)
    // - int energyCost (pourcentage entre 0 et 100)
    // - Capability requiredCapability
    // - double payloadKg (masse à remonter en kg, 0 si aucune)
    // - MissionStatus status

    private int id;
    private String name;
    private int depth;
    private int energyCost;
    private Capability requiredCapability;
    private double payloadKg;
    private MissionStatus status;

    // TODO 2.2 : Compléter le constructeur
    public Mission(int id, String name, int depth, int energyCost, Capability requiredCapability, double payloadKg) {
        this.id = id;
        this.name = name;
        this.depth = depth;
        this.energyCost = energyCost;
        this.requiredCapability = requiredCapability;
        this.payloadKg = payloadKg;
        this.status = MissionStatus.PENDING;
    }

    // TODO 2.3 : Vérifier et compléter les getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDepth() {
        return depth;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public Capability getRequiredCapability() {
        return requiredCapability;
    }

    public double getPayloadKg() {
        return payloadKg;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%d] %-30s | Prof: %4dm | Coût: %2d%% | Cap. req: %-11s | Charge: %4.1fkg | Statut: %s",
                id, name, depth, energyCost, requiredCapability, payloadKg, status);
    }
}
