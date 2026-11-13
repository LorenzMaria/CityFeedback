# To-Do-Liste: Feedback Prof. Dr. Ipek-Ugay (Übung 3)

## 1. Stimmigkeit / Konsistenz

- [x] **Beschwerden-Kommentar**: Im Event Storming Command und Event für `kommentieren()` nachtragen (fehlt aktuell)
- [x] **Zuweisung klären**: Entscheidung treffen – manuelle Zuweisung (Stadtverwaltung) ODER automatische Zuweisung über Kategorie (ZuweisungsService) – und im gesamten Dokument einheitlich darstellen
- [x] **Kommentar & Standort**: Zwei eigene Klassen pro Context erstellen (eine Komposition kann nur ein Aggregate als Eigentümer haben)
- [x] **Domain Events**: `weiterleiten()`-Methode so anpassen, dass sie ein Domain Event erzeugt (Abschnitt 4 besagt: jede Methode erzeugt Event)
- [x] **Methoden ergänzen**: 
  - [x] Benutzerkonto: Methoden aus Aufgabe 2/4 nachtragen
  - [x] Benachrichtigung: Methoden aus Aufgabe 2/4 nachtragen
- [x] **Service-Tabelle**: Methoden für alle Services ergänzen
- [x] **FachamtRepository**: `speichern()`-Methode hinzufügen

---

## 2. Fachliche Anmerkungen

- [x] **Policies vs. Rollen**: „Eingangsbestätigung senden" und „Statusänderung mitteilen" als Policies kennzeichnen (nicht als Rollen)
- [x] **Context-Beziehung Benutzerverwaltung**: Customer/Supplier überdenken – ggf. Conformist oder Anti-Corruption Layer als passendere Pattern
- [x] **BenachrichtigungsService**: Als Anwendungsservice mit Port zum Mailversand korrigieren (enthält keine Fachregel)
- [x] **einreichen()**: Als Factory-Methode implementieren
- [x] **Invarianten & Aggregate-Größe**:
  - [x] Invarianten pro Aggregate auflisten
  - [x] Größe der Aggregates begründen (insb. bei unbegrenzten Listen von Unterstützungen)

---

## 3. Implementierungstechnische Hinweise

- [x] **Domain-Interface**: Port in der Domäne definieren, Adapter in der Infrastruktur (kein Spring Data in der Domäne → vermeidet Framework-Abhängigkeit)
- [x] **Zeit für Tests**: `LocalDateTime.now()` durch injectbare `Clock` ersetzen (erleichtert Testbarkeit)
- [x] **HTML-Kommentare**: Alle Kommentare mit Bearbeiternamen aus dem Dokument entfernen
