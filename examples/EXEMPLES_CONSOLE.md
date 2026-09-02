# Exemples de Sessions Console

Ce document illustre les **résultats métier** attendus lors de différentes interactions. La mise en forme exacte de la console peut varier selon le parcours et votre implémentation ; elle n'est pas un critère de conformité.

---

## 1. Menu principal et affichage de la flotte

```text
======================================================
   ABYSS EXPLORER — CONSOLE SUPERVISION
======================================================
1. Afficher les drones
2. Afficher les missions
3. Tester une affectation
4. Affecter une mission
5. Exécuter une mission
6. Recharger un drone
7. Recommander le meilleur drone / lister les compatibles
8. Planifier les missions (extension avancée)
0. Quitter
------------------------------------------------------
Votre choix : 1

--- ÉTAT DE LA FLOTTE ---
[1] Nautilus     | Bat:  80% | Prof. max:  500m | Charge:  5.0kg | Statut: AVAILABLE   | Caps: [OBSERVATION]
[2] Argos        | Bat:  65% | Prof. max: 1200m | Charge: 25.0kg | Statut: AVAILABLE   | Caps: [OBSERVATION, RECOVERY]
```

---

## 2. Test d'Affectation Valide (Scénario A)

```text
Votre choix : 3

--- TEST D'AFFECTATION ---
Sélectionnez le numéro du drone (1-5) : 1
Sélectionnez le numéro de la mission (1-6) : 1

=> Analyse d'affectation :
Drone   : Nautilus (80 % bat, 500 m max, OBSERVATION)
Mission : Photographier l'épave Aurora (350 m, 20 % énergie, OBSERVATION)

[SUCCÈS] Affectation POSSIBLE :
* Profondeur requise (350 m) <= Profondeur max (500 m)
* Énergie disponible (80 %) >= Coût (20 %) + Réserve de sécurité (15 %) = 35 %
* Capacité requise présente : OBSERVATION
```

---

## 3. Test d'Affectation Refusée — Profondeur Insuffisante (Scénario B)

```text
Votre choix : 3

--- TEST D'AFFECTATION ---
Sélectionnez le numéro du drone (1-5) : 1
Sélectionnez le numéro de la mission (1-6) : 2

=> Analyse d'affectation :
Drone   : Nautilus (500 m max, 80 % bat)
Mission : Récupérer la balise B12 (700 m, 30 % énergie)

[REFUS] Affectation IMPOSSIBLE. Raison(s) :
- Profondeur excessive : la mission requiert 700 m, or Nautilus est limité à 500 m.
- Capacité manquante : la mission requiert RECOVERY.
```

---

## 4. Test d'Affectation Refusée — Charge Trop Élevée (Scénario F)

```text
Votre choix : 3

--- TEST D'AFFECTATION ---
Sélectionnez le numéro du drone (1-5) : 2
Sélectionnez le numéro de la mission (1-6) : 5

=> Analyse d'affectation :
Drone   : Argos (Charge max : 25 kg)
Mission : Remonter une boîte noire (Charge : 40 kg)

[REFUS] Affectation IMPOSSIBLE. Raison(s) :
- Charge trop lourde : la mission requiert 40.0 kg, or Argos est limité à 25.0 kg.
```

---

## 5. Exécution d'une Mission et Diminution de Batterie (Scénario H)

```text
Votre choix : 4
Sélectionnez le drone : 1
Sélectionnez la mission : 1
=> Mission affectée avec succès.
Nautilus passe au statut : ON_MISSION
Mission "Photographier l'épave Aurora" passe au statut : ASSIGNED

Votre choix : 5
Sélectionnez le drone à faire rentrer de mission : 1
=> Exécution terminée.
Batterie de Nautilus : 80 % -> 60 % (-20 %)
Statut de la mission : COMPLETED
Statut du drone : AVAILABLE (Batterie restante 60 % >= seuil de sécurité 15 %)
```

---

## 6. Recharge et Maintenance (Scénario L)

```text
Votre choix : 6
Sélectionnez le drone à recharger : 4
Quantité d'énergie à injecter (%) : 40

=> Opération de recharge effectuée :
Titan : Batterie 45 % -> 85 % (+40 %)
Statut : AVAILABLE
```
