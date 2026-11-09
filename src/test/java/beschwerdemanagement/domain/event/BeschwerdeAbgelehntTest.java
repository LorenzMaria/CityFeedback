package beschwerdemanagement.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BeschwerdeAbgelehntTest {
 @Test
    @DisplayName("Eine Beschwerde wird abgelehnt")
    void sollteAbgelehnteBeschwerdeNichtWeiterleiten() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();

        // Act 
        beschwerde.ablehnen ();

        // Assert
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.ABGELEHNT);
    }   
}
