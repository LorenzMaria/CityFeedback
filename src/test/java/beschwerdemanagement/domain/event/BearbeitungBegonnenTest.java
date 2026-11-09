package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class BeschwerdeBegonnenTest {

    @Test
    @DisplayName("HP-05: Die Bearbeitung einer weitergeleiteten Beschwerde beginnt")
    void sollteBearbeitungBeginnen() {

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
