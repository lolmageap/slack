package cherhy.jung.slack.exception;

public class RequestNotFoundException extends GlobalException {


    public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 999;
    }
}
