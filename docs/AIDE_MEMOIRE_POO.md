# Aide-Mémoire Programmation Orientée Objet (POO)

Principes fondamentaux de conception objet illustrés par des exemples conceptuels.

---

## 1. Encapsulation

L'encapsulation consiste à masquer l'état interne d'un objet et à n'autoriser les modifications que par le biais de méthodes contrôlées qui préservent son intégrité.

* **Mauvaise pratique** : Rendre un champ public (`public int energy;`) ou fournir des setters aveugles sans validation (`setEnergy(int e) { this.energy = e; }`).
* **Bonne pratique** : Proposer des méthodes métier explicites qui valident les transitions (`consume(int amount)`).

---

## 2. Responsabilité Unique (Single Responsibility Principle)

Une classe doit avoir une seule raison d'exister et un périmètre de responsabilité cohérent.
* Une entité du domaine (ex. `Submarine`) gère son état propre et ses invariants.
* Un service métier (ex. `AssignmentService`) orchestre les interactions complexes entre plusieurs entités.
* La vue ou l'interface console (ex. `ConsoleUi`) s'occupe de l'affichage et de la saisie utilisateur. Elle ne contient aucune formule métier.

---

## 3. Invariants de Domaine

Un invariant est une condition logique qui doit **toujours** être vérifiée pendant toute l'existence d'une instance valide.

* **Exemple 1** : La batterie d'un appareil est toujours un entier compris entre $0$ et $100$.
* **Exemple 2** : Une mission `COMPLETED` ne peut jamais repasser au statut `PENDING`.
* **Garantie** : Les constructeurs et les méthodes mutatrices doivent refuser toute opération qui violerait un invariant (en levant une exception appropriée ou en retournant un résultat d'erreur).

---

## 4. Héritage vs Composition

### L'Héritage (*EST UN*)
* À utiliser lorsque la sous-classe est une spécialisation stricte et permanente de la classe mère (principe de substitution de Liskov).
* **Piège classique** : Créer `ObservationDrone`, `RepairDrone`, `CargoDrone` par héritage. Si un drone sait à la fois observer et réparer, l'héritage simple de Java ne permet pas d'exprimer cette combinaison sans explosion combinatoire (`ObservationAndRepairDrone`).

### La Composition (*POSSÈDE* / *CONTIENT*)
* Assembler des comportements ou des fonctionnalités par agrégation d'objets.
* Au lieu de faire hériter le drone de plusieurs classes, on lui attribue un ensemble de compétences (`Set<Capability>`) ou une liste de modules (`List<Equipment>`).
* **Règle d'or** : *« Favoriser la composition plutôt que l'héritage »* dès lors que les caractéristiques d'un objet peuvent varier dynamiquement ou se combiner.

---

## 5. Interfaces et Polymorphisme (*SAIT FAIRE*)

Une interface définit un **contrat de comportement**.

```java
public interface Propellable {
    void thrust(int power);
}
```

Le polymorphisme permet de manipuler différents types d'objets au travers d'une même interface sans connaître leur classe concrète à l'avance :

```java
public void launchAll(List<Propellable> vehicles) {
    for (Propellable vehicle : vehicles) {
        vehicle.thrust(100); // Exécute l'implémentation propre à chaque véhicule
    }
}
```

---

## 6. Modélisation d'Erreurs Métier : `boolean`, `Exception` ou `Result`

| Approche | Avantages | Inconvénients |
|---|---|---|
| `boolean canAssign(...)` | Très simple, adapté aux débutants | Ne donne aucune explication sur la cause exacte du refus |
| Exceptions (`BusinessException`) | Interrompt le flux, force la gestion | Coûteux si le rejet fait partie du fonctionnement nominal |
| Objet Résultat (`AssignmentResult`) | Exprime clairement le succès/échec avec motifs détaillés, hautement testable | Nécessite la création d'une classe/record dédiée |
