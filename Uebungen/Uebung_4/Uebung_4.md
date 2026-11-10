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

Das LLM bekam die Rolle eines Senior Java-Entwicklers als Pair-Programming-Partner, dazu die Beschreibung des Statusablaufs einer Beschwerde, die gewünschte Package-Struktur und alle acht Testdateien aus Aufgabe 2.
Für den ersten Schritt wurde bewusst die minimale Implementierung ohne fortgeschrittene Java-Techniken verlangt, damit die LLM nicht schon in diesem Schritt komplexeren Code generiert.
Da die Tests an einigen Stellen widersprüchliche Erwartungen haben, sollte das LLM diese zuerst auflisten und Lösungen vorschlagen, aber nicht selbst entscheiden.

Der vollständige Prompt liegt unter [llm-prompts/aufg-4-3-1-prompt.md](llm-prompts/aufg-4-3-1-prompt.md).

### Verbesserungen
- Zwei Tests nutzten verkürzte Signaturen ohne Beschreibung bzw. ohne Fachamt-Name 
- Die Tests wurden auf die vollständige Signatur vereinheitlicht, weil eine Beschwerde ohne Beschreibung und ein Fachamt ohne Namen fachlich keinen Sinn ergeben 
- Dadurch kommt die Implementierung mit je einer Methode bzw. einem Konstruktor aus
- `getStatusHistorie()` liefert eine Liste von `StatusHistorie`-Objekten statt nur Status, damit später Zeitstempel oder Bearbeiter ergänzt werden können
- Ungültige Statusübergänge werfen eine eigene `UngueltigerBeschwerdeStatusException`, die von `IllegalStateException` erbt, so bleiben alle Tests unverändert gültig
- Zwei Testklassen wurden umbenannt, damit Klassen- und Dateiname übereinstimmen und kein "ß" im Dateinamen steht
- Die Test-Helfer `neueBeschwerde()` und `zustaendigesFachamt()` liegen in einer gemeinsamen Klasse `BeschwerdeTestFixtures` statt in jedem Test einzeln

## Code-Generierung

Aus den acht Testdateien hat das LLM sechs Klassen in den Packages `model`, `valueObjects` und `exception` erzeugt, mit denen alle 13 Tests grün sind.
Kern der Implementierung ist eine private Hilfsmethode in `Beschwerde`, die jeden Statusübergang gegen den erlaubten Vorgängerstatus prüft und in der Historie festhält.

```java
public void annehmen() {
    wechsleStatus(BeschwerdeStatus.EINGEREICHT, BeschwerdeStatus.ANGENOMMEN);
}

private void wechsleStatus(BeschwerdeStatus erwartet, BeschwerdeStatus neu) {
    pruefeStatus(erwartet, neu);
    status = neu;
    statusHistorie.add(new StatusHistorie(neu));
}

private void pruefeStatus(BeschwerdeStatus erwartet, BeschwerdeStatus neu) {
    if (status != erwartet) {
        throw new UngueltigerBeschwerdeStatusException(
            "Uebergang nach " + neu + " ist aus Status " + status + " nicht erlaubt");
    }
}
```

## Iterative Verbesserung

Im zweiten Schritt hat das LLM pro fortgeschrittener Technik (Records, Generics, Lambdas, Optional, Streams) eine Variante mit Vorher/Nachher vorgeschlagen.
Übernommen wurden Records und Generics, Streams und Lambdas wurden abgelehnt, weil es im Aggregat keinen echten Bedarf dafür gibt.
(Genutzte fortgeschrittene Techniken: Record-Types für `StatusHistorie` und `Fachamt`, Generics für `Uebergangsregeln<S extends Enum<S>>`.)

Empfehlung des LLM und Entscheidung:

| Variante | Empfehlung | Grund | Umgesetzt |
|---|---|---|---|
| 1 Records | Ja | Echte Value Objects, weniger Code, kostet nur zwei Testzeilen | ✅ |
| 2 Generics `Uebergangsregeln` | Ja | Macht den Lebenszyklus deklarativ, größter Qualitätsgewinn | ✅ |
| 3 Lambda-Guard | Nein | Löst die Doppelprüfung, aber Variante 2 löst sie ohne Indirektion gleich mit | ❌ |
| 4 Optional `getFachamt()` | Ja, mit neuem Test | Fachlich sinnvoll, aber nicht ungetestet | ❌ (zurückgestellt) |
| 5 Streams | Nein | Kein Bedarf, wäre Technik um der Technik willen | ❌ |

### Beispiel 1: Records für Value Objects

Vorher:

```java
public class StatusHistorie {
    private final BeschwerdeStatus status;

    public StatusHistorie(BeschwerdeStatus status) {
        this.status = status;
    }

    public BeschwerdeStatus getStatus() {
        return status;
    }
}
```

Nachher:

```java
public record StatusHistorie(BeschwerdeStatus status) {
}
```

Der Record liefert Unveränderlichkeit sowie `equals`, `hashCode` und `toString` ohne eigenen Code.
In zwei Tests wird dafür `StatusHistorie::getStatus` durch `StatusHistorie::status` ersetzt.

### Beispiel 2: Generische Übergangsregeln

Vorher:

```java
public void annehmen() {
    wechsleStatus(BeschwerdeStatus.EINGEREICHT, BeschwerdeStatus.ANGENOMMEN);
}
```

Nachher:

```java
private static final Uebergangsregeln<BeschwerdeStatus> REGELN =
    Uebergangsregeln.fuer(BeschwerdeStatus.class)
        .erlaube(EINGEREICHT, ANGENOMMEN, ABGELEHNT)
        .erlaube(ANGENOMMEN, WEITERGELEITET)
        .erlaube(WEITERGELEITET, IN_BEARBEITUNG)
        .erlaube(IN_BEARBEITUNG, ERLEDIGT);

public void annehmen() {
    wechsleStatus(BeschwerdeStatus.ANGENOMMEN);
}
```

Der komplette Lebenszyklus steht damit an einer Stelle deklarativ, und die generische Klasse `Uebergangsregeln<S extends Enum<S>>` ist für andere Aggregate wiederverwendbar.
Alle Tests bleiben dabei unverändert grün.

## Kritische Qualitätsprüfung

Der generierte Code wurde auf Korrektheit, Lesbarkeit und Wartbarkeit geprüft.

### Was gut gelungen ist

- Die Übergangsregeln sind an einer Stelle deklariert und direkt mit dem Prozess aus der Aufgabenstellung vergleichbar.
- Ein abgelehnter Übergang verändert weder Status noch Historie.
- `getStatusHistorie()` gibt nur eine unveränderliche Sicht heraus, das Aggregat bleibt geschützt.

### Was noch schwach ist

- `Fachamt` als Record hat Wertegleichheit über Name und Kategorie, fachlich ist ein Fachamt aber eine Entität und braucht eine eigene ID.
- `einreichen()` validiert die Beschreibung nicht, der Regex-Test in `beschwerdeErstellen()` prüft nur den Teststring und nicht die Implementierung.

### Fehlende Tests

- Ablehnen aus einem anderen Status als `EINGEREICHT`
- Weiterleiten einer abgelehnten Beschwerde
- Einreichen mit leerer oder zu kurzer Beschreibung
- Edge Cases wie Statusänderung aus einem Endzustand

## Code-Review mit LLM

## Dokumentation des Pair-Programming-Prozesses

