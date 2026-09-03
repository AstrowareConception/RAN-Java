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

    // TODO 1.2 : Écrire le constructeur pour initialiser l'ensemble des champs
    // (id, name, battery, maxDepth, capabilities, maxPayloadKg)
    // et positionner le statut initial à DroneStatus.AVAILABLE.
    // Penser à borner ou vérifier la batterie entre 0 et 100 %.

    // TODO 1.3 : Écrire les getters pour tous les champs :
    // - getId()
    // - getName()
    // - getBattery()
    // - getMaxDepth()
    // - getStatus()
    // - getCapabilities()
    // - getMaxPayloadKg()
    // Ainsi que les setters :
    // - setStatus(DroneStatus status)
    // - setBattery(int battery)

    // TODO 1.4 : Optionnel : redéfinir toString() pour afficher les informations du drone.
}
