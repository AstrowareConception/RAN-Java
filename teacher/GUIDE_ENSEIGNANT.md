# Guide Enseignant — Abyss Explorer (Atelier 7 Heures)

Ce guide opérationnel est destiné à l'équipe pédagogique pour animer la journée de remise à niveau / positionnement Java POO (7 heures) auprès d'un public hétérogène (admissions parallèles B3 / M1).

---

## 1. Avant la Séance : Checklist Préparatoire

* [ ] Vérifier la présence d'un **JDK 21 LTS** sur les postes étudiants (`tools/check-environment.bat` ou `.sh`).
* [ ] S'assurer que les étudiants ont accès à un IDE moderne (IntelliJ IDEA Community/Ultimate, VS Code avec extension Java, ou Eclipse).
* [ ] Générer et distribuer les archives étudiantes via `tools/package-kits.ps1` (ou `.sh`). **Ne jamais distribuer le dossier `teacher/`**.

---

## 2. Déroulé Minuté des 7 Heures

| Horaire | Étape | Activité Enseignant & Étudiants | Jalons attendus |
|---|---|---|---|
| **00:00 - 00:20** | **Lancement & Orientation** | Présentation de l'univers Abyss Explorer (10 min). Auto-positionnement des étudiants avec `student/CHOISIR_MON_PARCOURS.md` (10 min). | Parcours choisi par chaque étudiant. Starter ouvert et premier `Main` exécuté. |
| **00:20 - 01:00** | **Modèle Initial** | *Essentiel* : Déclaration des attributs privés, constructeurs, enums (`Drone`, `Mission`).<br>*Conception* : Réflexion sur la modélisation (composition vs héritage), invariants, records. | Classes de base créées et constructeurs testés. |
| **01:00 - 02:00** | **Collections & Affichage** | *Essentiel* : Chargement des 5 drones et 6 missions, boucles `for-each`, menu console.<br>*Conception* : Immutabilité des collections, service d'affectation, premiers tests JUnit. | Affichage propre de la flotte et des missions en console. |
| **02:00 - 03:00** | **Règles d'Affectation** | *Essentiel* : Implémentation des règles R1 (Disponibilité), R2 (Profondeur), R3 (Batterie + réserve 15%).<br>*Conception* : Modélisation complète de `AssignmentResult` et règles R1 à R5. | Validation des scénarios A à G avec explications claires des refus. |
| **03:00 - 04:00** | **Cycle de Vie & Exécution** | *Essentiel* : Affectation réelle (`ON_MISSION`), début de l'exécution R6.<br>*Conception* : R6 (Exécution + bascule maintenance) et R7 (Recharge plafonnée). | Scénarios H à L fonctionnels. |
| **04:00 - 05:00** | **Différenciation Pédagogique** | *Essentiel* : Bonus E1 (Capacités R4), E2 (Charge R5).<br>*Conception* : Équipements modulaires (R8) ou sélection automatique (`findBestDroneFor`). | Les étudiants avancés manipulent les ��quipements et comparators. |
| **05:00 - 06:00** | **Approfondissement** | *Essentiel* : Recherche des missions compatibles (E5) et consolidation.<br>*Conception* : Heuristique de planification multi-missions (`MissionPlannerService`) et tests JUnit. | Projet complet et testé. |
| **06:00 - 06:40** | **Finalisation & Revue** | Nettoyage du code, passage de la grille d'évaluation, préparation au débrief. | Code prêt pour évaluation. |
| **06:40 - 07:00** | **Débrief Collectif** | Discussion autour de 3 architectures étudiantes réelles (voir `DEBRIEF.md`). | Synthèse des concepts POO. |

---

## 3. Gestion du Temps : Que Sacrifier en Cas de Retard ?

* **Pour le parcours Essentiel** :
  * Si retard à $H+4$ : Ne pas faire la charge utile (R5) ni la recharge (R7). Se concentrer sur un socle irréprochable : *Classes propres + Encapsulation + Menu + R1/R2/R3 + Affectation*.
* **Pour le parcours Conception Objet** :
  * Si retard à $H+5$ : Sacrifier la planification heuristique multi-missions et JavaFX. L'évaluation priorise la qualité du domaine et des tests unitaires sur R1-R7.

---

## 4. Posture Pédagogique et Accompagnement

### Face à un étudiant Essentiel en difficulté
1. **Ne jamais coder à sa place**.
2. Faire verbaliser la règle métier en français avant d'écrire la moindre ligne de code.
3. Renvoyer vers les paliers d'indices de `HINTS.md` et les exemples de `AIDE_MEMOIRE_JAVA.md`.
4. Valoriser immédiatement la propreté d'une classe bien encapsulée.

### Face à un étudiant Conception Objet rapide
1. Le challenger sur la robustesse de ses invariants : *« Que se passe-t-il si j'injecte une batterie de 150 % ? Si j'exécute une mission déjà COMPLETED ? »*.
2. L'interroger sur le couplage : *« Si j'ajoute demain une capacité ACOUSTIC_SAMPLING, combien de classes devez-vous modifier ? »*.
3. L'orienter vers les tests unitaires paramétrés (`@ParameterizedTest`) et le service de planification.

---

## 5. Anti-Patterns Pédagogiques Fréquents à Signaler

* **Anti-pattern 1 : La classe Dieu (`Main` de 800 lignes)** : Tout le code métier empilé dans une unique méthode `main`.
* **Anti-pattern 2 : L'encapsulation fantoche** : Attributs `public` ou getters/setters aveugles qui permettent à l'extérieur de faire `drone.setBattery(-80)`.
* **Anti-pattern 3 : L'explosion combinatoire d'héritage** : `ObservationAndRepairAndCargoDrone extends ...`.
* **Anti-pattern 4 : Les codes magiques en chaîne de caractères** : `if (drone.getType().equals("repair"))` au lieu d'énumérations fortement typées (`enum`).
