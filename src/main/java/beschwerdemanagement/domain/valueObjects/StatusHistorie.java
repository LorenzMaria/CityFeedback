package beschwerdemanagement.domain.valueObjects;

public class StatusHistorie {

    private final BeschwerdeStatus status;

    public StatusHistorie(BeschwerdeStatus status) {
        this.status = status;
    }

    public BeschwerdeStatus getStatus() {
        return status;
    }
}
