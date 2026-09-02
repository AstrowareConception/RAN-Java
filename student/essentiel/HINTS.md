# Indices Progressifs — Parcours Essentiel

Consultez ces indices uniquement lorsque vous êtes bloqué sur une étape précise. Lisez-les dans l'ordre, du premier au dernier indice.

---

## 1. Modélisation de `Drone` et encapsulation

* **Indice 1** : Tous les attributs de la classe doivent porter le mot-clé `private` pour interdire les accès directs extérieurs non contrôlés.
* **Indice 2** : Les getters retournent simplement la valeur du champ (ex. `public int getBattery() { return battery; }`).
* **Indice 3** : Pour stocker plusieurs capacités dans un drone, vous pouvez utiliser une `List<Capability>` ou un `Set<Capability>`.
* **Indice 4** : Dans le constructeur, le mot-clé `this` permet de lever l'ambiguïté entre le paramètre et le champ de la classe :
  ```java
  public Drone(int id, String name, int battery, int maxDepth, Set<Capability> capabilities, double maxPayloadKg) {
      this.id = id;
      this.name = name;
      this.battery = battery;
      // ...
  }
  ```

---

## 2. Règle R2 — Profondeur Maximale

* **Indice 1** : Comparez la profondeur maximale du drone avec la profondeur demandée par la mission.
* **Indice 2** : Le drone peut plonger si sa profondeur maximale est supérieure ou égale (`>=`) à celle de la mission.
* **Indice 3** : En Java :
  ```java
  if (drone.getMaxDepth() < mission.getDepth()) {
      // Refus : mission trop profonde pour ce drone
  }
  ```

---

## 3. Règle R3 — Énergie et Réserve de Sécurité

* **Indice 1** : Le drone ne doit pas épuiser toute sa batterie durant l'opération. Il lui faut 15 % d'énergie de réserve pour remonter.
* **Indice 2** : L'énergie requise totale pour accepter la mission est donc égale au coût de la mission plus 15.
* **Indice 3** : Le drone a assez d'énergie si sa batterie actuelle est supérieure ou égale à cette somme.
* **Indice 4** : Condition exacte :
  ```java
  int requiredEnergy = mission.getEnergyCost() + 15;
  if (drone.getBattery() < requiredEnergy) {
      // Refus : batterie insuffisante
  }
  ```

---

## 4. Règle R4 — Vérification des Capacités (Bonus)

* **Indice 1** : Une mission requiert une seule capacité principale (`Capability.OBSERVATION`, `RECOVERY` ou `REPAIR`).
* **Indice 2** : Un drone possède une collection de capacités (ex. `Set<Capability>`).
* **Indice 3** : Utilisez la méthode `.contains()` de la collection :
  ```java
  if (!drone.getCapabilities().contains(mission.getRequiredCapability())) {
      // Refus : le drone ne possède pas la capacité requise
  }
  ```

---

## 5. Règle R6 — Exécution de Mission (Bonus)

* **Indice 1** : L'exécution d'une mission modifie à la fois la batterie du drone et les statuts du drone et de la mission.
* **Indice 2** : Soustrayez le coût de la mission de la batterie : `battery = battery - mission.getEnergyCost()`.
* **Indice 3** : Passez la mission au statut `MissionStatus.COMPLETED`.
* **Indice 4** : Si la batterie restante est inférieure à 15, le drone doit passer à `DroneStatus.MAINTENANCE`, sinon il redevient `DroneStatus.AVAILABLE`.
