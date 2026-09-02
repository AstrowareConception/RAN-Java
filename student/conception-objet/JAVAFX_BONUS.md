# Extension Bonus Graphique : JavaFX

> **Avertissement préalable** : Ne commencez cette extension que si votre modèle métier fonctionne intégralement, que vos tests JUnit passent et si vous connaissez déjà JavaFX. L'évaluation porte en priorité sur la conception objet du domaine, jamais sur les éléments graphiques.

---

## 1. Objectif

Construire une interface graphique minimaliste pour piloter la flotte sans mélanger logique métier et composants visuels.

---

## 2. Règle d'Architecture Fondamentale

* **Zéro règle métier dans les contrôleurs UI** : Le contrôleur graphique doit uniquement déléguer les actions à vos services métier (`FleetService`, `MissionAssignmentService`).
* Le contrôleur écoute les événements de boutons et rafraîchit les listes d'affichage (`ObservableList`).

---

## 3. Écrans Suggérés

* **Vue Flotte & Missions** :
  * Un `TableView<Drone>` affichant les drones et leur batterie / statut.
  * Un `TableView<Mission>` affichant les missions en attente.
  * Un panneau de détail avec boutons :
    * `[ Tester Affectation ]`
    * `[ Affecter ]`
    * `[ Exécuter Mission ]`
    * `[ Recharger (+30%) ]`
* **Zone de Messages / Logs** : Un `TextArea` affichant les rapports d'affectation (`AssignmentResult`) et les raisons détaillées en cas de refus.

---

## 4. Dépendances Maven JavaFX (si utilisé)

Pour ajouter JavaFX à votre `pom.xml` :

```xml
<dependencies>
    <!-- JavaFX Controls -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.2</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-maven-plugin</artifactId>
            <version>0.0.8</version>
            <configuration>
                <mainClass>fr.abyss.ui.AbyssFxApp</mainClass>
            </configuration>
        </plugin>
    </plugins>
</build>
```
