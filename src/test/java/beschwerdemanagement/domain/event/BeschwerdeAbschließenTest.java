package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

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
