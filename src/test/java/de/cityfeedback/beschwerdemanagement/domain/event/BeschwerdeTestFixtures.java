package de.cityfeedback.beschwerdemanagement.domain.event;

import de.cityfeedback.beschwerdemanagement.domain.model.Beschwerde;
import de.cityfeedback.beschwerdemanagement.domain.model.Fachamt;
import de.cityfeedback.beschwerdemanagement.domain.valueObjects.Kategorie;

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
