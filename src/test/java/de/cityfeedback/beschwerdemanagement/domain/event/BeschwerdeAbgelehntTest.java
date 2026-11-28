package de.cityfeedback.beschwerdemanagement.domain.event;

import de.cityfeedback.beschwerdemanagement.domain.model.Beschwerde;
import de.cityfeedback.beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static de.cityfeedback.beschwerdemanagement.domain.event.BeschwerdeTestFixtures.neueBeschwerde;
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
