# Aufgabe 5.2: Test Coverage erweitern und Code Coverage verbessern

Zur Überprüfung der Testabdeckung wurde das Tool **JaCoCo** in das Gradle-Projekt eingebunden. Damit konnten die bereits vorhandenen Tests ausgewertet und Bereiche mit fehlender Testabdeckung identifiziert werden.

Bei der Auswertung fiel auf, dass die Klasse `Uebergangsregeln` im Paket `beschwerdemanagement.domain.valueObjects` nur eine Branch Coverage von **75 %** aufwies. Deshalb wurde gezielt eine neue Testklasse namens `UebergangsregelnTest` erstellt.

Für die Methode `istErlaubt()` wurden drei zusätzliche Tests geschrieben:

1. Ein erlaubter Zustandsübergang liefert `true`.
2. Ein nicht erlaubter Zustandsübergang liefert `false`.
3. Ein unbekannter Ausgangszustand liefert `false`.

Die Testfälle wurden mithilfe von ChatGPT als LLM-Unterstützung entwickelt, um insbesondere unterschiedliche Entscheidungswege und mögliche Edge Cases der Methode abzudecken.

Anschließend wurden die Tests erneut ausgeführt und der JaCoCo-Bericht aktualisiert. Alle Tests waren erfolgreich (`BUILD SUCCESSFUL`).

**Ergebnis:** Die Branch Coverage im Paket `beschwerdemanagement.domain.valueObjects` konnte von **75 % auf 100 %** verbessert werden. Damit werden nun alle Entscheidungswege der Methode `istErlaubt()` durch Tests abgedeckt.

Durch diese gezielte Erweiterung konnte die Testabdeckung einer zentralen Logikkomponente verbessert und die Qualität der Anwendung zusätzlich abgesichert werden.

## Nachweise

- JaCoCo-Bericht vor der Erweiterung: 75 % Branch Coverage
- JaCoCo-Bericht nach der Erweiterung: 100 % Branch Coverage
- Erfolgreicher Testlauf mit Gradle (`BUILD SUCCESSFUL`)