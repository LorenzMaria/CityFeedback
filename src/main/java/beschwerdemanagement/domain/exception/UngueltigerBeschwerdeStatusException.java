package beschwerdemanagement.domain.exception;

public class UngueltigerBeschwerdeStatusException extends IllegalStateException {

    public UngueltigerBeschwerdeStatusException(String message) {
        super(message);
    }
}
