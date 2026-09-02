# Pour Aller Plus Loin — Parcours Conception Objet

Défis de conception logicielle destinés aux étudiants rapides ayant finalisé le socle avancé (R1-R7, `AssignmentResult`, sélection automatique, JUnit 5).

---

## Défi 1 — Architecture Modulaire des Équipements (Règle R8)

* **Problématique** : Un drone n'est plus intrinsèquement « réparateur » ou « observateur » par nature, mais acquiert ses compétences grâce à des modules amovibles (`Equipment`) installés dans sa baie technique.
* **Conception suggérée** :
  * Définir une interface ou classe abstraite `Equipment` :
    ```java
    public interface Equipment {
        String getName();
        Capability getProvidedCapability();
        double getWeightKg();
        int getExtraEnergyConsumption();
    }
    ```
  * Implémenter plusieurs équipements concrets :
    * `Camera` $\rightarrow$ confère `OBSERVATION`, poids $1.5\text{ kg}$, surcoût $0\,\%$
    * `ManipulatorArm` $\rightarrow$ confère `REPAIR`, poids $8.0\text{ kg}$, surcoût $+5\,\%$
    * `Sonar` $\rightarrow$ confère `OBSERVATION`, poids $4.0\text{ kg}$, surcoût $+10\,\%$
    * `CargoModule` $\rightarrow$ confère `RECOVERY`, poids $5.0\text{ kg}$, augmente la charge utile du drone
  * Adapter le calcul de consommation énergétique et de charge maximale dans le drone et l'affectation.

---

## Défi 2 — Planification Multi-Missions Heuristique (`MissionPlannerService`)

* **Problématique** : Le centre océanographique reçoit une liste de $N$ missions à accomplir simultanément avec une flotte de $M$ drones disponibles ($N \ge M$). Comment allouer au mieux les ressources ?
* **Spécification** :
  ```java
  public List<PlanAssignment> planMissions(List<Drone> availableDrones, List<Mission> pendingMissions)
  ```
* **Heuristique proposée** :
  1. Trier les missions par ordre de priorité décroissante (`CRITICAL` > `HIGH` > `NORMAL` > `LOW`), puis par profondeur décroissante.
  2. Pour chaque mission prioritaire, identifier les drones compatibles encore libres.
  3. Affecter le drone optimal selon votre stratégie (ex. préserver la réserve ou minimiser le surdimensionnement).
  4. Retirer le drone affecté de la réserve disponible et passer à la mission suivante.
  5. Retourner le plan complet avec les missions non affectées et leur motif.

---

## Défi 3 — Invariants de Concurrence et Immuabilité

* Rendre les entités les plus immuables possibles.
* Fournir des vues non modifiables (`Collections.unmodifiableList`, `Collections.unmodifiableSet`) pour empêcher la corruption externe de l'état d'un drone ou d'une mission.
* Valider toutes les transitions d'état de manière atomique (ex. échec de transition si préconditions non remplies).
