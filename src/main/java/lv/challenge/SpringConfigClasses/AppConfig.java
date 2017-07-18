package lv.challenge.SpringConfigClasses;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Daniil on 18.07.2017.
 */
@Configuration
public class AppConfig {
    @Bean("appProperties")
    Properties appProperties() throws IOException {
        File f = new File(getClass().getResource("/app.properties").getFile());
        Properties prop = new Properties();
        InputStream io = new FileInputStream(f);
        prop.load(io);
        if (!prop.containsKey("path")) {
            prop.setProperty("path", f.getAbsolutePath());
        } else prop.replace("path", f.getAbsolutePath());
        return prop;
    }
}
