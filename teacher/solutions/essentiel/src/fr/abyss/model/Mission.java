package fr.abyss.model;

/**
 * Modélise une mission sous-marine dans la solution Essentiel.
 */
public class Mission {

    private final int id;
    private final String name;
    private final int depth;
    private final int energyCost;
    private final Capability requiredCapability;
    private final double payloadKg;
    private MissionStatus status;

    public Mission(int id, String name, int depth, int energyCost, Capability requiredCapability, double payloadKg) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Le nom de la mission ne peut pas être vide.");
        }
        if (depth <= 0) {
            throw new IllegalArgumentException("La profondeur doit être strictement positive.");
        }
        if (energyCost < 0 || energyCost > 100) {
            throw new IllegalArgumentException("Le coût énergétique doit être compris entre 0 et 100%.");
        }
        if (requiredCapability == null) {
            throw new IllegalArgumentException("Une capacité requise doit être spécifiée.");
        }
        if (payloadKg < 0) {
            throw new IllegalArgumentException("La charge de la mission doit être positive ou nulle.");
        }

        this.id = id;
        this.name = name;
        this.depth = depth;
        this.energyCost = energyCost;
        this.requiredCapability = requiredCapability;
        this.payloadKg = payloadKg;
        this.status = MissionStatus.PENDING;
    }

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

    public boolean isPending() {
        return this.status == MissionStatus.PENDING;
    }

    @Override
    public String toString() {
        return String.format("[%d] %-30s | Prof: %4dm | Coût: %2d%% | Cap. req: %-11s | Charge: %4.1fkg | Statut: %s",
                id, name, depth, energyCost, requiredCapability, payloadKg, status);
    }
}
