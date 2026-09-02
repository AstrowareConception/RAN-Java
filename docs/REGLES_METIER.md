# Règles Métier — Abyss Explorer

Ce document formalise l'ensemble des règles de gestion applicables au système de supervision de la flotte sous-marine **Abyss Explorer**.
Les deux parcours pédagogiques (Essentiel et Conception objet) partagent ce même référentiel métier.

---

## 1. Entités Principales et Attributs

### 1.1 Le Drone (`Drone`)
* **Identifiant (`id`)** : entier ou chaîne unique identifiant l'appareil.
* **Nom (`name`)** : libellé usuel (ex. *Nautilus*, *Argos*).
* **Niveau de batterie (`battery`)** : pourcentage entier compris entre **0 et 100 %**, bornes incluses.
* **Profondeur maximale (`maxDepth`)** : entier positif exprimé en mètres.
* **Statut (`status`)** : état opérationnel parmi `AVAILABLE`, `ON_MISSION`, `MAINTENANCE`.
* **Capacités (`capabilities`)** : ensemble de compétences intrinsèques ou conférées par des équipements (`OBSERVATION`, `RECOVERY`, `REPAIR`).
* **Charge utile maximale (`maxPayloadKg`)** : capacité maximale d'emport en kilogrammes.

### 1.2 La Mission (`Mission`)
* **Identifiant (`id`)** : entier ou chaîne unique.
* **Nom (`name`)** : descriptif de l'opération (ex. *Photographier l'épave Aurora*).
* **Profondeur (`depth`)** : profondeur requise en mètres.
* **Coût énergétique (`energyCost`)** : consommation estimée en pourcentage de batterie (0-100 %).
* **Capacité requise (`requiredCapability`)** : type d'intervention exigé (`OBSERVATION`, `RECOVERY`, `REPAIR`).
* **Charge à remonter (`payloadKg`)** : masse de l'objet à récupérer en kilogrammes (0 kg si aucune charge).
* **Statut (`status`)** : état parmi `PENDING`, `ASSIGNED`, `COMPLETED`, `CANCELLED`.
* **Priorité (`priority`)** *(parcours avancé)* : niveau d'urgence (`LOW`, `NORMAL`, `HIGH`, `CRITICAL`).

---

## 2. Règles Métier Détaillées

### R1 — Disponibilité opérationnelle
* **R1.1 Drone** : Seul un drone au statut `AVAILABLE` peut se voir affecter une mission. Un drone `ON_MISSION` ou `MAINTENANCE` est immédiatement exclu.
* **R1.2 Mission** : Seule une mission au statut `PENDING` peut être affectée. Une mission `ASSIGNED`, `COMPLETED` ou `CANCELLED` ne peut pas recevoir de nouvelle affectation.

### R2 — Limite de profondeur
* La profondeur maximale supportée par le drone doit être supérieure ou égale à la profondeur de la mission :
  $$\text{maxDepth}_{\text{drone}} \ge \text{depth}_{\text{mission}}$$

### R3 — Énergie et réserve de sécurité
* Toute intervention consomme de l'énergie. Pour garantir le retour sécurisé du drone à la station de surface, une **réserve de sécurité obligatoire de 15 %** doit être conservée après l'opération.
* Condition d'affectation :
  $$\text{battery}_{\text{drone}} \ge \text{energyCost}_{\text{mission}} + 15$$
* Exemple : Pour une mission coûtant $30\,\%$, le drone doit posséder au minimum $30 + 15 = 45\,\%$ de batterie.

### R4 — Adéquation de capacité
* Le drone doit obligatoirement posséder la capacité requise par la mission (`requiredCapability`).
* Si la mission requiert `RECOVERY`, le drone doit avoir `RECOVERY` dans ses capacités actives.

### R5 — Limite de charge utile (Payload)
* Si la mission nécessite de remonter un équipement ou une balise ($\text{payloadKg} > 0$), la charge maximale admissible du drone doit être supérieure ou égale à la charge de la mission :
  $$\text{maxPayloadKg}_{\text{drone}} \ge \text{payloadKg}_{\text{mission}}$$

### R6 — Cycle de vie et exécution de mission
Lors de l'affectation validée :
1. Le statut du drone devient `ON_MISSION`.
2. Le statut de la mission devient `ASSIGNED`.

Lors de l'exécution de la mission :
1. La batterie du drone diminue du coût réel de la mission :
   $$\text{battery}_{\text{nouvelle}} = \text{battery}_{\text{actuelle}} - \text{energyCost}_{\text{mission}}$$
2. Le statut de la mission passe définitivement à `COMPLETED`.
3. Évaluation du retour :
   * Si la batterie restante est **inférieure à 15 %**, le drone passe immédiatement au statut `MAINTENANCE` (mise en sécurité requise).
   * Sinon, le drone redevient `AVAILABLE`.

### R7 — Maintenance et recharge
* Un drone au statut `MAINTENANCE` est indisponible pour toute affectation.
* Un drone peut être rechargé d'une quantité $\Delta \ge 0$ de batterie.
* **Invariant de batterie** : la batterie ne peut jamais dépasser $100\,\%$ ni être inférieure à $0\,\%$.
* **Remise en service** : un drone en maintenance ne peut revenir à `AVAILABLE` que si sa batterie a atteint au minimum le seuil de réserve opérationnel ($15\,\%$).

### R8 — Équipements modulaires *(Parcours avancé)*
* Un drone peut embarquer des modules d'équipements (`Equipment`) amovibles :
  * *Camera* $\rightarrow$ confère `OBSERVATION`
  * *Sonar* $\rightarrow$ améliore la détection / surcoût énergétique
  * *ManipulatorArm* $\rightarrow$ confère `REPAIR`
  * *RepairKit* $\rightarrow$ confère `REPAIR`
  * *CargoModule* $\rightarrow$ confère `RECOVERY` et augmente la capacité d'emport
* Les équipements peuvent ajouter une masse propre ou une consommation énergétique d'appoint.

---

## 3. Données de Référence

### Flotte initiale

| ID | Nom | Batterie | Profondeur max. | Capacités | Charge max. | Statut initial |
|---|---|---|---|---|---|---|
| 1 | **Nautilus** | 80 % | 500 m | OBSERVATION | 5 kg | AVAILABLE |
| 2 | **Argos** | 65 % | 1 200 m | OBSERVATION, RECOVERY | 25 kg | AVAILABLE |
| 3 | **Hephaistos** | 90 % | 800 m | REPAIR | 10 kg | AVAILABLE |
| 4 | **Titan** | 45 % | 2 000 m | RECOVERY | 50 kg | AVAILABLE |
| 5 | **Proteus** | 100 % | 1 000 m | OBSERVATION, REPAIR | 8 kg | AVAILABLE |

### Missions initiales

| ID | Nom | Profondeur | Coût Énergie | Capacité requise | Charge | Statut initial |
|---|---|---|---|---|---|---|
| 1 | Photographier l'épave Aurora | 350 m | 20 % | OBSERVATION | 0 kg | PENDING |
| 2 | Récupérer la balise B12 | 700 m | 30 % | RECOVERY | 18 kg | PENDING |
| 3 | Réparer le capteur Omega | 600 m | 35 % | REPAIR | 0 kg | PENDING |
| 4 | Explorer la fosse Hécate | 1 500 m | 35 % | OBSERVATION | 0 kg | PENDING |
| 5 | Remonter une boîte noire | 900 m | 25 % | RECOVERY | 40 kg | PENDING |
| 6 | Inspecter le pipeline Nord | 750 m | 20 % | OBSERVATION | 0 kg | PENDING |
