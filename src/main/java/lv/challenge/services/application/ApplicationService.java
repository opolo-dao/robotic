package lv.challenge.services.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Daniil on 18.07.2017.
 */
@Component
public class ApplicationService {
    @Autowired
    Properties appProperties;

    public void save(String comment) throws IOException {
        FileOutputStream fos = new FileOutputStream(appProperties.getProperty("path"));
        appProperties.store(fos, comment);
        fos.close();
    }
}
