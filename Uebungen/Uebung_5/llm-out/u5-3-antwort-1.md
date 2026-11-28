# Übung 5, Teil 3: Vollständige LLM-Antwort (Prompt 1)

Unveränderte Antwort des LLM auf den Prompt aus Abschnitt 1.3, nur als Markdown formatiert.
Stellen, an denen der kopierte Text abgeschnitten war, sind mit `[…]` markiert.

> Hinweis des LLM: In deinem Prompt stand nur der Platzhalter `<INHALT DER JAVA-DATEIEN AUS src/main/java>`.
> Ich habe deshalb die 7 Dateien direkt aus dem Repo gelesen, dazu build.gradle, die Checkstyle-Konfiguration und die CI-Pipeline.

## Technische Schuld

### 1. Fachamt wird beim Weiterleiten nicht gespeichert
- **Problem:** `weiterleiten(fachamt)` prüft das Fachamt, speichert es aber nicht (Beschwerde.java:54). Die Fachdomäne verliert, wer zuständig ist.
- **Kategorie:** Design/Smell
- **Schweregrad:** hoch
- **Maßnahme:** Feld `zustaendigesFachamt` einführen, beim Weiterleiten setzen und in der Historie mitschreiben
- **Aufwand:** niedrig

### 2. Entity ohne Identität
- **Problem:** Die Entity `Beschwerde` hat keine Identität (keine ID, kein `equals`/`hashCode`), sie verhält sich also nicht wie eine DDD-Entity.
- **Kategorie:** Design/Smell
- **Schweregrad:** hoch
- **Maßnahme:** VO `BeschwerdeId` (UUID) einführen und `equals`/`hashCode` nur über die ID bilden
- **Aufwand:** niedrig

### 3. Keine Invarianten
- **Problem:** Es gibt keine Invarianten: `beschreibung` darf null oder leer sein, `kategorie` und `fachamt` dürfen null sein. Die Folge ist eine NPE statt eines Fachfehlers.
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** Mit `Objects.requireNonNull` und einer Prüfung auf leeren Text in der Factory bzw. im Record-Konstruktor (`Fachamt`) absichern
- **Aufwand:** niedrig

### 4. Uneinheitliche Fehlerstrategie
- **Problem:** Die Fehlerstrategie ist uneinheitlich: Bei falschem Fachamt kommt eine `IllegalArgumentException`, bei falschem Status die Domain-Exception.
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** Gemeinsame Basis `BeschwerdeDomainException` anlegen, dazu z. B. `FachamtNichtZustaendigException`
- **Aufwand:** niedrig

### 5. StatusHistorie ohne fachlichen Wert
- **Problem:** `StatusHistorie` ist ein einzelner Status, ohne Zeitpunkt oder Akteur. Damit ist die Historie fachlich fast wertlos, und der Name passt nicht (der Test heißt bereits `StatusAenderungTest`).
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** In `StatusAenderung(status, zeitpunkt, ...)` umbenennen und `Clock` injizieren, damit die Tests deterministisch bleiben
- **Aufwand:** niedrig

### 6. Status doppelt gehalten
- **Problem:** `status` liegt doppelt vor, einmal als Feld und einmal als letzter Historien-Eintrag.
- **Kategorie:** Duplizierung
- **Schweregrad:** niedrig
- **Maßnahme:** Den Status aus der Historie ableiten oder die Konsistenz in `wechsleStatus` kapseln (ist schon so, also nur dokumentieren)
- **Aufwand:** niedrig

### 7. Doppelte Übergangsprüfung
- **Problem:** `pruefeUebergang` läuft in `weiterleiten` zweimal.
- **Kategorie:** Duplizierung
- **Schweregrad:** niedrig
- **Maßnahme:** Die Fachamtsprüfung in `wechsleStatus` über einen Guard-Parameter abwickeln oder bewusst die Reihenfolge prüfen, dann zuweisen
- **Aufwand:** niedrig

### 8. Uebergangsregeln veränderbar und falsch platziert
- **Problem:** `Uebergangsregeln` liegt im Package `valueObjects`, ist aber ein veränderlicher, generischer Builder und kein VO. Die statische Konstante `REGELN` bleibt über `erlaube()` veränderbar.
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** Das Objekt unveränderlich machen (Builder mit `build()`, `Map.copyOf`) und nach `domain.model` oder `domain.shared` verschieben. Alternativ die Regeln direkt in `BeschwerdeStatus` ablegen (`kannWechselnZu`).
- **Aufwand:** niedrig

### 9. Inkonsistente Package-Struktur
- **Problem:** Die Package-Struktur ist inkonsistent. `valueObjects` ist camelCase, was die Java-Konvention verletzt. Fachamt (ein Record bzw. VO) […] liegen in `domain.event`, obwohl es keine Domain Events gibt.
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** Packages nach Aggregat schne[…]omain.fachamt), alles kleinschreiben und die Tests ins Package der getesteten Klasse legen
- **Aufwand:** niedrig

### 10. Fehlende Domain Events
- **Problem:** Domain Events fehlen. Die Zustandswechsel lassen sich nicht nach außen publizieren, obwohl die Testnamen sie suggerieren.
- **Kategorie:** Design/Smell
- **Schweregrad:** niedrig
- **Maßnahme:** Events wie `BeschwerdeWeitergeleitet` einführen, sobald eine Anwendungsschicht kommt
- **Aufwand:** mittel

### 11. Toter Code
- **Problem:** Unter `_alt_platzhalter/` liegt toter Code (`Calculator`, `NumberChecker`).
- **Kategorie:** Design/Smell
- **Schweregrad:** niedrig
- **Maßnahme:** Löschen
- **Aufwand:** niedrig

### 12. Schwacher Checkstyle-Regelsatz
- **Problem:** Der Checkstyle-Regelsatz hat […]ße sagen deshalb kaum etwas aus.
- **Kategorie:** Design/Smell
- **Schweregrad:** hoch
- **Maßnahme:** Auf Basis von `sun_checks` od[…]end gezielt lockern
- **Aufwand:** niedrig

### 13. Coverage wird nicht gemessen
- **Problem:** Coverage wird nicht gemessen: kein JaCoCo, kein Sonar-Schritt in der CI, keine Java-Toolchain in `build.gradle`.
- **Kategorie:** Design/Smell
- **Schweregrad:** mittel
- **Maßnahme:** JaCoCo und `jacocoTestCoverageVerification` einbauen, `java { toolchain { languageVersion = 21 } }` setzen und ein Sonar-Quality-Gate in d[…]
- **Aufwand:** niedrig

### 14. Duplizierung in der CI
- **Problem:** In der CI wird der Setup-Block dreimal kopiert und dreimal neu gebaut. Der `report`-Job ist wirkungslos, weil er nichts auscheckt […]n wie `test` nutzt.
- **Kategorie:** Duplizierung
- **Schweregrad:** niedrig
- **Maßnahme:** Einen Job `./gradlew check` verwenden oder eine Composite Action bauen, und den `report`-Job entfernen
- **Aufwand:** niedrig

### 15. AssertJ mit Sicherheitslücke
- **Problem:** `assertj-core` 3.27.3 ist von CVE-2026-24400 (XXE) betroffen. Es liegt nur im Test-Scope, und das Risiko besteht nur bei XML-Assertions.
- **Kategorie:** Sicherheit
- **Schweregrad:** mittel
- **Maßnahme:** Auf die gepatchte 3.27.x-Version laut Advisory heben. Das ist Drop-in, ohne Breaking Changes.
- **Aufwand:** niedrig

### 16. JUnit 5.12.2 → 6.x
- **Problem:** JUnit 6 b[…]ntfernt veraltete APIs und hat eine einheitliche Versionierung. Die Tests […]
- **Kategorie:** Abhängigkeit
- **Schweregrad:** niedrig
- **Maßnahme:** `testImplementation platform('org.junit:junit-bom:6.x')` einbinden, danach die Tests laufen lassen
- **Aufwand:** niedrig

### 17. Checkstyle 10.21.0 → 14.x
- **Problem:** Neue Versionen haben strengere Checks. Bei 5 Modulen sind kaum neue Funde zu erwarten.
- **Kategorie:** Abhängigkeit
- **Schweregrad:** niedrig
- **Maßnahme:** `toolVersion` anheben. Am besten zusammen mit dem erweiterten Regelsatz, damit die Funde nur einmal triagiert werden müssen.
- **Aufwand:** niedrig

## Top 3 nach Priorität

1. **Domänenmodell vervollständigen:** das[…]ern, eine `BeschwerdeId` einführen, Invarianten und eine einheitliche D[…].
   Das ist ein echter fachlicher Mangel, den kein Tool findet.
2. **Messbarkeit herstellen:** Checkstyle-[…]o und das Sonar-Quality-Gate in die CI aufnehmen.
   Ohne das sind die Werte "0 Issues" und "0 Verstöße" trügerisch.
3. **AssertJ patchen:** Das ist der einzige Sicherheitspunkt, die Änderung ist eine Zeile und hat kein Risiko.
   JUnit 6 und Checkstyle 14 lassen sich direkt mitziehen.

## Strengere Regeln für die Zukunft

- **Checkstyle:**
  - `PackageName` (Standard-Pattern) hätte `valueObjects` gefunden.
  - Außerdem sinnvoll: `FinalClass`, `Hid[…]terals`, `IllegalInstantiation`, `MissingJavadocType` für die öffentliche Domänen-API.
  - `ImportControl` erzwingt Schichtgrenzen, z. B. dass `domain` nichts außerhalb von `java.*` importiert.
- **SonarQube (über "Sonar way" hinaus):**
  - S120 (Package-Namenskonvention)
  - S1192 (doppelte String-Literale)
  - S2333/S1118 für Utility-Klassen
  - S2201 (ignorierte Rückgabewerte)
  - Quality Gate mit mindestens 80 % Coverage auf New Code
- **ArchUnit-Test:** prüft Package-Regeln, […]omain.. unveränderlich sind, dass Exceptions von der Domain-Basis-Exce[…] Objects nicht von Entities abhängen.
  Das ist die einzige Möglichkeit, DDD-Strukturschuld automatisch zu erkennen.
