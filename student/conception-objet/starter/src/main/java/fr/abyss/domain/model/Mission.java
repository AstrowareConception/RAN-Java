package fr.abyss.domain.model;

/**
 * Entité de domaine représentant une mission sous-marine avec priorité.
 *
 * TODO 2 : Concevoir la classe Mission en protégeant ses invariants.
 */
public class Mission {

    // TODO 2.1 : Déclarer les attributs privés :
    // - id (int, immuable)
    // - name (String, immuable)
    // - depth (int, immuable)
    // - energyCost (int, immuable)
    // - requiredCapability (Capability, immuable)
    // - payloadKg (double, immuable)
    // - priority (MissionPriority, immuable)
    // - status (MissionStatus, initialisé à PENDING)

    // TODO 2.2 : Écrire les constructeurs en validant les invariants :
    // - Constructeur complet (id, name, depth, energyCost, requiredCapability, payloadKg, priority)
    //   * name non null et non vide
    //   * depth > 0
    //   * energyCost compris entre 0 et 100
    //   * requiredCapability non null
    //   * payloadKg >= 0
    //   * priority par défaut à MissionPriority.NORMAL si null
    //   * status initialisé à MissionStatus.PENDING
    // - Constructeur de commodité sans priority (assigne NORMAL par défaut)

    // TODO 2.3 : Écrire les accesseurs pour tous les champs :
    // - getId()
    // - getName()
    // - getDepth()
    // - getEnergyCost()
    // - getRequiredCapability()
    // - getPayloadKg()
    // - getPriority()
    // - getStatus()
    // Et le setter : setStatus(MissionStatus status)
    // Ainsi que la méthode d'aide isPending() : retourne true si status == MissionStatus.PENDING

    // TODO 2.4 : Optionnel : redéfinir toString() pour l'affichage console.
}
