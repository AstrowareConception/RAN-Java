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

    // TODO 2.2 : Écrire le constructeur pour initialiser :
    // (id, name, depth, energyCost, requiredCapability, payloadKg)
    // et positionner le statut initial à MissionStatus.PENDING.

    // TODO 2.3 : Écrire les getters pour tous les champs :
    // - getId()
    // - getName()
    // - getDepth()
    // - getEnergyCost()
    // - getRequiredCapability()
    // - getPayloadKg()
    // - getStatus()
    // Ainsi que le setter :
    // - setStatus(MissionStatus status)

    // TODO 2.4 : Optionnel : redéfinir toString() pour afficher la mission.
}
