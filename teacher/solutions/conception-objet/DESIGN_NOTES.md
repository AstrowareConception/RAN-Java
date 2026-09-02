# Notes de Conception & Analyse d'Architecture

Ce document analyse les choix techniques et conceptuels réalisés dans la solution de référence du parcours **Conception Objet**.

---

## 1. Pourquoi la Composition plutôt que l'Héritage pour les Drones et Capacités ?

### L'impasse de l'héritage simple
Si l'on crée des sous-classes de `Drone` telles que :
* `ObservationDrone extends Drone`
* `RecoveryDrone extends Drone`
* `RepairDrone extends Drone`

On se heurte immédiatement au cas d'**Argos** (Observation + Recovery) et de **Proteus** (Observation + Repair).
Comme Java n'autorise pas l'héritage multiple de classes (`class ArgosDrone extends ObservationDrone, RecoveryDrone` est interdit), cela obligerait soit à multiplier les classes combinatoires (`ObservationAndRecoveryDrone`), soit à dupliquer du code.

### L'apport de la composition
En conférant au drone une collection d'équipements (`List<Equipment>`) et de capacités intrinsèques (`Set<Capability>`), le modèle devient :
1. **Extensible dynamiquement** : Un drone peut embarquer ou retirer un équipement à l'exécution sans recréer d'objet.
2. **Conforme à Open/Closed Principle** : L'ajout d'une nouvelle capacité (ex. `SAMPLING`) ou d'un nouvel équipement (`Spectrometer`) ne modifie ni la classe `Drone` ni les autres équipements.

---

## 2. Répartition des Responsabilités : `Drone` vs `MissionAssignmentService`

* **Responsabilité de `Drone`** : Gérer son état propre et ses invariants locaux (ex. s'assurer que sa batterie reste entre 0 et 100 %, savoir si sa propre profondeur maximale est suffisante, décrémenter sa batterie lors d'une mission).
* **Responsabilité de `MissionAssignmentService`** : Évaluer les règles d'adéquation globale entre un `Drone` et une `Mission` (R1 à R5, R8).
  * *Avantage* : Le service est sans état (*stateless*), ce qui rend son évaluation pure, hautement parallélisable et testable de manière unitaire sans effet de bord.

---

## 3. Pourquoi un `AssignmentResult` plutôt que des Exceptions ?

* Le rejet d'une mission (ex. drone trop peu profond ou batterie insuffisante) fait partie du **fonctionnement nominal** d'un centre de contrôle d'exploration sous-marine. Ce n'est pas une anomalie système imprévue.
* L'utilisation d'exceptions pour du contrôle de flux métier présente plusieurs défauts pédagogiques et techniques :
  * Coût de capture de la pile (*stack trace*).
  * Arrêt dès la première règle non respectée (impossible d'indiquer à l'utilisateur *toutes* les raisons d'incompatibilité en une seule passe).
* `AssignmentResult` (modélisé sous forme de `record` Java 21) permet de collecter l'ensemble exhaustif des motifs de refus (`List<AssignmentFailureReason>`), facilitant l'affichage utilisateur et les assertions précises dans les tests unitaires.

---

## 4. Protection des Invariants et Immuabilité

* Les collections internes de `Drone` et `Mission` sont encapsulées et exposées via `Collections.unmodifiableList` / `Collections.unmodifiableSet` afin d'empêcher toute modification directe non autorisée depuis l'extérieur.
* Les transitions de statut sont strictement contrôlées (par exemple, une mission `COMPLETED` ne peut pas être réaffectée).
* La méthode `recharge(amount)` et `executeMission(cost)` protègent les bornes $[0, 100]$ de la batterie grâce à `Math.min` et `Math.max`.
