package lv.challenge.servlets.mailService;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;

@Service
@Async
public class TournamentMailService implements MailService {
    @Autowired
    Configuration freeMarkerConfig;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailTemplates mailTemplates;

    @Override
    public void sendPreparedMail(String email, MailingEventType eventType, Locale locale) {
        sendPreparedMail(email, eventType, locale, null);
    }

    @Override
    public void sendPreparedMail(String email, MailingEventType eventType, Locale locale, Map<String, Object> model) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            messageHelper.setFrom("TSI Robotikas Turnirs <RoboticTournament@tsi.lv>");
            messageHelper.setTo(email);
            messageHelper.setSubject(getSubject(eventType));
            messageHelper.setText(getPreparedTemplateText(eventType, locale, model), true);
            mailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {

        }
    }

    private String getSubject(MailingEventType eventType) {
        switch (eventType) {
            case REGISTRATION:
                return "Welcome to tournament";
            case ROBOT_ACCEPTED:
                return "Robot accepted";
            default:
                return "TSI Robotikas Tournament";
        }
    }

    private String getPreparedTemplateText(MailingEventType eventType, Locale locale, Map<String, Object> model) {
        StringBuilder preparedText = new StringBuilder();
        try {
            preparedText.append(FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfig.getTemplate(mailTemplates.getEventTemplate(eventType), locale), model));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return preparedText.toString();
    }

}
