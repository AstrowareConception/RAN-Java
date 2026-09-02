# Guide de Débriefing Collectif (20 min de Clôture)

Ce document structure la discussion collective finale (dernière demi-heure de l'atelier) pour ancrer les apprentissages clés de la programmation orientée objet.

---

## 1. Trame d'Animation du Débrief (20 Minutes)

1. **Tour de table & ressentis (3 min)** : Que retenez-vous de cette journée ? Quels ont été vos principaux défis ?
2. **Comparaison de 3 conceptions étudiantes (10 min)** : Projeter et analyser 3 modélisations types (voir section 2 ci-dessous).
3. **Synthèse des 4 piliers POO (5 min)** : Encapsulation, Invariants, Composition vs Héritage, Testabilité.
4. **Conclusion & Perspectives (2 min)**.

---

## 2. Étude Comparative de 3 Modélisations Types

### Modélisation 1 : Le Modèle Procédural Anémique (*L'Anti-Pattern*)
* **Structure** : Des classes `Drone` et `Mission` contenant uniquement des champs publics ou des getters/setters aveugles. Toute la logique d'affectation et de validation est écrite dans une longue méthode `main` ou dans des blocs `if` éparpillés.
* **Problèmes constatés** :
  * Impossible de garantir que la batterie reste entre 0 et 100 %.
  * Code non réutilisable et impossible à tester unitairement.
  * Forte duplication de code dès qu'on ajoute une nouvelle vue.

### Modélisation 2 : L'Héritage Forcé (*L'Impasse Combinatoire*)
* **Structure** :
  ```text
                 Drone
                   |
       +-----------+-----------+
       |           |           |
  Observation    Cargo       Repair
     Drone       Drone       Drone
  ```
* **Problèmes constatés** :
  * Comment instancier **Argos** (Observation + Recovery) et **Proteus** (Observation + Repair) ?
  * L'héritage simple de Java force à créer `ObservationAndCargoDrone` ou à abandonner le polymorphisme.
  * **Leçon POO** : L'héritage modélise une nature fixe et exclusive (*EST UN*), pas un ensemble de compétences combinables.

### Modélisation 3 : La Composition Modulaire & Résultat Qualifié (*La Conception Recommandée*)
* **Structure** :
  * Le `Drone` agrège un `Set<Capability>` ou une `List<Equipment>` (*POSSÈDE* / *CONTIENT*).
  * Le `MissionAssignmentService` valide les règles sans modifier l'état et renvoie un `AssignmentResult` qualifié.
  * Les invariants sont protégés dans les entités du domaine.
* **Bénéfices constatés** :
  * Argos et Proteus sont modélisés naturellement sans sous-classe.
  * Ajout d'une nouvelle capacité sans modifier le code existant (Respect du principe Ouvert/Fermé).
  * 100 % testable unitairement avec JUnit.

---

## 3. Les 4 Messages Clés à Retenir

1. **L'encapsulation n'est pas une contrainte esthétique** : C'est la garantie fondamentale que l'état d'un objet ne sera jamais corrompu de l'extérieur.
2. **Privilégier la composition à l'héritage** : Dès lors qu'un comportement ou une caractéristique peut varier, s'ajouter ou se combiner, la composition offre une souplesse infiniment supérieure.
3. **Un objet résultat vaut mieux qu'une cascade de booléens** : En ingénierie logicielle, qualifier précisément la cause d'un refus fait partie intégrante de la valeur métier.
4. **La testabilité est le miroir de votre conception** : Si une méthode est difficile à tester, c'est presque toujours le signe d'un mauvais découpage des responsabilités.
