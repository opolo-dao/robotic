package lv.challenge.application;

import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by Daniil on 18.07.2017.
 */

public class ApplicationService {
    Application app = new Application();
    String propertiesFileName = "/app.properties";
    public final String SAVE_PATH;
    @Autowired
    TournamentService tournamentService;
    private ArrayList<String> storeDirs = new ArrayList<>(Arrays.asList(
            "/html/sumo", "/html/linefollower", "/html/folkrace", "/html/labyrinth", "/html/matchtables",
            "/pictures",
            "/photos",
            "/temp"
    ));

    public ApplicationService() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {
            System.out.println("Error while reading property file");
            System.out.println(e.getMessage());
        }
        SAVE_PATH = prop.getProperty("SAVE_PATH");
    }

    @PostConstruct
    private void init() {
        Optional<Tournament> opt = tournamentService.getActiveTournament();
        if (opt.isPresent())
            app.tournament = opt.get();
        else app.tournament = null;
        try {
            createDirStructure();
        } catch (IOException e) {
            System.out.println("Could create directories structure");
        }
    }

    private void createDirStructure() throws IOException {
        for (String s : storeDirs) {
            Files.createDirectories(Paths.get(SAVE_PATH + s));
        }
    }

    public void setActiveTournament(Tournament tournament) {
        app.tournament = tournament;
    }

    @PreDestroy
    public void saveAppSettings() {
    }

    public Tournament getActiveTournament() {
        return app.tournament;
    }
}
