package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class BeschwerdeprozessTest {
    @Test
@DisplayName("Eine Beschwerde durchläuft den vollständigen Lebenszyklus")
void vollstaendigenBeschwerdeprozessDurchlaufen() {

    // Arrange
    Beschwerde beschwerde = Beschwerde.einreichen(
        "Straßenbeleuchtung funktioniert nicht",
        Kategorie.INFRASTRUKTUR
    );

    Fachamt fachamt = new Fachamt(
        "Tiefbauamt",
        Kategorie.INFRASTRUKTUR
    );

    // Act
    beschwerde.annehmen();
    beschwerde.weiterleiten(fachamt);
    beschwerde.bearbeitungBeginnen();
    beschwerde.abschliessen();

    // Assert
    assertThat(beschwerde.getStatus())
        .isEqualTo(BeschwerdeStatus.ERLEDIGT);

    assertThat(beschwerde.getStatusHistorie())
        .extracting(StatusHistorie::getStatus)
        .containsExactly(
            BeschwerdeStatus.EINGEREICHT,
            BeschwerdeStatus.ANGENOMMEN,
            BeschwerdeStatus.WEITERGELEITET,
            BeschwerdeStatus.IN_BEARBEITUNG,
            BeschwerdeStatus.ERLEDIGT
        );
}
}
