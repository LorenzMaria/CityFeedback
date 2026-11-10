package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import beschwerdemanagement.domain.valueObjects.Kategorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeschwerdeEinreichenTest {

@Test
@DisplayName("Eine Beschwerde wird eingereicht")
void beschwerdeErstellen() {

    // Arrange
    String beschreibung = "Straßenbeleuchtung funktioniert nicht";
    Kategorie kategorie = Kategorie.INFRASTRUKTUR;

    // Regex-Validierung
    String regex = "^(?=.*\\S).{10,}$";

    // Act
    Beschwerde beschwerde = Beschwerde.einreichen(
        beschreibung,
        kategorie
    );

    // Assert
    assertThat(beschreibung).matches(regex);
    assertThat(beschwerde).isNotNull();
    assertThat(beschwerde.getStatus()).isEqualTo(BeschwerdeStatus.EINGEREICHT);
}
}
