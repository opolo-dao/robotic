package lv.challenge.servlets.mailService;

import lv.challenge.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class MailTemplates {
    @Autowired
    ApplicationService appService;
    private Properties mailingEventProperties = new Properties();

    @PostConstruct
    protected void initProperties() {
        Path propertiesPath = Paths.get(appService.SAVE_PATH + File.separator + "WEB-INF" + File.separator + "mail.properties");
        try (BufferedReader reader = Files.newBufferedReader(propertiesPath)) {
            mailingEventProperties.load(reader);
        } catch (IOException e) {
            try {
                Files.createFile(propertiesPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String getMailTemplate(String templateName) {
        Path file = getMailTemplatePath(templateName);
        List<String> template = new ArrayList<>();
        try {
            template = Files.readAllLines(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Problem with template read";
        }
        return String.join("", template);
    }

    public String updateMailTemplate(String templateName, String text) {
        Path file = getMailTemplatePath(templateName);
        try {
            Files.write(file, text.getBytes());
        } catch (IOException e) {
            return "Error while writing mail template";
        }
        return "Mail template has been refreshed";
    }

    public String deleteMailTemplate(String templateName) {
        Path file = getMailTemplatePath(templateName);
        try {
            Files.delete(file);
        } catch (IOException e) {
            return "Can't delete this template";
        }
        return "Template successfully deleted";
    }

    private Path getMailTemplatePath(String templateName) {
        return Paths.get(appService.SAVE_PATH + File.separator
                + "WEB-INF" + File.separator
                + "mail_templates" + File.separator
                + templateName + ".ftlh");
    }

    public List<String> getAllExistMailTemplates() {
        Path mailTemplateFolder = Paths.get(appService.SAVE_PATH + File.separator
                + "WEB-INF" + File.separator
                + "mail_templates");
        return Arrays.stream(mailTemplateFolder.toFile().list()).map(s -> s.substring(0, s.lastIndexOf('.'))).collect(Collectors.toList());
    }

    public String setMailingEventTemplate(String templateName, MailingEventType eventType) {
        if (templateName.contains("_")) {
            templateName = templateName.substring(0, templateName.lastIndexOf('_'));
        }
        mailingEventProperties.put(eventType.toString(), templateName + ".ftlh");
        return saveProperties();
    }

    private String saveProperties() {
        Path propertiesPath = Paths.get(appService.SAVE_PATH + File.separator + "WEB-INF" + File.separator + "mail.properties");
        try (FileOutputStream fileOutputStream = new FileOutputStream(propertiesPath.toFile())) {
            mailingEventProperties.store(fileOutputStream, "Properties of mailing events");
            return "Properties stored";
        } catch (IOException e) {
            return "Error while trying save properties";
        }
    }

    public String getEventTemplate(MailingEventType eventType) {
        return (String) mailingEventProperties.get(eventType.toString());
    }
}
