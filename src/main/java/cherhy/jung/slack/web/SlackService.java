package cherhy.jung.slack.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.Slack;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SlackService implements SlackApi {
    
    @Value("${notification.slack.webhook.url}")
    private String slackAlertWebhookUrl;
    
    @Override
    public String sendErrorForSlack(Exception exception) {
        Slack slack = Slack.getInstance();
        WebhookResponse response = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> slackMessage = new HashMap<>();
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer));

            String emoji = "\u2620";
            String errorTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String errorPath = request.getRequestURI().toString();
            String exceptionName = exception.getClass().toString();
            String exceptionRoot = exception.getStackTrace()[0].toString();
            String message = String.format("%s [%s] - [My-Slack-Message]- [%s] - [%s] - [%s]", emoji, errorTime, errorPath, exceptionName, exceptionRoot);

            slackMessage.put("text", message);
            response = slack.send(slackAlertWebhookUrl, objectMapper.writeValueAsString(slackMessage));
            return "Hello Slack Alert Sent = " + response.getCode();
        } catch (IOException e) {
            // TODO : 예외 처리
        }
        return null;
    }
}
