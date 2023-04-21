package cherhy.jung.slack.web;

import cherhy.jung.slack.exception.RequestNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ReadOnlyBufferException;

@RestController
public class SlackController {

    @GetMapping(value = "/error/v1")
    public String ErrorSlackClient1() {
        throw new ReadOnlyBufferException();
    }
    @GetMapping(value = "/error/v2")
    public String ErrorSlackClient2() {
        throw new RequestNotFoundException("뀨잉");
    }
}
