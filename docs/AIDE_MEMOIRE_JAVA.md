# Aide-Mémoire Java Moderne

Un guide de référence synthétique pour retrouver rapidement la syntaxe Java essentielle.

---

## 1. Variables et Types Primitifs

```java
int depth = 500;            // Entier standard
double weightKg = 18.5;     // Nombre à virgule flottante
boolean available = true;   // Booléen (true / false)
String name = "Nautilus";   // Chaîne de caractères (objet immuable)
```

---

## 2. Conditions et Branchements

### `if` / `else if` / `else`
```java
if (battery < 15) {
    System.out.println("Niveau critique");
} else if (battery < 50) {
    System.out.println("Niveau moyen");
} else {
    System.out.println("Niveau optimal");
}
```

### `switch` Moderne (Java 14+)
```java
String description = switch (status) {
    case AVAILABLE -> "Disponible";
    case ON_MISSION -> "En mission";
    case MAINTENANCE -> "En maintenance";
};
```

---

## 3. Boucles

### Boucle `for-each` (parcours de liste)
```java
List<String> droneNames = List.of("Nautilus", "Argos", "Titan");
for (String drone : droneNames) {
    System.out.println("Drone : " + drone);
}
```

### Boucle `while`
```java
while (battery < 100) {
    battery += 10;
}
```

---

## 4. Classes, Attributs, Constructeurs et Encapsulation

```java
package fr.abyss.model;

public class Submarine {
    // 1. Attributs privés (encapsulation)
    private final String name;
    private int battery;

    // 2. Constructeur
    public Submarine(String name, int initialBattery) {
        if (initialBattery < 0 || initialBattery > 100) {
            throw new IllegalArgumentException("La batterie doit être entre 0 et 100%");
        }
        this.name = name;
        this.battery = initialBattery;
    }

    // 3. Getters
    public String getName() {
        return name;
    }

    public int getBattery() {
        return battery;
    }

    // 4. Méthode métier
    public void consumeEnergy(int cost) {
        this.battery = Math.max(0, this.battery - cost);
    }
}
```

---

## 5. Énumérations (`enum`)

```java
public enum DroneStatus {
    AVAILABLE("Disponible"),
    ON_MISSION("En mission"),
    MAINTENANCE("En maintenance");

    private final String label;

    DroneStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
```

---

## 6. Collections : `List`, `Set`, `Map`

### Listes (`ArrayList`)
```java
import java.util.List;
import java.util.ArrayList;

List<Submarine> fleet = new ArrayList<>();
fleet.add(new Submarine("Nautilus", 80));
int total = fleet.size();
Submarine first = fleet.get(0);
```

### Ensembles (`HashSet` / `Set.of`)
```java
import java.util.Set;
import java.util.HashSet;

Set<String> capabilities = new HashSet<>();
capabilities.add("OBSERVATION");
capabilities.add("RECOVERY");

boolean canObserve = capabilities.contains("OBSERVATION");
```

---

## 7. Entrées Console (`Scanner`)

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Entrez un identifiant : ");
if (scanner.hasNextInt()) {
    int id = scanner.nextInt();
    scanner.nextLine(); // Nettoie le saut de ligne restant
    System.out.println("ID saisi : " + id);
} else {
    String invalid = scanner.nextLine();
    System.out.println("Valeur invalide : " + invalid);
}
```

---

## 8. Notions Avancées (Parcours Conception objet)

### `Optional<T>`
```java
import java.util.Optional;

public Optional<Submarine> findByName(List<Submarine> fleet, String name) {
    for (Submarine s : fleet) {
        if (s.getName().equalsIgnoreCase(name)) {
            return Optional.of(s);
        }
    }
    return Optional.empty();
}
```

### Streams et Lambdas
```java
List<Submarine> readyToDive = fleet.stream()
    .filter(d -> d.getBattery() >= 50)
    .sorted(Comparator.comparingInt(Submarine::getBattery).reversed())
    .toList();
```

### `record` (Java 16+)
Idéal pour les structures de données immuables (ex. résultats de validation, DTO) :
```java
public record ValidationResult(boolean isValid, String message) {
    public static ValidationResult ok() {
        return new ValidationResult(true, "Opération valide");
    }
    public static ValidationResult reject(String reason) {
        return new ValidationResult(false, reason);
    }
}
```
