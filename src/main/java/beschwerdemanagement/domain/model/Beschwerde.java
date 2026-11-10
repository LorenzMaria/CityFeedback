package beschwerdemanagement.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import beschwerdemanagement.domain.exception.UngueltigerBeschwerdeStatusException;
import beschwerdemanagement.domain.valueObjects.BeschwerdeStatus;
import beschwerdemanagement.domain.valueObjects.Kategorie;
import beschwerdemanagement.domain.valueObjects.StatusHistorie;

public class Beschwerde {

    private final String beschreibung;
    private final Kategorie kategorie;
    private BeschwerdeStatus status;
    private final List<StatusHistorie> statusHistorie;

    private Beschwerde(String beschreibung, Kategorie kategorie) {
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        this.statusHistorie = new ArrayList<StatusHistorie>();
        this.status = BeschwerdeStatus.EINGEREICHT;
        this.statusHistorie.add(new StatusHistorie(BeschwerdeStatus.EINGEREICHT));
    }

    public static Beschwerde einreichen(String beschreibung, Kategorie kategorie) {
        return new Beschwerde(beschreibung, kategorie);
    }

    public void annehmen() {
        wechsleStatus(BeschwerdeStatus.EINGEREICHT, BeschwerdeStatus.ANGENOMMEN);
    }

    public void ablehnen() {
        wechsleStatus(BeschwerdeStatus.EINGEREICHT, BeschwerdeStatus.ABGELEHNT);
    }

    public void weiterleiten(Fachamt fachamt) {
        pruefeStatus(BeschwerdeStatus.ANGENOMMEN, BeschwerdeStatus.WEITERGELEITET);
        if (fachamt.getKategorie() != kategorie) {
            throw new IllegalArgumentException(
                "Fachamt " + fachamt.getName() + " ist nicht zustaendig fuer Kategorie " + kategorie);
        }
        wechsleStatus(BeschwerdeStatus.ANGENOMMEN, BeschwerdeStatus.WEITERGELEITET);
    }

    public void bearbeitungBeginnen() {
        wechsleStatus(BeschwerdeStatus.WEITERGELEITET, BeschwerdeStatus.IN_BEARBEITUNG);
    }

    public void abschliessen() {
        wechsleStatus(BeschwerdeStatus.IN_BEARBEITUNG, BeschwerdeStatus.ERLEDIGT);
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

    private void wechsleStatus(BeschwerdeStatus erwartet, BeschwerdeStatus neu) {
        pruefeStatus(erwartet, neu);
        status = neu;
        statusHistorie.add(new StatusHistorie(neu));
    }

    private void pruefeStatus(BeschwerdeStatus erwartet, BeschwerdeStatus neu) {
        if (status != erwartet) {
            throw new UngueltigerBeschwerdeStatusException(
                "Uebergang nach " + neu + " ist aus Status " + status + " nicht erlaubt");
        }
    }
}
