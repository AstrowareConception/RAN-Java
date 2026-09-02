# Parcours Conception Objet — Abyss Explorer

Bienvenue dans le parcours **Conception Objet** !
Ce parcours s'adresse aux étudiants à l'aise avec la syntaxe Java et désireux d'approfondir la modélisation logicielle, l'architecture orientée objet et la testabilité.

---

## 1. Objectifs Pédagogiques

* Concevoir un **domaine métier robuste** protégeant ses invariants à chaque instant.
* Séparer strictement les responsabilités entre le domaine (`domain`), les services d'orchestration (`service`) et l'interface utilisateur (`ui`).
* Arbitrer de manière justifiée entre **composition**, **interfaces** et **héritage**.
* Modéliser les retours métier avec un objet dédié (`AssignmentResult`) et une énumération des causes de rejet plutôt qu'un booléen primitif.
* Exploiter les fonctionnalités modernes de Java : `Optional<T>`, `Stream`, `record`, `Comparator`.
* Valider systématiquement les comportements et les cas limites avec des tests unitaires **JUnit 5**.

---

## 2. Socle Obligatoire du Parcours

1. **Règles métier complètes R1 à R7** :
   * Disponibilité (R1)
   * Profondeur maximale (R2)
   * Énergie & Réserve de 15 % (R3)
   * Capacités requises (R4)
   * Charge utile maximale (R5)
   * Cycle de vie et exécution avec passage sous réserve (R6)
   * Maintenance et recharge sous contrainte de borne (R7)
2. **Architecture et Séparation des Responsabilités** :
   * La couche `ui` (console) ne contient aucun calcul métier.
   * `Drone` et `Mission` protègent leurs états internes (batterie entre 0 et 100%, transitions de statuts cohérentes).
3. **Objet de Résultat Métier** :
   * `AssignmentResult` retournant un statut d'autorisation et une liste typée de motifs de refus (`AssignmentFailureReason`).
4. **Sélection Automatique** :
   * `List<Drone> findCompatibleDrones(Mission mission)`
   * `Optional<Drone> findBestDroneFor(Mission mission)` avec justification de la stratégie retenue.
5. **Tests Automatisés (JUnit 5)** :
   * Au minimum 6 tests unitaires couvrant les cas nominaux et les rejets clés.

---

## 3. Démarche de Conception : Éviter les Pièges Classiques

> **Important** : Il n'existe pas une unique architecture parfaite imposée. Vous devez faire des choix réfléchis et être en mesure de les défendre.

* **Piège de l'héritage abusif** : Si vous créez une hiérarchie `ObservationDrone`, `RepairDrone`, `CargoDrone`, comment modéliserez-vous *Argos* qui sait observer ET remonter des charges ?
* **Alternative par composition** : Attribuer un `Set<Capability>` ou une collection de modules `Equipment` branchés dynamiquement sur le drone.
* **Piège du modèle anémique** : Ne réduisez pas vos classes `Drone` et `Mission` à de simples conteneurs de données passifs avec getters/setters. Donnez-leur de véritables méthodes métier qui garantissent leurs invariants.

---

## 4. Organisation du Projet

Le starter est structuré comme un projet Maven standard :

```text
starter/
├── pom.xml
├── TODO.md
└── src/
    ├── main/java/fr/abyss/
    │   ├── domain/model/     <- Entités, enums, records de résultat
    │   ├── service/          <- Services métier (affectation, flotte, sélection)
    │   ├── ui/               <- Interface utilisateur console
    │   └── Main.java
    └── test/java/fr/abyss/
        └── domain/           <- Tests unitaires JUnit 5
```

---

## 5. Commandes Maven Utiles

```bash
# Compiler le projet
mvn compile

# Lancer les tests unitaires JUnit 5
mvn test

# Exécuter l'application console
mvn exec:java -Dexec.mainClass="fr.abyss.Main"
```

---

## 6. Pour Aller Plus Loin

Consultez `POUR_ALLER_PLUS_LOIN.md` pour aborder les défis avancés :
* Modélisation modulaire des **équipements** (`Equipment`, R8).
* Algorithme heuristique de **planification de missions multiples** (`planMissions()`) selon la priorité.
* Bonus graphique facultatif **JavaFX** (`JAVAFX_BONUS.md`).
