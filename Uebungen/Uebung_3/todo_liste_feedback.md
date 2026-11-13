# To-Do-Liste: Feedback Prof. Dr. Ipek-Ugay (Übung 3)

## 1. Stimmigkeit / Konsistenz

- [ ] **Beschwerden-Kommentar**: Im Event Storming Command und Event für `kommentieren()` nachtragen (fehlt aktuell)
- [ ] **Zuweisung klären**: Entscheidung treffen – manuelle Zuweisung (Stadtverwaltung) ODER automatische Zuweisung über Kategorie (ZuweisungsService) – und im gesamten Dokument einheitlich darstellen
- [ ] **Kommentar & Standort**: Zwei eigene Klassen pro Context erstellen (eine Komposition kann nur ein Aggregate als Eigentümer haben)
- [ ] **Domain Events**: `weiterleiten()`-Methode so anpassen, dass sie ein Domain Event erzeugt (Abschnitt 4 besagt: jede Methode erzeugt Event)
- [ ] **Methoden ergänzen**: 
  - [ ] Benutzerkonto: Methoden aus Aufgabe 2/4 nachtragen
  - [ ] Benachrichtigung: Methoden aus Aufgabe 2/4 nachtragen
- [ ] **Service-Tabelle**: Methoden für alle Services ergänzen
- [ ] **FachamtRepository**: `speichern()`-Methode hinzufügen

---

## 2. Fachliche Anmerkungen

- [ ] **Policies vs. Rollen**: „Eingangsbestätigung senden" und „Statusänderung mitteilen" als Policies kennzeichnen (nicht als Rollen)
- [ ] **Context-Beziehung Benutzerverwaltung**: Customer/Supplier überdenken – ggf. Conformist oder Anti-Corruption Layer als passendere Pattern
- [ ] **BenachrichtigungsService**: Als Anwendungsservice mit Port zum Mailversand korrigieren (enthält keine Fachregel)
- [ ] **einreichen()**: Als Factory-Methode implementieren
- [ ] **Invarianten & Aggregate-Größe**:
  - [ ] Invarianten pro Aggregate auflisten
  - [ ] Größe der Aggregates begründen (insb. bei unbegrenzten Listen von Unterstützungen)

---

## 3. Implementierungstechnische Hinweise

- [ ] **Domain-Interface**: Port in der Domäne definieren, Adapter in der Infrastruktur (kein Spring Data in der Domäne → vermeidet Framework-Abhängigkeit)
- [ ] **Zeit für Tests**: `LocalDateTime.now()` durch injectbare `Clock` ersetzen (erleichtert Testbarkeit)
- [x] **HTML-Kommentare**: Alle Kommentare mit Bearbeiternamen aus dem Dokument entfernen
