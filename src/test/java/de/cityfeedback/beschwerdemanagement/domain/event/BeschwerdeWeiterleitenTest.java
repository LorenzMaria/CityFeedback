package de.cityfeedback.beschwerdemanagement.domain.event;

import de.cityfeedback.beschwerdemanagement.domain.exception.UngueltigerBeschwerdeStatusException;
import de.cityfeedback.beschwerdemanagement.domain.model.Beschwerde;
import de.cityfeedback.beschwerdemanagement.domain.model.Fachamt;
import de.cityfeedback.beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import de.cityfeedback.beschwerdemanagement.domain.valueObjects.Kategorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.cityfeedback.beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static de.cityfeedback.beschwerdemanagement.domain.event.BeschwerdeTestFixtures.zustaendigesFachamt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

// Unit-Test:
// Die Geschäftsregeln liegen im Aggregat Beschwerde, deshalb reicht JUnit.
public class BeschwerdeWeiterleitenTest {

// Happy Path: erlaubter Statuswechsel ANGENOMMEN -> WEITERGELEITET
@Test
@DisplayName("Eine angenommene Beschwerde wird weitergeleitet")
void sollteBeschwerdeAnFachamtWeiterleiten() {

        // Arrange: Fixture erzeugt die Beschwerde über die Factory-Methode einreichen(),
        // Status ist danach EINGEREICHT, mit annehmen() wird er ANGENOMMEN
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();

        Fachamt fachamt = zustaendigesFachamt();

        // Act
        beschwerde.weiterleiten(fachamt);

        // Assert: Statuswechsel ANGENOMMEN -> WEITERGELEITET
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.WEITERGELEITET);
    }


// Negativtest (Invariante): nur angenommene Beschwerden duerfen weitergeleitet werden
@Test
@DisplayName("Eine nicht angenommene Beschwerde darf nicht weitergeleitet werden")
    void sollteNichtAngenommeneBeschwerdeNichtWeiterleiten() {

        // Arrange: Beschwerde bleibt im Status EINGEREICHT
        Beschwerde beschwerde = neueBeschwerde();

        // Act & Assert: Invariante "nur angenommene Beschwerden weiterleiten"
        assertThatThrownBy(() ->
                beschwerde.weiterleiten(zustaendigesFachamt())
        ).isInstanceOf(IllegalStateException.class);

        // Der Status darf sich bei einer abgelehnten Operation nicht ändern
        assertThat(beschwerde.getStatus())
                .isEqualTo(BeschwerdeStatus.EINGEREICHT);
    }

// Negativtest (Invariante): nur an ein Fachamt mit passender Kategorie weiterleiten
@Test
@DisplayName("Eine Beschwerde darf nur an ein zuständiges Fachamt weitergeleitet werden")
void sollteBeschwerdeNurAnZustaendigesFachamtWeiterleiten() {

        // Arrange
        Beschwerde beschwerde = Beschwerde.einreichen(
            "Straßenbeleuchtung funktioniert nicht",
            Kategorie.INFRASTRUKTUR
        );

        beschwerde.annehmen();

        Fachamt fachamt = new Fachamt(
            "Gesundheitsamt",
            Kategorie.GESUNDHEIT
        );

        // Act & Assert: Kategorie der Beschwerde (INFRASTRUKTUR) passt nicht zum Fachamt (GESUNDHEIT)
        assertThatThrownBy(() -> beschwerde.weiterleiten(fachamt))
            .isInstanceOf(IllegalArgumentException.class);

        assertThat(beschwerde.getStatus())
            .isEqualTo(BeschwerdeStatus.ANGENOMMEN);
}

// Negativtest (Reihenfolge): Bearbeitung erst nach der Weiterleitung
@Test
@DisplayName("Die Bearbeitung darf nicht vor der Weiterleitung beginnen")
void bearbeitungVorWeiterleitungVerhindern() {

        // Arrange
        Beschwerde beschwerde = Beschwerde.einreichen(
            "Straßenbeleuchtung funktioniert nicht",
            Kategorie.INFRASTRUKTUR
        );

        beschwerde.annehmen();

        // Act & Assert: Reihenfolge aus Prozess 2 (angenommen -> weitergeleitet -> in Bearbeitung)
        assertThatThrownBy(() -> beschwerde.bearbeitungBeginnen())
            .isInstanceOf(UngueltigerBeschwerdeStatusException.class);

        assertThat(beschwerde.getStatus())
            .isEqualTo(BeschwerdeStatus.ANGENOMMEN);
    }
}
