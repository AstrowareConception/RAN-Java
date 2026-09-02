# Indices Progressifs — Parcours Conception Objet

Consultez ces indices pour guider votre réflexion architecturale sans dévoiler l'implémentation finale.

---

## 1. Composition vs Interfaces vs Héritage

* **Indice 1** : L'héritage simple de Java interdit d'hériter de deux classes distinctes (`class Argos extends ObservationDrone, CargoDrone` est invalide).
* **Indice 2** : Si vous utilisez des interfaces (`ObservationCapable`, `CargoCarrier`), les drones peuvent implémenter plusieurs interfaces, mais la logique de stockage des capacités reste à répéter ou déléguer.
* **Indice 3** : La composition avec un `Set<Capability>` ou une liste de modules `List<Equipment>` permet d'ajouter, retirer ou modifier les capacités à l'exécution sans modifier la structure de classe du drone.

---

## 2. Modélisation de `AssignmentResult`

* **Indice 1** : En Java 16+, vous pouvez utiliser un `record` pour représenter ce conteneur de données immuable :
  ```java
  public record AssignmentResult(boolean isAllowed, List<AssignmentFailureReason> reasons) {
      public static AssignmentResult success() {
          return new AssignmentResult(true, List.of());
      }
      public static AssignmentResult failure(List<AssignmentFailureReason> reasons) {
          return new AssignmentResult(false, List.copyOf(reasons));
      }
  }
  ```
* **Indice 2** : L'énumération `AssignmentFailureReason` centralise tous les motifs possibles (`DRONE_UNAVAILABLE`, `DEPTH_EXCEEDED`, `INSUFFICIENT_BATTERY`, `MISSING_CAPABILITY`, `PAYLOAD_TOO_HEAVY`, etc.).

---

## 3. Sélection Automatique et « Meilleur Drone »

* **Indice 1** : Pour trouver les drones compatibles, filtrez la flotte en testant si `assignmentService.validate(drone, mission).isAllowed()`.
* **Indice 2** : Pour choisir le *meilleur drone*, définissez un critère :
  * *Stratégie A (Énergie maximale résiduelle)* : Choisir le drone qui aura le pourcentage de batterie le plus élevé après l'opération (`Comparator.comparingInt(Drone::getBattery).reversed()`).
  * *Stratégie B (Préservation des grands fonds)* : Choisir le drone compatible ayant la plus petite profondeur maximale suffisante, afin de garder les drones de grande profondeur en réserve (`Comparator.comparingInt(Drone::getMaxDepth)`).
* **Indice 3** : Retournez un `Optional<Drone>` pour signifier proprement l'absence de drone compatible :
  ```java
  public Optional<Drone> findBestDroneFor(Mission mission) {
      return findCompatibleDrones(mission).stream()
          .max(Comparator.comparingInt(Drone::getBattery));
  }
  ```

---

## 4. Tests Unitaires JUnit 5

* **Indice 1** : Utilisez l'annotation `@Test` sur chaque méthode de validation.
* **Indice 2** : Utilisez `@BeforeEach` pour réinitialiser une flotte propre avant chaque test.
* **Indice 3** : Exemple de structure de test :
  ```java
  @Test
  void shouldRejectAssignmentWhenDepthExceedsDroneCapacity() {
      Drone nautilus = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
      Mission deepMission = new Mission(2, "Balise B12", 700, 30, Capability.RECOVERY, 18.0);

      AssignmentResult result = assignmentService.validate(nautilus, deepMission);

      assertFalse(result.isAllowed());
      assertTrue(result.reasons().contains(AssignmentFailureReason.DEPTH_EXCEEDED));
  }
  ```
