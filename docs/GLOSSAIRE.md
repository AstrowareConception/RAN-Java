# Glossaire Pédagogique

Lexique des termes clés utilisés dans le projet **Abyss Explorer**.

---

* **Classe** : Modèle abstrait (plan de construction) définissant la structure (attributs) et le comportement (méthodes) communs à un ensemble d'objets.
* **Objet / Instance** : Entité concrète allouée en mémoire créée à partir d'une classe via l'opérateur `new`.
* **Attribut / Propriété** : Variable d'état interne associée à chaque instance d'une classe.
* **Constructeur** : Méthode spéciale invoquée lors de l'instanciation pour initialiser l'état de l'objet et garantir ses invariants de départ.
* **Encapsulation** : Principe consistant à cacher les détails d'implémentation et à protéger l'état interne d'un objet en limitant sa visibilité (`private`) et en passant par des méthodes dédiées.
* **Méthode métier** : Opération portant une logique spécifique au domaine applicatif (ex. `recharge()`, `canAccept()`) plutôt qu'un simple accès direct à un attribut.
* **Énumération (`enum`)** : Type Java particulier restreint à un ensemble fini de constantes nommées (ex. `DroneStatus.AVAILABLE`).
* **Collection** : Structure de données dynamique permettant de regrouper et manipuler des objets (`List`, `Set`, `Map`).
* **Invariant** : Règle ou contrainte logique qui doit impérativement demeurer vraie à tout instant pour une instance valide.
* **Composition** : Relation structurelle où un objet contient et orchestre d'autres objets pour réaliser ses fonctionnalités (*« a un »*).
* **Héritage** : Mécanisme permettant à une classe d'étendre une classe mère en héritant de ses caractéristiques (*« est un »*).
* **Interface** : Spécification d'un ensemble de signatures de méthodes constituant un contrat sans imposer l'implémentation (*« sait faire »*).
* **Polymorphisme** : Capacité à traiter des objets de types différents de manière uniforme à travers une interface ou une classe commune.
* **Service Métier** : Composant applicatif responsable de l'orchestration des règles de gestion impliquant plusieurs entités.
* **Test Unitaire** : Procédure de vérification automatisée (ex. avec JUnit) validant le comportement isolé d'une unité de code face à des cas nominaux et d'erreurs.
