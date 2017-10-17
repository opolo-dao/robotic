package lv.challenge.servlets.mailService;

import java.util.Locale;
import java.util.Map;

public interface MailService {
    void sendPreparedMail(String email, MailingEventType eventType, Locale locale, Map<String, Object> model);

    void sendPreparedMail(String email, MailingEventType eventType, Locale locale);
}
