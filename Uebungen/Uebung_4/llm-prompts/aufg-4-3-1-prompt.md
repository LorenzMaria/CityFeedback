# Übung 4, Aufgabe 3.1: Prompt für die Code-Generierung

Du bist ein erfahrener Senior Java-Entwickler und mein Pair-Programming-Partner. Wir arbeiten nach Test-Driven Design: Die Tests existieren bereits, du schreibst die Implementierung dazu.

## Kontext

Projekt: CityFeedback, ein Beschwerdemanagement-System für Bürger einer Stadt. Wir implementieren den Bounded Context "Beschwerdemanagement" nach Domain-Driven Design. Build: Gradle, Java 21, JUnit 5, AssertJ.

Eine Beschwerde durchläuft folgende Status: EINGEREICHT -> ANGENOMMEN -> WEITERGELEITET -> IN_BEARBEITUNG -> ERLEDIGT. Aus EINGEREICHT kann sie alternativ nach ABGELEHNT gehen. Jeder Übergang ist nur aus dem direkten Vorgängerstatus erlaubt. Eine Beschwerde darf nur an ein Fachamt mit derselben Kategorie weitergeleitet werden. Jede Statusänderung wird in einer Historie festgehalten.

## Aufgabe

Schreibe die minimale Implementierung, die die unten stehenden Tests besteht.

Regeln für diesen ersten Schritt:
- Nur so viel Code wie nötig, damit die Tests grün werden. Keine zusätzlichen Features.
- Noch KEINE Streams, Lambdas, Optional, Records oder Generics. Einfache Klassen, Enums, Listen, if-Abfragen. Fortgeschrittene Techniken kommen in einem späteren Schritt.
- Keine Frameworks sondern reines Java.
- Jede Klasse in einer eigenen Datei mit Package-Deklaration.

## Package-Struktur

Lege die Klassen in diese Packages:
- beschwerdemanagement.domain.model: Beschwerde, Fachamt
- beschwerdemanagement.domain.valueObjects: Kategorie, BeschwerdeStatus, StatusHistorie
- beschwerdemanagement.domain.exception: UngueltigerBeschwerdeStatusException

Die Tests liegen in beschwerdemanagement.domain.event und beschwerdemanagement.domain.valueObjects. Gib mir die Import-Zeilen, die ich in den Tests ergänzen muss.

## Test-Helfer

Die Tests rufen neueBeschwerde() und zustaendigesFachamt() auf. Diese Methoden existieren noch nicht. Liefere sie als eigene Klasse im Test-Package, z.B. BeschwerdeTestFixtures, und sag mir, wie ich sie in den Tests einbinde.

## Wichtig: Widersprüche in den Tests

Die Tests wurden von mehreren Personen geschrieben und sind an einigen Stellen nicht konsistent (z.B. Methodensignaturen, Rückgabetypen, erwartete Exceptions). Bevor du Code schreibst:
1. Liste alle Stellen auf, an denen sich die Tests widersprechen, mit Datei und Zeile.
2. Schlag mir pro Widerspruch eine Lösung vor und begründe sie kurz.
3. Entscheide NICHT selbst. Warte auf meine Antwort, dann generiere den Code.

## Ausgabeformat

Nach meiner Entscheidung: pro Datei ein Codeblock mit vollständigem Pfad als Überschrift, z.B. src/main/java/beschwerdemanagement/domain/model/Beschwerde.java. Dazu die nötigen Änderungen an den Tests als Diff oder als kurze Liste.

## Die Tests

- BearbeitungBegonnenTest.java
- BeschwerdeAbgelehntTest.java
- BeschwerdeAbschließenTest.java
- BeschwerdeAnnehmenTest.java
- BeschwerdeEinreichenTest.java
- BeschwerdeprozessTest.java
- BeschwerdeWeiterleitenTest.java
- StatusAenderungTest.java
