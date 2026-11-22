# CityFeedback starten

## Voraussetzungen

- Java 21 (JDK)
- Kein lokales Gradle nötig, das Projekt bringt den Gradle Wrapper mit.

## Bauen und testen

```bash
./gradlew build
```

Der Befehl kompiliert das Projekt, führt Checkstyle aus und startet alle Tests.

## Anwendung starten

```bash
./gradlew bootRun
```

Die Anwendung läuft danach unter `http://localhost:8080`.
Beenden mit `Ctrl+C`.

## H2-Datenbank

Die Anwendung nutzt eine H2-In-Memory-Datenbank.
Die Daten gehen beim Beenden der Anwendung verloren.

Die H2-Console ist unter `http://localhost:8080/h2-console` erreichbar.
Zum Verbinden folgende Werte eintragen:

| Feld | Wert |
|---|---|
| JDBC URL | `jdbc:h2:mem:cityfeedback` |
| User Name | `sa` |
| Password | (leer) |

Die Konfiguration liegt in `src/main/resources/application.properties`.
