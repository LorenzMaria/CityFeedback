# CityFeedback
Bürgerbeteiligungs- und Beschwerdesystem

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

 `git init` = alle notwendigen Datien werden unter .git erstellt, das Verzeichnis wird gitifiziert

 `git clone` = es wird eine lokale Kopie des Server-Repositorys erstellt

 `git status` = zeigt an welche Dateien geändert wurden und in welchem Zustand diese sich befinden

 `git diff` = listet alle Änderungen im Detail auf(neuer Inhalt grün und alter Inhalt rot)
 
 `git pull` = vergleicht wie. Änderungen auf dem Server mit dem lokalen Branch

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


**4. Git mit IntelliJ/Phycharm benutzen: Local Repositorz vs. Remote Repository**


**5. Nutzliche Git-Tools und Plattformen**
