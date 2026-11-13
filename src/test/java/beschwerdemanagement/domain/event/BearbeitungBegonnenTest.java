package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.zustaendigesFachamt;
import static org.assertj.core.api.Assertions.assertThat;

class BearbeitungBegonnenTest {

    @Test
    @DisplayName("Die Bearbeitung einer weitergeleiteten Beschwerde beginnt")
    void bearbeitungBeginnen() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();
        beschwerde.weiterleiten(zustaendigesFachamt());

        // Act
        beschwerde.bearbeitungBeginnen();

        // Assert
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.IN_BEARBEITUNG);
    }
}
