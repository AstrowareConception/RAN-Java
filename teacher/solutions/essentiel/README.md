# Solution Complète — Parcours Essentiel

Ce dossier contient la solution type pour le parcours **Essentiel**.

---

## 1. Philosophie de Conception

* **Simplicité et clarté** : Code lisible sans sur-architecture (aucune dépendance externe, pas de framework).
* **Encapsulation stricte** : Attributs `private`, validation des arguments aux constructeurs et méthodes mutatrices.
* **Méthodes métier explicites** :
  * `drone.canDiveTo(depth)` (R2)
  * `drone.hasEnoughBatteryFor(energyCost)` (R3)
  * `drone.hasCapability(cap)` (R4)
  * `drone.canCarry(weight)` (R5)
  * `drone.executeMission(cost)` (R6)
  * `drone.recharge(amount)` (R7)
* **Séparation minimale** :
  * `fr.abyss.model` : Données métier et invariants élémentaires.
  * `fr.abyss.service` : Gestion de flotte, validation multi-critères, recherche de missions compatibles.
  * `fr.abyss.Main` : Interface console utilisateur avec boucle d'interaction.

---

## 2. Compilation et Exécution

Depuis le répertoire `teacher/solutions/essentiel/` :

```bash
# Compilation
javac -d bin src/fr/abyss/model/*.java src/fr/abyss/service/*.java src/fr/abyss/Main.java

# Exécution
java -cp bin fr.abyss.Main
```
