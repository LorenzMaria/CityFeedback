package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
import static org.assertj.core.api.Assertions.assertThat;

class BeschwerdeAnnehmenTest {
    
@Test
@DisplayName("Eine eingereichte Beschwerde wird angenommen")
void beschwerdeAnnehmen() {

        // Arrange
        Beschwerde beschwerde = neueBeschwerde();

        // Act
        beschwerde.annehmen();

        // Assert
        assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.ANGENOMMEN);
    }

}
