package beschwerdemanagement.domain.valueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UebergangsregelnTest {

    private enum Status {
        OFFEN, IN_BEARBEITUNG, ABGESCHLOSSEN
    }

    @Test
    void erlaubterUebergangGibtTrueZurueck() {
        Uebergangsregeln<Status> regeln =
                Uebergangsregeln.fuer(Status.class)
                        .erlaube(Status.OFFEN, Status.IN_BEARBEITUNG);

        assertTrue(regeln.istErlaubt(
                Status.OFFEN, Status.IN_BEARBEITUNG));
    }

    @Test
    void nichtErlaubterUebergangGibtFalseZurueck() {
        Uebergangsregeln<Status> regeln =
                Uebergangsregeln.fuer(Status.class)
                        .erlaube(Status.OFFEN, Status.IN_BEARBEITUNG);

        assertFalse(regeln.istErlaubt(
                Status.OFFEN, Status.ABGESCHLOSSEN));
    }

    @Test
    void unbekannterAusgangszustandGibtFalseZurueck() {
        Uebergangsregeln<Status> regeln =
                Uebergangsregeln.fuer(Status.class)
                        .erlaube(Status.OFFEN, Status.IN_BEARBEITUNG);

        assertFalse(regeln.istErlaubt(
                Status.ABGESCHLOSSEN, Status.OFFEN));
    }
}