package beschwerdemanagement.domain.model;

import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.ABGELEHNT;
import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.ANGENOMMEN;
import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.EINGEREICHT;
import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.ERLEDIGT;
import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.IN_BEARBEITUNG;
import static beschwerdemanagement.domain.valueObjects.BeschwerdeStatus.WEITERGELEITET;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import beschwerdemanagement.domain.exception.UngueltigerBeschwerdeStatusException;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import beschwerdemanagement.domain.valueObjects.Kategorie;
import beschwerdemanagement.domain.valueObjects.StatusHistorie;
import beschwerdemanagement.domain.valueObjects.Uebergangsregeln;

public class Beschwerde {

    private static final Uebergangsregeln<BeschwerdeStatus> REGELN =
        Uebergangsregeln.fuer(BeschwerdeStatus.class)
            .erlaube(EINGEREICHT, ANGENOMMEN, ABGELEHNT)
            .erlaube(ANGENOMMEN, WEITERGELEITET)
            .erlaube(WEITERGELEITET, IN_BEARBEITUNG)
            .erlaube(IN_BEARBEITUNG, ERLEDIGT);

    private final String beschreibung;
    private final Kategorie kategorie;
    private BeschwerdeStatus status;
    private final List<StatusHistorie> statusHistorie;

    private Beschwerde(String beschreibung, Kategorie kategorie) {
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.statusHistorie = new ArrayList<>();
        this.status = EINGEREICHT;
        this.statusHistorie.add(new StatusHistorie(EINGEREICHT));
    }

    public static Beschwerde einreichen(String beschreibung, Kategorie kategorie) {
        return new Beschwerde(beschreibung, kategorie);
    }

    public void annehmen() {
        wechsleStatus(ANGENOMMEN);
    }

    public void ablehnen() {
        wechsleStatus(ABGELEHNT);
    }

    public void weiterleiten(Fachamt fachamt) {
        pruefeUebergang(WEITERGELEITET);
        if (fachamt.kategorie() != kategorie) {
            throw new IllegalArgumentException(
                "Fachamt " + fachamt.name() + " ist nicht zustaendig fuer Kategorie " + kategorie);
        }
        wechsleStatus(WEITERGELEITET);
    }

    public void bearbeitungBeginnen() {
        wechsleStatus(IN_BEARBEITUNG);
    }

    public void abschliessen() {
        wechsleStatus(ERLEDIGT);
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Kategorie getKategorie() {
        return kategorie;
    }

    public BeschwerdeStatus getStatus() {
        return status;
    }

    public List<StatusHistorie> getStatusHistorie() {
        return Collections.unmodifiableList(statusHistorie);
    }

    private void wechsleStatus(BeschwerdeStatus neu) {
        pruefeUebergang(neu);
        status = neu;
        statusHistorie.add(new StatusHistorie(neu));
    }

    private void pruefeUebergang(BeschwerdeStatus neu) {
        if (!REGELN.istErlaubt(status, neu)) {
            throw new UngueltigerBeschwerdeStatusException(
                "Uebergang nach " + neu + " ist aus Status " + status + " nicht erlaubt");
        }
    }
}
