# Solution Enseignant — Parcours Conception Objet

Ce dossier contient une proposition de solution robuste et modulaire pour le parcours **Conception Objet**.

---

## 1. Principes et Architecture Retenue

* **Proposition de conception (non unique)** : Cette implémentation démontre une architecture fondée sur la **composition** et des **objets résultats typés**, tout en restant sobre et directement compréhensible.
* **Modules d'équipements (`Equipment`)** : Modélisation des capacités par interface et équipements concrets (`Camera`, `Sonar`, `ManipulatorArm`, `RepairKit`, `CargoModule`).
* **Service d'affectation (`MissionAssignmentService`)** : Évaluation déclarative des règles R1 à R5 et R8 sans effet de bord.
* **Service de planification heuristique (`MissionPlannerService`)** : Allocation automatique des missions prioritaires aux meilleurs drones disponibles.
* **Garantie des invariants** : Protection stricte des bornes de batterie ($[0, 100]$), intégrité des statuts et vues non modifiables (`unmodifiableList`, `unmodifiableSet`).

---

## 2. Commandes de Compilation et Tests

```bash
# Compiler le projet
mvn compile

# Exécuter l'ensemble de la suite de tests unitaires JUnit 5 (21 tests de référence)
mvn test

# Démarrer l'application console de supervision
mvn exec:java
```

---

## 3. Analyse des Choix de Conception

Pour le détail des arbitrages (Composition vs Héritage vs Interfaces, Exceptions vs Result Object, Invariants), consultez le document `DESIGN_NOTES.md`.
