# CityFeedback starten

## Voraussetzungen

Installiert werden muss nur ein JDK 21.
Wir nutzen Eclipse Temurin, das gleiche JDK wie in der CI.

| System | Installation |
|---|---|
| macOS | `brew install --cask temurin@21` |
| Windows | `winget install EclipseAdoptium.Temurin.21.JDK` |
| Linux / manuell | Download unter [adoptium.net](https://adoptium.net/temurin/releases/?version=21) |

Prüfen, ob JDK 21 gefunden wird:

```bash
/usr/libexec/java_home -V   # macOS
java -version               # Windows / Linux
```

Alles andere muss nicht installiert werden:

- Gradle lädt `./gradlew` beim ersten Aufruf selbst herunter.
- Spring Boot, H2 und alle weiteren Abhängigkeiten lädt Gradle beim ersten Build automatisch.
  Dafür ist beim ersten Start eine Internetverbindung nötig.

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

Hinweis: Es gibt noch keine Endpoints und keine Startseite.
`http://localhost:8080/` zeigt deshalb vorerst eine "Whitelabel Error Page" mit Status 404, das ist kein Fehler.

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

## Sidenote: Keine Produktiv-Datenbank und kein Deployment

- Laut Vorlage für die finale Projektdoku reicht ein Datenmodell mit "inMemory / SQLite" aus, CD ist optional und ein Deployment wird nicht verlangt.
  Deshalb bleibt es bei H2 in-memory, die Demo läuft lokal über `./gradlew bootRun`.
- `spring.jpa.hibernate.ddl-auto=create-drop` ist Absicht: Bei jedem Start ist die Datenbank in einem sauberen Zustand.
- Die Domäne ist frameworkfrei und Repositories sind Ports.
  Eine echte Datenbank wie PostgreSQL ließe sich später einsetzen, ohne die Domäne zu ändern.
- GitHub Pages kann nur statische Dateien ausliefern und kommt für das Backend nicht in Frage.

Offene Punkte, jeweils in einem eigenen PR:

- [ ] Demo-Daten per `data.sql`, sobald es Entities gibt.
- [ ] Technische Annahmen in Übung 3 (6.1) angleichen: NoSQL zu H2, Maven zu Gradle.
