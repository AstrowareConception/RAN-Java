# Guide « Je suis bloqué »

Une méthode pas-à-pas pour diagnostiquer et résoudre les blocages fréquents en programmation Java.

---

## 1. Méthode Universelle de Diagnostic (en 8 étapes)

1. **Lire attentivement le message d'erreur** : Ne sautez pas directement le bloc rouge. La première ou la dernière ligne indique précisément le type de problème.
2. **Repérer le fichier et le numéro de ligne** : Cliquez sur le lien dans la pile d'exécution (*stack trace*) pour vous positionner sur la ligne exacte qui déclenche l'erreur.
3. **Inspecter le contenu des variables** : Placez un `System.out.println("DEBUG: maVariable=" + maVariable);` juste avant la ligne problématique (ou utilisez le débogueur de votre IDE).
4. **Isoler le problème** : Testez votre méthode avec un exemple minimal et des valeurs simples en dur.
5. **Consulter les indices (`HINTS.md`)** : Votre parcours contient un fichier d'indices progressifs conçu pour vous débloquer sans vous donner la solution brute.
6. **Consulter les aides-mémoire** : Vérifiez la syntaxe dans `docs/AIDE_MEMOIRE_JAVA.md` ou le principe dans `docs/AIDE_MEMOIRE_POO.md`.
7. **Consulter la documentation officielle** : Référez-vous aux liens de `docs/RESSOURCES.md`.
8. **Solliciter l'enseignant** : Expliquez clairement :
   * Ce que vous essayez d'accomplir ;
   * Ce qui se produit réellement (message d'erreur, comportement inattendu) ;
   * Ce que vous avez déjà vérifié et testé.

---

## 2. Pannes Fréquentes et Solutions Rapides

### Problème 1 : `NullPointerException` (NPE)
* **Cause** : Vous tentez d'appeler une méthode ou d'accéder à un champ sur une variable qui vaut `null`.
* **Exemple typique** : Une liste de drones déclarée mais non initialisée (`private List<Drone> drones;` sans `= new ArrayList<>()`).
* **Solution** : Initialisez toujours vos collections dès leur déclaration ou dans le constructeur.

### Problème 2 : `IndexOutOfBoundsException`
* **Cause** : Vous tentez d'accéder à un index inexistant dans une liste (`list.get(5)` alors que `list.size()` vaut 5 $\rightarrow$ les indices vont de 0 à 4).
* **Solution** : Vérifiez toujours que `index >= 0 && index < list.size()`.

### Problème 3 : Le `Scanner` saute une saisie de texte
* **Cause** : Après un appel à `scanner.nextInt()`, le caractère de retour à la ligne (`\n`) reste dans le tampon. L'appel suivant à `scanner.nextLine()` lit ce saut de ligne vide.
* **Solution** : Ajoutez systématiquement `scanner.nextLine();` immédiatement après `scanner.nextInt()` pour vider le tampon.

### Problème 4 : Comparaison de chaînes ou d'objets avec `==`
* **Cause** : En Java, `str1 == str2` compare les références mémoire et non le contenu textuel.
* **Solution** : Utilisez toujours `str1.equals(str2)` ou `str1.equalsIgnoreCase(str2)`. Pour les énumérations en revanche, `status == DroneStatus.AVAILABLE` est correct et recommandé.

### Problème 5 : `Non-static method cannot be referenced from a static context`
* **Cause** : Vous essayez d'appeler une méthode d'instance directement depuis la méthode `public static void main(String[] args)`.
* **Solution** : Créez une instance de votre classe dans le `main` (ex. `FleetManager manager = new FleetManager();`) puis appelez `manager.maMethode()`.

### Problème 6 : Erreurs de compilation de package / import
* **Cause** : Le fichier n'est pas dans le dossier correspondant à son instruction `package` (ex. `package fr.abyss.model;` doit se trouver dans le dossier `fr/abyss/model/`).
* **Solution** : Vérifiez l'arborescence des dossiers ou utilisez la fonction de correction automatique de votre IDE (`Alt + Entrée` sur IntelliJ / Eclipse).

### Problème 7 (Parcours avancé) : Maven ne trouve pas les tests ou le JDK
* **Cause** : La variable d'environnement `JAVA_HOME` pointe vers une ancienne version ou le plugin surefire n'est pas synchronisé.
* **Solution** : Lancez `./tools/check-environment.bat` (ou `.sh`) et vérifiez que votre IDE utilise bien Java 21 LTS comme SDK de projet.
