package beschwerdemanagement.domain.event;

import beschwerdemanagement.domain.model.Beschwerde;
import beschwerdemanagement.domain.model.Fachamt;
import beschwerdemanagement.domain.valueObjects.Kategorie;

public final class BeschwerdeTestFixtures {

    private BeschwerdeTestFixtures() {
    }

    public static Beschwerde neueBeschwerde() {
        return Beschwerde.einreichen("Straßenbeleuchtung funktioniert nicht", Kategorie.INFRASTRUKTUR);
    }

    public static Fachamt zustaendigesFachamt() {
        return new Fachamt("Tiefbauamt", Kategorie.INFRASTRUKTUR);
    }
}
