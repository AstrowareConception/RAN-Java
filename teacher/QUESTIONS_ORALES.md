# Référentiel de Questions Orales & Évaluation Individuelle

Ce référentiel fournit 30 questions ciblées (15 Essentiel + 15 Conception Objet) pour évaluer la compréhension réelle d'un étudiant lors des passages individuels.

---

# PARTIE 1 — 15 Questions Parcours Essentiel

### Q1. Quelle est la différence entre une classe et un objet ?
* **Réponse attendue** : La classe est le modèle abstrait / plan de construction ; l'objet est l'instance concrète allouée en mémoire via `new`.
* **Erreur fréquente** : Confondre la classe avec le fichier source ou penser qu'une classe contient des données partagées uniques.
* **Relance** : *« Dans votre code, où est la classe et où sont les objets ? »*

### Q2. Pourquoi déclarer les attributs en `private` ?
* **Réponse attendue** : Pour garantir l'encapsulation, protéger l'état interne contre les modifications directes et obliger à passer par des méthodes contrôlées.
* **Erreur fréquente** : Répondre que c'est « obligatoire en Java » sans pouvoir expliquer le risque d'un champ public.
* **Relance** : *« Que se passerait-il si un développeur écrivait `drone.battery = -50;` ? »*

### Q3. À quoi sert un constructeur ?
* **Réponse attendue** : À initialiser l'état de l'objet à la création et garantir que l'instance démarre dans un état cohérent.
* **Erreur fréquente** : Penser que le constructeur alloue la mémoire (c'est le rôle de `new`).
* **Relance** : *« Que se passe-t-il si vous ne définissez aucun constructeur dans une classe ? »*

### Q4. Pourquoi utiliser une `enum` pour le statut plutôt qu'une chaîne (`String`) ?
* **Réponse attendue** : Sécurité au typage à la compilation, ensemble fini de valeurs autorisées, évite les fautes de frappe.
* **Erreur fréquente** : Ne voir l'enum que comme une liste de constantes sans comprendre le gain de sécurité.
* **Relance** : *« Que se passe-t-il si j'écris `"Dispo"` au lieu de `"AVAILABLE"` avec des String ? »*

### Q5. Quelle est la différence entre `==` et `.equals()` ?
* **Réponse attendue** : `==` compare l'égalité des références mémoire (sauf types primitifs et enums), `.equals()` compare le contenu logique des objets.
* **Erreur fréquente** : Utiliser `==` pour comparer deux `String`.
* **Relance** : *« Pourquoi `==` fonctionne-t-il sur les enum mais pas sur les String ? »*

### Q6. Pourquoi utiliser `List<Drone>` plutôt qu'un tableau fixe `Drone[]` ?
* **Réponse attendue** : Une `List` a une taille dynamique, offre des méthodes pratiques (`add`, `remove`, `contains`) et s'intègre avec les itérateurs.
* **Erreur fréquente** : Ignorer que les tableaux ont une taille figée à l'allocation.
* **Relance** : *« Comment ajouteriez-vous un 6e drone dans un tableau de taille 5 ? »*

### Q7. Pourquoi ne doit-on pas tout coder dans la méthode `main` ?
* **Réponse attendue** : Pour respecter la séparation des responsabilités, rendre le code lisible, réutilisable, maintenable et testable.
* **Erreur fréquente** : Penser que `main` est l'endroit naturel où s'exécute toute la logique.
* **Relance** : *« Si vous deviez créer une interface graphique demain, pourriez-vous réutiliser votre logique si elle est dans `main` ? »*

### Q8. À quoi sert le mot-clé `this` dans une classe ?
* **Réponse attendue** : Il fait référence à l'instance courante sur laquelle la méthode ou le constructeur s'exécute, et permet de lever l'ambiguïté avec les paramètres.
* **Erreur fréquente** : Penser que `this` crée un nouvel objet.
* **Relance** : *« Que se passe-t-il dans `battery = battery;` sans `this` ? »*

### Q9. Que fait la boucle `for (Drone d : drones)` ?
* **Réponse attendue** : C'est une boucle `for-each` qui parcourt séquentiellement chaque élément de la collection `drones`.
* **Erreur fréquente** : Confondre l'élément courant `d` avec un index entier.
* **Relance** : *« Avez-vous besoin d'un compteur `i` pour afficher tous les drones ? »*

### Q10. Quelle classe doit décider si la batterie d'un drone est suffisante ?
* **Réponse attendue** : Le `Drone` (ou un service d'affectation), car il possède la donnée `battery`.
* **Erreur fréquente** : Faire le calcul directement dans l'affichage console.
* **Relance** : *« Pourquoi l'interface console ne devrait-elle pas contenir la formule `battery >= cost + 15` ? »*

### Q11. Pourquoi la réserve de 15% est-elle ajoutée au coût de la mission ?
* **Réponse attendue** : Pour garantir que le drone conserve au moins 15% après avoir dépensé l'énergie de la mission ($\text{batterie} - \text{coût} \ge 15 \iff \text{batterie} \ge \text{coût} + 15$).
* **Erreur fréquente** : Vérifier seulement $\text{batterie} \ge 15$.
* **Relance** : *« Si un drone a 20% et que la mission coûte 10%, que reste-t-il après ? Est-ce suffisant ? »*

### Q12. À quoi sert le mot-clé `final` sur un attribut ?
* **Réponse attendue** : À rendre l'attribut non modifiable après son initialisation (immuabilité de référence).
* **Erreur fréquente** : Confondre avec `static` ou `private`.
* **Relance** : *« L'identifiant ou le nom d'un drone doivent-ils changer au cours du temps ? »*

### Q13. Pourquoi votre `Scanner` nécessite-t-il parfois un `nextLine()` supplémentaire ?
* **Réponse attendue** : Parce que `nextInt()` lit uniquement le chiffre et laisse le retour chariot `\n` dans le tampon d'entrée.
* **Erreur fréquente** : Penser que le programme plante aléatoirement.
* **Relance** : *« Qu'arrive-t-il si vous demandez un texte immédiatement après un entier ? »*

### Q14. Qu'est-ce qu'une `NullPointerException` et comment l'éviter ?
* **Réponse attendue** : Une exception levée lorsqu'on tente d'accéder à un membre sur une référence qui vaut `null`. On l'évite en initialisant ses variables et en validant les entrées.
* **Erreur fréquente** : Penser qu'il faut entourer chaque ligne d'un `try-catch`.
* **Relance** : *« Si votre liste `capabilities` n'est pas instanciée, que produit `capabilities.contains(...)` ? »*

### Q15. Comment avez-vous testé vos règles métier ?
* **Réponse attendue** : En exécutant les scénarios de référence depuis la console et en vérifiant les rejets attendus.
* **Erreur fréquente** : Ne tester que les cas où tout fonctionne (cas nominaux).
* **Relance** : *« Avez-vous vérifié ce qui se passe si Nautilus tente de plonger à 700 m ? »*

---

# PARTIE 2 — 15 Questions Parcours Conception Objet

### Q16. Pourquoi avoir préféré la composition à l'héritage pour les capacités des drones ?
* **Réponse attendue** : L'héritage simple de Java ne permet pas à un drone d'être à la fois réparateur et transporteur sans explosion combinatoire. La composition permet de cumuler et modifier dynamiquement des capacités.
* **Erreur fréquente** : Penser que l'héritage est toujours le concept supérieur en POO.
* **Relance** : *« Comment modéliseriez-vous Argos s'il hérite de `ObservationDrone` ? »*

### Q17. Quelle est la différence conceptuelle entre une interface et une classe abstraite ?
* **Réponse attendue** : Une interface définit un contrat de comportement (*sait faire*), une classe abstraite fournit une base commune d'état et d'implémentation partielle (*est un*).
* **Erreur fréquente** : Répondre uniquement sur les différences de syntaxe sans vision architecturale.
* **Relance** : *« Pourquoi `Equipment` est-il une interface plutôt qu'une classe concrète ? »*

### Q18. Qu'est-ce qu'un invariant de domaine et comment le protégez-vous ?
* **Réponse attendue** : Une règle qui doit rester vraie pendant toute la vie de l'objet (ex. batterie $\in [0, 100]$). On la protège en validant dès le constructeur et dans chaque méthode mutatrice.
* **Erreur fréquente** : Dépendre de la vue ou de l'appelant pour valider les données.
* **Relance** : *« Que se passe-t-il si j'appelle `drone.recharge(-50)` ou `drone.executeMission(200)` ? »*

### Q19. Pourquoi retourner un objet `AssignmentResult` plutôt qu'un simple `boolean` ?
* **Réponse attendue** : Pour enrichir le retour métier avec l'ensemble des motifs de refus précis, faciliter le débogage, l'affichage et les assertions de tests.
* **Erreur fréquente** : Renvoyer `false` ou lever une exception générique.
* **Relance** : *« Comment l'UI sait-elle si le rejet est dû à la profondeur OU à la batterie avec un booléen ? »*

### Q20. Pourquoi ne pas avoir utilisé d'exceptions pour signaler les refus d'affectation ?
* **Réponse attendue** : Le refus d'affectation fait partie du flux métier nominal. Les exceptions ont un coût de performance et arrêtent le traitement dès la première anomalie sans lister les autres.
* **Erreur fréquente** : Utiliser des exceptions pour tout contrôle conditionnel.
* **Relance** : *« Quand une exception métier devient-elle réellement pertinente ? »*

### Q21. Quel est l'intérêt de `Optional<Drone>` dans `findBestDroneFor(...)` ?
* **Réponse attendue** : Expliciter dans la signature de méthode que le résultat peut être absent, évitant les retours `null` silencieux et les NPE.
* **Erreur fréquente** : Retourner `null` et compter sur l'appelant pour ne pas l'oublier.
* **Relance** : *« Comment traitez-vous proprement le cas où aucun drone n'est compatible avec la mission ? »*

### Q22. Comment garantissez-vous que `getEquipments()` ou `getCapabilities()` ne permet pas de corrompre l'état du drone ?
* **Réponse attendue** : En retournant une vue non modifiable (`Collections.unmodifiableList` / `unmodifiableSet`) ou une copie défensive.
* **Erreur fréquente** : Retourner directement la référence de la liste interne mutable.
* **Relance** : *« Que se passe-t-il si j'écris `drone.getCapabilities().clear()` depuis le `Main` ? »*

### Q23. Comment testeriez-vous qu'une affectation refusée ne modifie aucun état ?
* **Réponse attendue** : En vérifiant dans le test que le statut du drone reste `AVAILABLE`, que la mission reste `PENDING` et que la batterie n'a pas bougé.
* **Erreur fréquente** : Se contenter de vérifier `result.isAllowed() == false`.
* **Relance** : *« Comment s'assurer qu'il n'y a pas eu d'effet de bord caché ? »*

### Q24. Quand l'utilisation de l'API Stream apporte-t-elle une réelle valeur ajoutée ?
* **Réponse attendue** : Pour les opérations de filtrage, transformation, tri et agrégation déclaratives sur des collections (`filter`, `sorted`, `map`, `toList`).
* **Erreur fréquente** : Utiliser des Streams complexes et illisibles pour de simples boucles à effets de bord (`forEach` mutateur).
* **Relance** : *« Pourquoi `fleet.stream().filter(...).max(...)` est-il plus lisible qu'une boucle avec 3 variables temporaires ? »*

### Q25. Quelle stratégie avez-vous choisie pour `findBestDroneFor` et pourquoi ?
* **Réponse attendue** : Justification claire (ex. maximiser la batterie restante pour la sécurité, OU minimiser le surdimensionnement en profondeur pour garder les drones d'élite).
* **Erreur fréquente** : Avoir codé un tri sans pouvoir expliquer la logique retenue.
* **Relance** : *« Pour une mission à 300 m, vaut-il mieux envoyer Nautilus (500 m) ou Titan (2 000 m) ? Pourquoi ? »*

### Q26. Quel principe SOLID votre architecture respecte-t-elle particulièrement bien ?
* **Réponse attendue** : Single Responsibility (séparation domain/service/ui), Open/Closed (nouveaux équipements sans toucher Drone), etc.
* **Erreur fréquente** : Réciter l'acronyme sans faire le lien avec son propre code.
* **Relance** : *« Où s'applique le principe Ouvert/Fermé dans votre gestion des capacités ? »*

### Q27. Comment fonctionne votre heuristique de planification multi-missions ?
* **Réponse attendue** : Tri des missions par priorité (`CRITICAL` > `HIGH`...) puis par profondeur, allocation itérative du meilleur drone libre, marquage des drones occupés.
* **Erreur fréquente** : Tenter un algorithme de force brute combinatoire ou ignorer les conflits de drones.
* **Relance** : *« Comment garantissez-vous qu'un même drone n'est pas assigné à deux missions simultanées ? »*

### Q28. Quelle partie de votre architecture est la plus fortement couplée et comment la découpler ?
* **Réponse attendue** : Analyse lucide du couplage entre `FleetService` et `MissionAssignmentService`, ou entre le modèle et l'enum `Capability`.
* **Erreur fréquente** : Prétendre que son code n'a aucun couplage.
* **Relance** : *« Si nous devions passer d'une console textuelle à une API REST, quelles classes changeraient ? »*

### Q29. Pourquoi utiliser un `record` pour `AssignmentResult` et les équipements simples ?
* **Réponse attendue** : Concision syntaxique, immuabilité native, constructeur canonique, `equals`, `hashCode` et `toString` générés automatiquement.
* **Erreur fréquente** : Confondre `record` et `class` mutable.
* **Relance** : *« Pourquoi un record est-il parfait pour un objet de transport de données ou de résultat ? »*

### Q30. Comment avez-vous structuré vos tests unitaires JUnit 5 ?
* **Réponse attendue** : Découpage par cas d'usage, nommage explicite (`@DisplayName`), initialisation propre via `@BeforeEach`, assertions ciblées (`assertTrue`, `assertEquals`, `assertThrows`).
* **Erreur fréquente** : Avoir un unique test de 100 lignes testant tout le projet d'un coup.
* **Relance** : *« Pourquoi tester les cas limites (0%, 100%, seuil exact de 15%) est-il indispensable ? »*
