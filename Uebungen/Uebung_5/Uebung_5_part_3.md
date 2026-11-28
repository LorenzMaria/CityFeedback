# 3. Technical Debt und Regelverletzungen mit LLM analysieren

**Ziel:** Technische Schuld und Regelverletzungen erfassen, nach Schweregrad einordnen und mit LLM-Unterstützung reduzieren

**Tools:** SonarQube (siehe Teil 1), SonarQube for IDE, OSV-Datenbank, _LLM_

## Phase 1: Analyse mit Tools und LLM

### 1.1 Ausgangslage aus Teil 1

Die SonarQube-Analyse aus Teil 1 dient als Basis und wird hier nicht wiederholt.
Ergebnis: 0 Issues in Security, Reliability und Maintainability, 0 % Duplizierung, Technical Debt nahe 0, Coverage unter 30 %.
Mit den Standardregeln ("Sonar way") findet SonarQube also keine technische Schuld, daher verschärfen wir die Regeln.

### 1.2 Erweiterte Regeln in SonarQube for IDE

Zusätzlich aktiviert: S109 (Magic Numbers), S2384 (Mutable Members), S1176 (Javadoc für öffentliche API).
S1176 prüft standardmäßig nur Packages unter `**.api.**`, daher wurde `forClasses` auf `beschwerdemanagement.**` gesetzt.

![Zusätzlich aktivierte Regeln](img/u5-3-rules.png)
![Parameter von S1176](img/u5-3-rule-param.png)

### 1.3 Ergebnisse der Tools

![SonarQube for IDE Report](img/u5-3-sonarcube-analysis.png)

| Regel | Verletzung | Schweregrad | Anzahl | Ort |
|---|---|---|---|---|
| S1176 | "Document this public … by adding an explicit description" (keine Javadoc an öffentlicher API) | Mittel | 16 | `main`, 7 Dateien |
| S5778 | "Refactor the code of the lambda to have only one invocation possibly throwing a runtime exception" | Mittel | 1 | `test` |
| S1612 | "Replace this lambda with method reference" | Niedrig | 3 | `test` |
| **Gesamt** | | | **20** | **10 Dateien** |

Veraltete Abhängigkeiten (abgeglichen mit OSV-Datenbank und Maven Central):

| Abhängigkeit | Version | Aktuell | Befund |
|---|---|---|---|
| `assertj-core` | 3.27.3 | 3.27.7 | Sicherheitslücke CVE-2026-24400 (XXE) |
| Checkstyle | 10.21.0 | 14.x | 4 Major-Versionen zurück |
| JUnit Jupiter | 5.12.2 | 6.x | 1 Major-Version zurück |

### 1.4 Prompt an das LLM

![Prompt](img/u5-3-prompt-2.png)

### 1.5 Antwort des LLM

_TODO_

> **Iteration 1 (verworfen):** Der erste Prompt ließ das LLM selbst im Code nach Schuld suchen, statt Tool-Ergebnisse zu bewerten ([Prompt](img/u5-3-prompt-1.png), [Antwort](llm-out/u5-3-antwort-1.md)).
> Das widerspricht der Aufgabe, die Funde müssen vom Tool kommen.
> Übernommen wurde nur die Erkenntnis, dass "0 Issues" mit Standardregeln trügerisch ist, daraus entstanden die erweiterten Regeln in 1.2.

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
