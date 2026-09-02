# Scénarios de Référence et Validation

Ce document présente l'ensemble des scénarios de test fonctionnels servant à valider le comportement du système **Abyss Explorer**.

---

## 1. Matrice des Scénarios de Validation

| Réf. | Drone testé | Mission ciblée | Résultat attendu | Règles appliquées | Justification détaillée |
|---|---|---|---|---|---|
| **A** | **Nautilus** (80 %, 500 m, Obs) | *Photographier Aurora* (350 m, 20 %, Obs) | **ACCEPTÉ** | R1, R2, R3, R4 | Profondeur $350 \le 500$, énergie $80 \ge 20+15=35$, capacité OK. |
| **B** | **Nautilus** (80 %, 500 m, Obs) | *Récupérer balise B12* (700 m, 30 %, Rec, 18kg) | **REFUSÉ** | R2 (et R4) | Profondeur insuffisante ($500 < 700\text{ m}$). |
| **C** | **Hephaistos** (90 %, 800 m, Rep) | *Réparer capteur Omega* (600 m, 35 %, Rep) | **ACCEPTÉ** | R1, R2, R3, R4 | Profondeur $600 \le 800$, énergie $90 \ge 35+15=50$, capacité OK. |
| **D** | **Titan** (45 %, 2000 m, Rec) | *Explorer fosse Hécate* (1500 m, 35 %, Obs) | **REFUSÉ** | R4 (et R3) | Manque capacité `OBSERVATION` + énergie insuffisante ($45 < 35+15=50$). |
| **E** | **Argos** (65 %, 1200 m, Obs+Rec, 25kg) | *Récupérer balise B12* (700 m, 30 %, Rec, 18kg) | **ACCEPTÉ** | R1, R2, R3, R4, R5 | Profondeur OK, énergie $65 \ge 45$, capacité OK, charge $18 \le 25\text{ kg}$. |
| **F** | **Argos** (65 %, 1200 m, Obs+Rec, 25kg) | *Remonter boîte noire* (900 m, 25 %, Rec, 40kg) | **REFUSÉ** | R5 | Charge maximale dépassée ($25 < 40\text{ kg}$). |
| **G** | *Drone fictif* (40 % bat) | *Mission coût 30 %* | **REFUSÉ** | R3 | Seuil de sécurité non atteint : $40 < 30 + 15 = 45\,\%$. |
| **H** | **Nautilus** affecté à *Aurora* | *Exécution opérationnelle* | **SUCCÈS** | R6 | Batterie passe de 80 % à 60 %. Statut mission = `COMPLETED`. Drone = `AVAILABLE` ($60 \ge 15$). |
| **I** | *Drone fictif* (30 %) affecté à une mission de 20 % | *Exécution opérationnelle* | **SUCCÈS + MAINTENANCE** | R6 | Batterie passe de 30 % à 10 %. La mission est `COMPLETED` et le drone passe en `MAINTENANCE` car $10 < 15$. |
| **J** | Drone au statut `ON_MISSION` | Tentative d'affectation | **REFUSÉ** | R1.1 | Un drone déjà en mission ne peut pas recevoir une seconde tâche. |
| **K** | Mission au statut `COMPLETED` | Tentative de réaffectation | **REFUSÉ** | R1.2 | Une mission terminée ne peut pas être réaffectée. |
| **L** | Drone en `MAINTENANCE` | Recharge de +50 % puis remise en service | **SUCCÈS** | R7 | Batterie rechargée (plafonnée à 100 %), passage à `AVAILABLE`. |

---

## 2. Déroulement Pas à Pas d'un Scénario d'Intégration (Scénario H)

1. **État Initial** :
   * `Nautilus` : batterie = 80 %, statut = `AVAILABLE`.
   * `Mission 1 (Aurora)` : coût = 20 %, statut = `PENDING`.
2. **Action 1 : Affectation** :
   * Vérification des règles : OK.
   * `Nautilus.status` $\rightarrow$ `ON_MISSION`.
   * `Mission 1.status` $\rightarrow$ `ASSIGNED`.
3. **Action 2 : Exécution** :
   * Calcul batterie : $80 - 20 = 60\,\%$.
   * `Mission 1.status` $\rightarrow$ `COMPLETED`.
   * Comme $60 \ge 15$, `Nautilus.status` $\rightarrow$ `AVAILABLE`.
4. **Action 3 : Tentative de ré-exécution immédiate** :
   * Rejet immédiat : la mission est déjà `COMPLETED`.
