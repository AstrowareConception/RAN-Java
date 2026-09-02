# Feuille de Route — Parcours Conception Objet (TODO)

Ce fichier liste les jalons recommandés pour mener à bien la conception de votre domaine Abyss Explorer.

---

- [ ] **TODO 1 — Modèle de Domaine & Invariants** :
  - Compléter les entités `Drone` et `Mission` dans `fr.abyss.domain.model`.
  - Garantir l'encapsulation et l'immutabilité des champs structurels (`id`, `name`, `maxDepth`).
  - Protéger les invariants : batterie strictement bornée dans $[0, 100]$, transitions d'état autorisées.

- [ ] **TODO 2 — Objet de Résultat Métier** :
  - Utiliser `AssignmentResult` et l'énumération `AssignmentFailureReason` pour qualifier tout résultat d'évaluation de mission (succès ou échec multi-causes).

- [ ] **TODO 3 — Service d'Affectation (`MissionAssignmentService`)** :
  - Implémenter l'évaluation des règles R1 (Disponibilité), R2 (Profondeur), R3 (Énergie & Réserve 15%), R4 (Capacités) et R5 (Charge max).
  - Retourner un `AssignmentResult` exhaustif listant toutes les non-conformités constatées.

- [ ] **TODO 4 — Cycle de Vie et Exécution (R6, R7)** :
  - Implémenter l'exécution d'une mission (décrémentation de batterie, passage à `COMPLETED`, bascule automatique en `MAINTENANCE` si batterie $< 15\,\%$).
  - Implémenter la recharge sécurisée et la remise en service opérationnelle.

- [ ] **TODO 5 — Sélection Automatique & Stratégie** :
  - Implémenter `findCompatibleDrones(Mission mission)`.
  - Implémenter `findBestDroneFor(Mission mission)` en exploitant `Optional<Drone>` et `Comparator`. Justifier votre critère d'optimisation.

- [ ] **TODO 6 — Tests Unitaires Automatisés (JUnit 5)** :
  - Compléter la classe `MissionAssignmentTest` dans `src/test/java/fr/abyss/domain/` pour valider chaque scénario de référence (Scénarios A à H).

- [ ] **TODO 7 — Interface Console** :
  - Connecter l'interface textuelle dans `fr.abyss.ui.ConsoleApplication` aux services du domaine.

- [ ] **TODO 8 (Pour Aller Plus Loin)** :
  - Introduire les équipements modulaires (R8).
  - Implémenter le service de planification multi-missions (`MissionPlannerService`).
