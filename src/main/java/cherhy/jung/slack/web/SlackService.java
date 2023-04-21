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

    private final ObjectMapper objectMapper;

    @Override
    public String sendErrorForSlack(Exception exception) {
        Slack slack = Slack.getInstance();
        WebhookResponse response = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter writer = new StringWriter();
            exception.printStackTrace(new PrintWriter(writer));

            // TODO : 주석 처리 해둔 Map으로 아래와 같이 사용 가능~!
//            Map<String, String> slackMessage = new HashMap<>();
//            slackMessage.put("text", writer.toString());
            SlackErrorMessage slackErrorMessage = new SlackErrorMessage(writer.toString());
            response = slack.send(slackAlertWebhookUrl, objectMapper.writeValueAsString(slackErrorMessage));
            return "Hello Slack Alert Sent = " + response.getCode();
        } catch (IOException e) {
            // TODO : 예외 처리
        }
        return null;
    }
}