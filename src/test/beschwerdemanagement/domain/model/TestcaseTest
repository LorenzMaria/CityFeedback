public class BeschwerdeEinreichenTest {

@Test
@DisplayName("Eine Beschwerde wird mit Status EINGEREICHT erstellt")
void sollteBeschwerdeMitStatusEingereichtErstellen() {

    // Arrange
    String beschreibung = "Straßenbeleuchtung funktioniert nicht";
    Kategorie kategorie = Kategorie.INFRASTRUKTUR;

    // Act
    Beschwerde beschwerde = Beschwerde.einreichen(
        beschreibung,
        kategorie
    );

    // Assert
    assertThat(beschwerde.getStatus())
        .isEqualTo(BeschwerdeStatus.EINGEREICHT);

    assertThat(beschwerde.getStatusHistorie())
        .hasSize(1);

    assertThat(beschwerde.getStatusHistorie().get(0).getStatus())
        .isEqualTo(BeschwerdeStatus.EINGEREICHT);
}
}