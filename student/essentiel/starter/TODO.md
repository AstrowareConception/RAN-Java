# Liste des Tâches — Parcours Essentiel (TODO)

Suivez les étapes ci-dessous dans l'ordre pour construire progressivement votre application.

---

- [ ] **TODO 1** : Dans `fr.abyss.model.Drone`, déclarer les attributs privés nécessaires (`id`, `name`, `battery`, `maxDepth`, `status`, `capabilities`, `maxPayloadKg`) et compléter le constructeur et les getters.
- [ ] **TODO 2** : Dans `fr.abyss.model.Mission`, déclarer les attributs privés nécessaires (`id`, `name`, `depth`, `energyCost`, `requiredCapability`, `payloadKg`, `status`) et compléter le constructeur et les getters.
- [ ] **TODO 3** : Dans `fr.abyss.service.FleetManager`, implémenter `initializeData()` pour créer les 5 drones et les 6 missions de référence (voir `docs/REGLES_METIER.md`).
- [ ] **TODO 4** : Dans `fr.abyss.service.FleetManager`, compléter les méthodes `displayDrones()` et `displayMissions()` pour un affichage lisible dans la console.
- [ ] **TODO 5** : Dans `fr.abyss.service.FleetManager`, implémenter la méthode `testAssignment(int droneId, int missionId)` pour vérifier les règles métier R1 (Disponibilité), R2 (Profondeur) et R3 (Batterie + Réserve de 15 %) et afficher le motif en cas de refus.
- [ ] **TODO 6** : Dans `fr.abyss.service.FleetManager`, implémenter `assignMission(int droneId, int missionId)` pour changer les statuts du drone (`ON_MISSION`) et de la mission (`ASSIGNED`) après validation des règles.
- [ ] **TODO 7** : Dans `fr.abyss.Main`, connecter le menu console interactif aux méthodes du `FleetManager`.
- [ ] **TODO 8 (Optionnel / Bonus)** : Implémenter les règles R4 (Capacités), R5 (Charge max), R6 (Exécution) et R7 (Recharge / Maintenance).
