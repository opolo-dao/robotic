package lv.challenge.services.common;

import lv.challenge.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

@Component
public class HTMLStoreService {
    @Autowired
    ApplicationService appService;

    public void writeToHTMLFileStore(String data, String filename, Locale locale) {

        StringBuilder file = new StringBuilder();
        file.append(appService.SAVE_PATH);
        file.append(File.separator);
        file.append("html");
        file.append(File.separator);
        file.append(filename);
        file.append("_");
        file.append(locale.getLanguage());
        file.append(".html");
        try (FileOutputStream fos = new FileOutputStream(file.toString())) {
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            System.out.println("Cant open html file to rewrite");
        } catch (IOException e) {
            System.out.println("Can't write to html file");
        }
    }
}
