package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static org.assertj.core.api.Assertions.assertThat;

public class BeschwerdeAbgelehntTest {
 @Test
    @DisplayName("Eine Beschwerde wird abgelehnt")
    void abgelehnteBeschwerdeNichtWeiterleiten() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();

        // Act 
        beschwerde.ablehnen ();

        // Assert
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.ABGELEHNT);
    }   
}
