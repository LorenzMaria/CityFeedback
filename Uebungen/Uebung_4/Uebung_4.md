Übung 4

# 1. Review der Implementierungsstrategie
Als wichtigste Domain Events unseres Projekts wurden BeschwerdeEingereicht, BeschwerdeWeitergeleitet, BeschwerdeErledigt und VorschlagEingereicht identifiziert, da sie zentrale Geschäftsprozesse der beiden Core Domains abbilden. Übernommen wurde der LLM-Vorschlag, die Bounded Contexts zusätzlich in domain, application und infrastructure zu unterteilen, um fachliche Logik und technische Implementierung klar voneinander zu trennen. Ebenfalls übernommen wurde die Empfehlung, Domain Events erst nach erfolgreicher Speicherung zu veröffentlichen, damit es keine Eingangsbestätigung für eine Beschwerde geben kann, die nicht gespeichert wurde. Die Entwicklungsreihenfolge wurde dahingehend angepasst, dass zunächst die beiden Core Domains Beschwerdemanagement und Bürgerbeteiligung und anschließend die unterstützenden Contexts Benutzerverwaltung und Benachrichtigung implementiert werden. Abgelehnt wurde die Empfehlung, dass eine einfache Eventverarbeitung mit Spring Boot für den aktuellen Projektumfang ausreichend ist da es eine geringere technische Komplexität mit sich bringt, da die Planung für dieses Projekt von der Realität ausgeht.

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