# Grille d'Évaluation Pédagogique (Sur 20 Points)

Ce document formalise les critères d'évaluation adaptés aux deux parcours. L'accent est mis sur la **qualité de conception** et la **compréhension objet** plutôt que sur le volume brut de code produit.

---

## 1. Grille d'Évaluation — Parcours Essentiel

> **Principe** : Ne pas pénaliser l'absence de tests unitaires, d'interfaces, d'héritage, de Streams ou de JavaFX.

| Critère | Barème | Indicateurs d'Excellence |
|---|---|---|
| **Modélisation & Encapsulation** | **/ 5 pts** | - Attributs strictement `private`.<br>- Constructeurs cohérents validant les paramètres.<br>- Getters pertinents, absence de setters aveugles dégradant l'intégrité.<br>- Utilisation judicieuse des `enum` (`DroneStatus`, `MissionStatus`, `Capability`). |
| **Collections & Données** | **/ 3 pts** | - Flotte et missions stockées dans des `List<Drone>` et `List<Mission>`.<br>- Manipulation propre des listes (parcours `for-each`, recherche par ID sans crash index). |
| **Règles Métier (R1, R2, R3)** | **/ 5 pts** | - R1 (Disponibilité) vérifiée côté drone et mission.<br>- R2 (Profondeur maximale $\ge$ mission) correctement formulée.<br>- R3 (Batterie $\ge$ coût + 15 % réserve) exactement respectée.<br>- Messages de refus clairs et explicites en console. |
| **Affectation & Menu Console** | **/ 4 pts** | - Menu interactif clair, boucle `while` sans boucle infinie.<br>- Gestion robuste des saisies `Scanner` (pas de crash sur mauvaise entrée).<br>- Affectation effective modifiant les statuts (`ON_MISSION`, `ASSIGNED`). |
| **Qualité du Code & Autonomie** | **/ 3 pts** | - Nommage clair en anglais ou français homogène.<br>- Code aéré et indenté.<br>- Capacité à expliquer son code lors des questions orales. |

**Bonus Essentiel (jusqu'à +3 pts)** :
* $+1$ pt : Règle R4 (Capacités) & R5 (Charge max).
* $+1$ pt : Exécution de mission R6 avec baisse de batterie et statut.
* $+1$ pt : Maintenance et recharge R7 ou missions compatibles (E5).

---

## 2. Grille d'Évaluation — Parcours Conception Objet

> **Principe** : La quantité de code ne remplace pas la rigueur architecturale et la protection des invariants.

| Critère | Barème | Indicateurs d'Excellence |
|---|---|---|
| **Architecture & Responsabilités** | **/ 4 pts** | - Séparation nette : domaine (`domain`), services (`service`), interface (`ui`).<br>- La vue console ne contient aucun calcul métier.<br>- Services sans état (*stateless*) facilitant la testabilité. |
| **Invariants & Encapsulation Forte** | **/ 3 pts** | - Protection absolue de la batterie ($[0, 100]$) à la construction et à la mutation.<br>- Protection contre les modifications externes (`unmodifiableList`, `unmodifiableSet`).<br>- Transitions de statut atomiques et vérifiées. |
| **Choix Objet (Composition / Interfaces / Records)** | **/ 4 pts** | - Arbitrage justifié entre composition (`Set<Capability>`, `Equipment`) et héritage.<br>- Utilisation de `record` pour les objets immuables (`AssignmentResult`, équipements).<br>- Utilisation d'`Optional<T>` pour la sélection de drone. |
| **Gestion des Retours Métier & Règles R1-R7** | **/ 4 pts** | - `AssignmentResult` qualifié avec `AssignmentFailureReason` détaillés.<br>- Implémentation sans faille des règles R1 à R7.<br>- Recherche des drones compatibles et sélection optimale (`findBestDroneFor`). |
| **Tests Automatisés (JUnit 5)** | **/ 3 pts** | - Tests unitaires couvrant les cas nominaux et les cas limites clés.<br>- Utilisation propre des assertions et structures JUnit 5 (`@Test`, `@BeforeEach`, `@DisplayName`). |
| **Soutenance Orale & Justification** | **/ 2 pts** | - Capacité à argumenter ses choix de conception (pourquoi composition, pourquoi pas d'exceptions).<br>- Lucidité sur le couplage et l'extensibilité du modèle. |

**Bonus Conception Objet (jusqu'à +3 pts)** :
* $+1$ pt : Modules d'équipements complets avec calcul de surcoût énergétique (R8).
* $+1.5$ pt : Service de planification multi-missions heuristique (`MissionPlannerService`).
* $+0.5$ pt : Interface graphique JavaFX fonctionnelle et découplée.
