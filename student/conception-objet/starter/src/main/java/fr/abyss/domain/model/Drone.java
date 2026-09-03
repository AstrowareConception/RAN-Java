package fr.abyss.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité de domaine représentant un drone sous-marin autonome.
 *
 * TODO 1 : Concevoir la classe Drone en protégeant ses invariants.
 */
public class Drone {

    // TODO 1.1 : Déclarer les attributs privés et protégés :
    // - id (int, immuable)
    // - name (String, immuable)
    // - battery (int, mutable, entre 0 et 100)
    // - maxDepth (int, immuable)
    // - status (DroneStatus, initialisé à AVAILABLE)
    // - capabilities (Set<Capability>, encapsulé)
    // - maxPayloadKg (double, immuable)

    // TODO 1.2 : Écrire le constructeur principal en validant les invariants :
    // - Le nom ne doit pas être null ni vide (lever IllegalArgumentException)
    // - La batterie doit être comprise entre 0 et 100 %
    // - La profondeur maximale doit être strictement positive
    // - La charge utile maximale doit être positive ou nulle
    // - Le statut initial doit être DroneStatus.AVAILABLE

    // TODO 1.3 : Écrire les getters pour tous les champs :
    // - getId()
    // - getName()
    // - getBattery()
    // - getMaxDepth()
    // - getStatus()
    // - getCapabilities() (penser à retourner une vue non modifiable avec Collections.unmodifiableSet)
    // - getMaxPayloadKg()
    // Et le setter de statut : setStatus(DroneStatus status)

    // TODO 1.4 : Implémenter les méthodes métier du domaine :
    // - isAvailable() : retourne true si le statut est DroneStatus.AVAILABLE
    // - canDiveTo(int targetDepth) : vérifie si targetDepth <= maxDepth
    // - hasEnoughBatteryFor(int energyCost) : vérifie battery >= energyCost + 15 (réserve de 15 %)
    // - hasCapability(Capability capability) : vérifie si le drone possède la capacité
    // - canCarry(double payload) : vérifie si payload <= maxPayloadKg
    // - executeMission(int energyCost) : décrémente la batterie, bascule en MAINTENANCE si batterie < 15 %, sinon AVAILABLE
    // - recharge(int amount) : augmente la batterie (plafonnée à 100), remet le drone à AVAILABLE si batterie >= 15 %

    // TODO 1.5 : Optionnel : redéfinir toString() pour l'affichage console.
}
