# CityFeedback: Bürgerbeteiligungs- und Beschwerdesystem


**1. Was ist git und warum sollte es verwendet werden?**

  **Was ist Git**
  
  Git ist ein Versionsverwaltungssystem, mit dem man Änderungen an einem Projekt bzw. am Quellcode speichern und nachvollziehen kann.
  
  Git speichert nicht nur die Unterschiede zwischen Dateien, sondern erstellt bei einem Commit im Wesentlichen einen Snapshot des aktuellen Zustands des Projekts.

  Git dient dazu, an verschiedene Entwicklungsständen über Branches getrennt, im Team zu bearbeiten


**Warum sollte man Git verwenden**

  Git arbeitet größtenteils lokal
    - man kann ohne Internet Änderungen machen, Commits erstellen und jederzeit zu einem früheren Zustand zurückkehren
    - man kann die verschiedenen Entwicklungsstände über Branches getrennt bearbeiten
    - man kann die Änderungen über Commits übersichtlich dokumentieren

  jeder Commit einen eindeutigen Hashwert (SHA-1), über den Git die Integrität der Versionshistorie überprüfen kann

Fazit: Git sorgt dafür, dass man beim Programmieren den Überblick über Änderungen behält und nichts versehentlich verliert.
  

**2. Grundlegende Git-Befehle**

 `git help + Befehl` = öffnet die originale Dokumentationsseite des Befehls

 `git init` = alle notwendigen Dateien werden unter .git erstellt, das Verzeichnis wird gitifiziert

 `git clone` = es wird eine lokale Kopie des Server-Repositorys erstellt

 `git status` = zeigt an welche Dateien geändert wurden und in welchem Zustand diese sich befinden

 `git diff` = listet alle Änderungen im Detail auf (neuer Inhalt grün und alter Inhalt rot)
 
 `git pull` = vergleicht wie Änderungen auf dem Server mit dem lokalen Branch

 `git add` = Dateien oder Verzeichnisbäume werden unter die Aufsicht an git gestellt

 `git commit` = unter Angabe einer passenden Nachricht werden die gestageten Änderungen hinzugefügt
 
 `git commit -a -m "Nachricht"` = es werden die Dateien unter die aufsicht von git gestellte und im Anschluss mit einer passenden Nachricht hinzugefügt

 `git push` = alle lokalen Commits werden mit dem Remote-Server synchronisiert

 `git push --force` = schiebt den lokalen Stand auf das Remoterepositpry und überschreibt die bereits vorhandene Historie auf jeden Fall

 `$ git reset -- soft HEAD` = einen falschen Commit ändern

 `git rm` = entfernt eine von git verfolgte Datei aus dem Arbeitsverzeichnis

 `git log` = zeigt die Historie  der Commits an

 `git branch` = listet die lokalen Branches auf und markiert den aktuell ausgecheckten Branch

 `git branch (branch name)` = erstellt einen neuen Branch mit dem angegebenen Branch Namen

 `git branch -d (branch name)` = löscht den angegebenen Branch

 `git checkout (branch name)` = wechselt auf den angegebenen Branch

 `git merge (branch name)` =übernimmt die Änderungen aus dem angegeben Branch in den aktuellen mit dem man gerade arbeitet.

 
**3. Branches und ihre Nutzung, Umgang mit Merge-Konflikten**

### Was sind Branches?

Ein **Branch** (Zweig) in Git ist eine unabhängige Entwicklungslinie innerhalb eines Repositories.  
Branches ermöglichen es, **parallel an verschiedenen Features, Bugfixes oder Experimenten** zu arbeiten, ohne den Hauptcode (z. B. den `main`- oder `master`-Branch) zu beeinträchtigen.

### Vorteile von Branches

- Unabhängige Entwicklung neuer Funktionen
- Sicheres Testen ohne Risiko für den Hauptzweig
- Einfaches Zusammenführen (Merge) nach Abschluss der Arbeit
- Verbesserte Zusammenarbeit im Team

### Branches erstellen und verwalten

#### Neuen Branch erstellen

```bash
git branch feature-login
```

#### In den Branch wechseln

```bash
git checkout feature-login
```

#### Alle Branches anzeigen

```bash
git branch
```

#### Branch löschen

```bash
git branch -d feature-login
```

#### Branch umbenennen

```bash
git branch -m alter-name neuer-name
```

---

### Arbeiten mit Branches

Typischer Workflow:

1. Neuen Branch erstellen (z. B. für ein neues Feature)
2. Änderungen vornehmen und committen
3. Branch in das Hauptprojekt integrieren (per Merge oder Rebase)
4. Branch löschen, wenn er nicht mehr benötigt wird

Beispiel:

```bash
git switch -c feature-neues-ui
# Code ändern, committen
git add .
git commit -m "Neues UI erstellt"
# Zurück zu main
git switch main
# Merge
git merge feature-neues-ui
```

---

### Merge-Konflikte verstehen und lösen

#### Was ist ein Merge-Konflikt?

Ein **Merge-Konflikt** entsteht, wenn Git zwei Änderungen an derselben Datei und an derselben Stelle nicht automatisch zusammenführen kann.

Beispiel:

- Entwickler A ändert Zeile 10 in `main`
- Entwickler B ändert dieselbe Zeile in `feature-branch`
- Beim Merge kann Git nicht entscheiden, welche Version „richtig“ ist

---

#### Merge-Konflikt erkennen

Beim Versuch zu mergen:

```bash
git merge feature-branch
```

Git meldet:

```
CONFLICT (content): Merge conflict in datei.txt
```

Die betroffene Datei enthält Konfliktmarker:

```plaintext
Version aus feature-branch
```

---

#### Merge-Konflikt lösen

1. Datei öffnen
2. Konfliktstellen manuell bearbeiten
    - Entscheiden, welche Version behalten oder kombinieren
3. Konfliktmarker (`<<<<<<<`, `=======`, `>>>>>>>`) entfernen
4. Änderungen als gelöst markieren:
   ```bash
   git add datei.txt
   ```
5. Merge abschließen:
   ```bash
   git commit
   ```

---

### Tipps zum Vermeiden von Konflikten

- Regelmäßig **pullen** (z. B. `git pull origin main`), um Änderungen frühzeitig zu integrieren
- Kleine, häufige Commits statt großer auf einmal
- Kommunikation im Team über geänderte Dateien
- **Rebase** statt Merge für lineare Historien (wenn sinnvoll):
  ```bash
  git rebase main
  ```

---

### Fazit

Branches sind das Herzstück von Git-Workflows und ermöglichen parallele, saubere Entwicklung.  
Merge-Konflikte sind ein natürlicher Teil dieser Arbeit – mit etwas Sorgfalt und Routine lassen sie sich jedoch leicht erkennen und lösen.

## 4. Git mit IntelliJ/PyCharm benutzen: Local Repository und Remote Repository

Nachdem die grundlegenden Git-Befehle und der Umgang mit Branches erklärt wurden, wird in diesem Abschnitt die Nutzung von Git in Verbindung mit den Entwicklungsumgebungen IntelliJ und PyCharm behandelt.
Dies ermöglicht eine effiziente Verwaltung lokaler und entfernter Repositories direkt aus der IDE.

## Voraussetzungen

Bevor du beginnst, stelle sicher, dass du Folgendes installiert und eingerichtet hast:

- **Git**: Lade Git von [git-scm.com](https://git-scm.com/) herunter und installiere es.
- **IntelliJ** oder **PyCharm**: Wähle die für deine Programmiersprache geeignete IDE von [JetBrains](https://www.jetbrains.com/) und installiere sie.
- **Git-Integration**: Die JetBrains IDEs haben in der Regel Git bereits integriert. Überprüfe in den Einstellungen unter "Version Control > Git", ob der Pfad zur Git-ausführbaren Datei korrekt ist.

## Klonen oder `git init`?

> **Hinweis:** `git init` brauchst du nur, wenn du ein komplett neues, lokales Projekt startest.
> Gibt es das Repository bereits auf GitHub, GitLab oder Bitbucket (wie z. B. dieses Projekt), dann klonst du es stattdessen.
> Ein geklontes Repository ist schon fertig eingerichtet und mit dem Remote `origin` verbunden, `git init` ist dann nicht nötig.
>
> - In der IDE: "File > New > Project from Version Control", URL einfügen und auf "Clone" klicken.
> - Im Terminal: `git clone <URL>`
>
> Die folgenden Abschnitte "Lokales Repository erstellen" und "Remote Repository verbinden" beschreiben den Weg für ein neues Projekt.

## Lokales Repository erstellen

1. **Neues Projekt anlegen**:

- Öffne IntelliJ IDEA oder PyCharm.
- Wähle "File > New > Project" aus dem Hauptmenü.
- Konfiguriere dein Projekt nach Bedarf und klicke auf "Create".

![Beispiel Neues Projekt](/img/IJ-Neues-Projekt-small.webp)

_Beispiel: Neues Projekt anlegen_

2. **Git-Initialisierung**:

- Navigiere zu "VCS > Enable Version Control Integration" im Menü.
- Wähle "Git" aus dem Dropdown-Menü und bestätige.
- Alternativ kannst du im Terminal-Fenster der IDE `git init` eingeben.

![Beispiel Git](/img/IJ-VCS-Git-s.webp)

_Beispiel: Menüpunkt "VCS > Enable Version Control Integration > Git"_

3. **Erste Dateien hinzufügen und committen**:

- Erstelle oder bearbeite Dateien in deinem Projekt.
- Du hast nun zwei Möglichkeiten zum Committen:

  a) Über die IDE:

    - Öffne das "Commit"-Fenster (Alt + 0 oder ⌘0).
    - Wähle die zu committenden Dateien aus.
    - Gib eine aussagekräftige Commit-Nachricht ein.
    - Klicke auf "Commit" oder "Commit and Push".

  b) Über das Terminal in der IDE:

    - Öffne das Terminal-Fenster in der IDE.
    - Füge Dateien zum Staging-Bereich hinzu mit:
        - `git add .` (für alle Änderungen) oder
        - `git add <dateiname>` (für spezifische Dateien).
    - Führe den Commit aus mit:
        - `git commit -m "Deine aussagekräftige Commit-Nachricht"`.
    - Optional: Pushe die Änderungen mit:
        - `git push -u origin main` (beim ersten Push).
        - `git push` (wenn bereits gepusht wurde).

![Beispiel Commit](/img/IJ-commit.webp)

_Beispiel: Commit-Möglichkeit via GUI_

## Remote Repository verbinden

1. **Remote Repository erstellen**:

- Gehe zu GitHub, GitLab oder Bitbucket und erstelle ein neues Repository.
- Kopiere die URL des Remote-Repositories.

![Beispiel Github Copy Remote](/img/GH-copy-URL.webp)

_Beispiel: Copy GitHub URL_

2. **Remote Repository zum lokalen Projekt hinzufügen**:

- In IntelliJ/PyCharm, gehe zu "Git > Manage Remotes".
- Klicke auf das "+"-Symbol, um ein neues Remote hinzuzufügen.
- Gib "origin" als Name ein und füge die kopierte URL ein.
- Bestätige mit "OK".

![Beispiel IntelliJ Manage Remotes](/img/IJ-manage-remotes.webp)

_Beispiel: Remote Repository hinzufügen_

3. **Änderungen pushen**:

- Wähle "Git > Push" aus dem Menü oder nutze das "Commit"-Fenster.
- Wähle den Branch aus, den du pushen möchtest (meist "master" oder "main").
- Klicke auf "Push", um deine Änderungen zum Remote-Repository zu senden.

## Abschlussbemerkung zu Git mit IntelliJ/PyCharm

Die Integration von Git in IntelliJ/PyCharm bietet eine verknüpfte Versionskontrolle direkt in deiner Entwicklungsumgebung.
Diese Tools vereinfachen den Git-Workflow, indem sie visuelle Unterstützung für Commits, Merges und die Verwaltung von Branches bieten.
Durch die Nutzung der integrierten Funktionen von IntelliJ/PyCharm kannst du effizienter arbeiten und dich besser auf den Code konzentrieren, während du gleichzeitig von den Vorteilen der Versionskontrolle profitierst.


## 5. Nützliche Git-Tools und Plattformen

Git muss nicht ausschließlich im Terminal stattfinden. Es gibt Programme und Plattformen, die uns beim Arbeiten, Zusammenarbeiten und Lernen unterstützen.

**Wichtig:** Git verwaltet unsere Dateiversionen. GitHub und ähnliche Plattformen stellen gemeinsame Repositories online bereit. Andere Werkzeuge erleichtern die Bedienung von Git.

### Werkzeuge aus dem Modulskript

| Werkzeug | Was ist das? | Wobei hilft es uns? |
| --- | --- | --- |
| GitHub | Eine Onlineplattform für Git-Repositories | Beiträge teilen, Änderungen besprechen und Pull Requests prüfen |
| GitLab | Eine alternative Plattform für Git-Repositories | Projekte gemeinsam verwalten und Entwicklungsabläufe organisieren |
| Bitbucket | Eine Repository-Plattform von Atlassian | Git-Projekte online bereitstellen und gemeinsam bearbeiten |
| Git Bash | Eine Shell für Windows | Git-Befehle wie `git status` oder `git push` ausführen |
| gitk | Ein grafischer Betrachter der Git-Historie | Commits und Entwicklungszweige übersichtlich ansehen |
| Grafische Git-Clients | Programme mit Fenstern und Schaltflächen für Git | Git-Arbeitsschritte über eine Oberfläche erledigen |
| Eclipse-Plugins | Git-Erweiterungen für Eclipse | Git direkt in der Entwicklungsumgebung verwenden |

Das Skript nennt außerdem Lernhilfen wie **Pro Git**, **Git Magic**, das **Git Cheat Sheet von Tower** und die **Git Visual Reference**.

**Tipp:** Das Skript nennt mehrere Hilfen zum Nachschlagen,
darunter `git help`, die Git-Dokumentation und ein Git Cheat Sheet.
Bei unbekannten Befehlen lohnt sich ein Blick hinein.

**Aktualitätshinweis:** GitHub bietet auch private Repositories. Bitbucket Cloud unterstützt Mercurial seit 2020 nicht mehr. Die entsprechenden Vergleiche im Skript beschreiben ältere Stände.

### Über den Tellerrand hinaus

| Werkzeug | Wofür ist es hilfreich? |
| --- | --- |
| GitHub Desktop | Änderungen prüfen, Branches verwalten, Commits erstellen und hochladen – über eine grafische Oberfläche. |
| Visual Studio Code | Dateien bearbeiten und die integrierten Git-Funktionen direkt im Editor nutzen. |
| GitLens | Die Git-Historie und Zusammenhänge von Änderungen in VS Code genauer nachvollziehen. |
| Sourcetree | Eine weitere grafische Git-Oberfläche für Windows und macOS. |
| GitHub CLI | GitHub-Funktionen wie Pull Requests über das Terminal bedienen. |
| Learn Git Branching | Branches und Git-Befehle interaktiv mit grafischen Übungen verstehen. |

**Ein Beispiel aus unserer Teamarbeit:**
Ich schreibe meinen Abschnitt in VS Code. Anschließend prüfe ich die Änderungen in GitHub Desktop, erstelle einen Commit und lade meinen Branch hoch. Auf GitHub kann das Team meinen Pull Request lesen und Feedback geben.

Dabei muss ich die Git-Schritte nicht in mehreren Programmen wiederholen: Ich wähle die Oberfläche, mit der ich gut zurechtkomme.

### Ein kurzer Ausblick: Automatisierung

Das Skript behandelt GitHub Actions und erwähnt auch die integrierten CI/CD-Funktionen von GitLab sowie weitere Automatisierungswerkzeuge (z. B. Jenkins). Diese können beispielsweise nach einem Push automatisch Tests ausführen. Für unser Handout ist das noch nicht nötig, bei einem späteren Softwareprojekt helfen solche Prüfungen, Fehler früh zu erkennen.

### Quellen und weiterführende Links

- Modulskript „Moderne Softwareentwicklung", Kapitel 1 und 2.
- [GitHub Desktop](https://docs.github.com/en/desktop)
- [Git in VS Code](https://code.visualstudio.com/docs/sourcecontrol/overview)
- [GitLens](https://help.gitkraken.com/gitlens/gitlens-features/)
- [Sourcetree](https://www.sourcetreeapp.com/)
- [GitHub CLI](https://cli.github.com/manual/)
- [Learn Git Branching](https://learngitbranching.js.org/?locale=de_DE)

## Zusammenarbeit – Übung 1

Die Aufgaben zur Erstellung des Git-Handouts wurden wie folgt aufgeteilt:

| Teammitglied | Übernommene Aufgaben |
|---|---|
| Maria Lorenz | Repository erstellt und Teammitglieder eingeladen; Abschnitte „Was ist Git und warum sollte es verwendet werden?“ und „Grundlegende Git-Befehle“ erstellt. |
| Jesse Khala | Abschnitte „Branches und ihre Nutzung, Umgang mit Merge-Konflikten“ und „Git mit IntelliJ/PyCharm: lokales und Remote-Repository“ erstellt. |
| Cassandra Ludwig | Abschnitt „Nützliche Git-Tools und Plattformen“ erstellt. |

Alle Teammitglieder haben die Beiträge der anderen überprüft und gegenseitig Feedback gegeben.

