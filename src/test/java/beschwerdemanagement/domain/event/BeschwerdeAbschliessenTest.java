package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.zustaendigesFachamt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeschwerdeAbschliessenTest {
    
@Test
@DisplayName("Eine Beschwerde wird abgeschlossen")
    void beschwerdeAbschliessen() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();
        beschwerde.weiterleiten(zustaendigesFachamt());
        beschwerde.bearbeitungBeginnen();

        // Act
        beschwerde.abschliessen();

        // Assert
        assertThat(beschwerde.getStatus())
                .isEqualTo(BeschwerdeStatus.ERLEDIGT);
    }

    @Test 
       @DisplayName("Eine Beschwerde darf ohne Bearbeitungsbeginn nicht abgeschlossen werden")
    void beschwerdeOhneBearbeitungsbeginnNichtAbschliessen() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();
        beschwerde.weiterleiten(zustaendigesFachamt());

        // Act & Assert
        assertThatThrownBy(() ->
                beschwerde.abschliessen()
        ).isInstanceOf(IllegalStateException.class);

        assertThat(beschwerde.getStatus())
                .isEqualTo(BeschwerdeStatus.WEITERGELEITET);
    }
}
