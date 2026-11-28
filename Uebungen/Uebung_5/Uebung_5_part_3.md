# 3. Technical Debt und Regelverletzungen mit LLM analysieren

**Ziel:** Technische Schuld erfassen, nach Schweregrad einordnen und mit LLM-Unterstützung reduzieren.
**Tools:** SonarQube (Ergebnisse aus Teil 1), Checkstyle, OSV-Datenbank, _LLM_

## Phase 1: Analyse mit Tools und LLM

### 1.1 Ausgangslage aus Teil 1

Die SonarQube-Analyse aus [Teil 1](Uebung_5.md) dient als Basis und wird hier nicht wiederholt.
Ergebnis: 0 Issues in Security, Reliability und Maintainability, 0 % Duplizierung, Technical Debt nahe 0, Coverage unter 30 %.
Mit den Standardregeln findet SonarQube also keine technische Schuld, daher ergänzen wir weitere Quellen.

### 1.2 Ergänzende Analyse

| Quelle | Befund |
|---|---|
| Checkstyle (`./gradlew checkstyleMain checkstyleTest`) | 0 Verstöße |
| Abhängigkeiten (OSV / Maven Central) | `assertj-core` 3.27.3 mit Sicherheitslücke CVE-2026-24400 (XXE) |
| | Checkstyle 10.21.0, aktuell 14.x |
| | JUnit 5.12.2, aktuell JUnit 6 |
| Coverage (Teil 1) | unter 30 %, wird in Teil 2 bearbeitet |

### 1.3 Prompt an das LLM

![Prompt](img/u5-3-prompt-1.png)

### 1.4 Antwort des LLM

![LLM-Antwort](img/u5-3-antwort-1.png)

## Phase 2: Auswertung, Bewertung und Einordnung

| Problem | Kategorie (Duplizierung / Abhängigkeit / Smell) | Schweregrad | LLM-Empfehlung | Bewertung (übernehmen / verwerfen) |
|---|---|---|---|---|
| _TODO_ | | | | |

**Einordnung:** _TODO, 2-3 Sätze: wo liegt die meiste Schuld, was ist kritisch, wo lag das LLM daneben._

## Phase 3: Optimierung und Reduktion (ongoing)

| # | Maßnahme | Status | Vorher → Nachher |
|---|---|---|---|
| 1 | _TODO_ | offen | |

![SonarQube nach Optimierung](img/u5-3-sonarqube-after.png)

## Reflexion

- **Was hat das LLM gut gemacht?** _TODO_
- **Wo waren Grenzen / Fehler?** _TODO_
- **Mehrwert für den Prozess:** _TODO_
