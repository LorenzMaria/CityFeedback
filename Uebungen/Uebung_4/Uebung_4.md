Übung 4

# 1. Review der Implementierungsstrategie
Als wichtigste Domain Events unseres Projekts wurden BeschwerdeEingereicht, BeschwerdeWeitergeleitet, BeschwerdeErledigt und VorschlagEingereicht identifiziert, da sie zentrale Geschäftsprozesse der beiden Core Domains abbilden. Übernommen wurde der LLM-Vorschlag, die Bounded Contexts zusätzlich in domain, application und infrastructure zu unterteilen, um fachliche Logik und technische Implementierung klar voneinander zu trennen. Ebenfalls übernommen wurde die Empfehlung, Domain Events erst nach erfolgreicher Speicherung zu veröffentlichen, damit es keine Eingangsbestätigung für eine Beschwerde geben kann, die nicht gespeichert wurde. Die Entwicklungsreihenfolge wurde dahingehend angepasst, dass zunächst die beiden Core Domains Beschwerdemanagement und Bürgerbeteiligung und anschließend die unterstützenden Contexts Benutzerverwaltung und Benachrichtigung implementiert werden. Abgelehnt wurde die Empfehlung, dass eine einfache Eventverarbeitung mit Spring Boot für den aktuellen Projektumfang ausreichend ist da es eine geringere technische Komplexität mit sich bringt, da die Planung für dieses Projekt von der Realität ausgeht.

Ausgewählte Domain-Events :
    - BeschwerdeEingereicht
    - BeschwerdeWeitergeleitet
    - BeschwerdeAbgeschlossen

Prompt: 

![Prompt an ChatGPT](img/prompt_role.png)

![Prompt: Rolle für Implementierungsstrategie](img/prompt_role.png)

![Prompt: Ausschnitt 1](img/prompt_part_4.png)
![Prompt: Ausschnitt 2](img/prompt_part_5.png)
![Prompt: Ausschnitt 3](img/prompt_part_6.png)
![Prompt: Ausschnitt 4](img/prompt_part_7.png)

![Antwort von LLM](img/prompt_antwort_1.png)
![Antwort von LLM](img/prompt_antwort_2.png)
![Antwort von LLM](img/prompt_antwort_3.png)
![Antwort von LLM](img/prompt_antwort_4.png)


# 2. Tesfälle mit LLM generieren und validieren

## LLM Prompt

Der LLM wurde eine Rolle als erfahrerner Senior Softwareentwickler mit Java UnitTests nach dem TestDrivenDesign gegeben und im anschluss mit der Beschreibung der Domainlogik undinklusiver Domainevents gefüttert. 

## Generierte Tests

Happy Path:
    - beschwerdeErstellen()
    - sollteBeschwerdeAnFachamtWeiterleiten()
    - sollteBeschwerdeNurAnZustaendigesFachamtWeiterleiten()
    - beschwerdeAbschliessen()

Negativ Path:    
    - sollteNichtAngenommeneBeschwerdeNichtWeiterleiten()
    - beschwerdeOhneBearbeitungsbeginnNichtAbschliessen()

Edge-Case Path
    - sollteWiederholteStatusaenderungVerhindern()


## Kritische Bewertung

Beschwerde abgedeckt haben. Besonders hilfreich waren die Happy-Path-Tests, da sie den regulären Lebenszyklus einer Beschwerde nachvollziehbar überprüfen. Die Negative Tests wurden ebenfalls übernommen, um sicherzustellen, dass unzulässige Statusübergänge, beispielsweise die Weiterleitung einer abgelehnten Beschwerde, verhindert werden. Insbesondere dem Testfall beschwerdeErstellen() wurde erst auf konkrete Anfrage der LLM eine Regex-Validierung hinzugefügt. Einige Tests mussten angepasst werden,weil die namensgebung  nicht ideal war. Zusätzlich wurden Edge-Case-Tests ergänzt, um beispielsweise wiederholte Statusänderungen und ungültige Eingaben zu überprüfen. 


## Regex-Validierung

Bei der Regex-validierung wurde folgende Regel verwendet um sicher zu gehen, dass die Beschreibung Text enthält.

     // Regex-Validierung
    String regex = "^(?=.*\\S).{10,}$";


# 3. Implementierung der Domänenlogik (TDD Schritt 2) mit LLM-Pair-Programming

## 3.1 LLM Prompt

Das LLM bekam die Rolle eines Senior Java-Entwicklers, den Statusablauf einer Beschwerde, die Package-Struktur und alle acht Testdateien aus Aufgabe 2 (vollständiger Prompt: [llm-prompts/aufg-4-3-1-prompt.md](llm-prompts/aufg-4-3-1-prompt.md)).
Verlangt wurde zunächst die minimale Implementierung ohne fortgeschrittene Techniken, Widersprüche in den Tests sollte das LLM nur auflisten und nicht selbst entscheiden.

## 3.2 Code-Generierung

Aus den Tests entstanden sechs Klassen in den Packages `model`, `valueObjects` und `exception`, mit denen alle 13 Tests grün sind.
Kern ist eine Hilfsmethode in `Beschwerde`, die jeden Statusübergang gegen den erlaubten Vorgängerstatus prüft, in der Historie festhält und sonst eine `UngueltigerBeschwerdeStatusException` wirft.

## 3.3 Iterative Verbesserung

Im zweiten Schritt schlug das LLM pro Technik (Records, Generics, Lambdas, Optional, Streams) eine Vorher/Nachher-Variante vor, übernommen wurden nur zwei davon.

- Records für die Value Objects `StatusHistorie` und `Fachamt`, das spart Boilerplate und liefert Unveränderlichkeit
- Generische Klasse `Uebergangsregeln<S extends Enum<S>>`, die den gesamten Lebenszyklus an einer Stelle deklarativ beschreibt
- Lambdas, Optional und Streams abgelehnt, weil es im Aggregat keinen echten Bedarf dafür gibt

## 3.4 Kritische Qualitätsprüfung

- Gut: Ein abgelehnter Übergang verändert weder Status noch Historie, und die Historie wird nur als unveränderliche Sicht herausgegeben
- Schwach: `Fachamt` als Record hat Wertegleichheit, obwohl es fachlich eine Entität mit eigener ID wäre
- Fehlend: Tests für ungültige Übergänge aus Endzuständen und für leere oder zu kurze Beschreibungen

## 3.5 Code-Review mit LLM

Das abschließende Review fand trotz grüner Tests die fehlende Validierung in `einreichen()`, die doppelte Übergangsprüfung in `weiterleiten()` und die weiterhin veränderbare `Uebergangsregeln`-Tabelle.

## 3.6 Fazit zum Pair-Programming-Prozess

Das zweistufige Vorgehen hat sich bewährt: erst eine minimale, testgetriebene Version, dann gezielte Verbesserungen, die nur übernommen wurden, wenn sie einen echten Qualitätsgewinn bringen.
