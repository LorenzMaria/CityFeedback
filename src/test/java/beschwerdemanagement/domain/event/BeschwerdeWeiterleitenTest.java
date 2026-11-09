package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;

public class BeschwerdeWeiterleitenTest {
    
@Test
@DisplayName("Eine angenommene Beschwerde wird weitergeleitet")
void sollteBeschwerdeAnFachamtWeiterleiten() {

    // Arrange
    // Arrange
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();

        Fachamt fachamt = zustaendigesFachamt();

        // Act
        beschwerde.weiterleiten(fachamt);

        // Assert
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.WEITERGELEITET);
    }


@Test
@DisplayName("Eine nicht angenommene Beschwerde darf nicht weitergeleitet werden")
    void sollteNichtAngenommeneBeschwerdeNichtWeiterleiten() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();

        // Act & Assert
        assertThatThrownBy(() ->
                beschwerde.weiterleiten(zustaendigesFachamt())
        ).isInstanceOf(IllegalStateException.class);

        assertThat(beschwerde.getStatus())
                .isEqualTo(BeschwerdeStatus.EINGEREICHT);
    }

@Test
@DisplayName("Eine Beschwerde darf nur an ein zuständiges Fachamt weitergeleitet werden")
void sollteBeschwerdeNurAnZustaendigesFachamtWeiterleiten() {

    // Arrange
    Beschwerde beschwerde = Beschwerde.einreichen(
        Kategorie.INFRASTRUKTUR
    );

    beschwerde.annehmen();

    Fachamt fachamt = new Fachamt(
        Kategorie.GESUNDHEIT
    );

    // Act & Assert
    assertThatThrownBy(() -> beschwerde.weiterleiten(fachamt))
        .isInstanceOf(IllegalArgumentException.class);

    assertThat(beschwerde.getStatus())
        .isEqualTo(BeschwerdeStatus.ANGENOMMEN);
}

@Test
@DisplayName("Die Bearbeitung darf nicht vor der Weiterleitung beginnen")
void sollteBearbeitungVorWeiterleitungVerhindern() {

    // Arrange
    Beschwerde beschwerde = Beschwerde.einreichen(
        Kategorie.INFRASTRUKTUR
    );

    beschwerde.annehmen();

    // Act & Assert
    assertThatThrownBy(() -> beschwerde.bearbeitungBeginnen())
        .isInstanceOf(UngueltigerBeschwerdeStatusException.class);

    assertThat(beschwerde.getStatus())
        .isEqualTo(BeschwerdeStatus.ANGENOMMEN);
}
}
