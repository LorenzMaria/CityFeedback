package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

class BeschwerdeBegonnenTest {

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
