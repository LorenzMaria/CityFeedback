# Übung 5, Teil 3: Vollständige LLM-Antwort (Prompt 2)

Antwort des LLM auf den Prompt zur Bewertung der Tool-Funde (SonarQube for IDE, Checkstyle, OSV / Maven Central).

## Bewertung der Tool-Funde

| Problem | Kategorie | Schweregrad | Empfehlung | Übernehmen? |
|---|---|---|---|---|
| **CVE-2026-24400** in assertj-core 3.27.3 (XXE) | Sicherheit / veraltete Abhängigkeit | Tool: hoch → **angemessen: niedrig**, da nur `testImplementation` und `isXmlEqualTo` nicht genutzt wird, also nicht ausnutzbar | In `build.gradle` auf gepatchte Version anheben: `testImplementation 'org.assertj:assertj-core:3.27.7'` (bzw. aktuellste 3.x) | **ja**, Aufwand fast null, Scanner/CI bleiben sonst dauerhaft rot |
| **JUnit 5.12.2**, aktuell 6.x | veraltete Abhängigkeit | niedrig, angemessen, keine bekannte Schwachstelle | Auf JUnit 6 migrieren (Java 17+ ist mit Java 21 erfüllt), dabei BOM nutzen: `testImplementation platform('org.junit:junit-bom:6.0.x')`, `testImplementation 'org.junit.jupiter:junit-jupiter'` | **ja**, Migration bei Standard-Jupiter-Tests meist trivial, BOM verhindert Versionskonflikte mit dem Launcher |
| **Checkstyle 10.21.0**, aktuell 14.x | veraltete Abhängigkeit | niedrig, angemessen, reines Build-Tool ohne Laufzeitrisiko | `toolVersion = "14.x"` setzen, danach `checkstyle.xml` gegen geänderte/entfernte Module prüfen | **ja**, aber als eigener Schritt, da neue Regeln neue Verstöße erzeugen können |
| **S1176**, 16x fehlendes Javadoc in `main` | Code Smell (Dokumentationsschuld) | Tool: mittel → **angemessen: niedrig**, bei 170 LOC mit sprechenden DDD-Namen (`Fachamt`, `Kategorie`) ist triviales Javadoc wertlos | Gezielt dokumentieren, wo Fachwissen steckt: `Beschwerde` (Zustandsübergänge, Invarianten, geworfene Exceptions) und `Uebergangsregeln`. Bei Records/Enums ein Satz zum Klassenzweck (siehe Beispiel unten) | **teilweise**, ja für `Beschwerde` und `Uebergangsregeln`, nein für Pflicht-Javadoc an jedem Getter und Typparameter |
| **S5778**, `BeschwerdeWeiterleitenTest.java:49` | Code Smell (Test) | Tool: mittel → **angemessen: niedrig**, `zustaendigesFachamt()` ist eine triviale Fixture und wirft praktisch nie, der Fund ist aber formal korrekt | Argument vor dem Lambda erzeugen (siehe Beispiel unten) | **ja**, eine Zeile, macht den Test präziser |
| **S1612**, 3x Lambda statt Methodenreferenz | Code Smell (Stil) | niedrig, angemessen | `assertThatThrownBy(beschwerde::abschliessen)` usw. | **ja**, IDE-Quickfix in Sekunden, Nutzen nur kosmetisch |

**Code-Duplizierung:** Kein Fund der Tools.
**False Positives:** Keiner im engeren Sinn.
Der CVE-Fund ist im Projektkontext aber nicht ausnutzbar, und S1176 in voller Breite hat kaum Mehrwert.

### Beispiel S5778

```java
Fachamt fachamt = zustaendigesFachamt();
assertThatThrownBy(() -> beschwerde.weiterleiten(fachamt))
        .isInstanceOf(IllegalStateException.class);
```

### Beispiel S1176 (sinnvolles Javadoc in `Beschwerde`)

```java
/**
 * Leitet die Beschwerde an das zuständige Fachamt weiter.
 * Erlaubt nur im Status ANGENOMMEN.
 *
 * @throws IllegalStateException wenn die Beschwerde nicht angenommen ist
 */
public void weiterleiten(Fachamt fachamt) { ... }
```

## Top 3 (Nutzen im Verhältnis zum Aufwand)

1. **assertj-core patchen:** Eine Zeile, beseitigt den einzigen Sicherheitsfund und hält Scanner sauber.
2. **S5778 und S1612 beheben:** Wenige Minuten, präzisere Tests, 4 von 20 Sonar-Issues erledigt.
3. **JUnit 6 (mit BOM) und Checkstyle 14 aktualisieren, Javadoc gezielt für `Beschwerde` und `Uebergangsregeln` ergänzen:** Getrennte Commits.
   S1176 für triviale Typen per Sonar-Konfiguration ausnehmen statt Pflicht-Javadoc zu erzeugen.
