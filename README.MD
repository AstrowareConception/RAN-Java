# ABYSS EXPLORER — KIT PÉDAGOGIQUE JAVA POO (7 HEURES)

Gestion d'une flotte de drones sous-marins autonomes pour l'exploration océanique.
Atelier de remise à niveau et de positionnement en programmation orientée objet Java pour étudiants en admission parallèle (B3 / M1).

---

## 🎯 Vous êtes étudiant ?

1. **Vérifiez votre environnement technique** :
   * Exécutez le script de diagnostic : `tools/check-environment.bat` *(Windows)* ou `tools/check-environment.sh` *(Linux / macOS)*.
   * Version recommandée : **Java 21 LTS** ou supérieure.
2. **Choisissez votre parcours en 3 minutes** :
   * Ouvrez et répondez au questionnaire dans [`student/CHOISIR_MON_PARCOURS.md`](student/CHOISIR_MON_PARCOURS.md).
3. **Démarrez votre projet** :
   * 🟢 **Parcours Essentiel** : Rendez-vous dans [`student/essentiel/README.md`](student/essentiel/README.md) et travaillez dans le dossier `student/essentiel/starter/`.
   * 🔵 **Parcours Conception Objet** : Rendez-vous dans [`student/conception-objet/README.md`](student/conception-objet/README.md) et travaillez dans le dossier `student/conception-objet/starter/`.
4. **En cas de difficulté** :
   * Consultez le guide de dépannage [`student/JE_SUIS_BLOQUE.md`](student/JE_SUIS_BLOQUE.md) et les indices progressifs `HINTS.md` de votre parcours.

---

## 👨‍🏫 Vous êtes enseignant ?

* **Guide complet d'animation** : Consultez [`teacher/GUIDE_ENSEIGNANT.md`](teacher/GUIDE_ENSEIGNANT.md) (déroulé minuté des 7 heures, gestion du rythme, accompagnement différencié).
* **Évaluation & Questions orales** :
  * [`teacher/QUESTIONS_ORALES.md`](teacher/QUESTIONS_ORALES.md) : Référentiel de 30 questions avec réponses attendues et relances.
  * [`teacher/GRILLE_EVALUATION.md`](teacher/GRILLE_EVALUATION.md) : Grilles d'évaluation sur 20 points adaptées aux deux parcours.
* **Corrigés types complets et exécutables** :
  * `teacher/solutions/essentiel/` : Solution socle et extensions sans framework.
  * `teacher/solutions/conception-objet/` : Solution modulaire avec JUnit 5, équipements et planification (`teacher/solutions/conception-objet/DESIGN_NOTES.md`).
* **Débriefing collectif** : [`teacher/DEBRIEF.md`](teacher/DEBRIEF.md).
* **Génération des archives étudiantes** : Exécutez `tools/package-kits.ps1` (ou `.sh`) pour créer les fichiers ZIP à distribuer sans inclure `teacher/`.

---

## 📚 Documentation Commune de Référence

* 📋 [Règles Métier (`docs/REGLES_METIER.md`)](docs/REGLES_METIER.md) : Spécification des règles R1 à R8.
* 🧪 [Scénarios de Référence (`docs/SCENARIOS_REFERENCE.md`)](docs/SCENARIOS_REFERENCE.md) : Matrice des cas de test (Scénarios A à L).
* ☕ [Aide-Mémoire Java (`docs/AIDE_MEMOIRE_JAVA.md`)](docs/AIDE_MEMOIRE_JAVA.md) : Syntaxe, boucles, collections, enums, records.
* 🧱 [Aide-Mémoire POO (`docs/AIDE_MEMOIRE_POO.md`)](docs/AIDE_MEMOIRE_POO.md) : Encapsulation, invariants, composition vs héritage.
* 📖 [Glossaire (`docs/GLOSSAIRE.md`)](docs/GLOSSAIRE.md) : Vocabulaire technique essentiel.
* 🌐 [Ressources Externes (`docs/RESSOURCES.md`)](docs/RESSOURCES.md) : Liens officiels Java et documentation.
* 🖥️ [Exemples Console (`examples/EXEMPLES_CONSOLE.md`)](examples/EXEMPLES_CONSOLE.md) : Traces d'exécution attendues.

---

## 🗂️ Structure du Dépôt

```text
abyss-explorer-java/
├── README.md                          # Accueil et orientation
│
├── docs/                              # Référentiel métier et aides-mémoire
│   ├── REGLES_METIER.md
│   ├── SCENARIOS_REFERENCE.md
│   ├── AIDE_MEMOIRE_JAVA.md
│   ├── AIDE_MEMOIRE_POO.md
│   ├── RESSOURCES.md
│   └── GLOSSAIRE.md
│
├── student/                           # Espace de travail étudiant
│   ├── CHOISIR_MON_PARCOURS.md        # Questionnaire d'auto-positionnement
│   ├── JE_SUIS_BLOQUE.md              # Guide méthodologique de résolution de bugs
│   │
│   ├── essentiel/                     # Parcours Débutant / Consolidation
│   │   ├── README.md
│   │   ├── HINTS.md
│   │   ├── POUR_ALLER_PLUS_LOIN.md
│   │   └── starter/                   # Projet Java prêt à exécuter
│   │
│   └── conception-objet/              # Parcours Avancé / Conception
│       ├── README.md
│       ├── HINTS.md
│       ├── POUR_ALLER_PLUS_LOIN.md
│       ├── JAVAFX_BONUS.md
│       └── starter/                   # Projet Maven + JUnit 5
│
├── teacher/                           # Espace enseignant (privé)
│   ├── GUIDE_ENSEIGNANT.md            # Déroulé des 7h et conseils d'animation
│   ├── QUESTIONS_ORALES.md            # 30 questions d'évaluation individuelle
│   ├── GRILLE_EVALUATION.md           # Grilles de notation sur 20 points
│   ├── DEBRIEF.md                     # Trame du débriefing collectif de fin de journée
│   │
│   └── solutions/                     # Solutions complètes vérifiées
│       ├── essentiel/
│       └── conception-objet/
│
├── examples/                          # Exemples de sessions textuelles
│   └── EXEMPLES_CONSOLE.md
│
└── tools/                             # Outils de diagnostic et de packaging
    ├── check-environment.bat
    ├── check-environment.sh
    ├── package-kits.ps1
    └── package-kits.sh
```
