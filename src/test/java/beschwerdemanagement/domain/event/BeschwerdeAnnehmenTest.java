package beschwerdemanagement.domain.event;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

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
