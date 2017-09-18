package lv.challenge.application;

import lv.challenge.domain.tournament.Tournament;
import lv.challenge.services.tornament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Daniil on 18.07.2017.
 */

public class ApplicationService {
    Application app = new Application();
    public @Value("${SAVE_PATH}")
    String SAVE_PATH;
    @Autowired
    TournamentService tournamentService;
    private ArrayList<String> storeDirs = new ArrayList<>(Arrays.asList(
            "/html/sumo", "/html/linefollower", "/html/folkrace", "/html/labyrinth", "/html/matchtables", "/html/aboutCompetitions", "/html/results",
            "/pictures",
            "/photos",
            "/temp"
    ));

    public ApplicationService() {
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

    public String getSAVE_PATH() {
        return SAVE_PATH;
    }

    public List<Tournament> getAllTournaments() {
        return tournamentService.getAll();
    }
    public Tournament getActiveTournament() {
        return app.tournament;
    }
}
