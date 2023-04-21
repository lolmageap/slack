package cherhy.jung.slack.exception;
import cherhy.jung.slack.web.SlackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdviser {

    private final SlackApi slackApi;

    @ExceptionHandler(Exception.class)
    public void SlackErrorMessage(Exception e){
        slackApi.sendErrorForSlack(e);
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> RequestNotFoundException(RequestNotFoundException e) {
        int statusCode = e.getStatusCode();
        return ResponseEntity.status(statusCode).body(e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<String> GlobalException(GlobalException e) {
        int statusCode = e.getStatusCode();
        return ResponseEntity.status(statusCode).body(e.getMessage());
    }

}
