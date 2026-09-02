# Pour Aller Plus Loin — Parcours Essentiel

Vous avez terminé le socle minimal attendu (classes, menu, règles R1-R3, affectation) ? Félicitations !
Voici les extensions progressives que vous pouvez ajouter à votre projet pour enrichir votre application.

---

## Extension E1 — Règle R4 : Contrôle des Capacités
* **Objectif** : Un drone ne doit pouvoir effectuer une mission que s'il dispose de la capacité requise (`OBSERVATION`, `RECOVERY` ou `REPAIR`).
* **Mise en œuvre** :
  1. Vérifiez que la collection `capabilities` de votre classe `Drone` contient la `requiredCapability` de la `Mission`.
  2. Testez le cas avec *Titan* (Recovery) tentant la mission *Hécate* (Observation) $\rightarrow$ l'affectation doit être refusée.

---

## Extension E2 — Règle R5 : Contrôle de la Charge Utile (Payload)
* **Objectif** : Si une mission requiert de récupérer un objet lourd ($\text{payloadKg} > 0$), la capacité d'emport du drone (`maxPayloadKg`) doit être suffisante.
* **Mise en œuvre** :
  1. Ajoutez la condition : `drone.getMaxPayloadKg() >= mission.getPayloadKg()`.
  2. Testez le cas d'*Argos* (25 kg max) tentant la mission *Boîte noire* (40 kg) $\rightarrow$ l'affectation doit être refusée.

---

## Extension E3 — Règle R6 : Exécution Réelle d'une Mission
* **Objectif** : Permettre à l'utilisateur de simuler l'accomplissement d'une mission en cours.
* **Mise en œuvre** :
  1. Ajoutez une option « 5. Exécuter une mission » dans votre menu console.
  2. L'utilisateur sélectionne un drone actuellement au statut `ON_MISSION`.
  3. Réduisez la batterie du drone du montant du coût énergétique de la mission.
  4. Si la batterie finale est $< 15\,\%$, passez le drone en `MAINTENANCE`. Sinon, repassez-le en `AVAILABLE`.
  5. Marquez la mission associée comme `COMPLETED`.

---

## Extension E4 — Règle R7 : Maintenance et Recharge
* **Objectif** : Ajouter une option dans le menu pour recharger un drone en maintenance ou à batterie faible.
* **Mise en œuvre** :
  1. Ajoutez une option « 6. Recharger un drone » dans le menu.
  2. Demandez à l'utilisateur un pourcentage de recharge (ex. $+30\,\%$).
  3. Assurez-vous que la batterie ne dépasse jamais $100\,\%$.
  4. Si le drone était en `MAINTENANCE` et que sa batterie dépasse $15\,\%$, remettez-le en `AVAILABLE`.

---

## Extension E5 — Missions Compatibles pour un Drone
* **Objectif** : Proposer automatiquement la liste de toutes les missions que peut réaliser un drone donné.
* **Mise en œuvre** :
  1. Écrivez dans `FleetManager` la méthode :
     ```java
     public List<Mission> findCompatibleMissions(Drone drone)
     ```
  2. Parcourez la liste des missions en attente et filtrez celles qui respectent l'ensemble des règles R1 à R5 pour ce drone.
  3. Ajoutez cette option dans le menu console.

---

## Extension E6 — Sauvegarde Fichier Simple (Facultatif)
* **Objectif** : Exporter l'état de la flotte dans un fichier texte ou CSV à la fermeture du programme (`java.io.PrintWriter` ou `java.nio.file.Files`).
