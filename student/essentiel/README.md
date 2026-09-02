# Parcours Essentiel — Abyss Explorer

Bienvenue dans le parcours **Essentiel** !
Ce parcours s'adresse aux étudiants qui découvrent Java ou qui souhaitent consolider leurs compétences fondamentales en programmation orientée objet.

---

## 1. Objectifs Pédagogiques

À l'issue de cet atelier, vous saurez :
* Concevoir des classes Java propres et bien encapsulées avec des attributs `private`.
* Écrire des constructeurs robustes et des getters appropriés.
* Utiliser des énumérations (`enum`) pour modéliser des statuts et des capacités.
* Manipuler des listes d'objets (`List<Drone>`, `List<Mission>`) avec des boucles `for-each`.
* Implémenter des règles métier sous forme de méthodes dans vos classes.
* Structurer une application console interactive avec `Scanner`.

---

## 2. Le Socle Minimum Attendu (Définition de la Réussite)

Votre travail sera pleinement validé et considéré comme réussi dès lors que vous aurez produit :
1. Les classes `Drone` et `Mission` correctement encapsulées.
2. Les énumérations `DroneStatus`, `MissionStatus` et `Capability`.
3. Le chargement des données initiales dans le gestionnaire de flotte (`FleetManager`).
4. Un menu console interactif permettant d'afficher les drones et les missions.
5. La vérification et l'application des règles métier :
   * **R1** (Disponibilité)
   * **R2** (Profondeur maximale)
   * **R3** (Énergie et réserve de sécurité de 15 %)
6. L'affectation effective d'une mission avec mise à jour des statuts (`ON_MISSION` et `ASSIGNED`).

*Note : Il n'est absolument pas attendu d'utiliser de l'héritage, des interfaces, Maven ou JUnit dans ce parcours.*

---

## 3. Plan de Progression Étape par Étape

### Étape 0 — Prise en main du starter (10-15 min)
* Ouvrez le projet situé dans le dossier `student/essentiel/starter/` dans votre IDE.
* Exécutez la classe `fr.abyss.Main`. Le menu principal doit s'afficher en console.
* Consultez le fichier `TODO.md` qui liste les tâches dans l'ordre.

### Étape 1 — Finaliser la classe `Drone` (30-40 min)
* Ouvrez `fr.abyss.model.Drone`.
* Déclarez les attributs privés : `id`, `name`, `battery`, `maxDepth`, `status`, `capabilities`, `maxPayloadKg`.
* Complétez le constructeur et les getters.
* Assurez-vous que la batterie ne peut pas être initialisée en dehors de l'intervalle $[0, 100]$.

### Étape 2 — Finaliser la classe `Mission` (30 min)
* Ouvrez `fr.abyss.model.Mission`.
* Déclarez les attributs privés : `id`, `name`, `depth`, `energyCost`, `requiredCapability`, `payloadKg`, `status`.
* Complétez le constructeur et les getters.

### Étape 3 — Initialiser les données de la flotte (20 min)
* Dans `fr.abyss.service.FleetManager`, complétez la méthode `initializeData()` pour instancier les 5 drones et les 6 missions de référence (voir `docs/REGLES_METIER.md`).

### Étape 4 — Affichage de la flotte et des missions (20 min)
* Complétez les méthodes d'affichage dans `FleetManager` pour lister proprement les drones et les missions avec leurs caractéristiques.

### Étape 5 — Vérification d'une affectation : Règles R1, R2, R3 (40 min)
* Créez dans `Drone` ou `FleetManager` les méthodes de vérification :
  * **R1** : Le drone est-il `AVAILABLE` et la mission `PENDING` ?
  * **R2** : La profondeur max du drone est-elle $\ge$ à la profondeur de la mission ?
  * **R3** : La batterie du drone est-elle $\ge \text{coût} + 15$ ?
* Affichez un message explicite expliquant le motif exact en cas de refus.

### Étape 6 — Affectation effective (20 min)
* Lorsque toutes les règles sont validées, modifiez les statuts :
  * Le drone passe à `ON_MISSION`.
  * La mission passe à `ASSIGNED`.

### Étape 7 — Consolidation et Tests Manuels (30 min)
* Rejouez les scénarios de référence du document `docs/SCENARIOS_REFERENCE.md` depuis votre menu console.

---

## 4. Pour aller plus loin

Dès que votre socle fonctionne, consultez le guide `POUR_ALLER_PLUS_LOIN.md` pour implémenter :
* Le contrôle des capacités requises (R4).
* La vérification de la charge utile (R5).
* L'exécution de la mission et la décrémentation de la batterie (R6).
* La maintenance et la recharge (R7).
* La recherche des missions compatibles pour un drone.
