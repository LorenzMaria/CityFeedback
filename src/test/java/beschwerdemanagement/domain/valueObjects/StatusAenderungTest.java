package beschwerdemanagement.domain.valueObjects;

import beschwerdemanagement.domain.model.Beschwerde;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StatusAenderungTest {
    @Test
    @DisplayName("Eine wiederholte Statusänderung ist nicht erlaubt")
    void sollteWiederholteStatusaenderungVerhindern() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();
        beschwerde.annehmen();

        var historieVorher =
                beschwerde.getStatusHistorie().size();


        // Act & Assert
        assertThatThrownBy(() ->
                beschwerde.annehmen()
        ).isInstanceOf(IllegalStateException.class);

        // Die Historie darf keinen doppelten Eintrag enthalten.
        assertThat(beschwerde.getStatusHistorie())
                .hasSize(historieVorher);

        assertThat(beschwerde.getStatus())
                .isEqualTo(BeschwerdeStatus.ANGENOMMEN);
    }

        @Test
    @DisplayName("Eine Statusänderung wird in der Historie dokumentiert")
    void sollteStatushistorieAktualisieren() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();

        // Act
        beschwerde.annehmen();

        // Assert
        assertThat(beschwerde.getStatusHistorie())
                .extracting(StatusHistorie::status)
                .containsExactly(
                        BeschwerdeStatus.EINGEREICHT,
                        BeschwerdeStatus.ANGENOMMEN
                );
    }
}
